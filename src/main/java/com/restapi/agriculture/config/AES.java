package com.restapi.agriculture.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
@Component
public class AES {

    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String sharedKey = "Sh4redK3y";

    private void setKey(String myKey) {
        MessageDigest sha =  null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }

        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt) {
        try {
            setKey(sharedKey);
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(c.doFinal(strToEncrypt.getBytes("UTF-8"))).replaceAll("/","_");
        }
        catch (Exception e) {
            log.info("Error while encrypting: " + e.toString());
        }

        return null;

    }

    public String decrypt(String strToDecrypt) {
        try {
            strToDecrypt = strToDecrypt.replaceAll("_", "/");
            setKey(sharedKey);
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            c.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(c.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }

        catch (Exception e) {
            log.info("Error while decrypting: " + e.toString());
        }

        return null;
    }
}
