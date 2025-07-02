package com.azienda.middleware.middleware_kafka.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import com.azienda.middleware.middleware_kafka.config.AppProperties;
import org.springframework.stereotype.Component;

@Component
public class CryptoUtils {

    private final AppProperties appProperties;

    public CryptoUtils(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String encrypt(String strToEncrypt) {
        try {
            SecretKeySpec key = new SecretKeySpec(appProperties.getAesKey().getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            throw new RuntimeException("Errore durante la crittografia", e);
        }
    }

    public String decrypt(String strToDecrypt) {
        try {
            SecretKeySpec key = new SecretKeySpec(appProperties.getAesKey().getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
            return new String(decrypted);

        } catch (Exception e) {
            throw new RuntimeException("Errore durante la decrittazione", e);
        }
    }
}
