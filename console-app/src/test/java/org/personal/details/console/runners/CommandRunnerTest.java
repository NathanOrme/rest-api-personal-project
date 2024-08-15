package org.personal.details.console.runners;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.personal.details.console.model.PersonalDetails;
import org.personal.details.console.service.CsvReaderService;
import org.personal.details.console.service.RequestService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandRunnerTest {

    @Mock
    CsvReaderService csvReaderService;
    @Mock
    RequestService requestService;
    @InjectMocks
    CommandRunner commandRunner;

    @Test
    void run_WithDefaultBehaviour_DoesNotThrowExceptions() throws Exception {
        when(requestService.sendDetailsToEndpoint(any())).thenReturn(true);
        when(csvReaderService.readContentsOfCSV()).thenReturn(List.of(PersonalDetails.builder().build()));

        assertDoesNotThrow(() -> commandRunner.run());

    }

}
