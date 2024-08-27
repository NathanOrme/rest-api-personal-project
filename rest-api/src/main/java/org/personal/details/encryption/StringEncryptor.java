package org.personal.details.encryption;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * JPA attribute converter that handles encryption and decryption of string attributes.
 * This converter is used to automatically encrypt data before storing it in the database
 * and decrypt it when reading from the database.
 */
@Component
@Converter
@RequiredArgsConstructor
public class StringEncryptor implements AttributeConverter<String, String> {

    /**
     * Utility class for performing encryption and decryption operations.
     */
    private final EncryptionUtils encryptionUtils;

    /**
     * Converts the given string attribute to its encrypted form before storing it in the database.
     *
     * @param attribute The string attribute to be encrypted.
     * @return The encrypted string to be stored in the database.
     */
    @Override
    public String convertToDatabaseColumn(final String attribute) {
        return encryptionUtils.encrypt(attribute);
    }

    /**
     * Converts the given encrypted string from the database back to its original form.
     *
     * @param dbData The encrypted string from the database.
     * @return The decrypted string in its original form.
     */
    @Override
    public String convertToEntityAttribute(final String dbData) {
        return encryptionUtils.decrypt(dbData);
    }
}