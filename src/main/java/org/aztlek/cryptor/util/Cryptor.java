package org.aztlek.cryptor.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class Cryptor {
    private static final int AES_KEY_SIZE = 256 ;
    private static final int IV_SIZE = 96 ;
    private static final int TAG_BIT_LENGTH = 128 ;
    private static final String ALGO_TRANSFORMATION_STRING = "AES/GCM/NOPADDING" ;

    private SecretKey aesKey;
    private GCMParameterSpec gcmParamSpec;
    private byte[] aadData;
    private byte[] iv;
    private SecureRandom secRandom;

    public Cryptor() throws NoSuchAlgorithmException {

        aadData = "random".getBytes() ; // Any random data can be used as tag. Some common examples could be domain name...

        // Use different key+IV pair for encrypting/decrypting different parameters

        // Generating Key
        aesKey = null ;
        KeyGenerator keygen = KeyGenerator.getInstance("AES") ; // Specifying algorithm key will be used for
        keygen.init(AES_KEY_SIZE) ; // Specifying Key size to be used, Note: This would need JCE Unlimited Strength to be installed explicitly
        aesKey = keygen.generateKey() ;

        // Generating IV
        iv = new byte[IV_SIZE];
        secRandom = new SecureRandom() ;
        secRandom.nextBytes(iv); // SecureRandom initialized using self-seeding


        // Initialize GCM Parameters
        gcmParamSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv) ;

    }

    public void nextBytes() {
        // Make sure not to repeat Key + IV pair, for encrypting more than one plaintext.
        secRandom.nextBytes(iv);
    }

    public String aesEncrypt(String message) throws GeneralSecurityException {
        Cipher c = null ;
        c = Cipher.getInstance(ALGO_TRANSFORMATION_STRING); // Transformation specifies algortihm, mode of operation and padding
        c.init(Cipher.ENCRYPT_MODE, aesKey, gcmParamSpec, new SecureRandom()) ;
        c.updateAAD(aadData);
        byte[] cipherTextInByteArr = null ;
        cipherTextInByteArr = c.doFinal(message.getBytes()) ;
        return Base64.getEncoder().encodeToString(cipherTextInByteArr);
    }


    public String aesDecrypt(String encryptedTextEncode) throws GeneralSecurityException {
        byte[] encryptedMessage = Base64.getDecoder().decode(encryptedTextEncode);
        Cipher c = null ;
        c = Cipher.getInstance(ALGO_TRANSFORMATION_STRING); // Transformation specifies algortihm, mode of operation and padding
        c.init(Cipher.DECRYPT_MODE, aesKey, gcmParamSpec, new SecureRandom()) ;
        c.updateAAD(aadData) ; // Add AAD details before decrypting
        byte[] plainTextInByteArr = null ;
        plainTextInByteArr = c.doFinal(encryptedMessage);
        return new String(plainTextInByteArr);
    }
}

