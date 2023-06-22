package org.ib.signer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignatureManager {

    public SignatureManager() {

    }

    private PrivateKey loadPrivateKeyFromFile(String privateKeyName) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String keyStr = Files.readString((new File("KeyPair" + File.separator + privateKeyName)).toPath());
        keyStr = keyStr.replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "")
                .replace("-----END PRIVATE KEY-----", "");
//        System.out.println("private key String: " + keyStr);
        byte[] keyEncoded = DatatypeConverter.parseBase64Binary(keyStr);

        return keyEncoded == null ? null : KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyEncoded));
//        return this.loadPrivateKey(keyEncoded);
    }
    private PublicKey loadPublicKeyFromFile(String publicKeyName) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String keyStr = new String(Files.readAllBytes((new File("KeyPair" + File.separator + publicKeyName)).toPath()), StandardCharsets.UTF_8);
        keyStr = keyStr.replace("-----BEGIN PUBLIC KEY-----", "").replaceAll("\r", "").replaceAll("\n", "").replace("-----END PUBLIC KEY-----", "");
        byte[] keyEncoded = DatatypeConverter.parseBase64Binary(keyStr);


        return keyEncoded == null ? null : KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyEncoded));

//        return this.loadPublicKey(keyEncoded);
    }
    private String rsaSignPri(String data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update(data.getBytes());
        byte[] signature = signer.sign();
        return DatatypeConverter.printHexBinary(signature);
    }
    public boolean rsaVerifyPub(String data, String signatureHex, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initVerify(publicKey);
        signer.update(data.getBytes());
        return signer.verify(DatatypeConverter.parseHexBinary(signatureHex));
    }

    public String genSignatureWithPrivate(String data, String privateKeyName) {
        String signature = null;
        try {
            PrivateKey privateKey = this.loadPrivateKeyFromFile(privateKeyName);
            signature = this.rsaSignPri(data, privateKey);
        } catch (Exception var5) {

            var5.printStackTrace();
        }
        return signature;
    }
    public boolean verifySignaturePub(String data, String signatureHex, String clientId) {
        boolean status = false;
        try {
            PublicKey publicKey = this.loadPublicKeyFromFile(clientId);
            status = this.rsaVerifyPub(data, signatureHex, publicKey);
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        return status;
    }
}
