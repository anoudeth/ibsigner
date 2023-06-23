package org.ib.signer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Base64;

public class RSAKeyGenerator {

    public static void main(String[] args) throws Exception {
        System.out.println("===> merchant key");
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(512, RSAKeyGenParameterSpec.F4);
        generator.initialize(spec);
        KeyPair pair = generator.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        String merPriKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String merpubKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        System.out.printf("Merchant private key: %s\n", merPriKeyString);
        System.out.println("Merchant public key: " + merpubKeyString);

        System.out.println("===> Bank key");
        generator.initialize(spec);
        pair = generator.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();

        String bankPriKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String bankpubKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("Bank private key: " + bankPriKeyString);
        System.out.println("Bank public key: " + bankpubKeyString);


        String fileName = "private.key";
        File file = new File(fileName);
        FileWriter fr = new FileWriter(file);
        BufferedWriter br = new BufferedWriter(fr);
        br.write("-----BEGIN PRIVATE KEY-----\n");
        br.write(merPriKeyString + "\n");
        br.write("-----END PRIVATE KEY-----");
        br.close();
        fr.close();

        fileName = "public.key";
        file = new File(fileName);
        fr = new FileWriter(file);
        br = new BufferedWriter(fr);

        br.write("-----BEGIN PUBLIC KEY-----\n");
        br.write(merpubKeyString + "\n");
        br.write("-----END PUBLIC KEY-----");
        br.close();
        fr.close();


//        try (FileOutputStream fos = new FileOutputStream("pub.key")) {
//            fos.write(publicKey.getEncoded());
//        }


    }
}
