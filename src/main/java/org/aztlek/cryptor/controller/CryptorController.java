package org.aztlek.cryptor.controller;

import org.aztlek.cryptor.dto.Request;
import org.aztlek.cryptor.util.Cryptor;
import org.springframework.http.MediaType;
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
    public ResponseEntity<String> aesEncrypt(@RequestBody Request request) {
        System.out.println("aesEncrypt: request.getMessage() = '" + request.getMessage() + "´");
        try {
            Cryptor cryptor = new Cryptor();
            String encryptedTextEncode = cryptor.aesEncrypt(request.getMessage());
            System.out.println("aesEncrypt: encryptedTextEncode = '" + encryptedTextEncode + "´");
            return ResponseEntity.ok(encryptedTextEncode);
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
           return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> aesDecrypt(@RequestBody Request request) {
        System.out.println("aesDecrypt: request.getMessage() = '" + request.getMessage() + "´");
        try {
            Cryptor cryptor = new Cryptor();
            String decryptedText = cryptor.aesDecrypt(request.getMessage());
            System.out.println("aesDecrypt: decryptedText = '" + decryptedText + "'");
            return ResponseEntity.ok(cryptor.aesDecrypt(decryptedText));
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
