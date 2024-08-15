package org.personal.details;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@ExtendWith(SpringExtension.class)
class PersonalDetailsApplicationTest {

    @Test
    void main_WithDefaultValues_ApplicationLaunches() {
        assertDoesNotThrow(() -> PersonalDetailsApplication.main(new String[0]));
    }

}
