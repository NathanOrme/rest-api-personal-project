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

/**
 * Service for sending personal details to a remote REST API endpoint.
 * This service handles HTTP requests to save personal details and manages
 * responses and errors associated with the request process.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService {

    /**
     * Endpoint URL for saving personal details.
     */
    private static final String SAVE_DETAILS_ENDPOINT = "/personal-details/save";

    /**
     * The {@link RestClient} used for making HTTP requests.
     */
    private final RestClient restClient;

    /**
     * Checks if the given HTTP status code indicates a successful creation (HTTP 201 Created).
     *
     * @param statusCode The HTTP status code to check.
     * @return {@code true} if the status code indicates successful creation, otherwise {@code false}.
     */
    protected boolean isCreatedStatus(final HttpStatusCode statusCode) {
        return HttpStatus.CREATED == statusCode;
    }

    /**
     * Checks if the given HTTP status code indicates an unprocessable entity (HTTP 422 Unprocessable Entity).
     *
     * @param statusCode The HTTP status code to check.
     * @return {@code true} if the status code indicates an unprocessable entity, otherwise {@code false}.
     */
    protected boolean isUnprocessableEntityStatus(final HttpStatusCode statusCode) {
        return HttpStatus.UNPROCESSABLE_ENTITY == statusCode;
    }

    /**
     * Sends personal details to the configured REST API endpoint.
     *
     * @param personalDetail The {@link PersonalDetails} object to be sent.
     * @return {@code true} if the request was successful, {@code false} otherwise.
     * @throws RestAPIDownException    If the REST API endpoint is down.
     * @throws RequestServiceException If an unexpected error occurs during the request.
     */
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

    /**
     * Logs a message indicating that the record has been successfully saved to the database.
     *
     * @param personalDetail The {@link PersonalDetails} object that was saved.
     * @param response       The HTTP response received from the server.
     */
    private void logCreatedMessage(final PersonalDetails personalDetail, final ClientHttpResponse response) {
        log.info("The record for customer ref {} has successfully been saved to the database",
                personalDetail.getCustomerRef());
    }
}