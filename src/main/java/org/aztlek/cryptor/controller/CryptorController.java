package org.aztlek.cryptor.controller;

import org.aztlek.cryptor.util.Cryptor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
public class CryptorController {

    @PostMapping("/encrypt")
    public ResponseEntity<String> aesEncrypt(@RequestBody String message) {
        System.out.println("aesEncrypt: message = '" + message + "´");
        try {
            Cryptor cryptor = new Cryptor();
            String encryptedTextEncode = cryptor.aesEncrypt(message);
            System.out.println("aesEncrypt: encryptedTextEncode = '" + encryptedTextEncode + "´");
            return ResponseEntity.ok(encryptedTextEncode);
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
           return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> aesDecrypt(@RequestBody String message) {
        System.out.println("aesDecrypt: message = '" + message + "´");
        try {
            Cryptor cryptor = new Cryptor();
            String decryptedText = cryptor.aesDecrypt(message);
            System.out.println("aesDecrypt: decryptedText = '" + decryptedText + "'");
            return ResponseEntity.ok(cryptor.aesDecrypt(decryptedText));
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
