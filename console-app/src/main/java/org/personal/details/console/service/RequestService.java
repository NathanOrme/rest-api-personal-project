package org.personal.details.console.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.personal.details.console.exception.RequestServiceException;
import org.personal.details.console.exception.RestAPIDownException;
import org.personal.details.console.model.PersonalDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService {

    private static final String SAVE_DETAILS_ENDPOINT = "/personal-details/save";

    private final RestClient restClient;

    protected boolean isCreatedStatus(final HttpStatusCode statusCode) {
        return HttpStatus.CREATED == statusCode;
    }

    protected boolean isUnprocessableEntityStatus(final HttpStatusCode statusCode) {
        return HttpStatus.UNPROCESSABLE_ENTITY == statusCode;
    }

    public boolean sendDetailsToEndpoint(final PersonalDetails personalDetail) {
        log.debug("Initializing Request");
        AtomicBoolean isSuccessfulRequest = new AtomicBoolean(true);
        try {
            restClient.post()
                    .uri(SAVE_DETAILS_ENDPOINT)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(personalDetail)
                    .retrieve()
                    .onStatus(this::isUnprocessableEntityStatus, (request, response) -> isSuccessfulRequest.set(false))
                    .onStatus(this::isCreatedStatus, (request, response) -> logCreatedMessage(personalDetail, response))
                    .toEntity(String.class);
        } catch (final ResourceAccessException e) {
            log.error("The REST API Endpoint seems to be down, please contact support", e);
            throw new RestAPIDownException("The REST API Endpoint seems to be down, please contact support");
        } catch (final Exception e) {
            throw new RequestServiceException("An unexpected error occurred sending a request", e);
        }
        return isSuccessfulRequest.get();
    }

    private void logCreatedMessage(final PersonalDetails personalDetail, final ClientHttpResponse response) {
        log.info("The record for customer ref {} has successfully been saved to the database", personalDetail.getCustomerRef());
    }

}
