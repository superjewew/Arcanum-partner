package com.mahavira.partner.base.entity;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.util.encoders.Base64;

/**
 * Created by norman on 05/09/18.
 *
 */

public class EncryptedString {

    private String message;

    public EncryptedString(String message) {
        this.message = message;
    }

    public String getValue() {
        return message;
    }

    private SecretKey generateKey()
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return new SecretKeySpec("test".getBytes(), "AES");
    }

    public EncryptedString encryptMsg()
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException, InvalidKeySpecException {
        Cipher cipher;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey());
        byte[] cipherText = cipher.doFinal(message.getBytes("UTF-8"));
        message = Base64.toBase64String(cipherText);

        return this;
    }

    public EncryptedString decryptMsg()
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, generateKey());
        byte[] decryptedByte = cipher.doFinal(message.getBytes("UTF-8"));
        message = Base64.toBase64String(decryptedByte);
        return this;
    }

}
