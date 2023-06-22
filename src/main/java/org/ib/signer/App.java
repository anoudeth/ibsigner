package org.ib.signer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.SQLOutput;
import java.util.HashMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SignatureManager signatureManager = new SignatureManager();
        String signature;

        Gson gson = new Gson();

        if (args.length == 3) {
            String privateKey = args[1];
            String data = args[2];
            switch (args[0]) {
                case "SIGN" -> {
                    JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
                    signature = signatureManager.genSignatureWithPrivate(jsonObject.toString(), privateKey);
                    System.out.println(signature);
                }
                case "VERIFY" -> System.out.println("VERIFY");
                default -> System.out.println("default");
            }


        } else {
            System.out.println("invalid args");

//            System.out.println("===> sign");
//            String aaa = "{\n" +
//                    "    \"merchantId\": \"KKL\",\n" +
//                    "    \"mobileNo\":       \"2055555555x\"\n" +
//                    "}";
//            JsonObject jsonObject = new Gson().fromJson(aaa, JsonObject.class);
//            System.out.println("jsonObject: " + jsonObject.toString());
//            signature = signatureManager.genSignatureWithPrivate(jsonObject.toString(), "private.key");
//
//            System.out.println("===> verify");
//
//            HashMap<String, String> body = new HashMap<>();
//
//            // Add keys and values to the HashMap
//            body.put("merchantId", "KKL");
//            body.put("mobileNo", "2055555555x");
//            System.out.println("body: " + body.toString());
//
//            System.out.println("signature: " + signature);
//            JsonObject jsonObject2 = new Gson().fromJson(body.toString(), JsonObject.class);
//            System.out.println("jsonObject2: " + jsonObject2.toString());
//
//            boolean isVerifyOk = signatureManager.verifySignaturePub(jsonObject2.toString(), signature, "public.key");
//            System.out.println("isVerifyOk: " + isVerifyOk);

        }



    }
}
