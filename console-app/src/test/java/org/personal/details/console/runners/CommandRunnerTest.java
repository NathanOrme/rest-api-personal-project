package org.personal.details.console.runners;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.personal.details.console.domain.PersonalDetailsDTO;
import org.personal.details.console.service.DefaultCsvReaderService;
import org.personal.details.console.service.DefaultRequestService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandRunnerTest {

    @Mock
    DefaultCsvReaderService csvReaderService;
    @Mock
    DefaultRequestService requestService;
    @InjectMocks
    CommandRunner commandRunner;

    @Test
    void run_WithDefaultBehaviour_DoesNotThrowExceptions() throws Exception {
        when(requestService.sendDetailsToEndpoint(any())).thenReturn(true);
        when(csvReaderService.readContentsOfCSV()).thenReturn(List.of(new PersonalDetailsDTO(null, null, null, null, null, null, null, null)));

        assertDoesNotThrow(() -> commandRunner.run());

    }

}
