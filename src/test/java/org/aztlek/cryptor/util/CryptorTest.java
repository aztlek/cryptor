package org.aztlek.cryptor.util;


import org.junit.Test;

import java.security.GeneralSecurityException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CryptorTest {

    private static final String messageToEncrypt = "cualquier texto más largo" ;

    @Test
    public void aesEncrypt() throws GeneralSecurityException {
        Cryptor cryptor = new Cryptor();
        String encryptedTextEncode = cryptor.aesEncrypt(messageToEncrypt);
        assertNotEquals(messageToEncrypt,encryptedTextEncode);
        String decryptedText = cryptor.aesDecrypt(encryptedTextEncode);
        assertEquals(messageToEncrypt, decryptedText);
    }

    @Test
    public void aesEncrypt2() throws GeneralSecurityException {
        Cryptor cryptor1 = new Cryptor();
        String encryptedTextEncode = cryptor1.aesEncrypt(messageToEncrypt);
        assertNotEquals(messageToEncrypt,encryptedTextEncode);

//        Cryptor cryptor2 = new Cryptor();
//        String decryptedText = cryptor2.aesDecrypt(encryptedTextEncode);
//        assertEquals(messageToEncrypt, decryptedText);

    }
}