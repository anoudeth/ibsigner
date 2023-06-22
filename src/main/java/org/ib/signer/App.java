package org.ib.signer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

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
                    System.out.println("jsonObject: " + jsonObject.toString());
                    signature = signatureManager.genSignatureWithPrivate(jsonObject.toString(), privateKey);
                    System.out.println(signature);
                }
                case "VERIFY" -> System.out.println("VERIFY");
                default -> System.out.println("default");
            }

        } else {
            System.out.println("invalid args");

            System.out.println("===> sign");
            String aaa = "{\"merchantId\":    \"KKL\",\"mobileNo\"    :\"2055555555\"   }";
//            String aaa = "{\n" +
//                    "    \"merchantId\": \"KKL\",\n" +
//                    "    \"mobileNo\":       \"2055555555x\"\n" +
//                    "}";
            System.out.println("aaa: " + aaa);
            JsonObject jsonObject = new Gson().fromJson(aaa, JsonObject.class);
            System.out.println("jsonObject: " + jsonObject.toString());
            signature = signatureManager.genSignatureWithPrivate(jsonObject.toString(), "private.key");

            System.out.println("===> verify");

//            HashMap<String, String> body = new HashMap<>();
            // Add keys and values to the HashMap
//            body.put("merchantId", "KKL");
//            body.put("mobileNo", "2055555555x");
//            System.out.println("body: " + body.toString());
            ReqParam reqParam = new ReqParam();
            reqParam.setMerchantId("KKL");
            reqParam.setMobileNo("2055555555");
            System.out.println("reqParam: " + reqParam.toString());

            System.out.println("signature: " + signature);
//            JsonObject jsonObject2 = new Gson().fromJson(reqParam.toString(), JsonObject.class);
//            System.out.println("jsonObject2: " + jsonObject2.toString());

            boolean isVerifyOk = signatureManager.verifySignaturePub(reqParam.toString(), signature, "public.key");
            System.out.println("isVerifyOk: " + isVerifyOk);
        }
    }
}
