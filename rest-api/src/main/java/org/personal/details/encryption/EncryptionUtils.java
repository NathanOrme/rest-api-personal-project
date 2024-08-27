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

/**
 * Utility class for encrypting and decrypting strings using the AES algorithm.
 * This class handles encryption and decryption operations, including key management.
 */
@Component
public class EncryptionUtils {

    /**
     * Constant representing the AES encryption algorithm.
     */
    public static final String AES = "AES";

    /**
     * Secret key used for encryption and decryption, injected from application properties.
     */
    @Value("${encryption.key}")
    String secretKey;

    /**
     * Encrypts a given string using AES encryption and returns the encrypted value as a Base64-encoded string.
     *
     * @param value The string to be encrypted.
     * @return The encrypted string, encoded in Base64, or null if the input value is null.
     * @throws EncryptionException If any error occurs during encryption.
     */
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

    /**
     * Decrypts a given Base64-encoded string using AES decryption and returns the original value.
     *
     * @param value The Base64-encoded string to be decrypted.
     * @return The decrypted string, or null if the input value is null.
     * @throws EncryptionException If any error occurs during decryption.
     */
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

    /**
     * Creates and initializes a {@link Cipher} instance for encryption or decryption.
     *
     * @param cipherMode The mode of the cipher (either Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE).
     * @return A configured {@link Cipher} instance.
     * @throws NoSuchAlgorithmException If the AES algorithm is not available.
     * @throws NoSuchPaddingException   If the padding scheme is not available.
     * @throws InvalidKeyException      If the secret key is invalid.
     */
    private Cipher getCipher(final int cipherMode)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(cipherMode, getKey());
        return cipher;
    }

    /**
     * Retrieves the secret key used for encryption and decryption, decoded from Base64.
     *
     * @return A {@link SecretKey} for AES encryption.
     */
    private SecretKey getKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

}