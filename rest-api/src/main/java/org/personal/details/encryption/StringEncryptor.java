package org.personal.details.encryption;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Converter
@RequiredArgsConstructor
public class StringEncryptor implements AttributeConverter<String, String> {

    private final EncryptionUtils encryptionUtils;

    @Override
    public String convertToDatabaseColumn(final String attribute) {
        return encryptionUtils.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(final String dbData) {
        return encryptionUtils.decrypt(dbData);
    }
}
