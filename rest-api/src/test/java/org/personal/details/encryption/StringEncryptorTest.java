package org.personal.details.encryption;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This class uses spring boot test as want to ensure the encryption works properly
 */
@SpringBootTest
class StringEncryptorTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    void convertToDatabaseColumn_WithString_EncryptsString() {
        String testString = "I am a test";
        String result = stringEncryptor.convertToDatabaseColumn(testString);
        assertNotEquals(testString, result);
    }

    @Test
    void convertToEntityAttribute_WithString_EncryptsString() {
        String testString = "I am a test";
        String result = stringEncryptor.convertToDatabaseColumn(testString);
        assertNotEquals(testString, result);
        String backToNormal = stringEncryptor.convertToEntityAttribute(result);
        assertEquals(testString, backToNormal);
    }

}
