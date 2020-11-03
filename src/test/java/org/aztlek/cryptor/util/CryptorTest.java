package org.aztlek.cryptor.util;


import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class CryptorTest {

    private static final String messageToEncrypt = "cualquier texto más largo" ;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void aesEncrypt() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cryptor cryptor = new Cryptor();
        String encryptedTextEncode = cryptor.aesEncrypt(messageToEncrypt);
        assertNotEquals(messageToEncrypt,encryptedTextEncode);
        String decryptedText = cryptor.aesDecrypt(encryptedTextEncode);
        assertEquals(messageToEncrypt, decryptedText);
    }

    @Test
    public void aesEncrypt2() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cryptor cryptor1 = new Cryptor();
        String encryptedTextEncode = cryptor1.aesEncrypt(messageToEncrypt);
        assertNotEquals(messageToEncrypt,encryptedTextEncode);

//        Cryptor cryptor2 = new Cryptor();
//        String decryptedText = cryptor2.aesDecrypt(encryptedTextEncode);
//        assertEquals(messageToEncrypt, decryptedText);

    }
}