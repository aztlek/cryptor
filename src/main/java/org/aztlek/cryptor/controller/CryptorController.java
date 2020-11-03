package org.aztlek.cryptor.controller;

import org.aztlek.cryptor.util.Cryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

@Controller
public class CryptorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CryptorController.class);

    @PostMapping("/encrypt")
    public ResponseEntity<String> aesEncrypt(@RequestBody String message) throws GeneralSecurityException {
        LOGGER.info("aesEncrypt: message = '{}'", message);
        Cryptor cryptor = new Cryptor();
        String encryptedTextEncode = cryptor.aesEncrypt(message);
        LOGGER.info("aesEncrypt: encryptedTextEncode = '{}", encryptedTextEncode);
        return ResponseEntity.ok(encryptedTextEncode);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> aesDecrypt(@RequestBody String message) throws GeneralSecurityException {
        LOGGER.info("aesDecrypt: message = '{}", message);
        Cryptor cryptor = new Cryptor();
        String decryptedText = cryptor.aesDecrypt(message);
        LOGGER.info("aesDecrypt: decryptedText = '{}", decryptedText);
        return ResponseEntity.ok(cryptor.aesDecrypt(decryptedText));
    }
}
