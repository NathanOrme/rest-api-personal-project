package org.personal.details.encryption;

import org.personal.details.exceptions.EncryptionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class EncryptionUtils {

    public static final String AES = "AES";
    @Value("${encryption.key}")
    String secretKey;

    public String encrypt(final String value) {
        if (value == null) {
            return null;
        }
        try {
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
            byte[] encryptedValue = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (final Exception e) {
            throw new EncryptionException("Error while encrypting", e);
        }
    }

    public String decrypt(final String value) {
        if (value == null) {
            return null;
        }
        try {
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
            byte[] decryptedValue = cipher.doFinal(Base64.getDecoder().decode(value));
            return new String(decryptedValue, StandardCharsets.UTF_8);
        } catch (final Exception e) {
            throw new EncryptionException("Error while decrypting", e);
        }
    }

    private Cipher getCipher(final int cipherMode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(cipherMode, getKey());
        return cipher;
    }

    private SecretKey getKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

}
