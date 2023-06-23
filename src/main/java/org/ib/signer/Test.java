package org.ib.signer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.security.Signature;

public class Test {
    public static void main(String[] args) {
        SignatureManager signatureManager = new SignatureManager();
        String signature;

        System.out.println("===> sign");
        String jsonStr = "{\"merchantId\":    \"KKL\",\"mobileNo\"    :\"2055555555\"   }";
//            String aaa = "{\n" +
//                    "    \"merchantId\": \"KKL\",\n" +
//                    "    \"mobileNo\":       \"2055555555x\"\n" +
//                    "}";
        System.out.println("jsonStr: " + jsonStr);
        JsonObject jsonObject = new Gson().fromJson(jsonStr, JsonObject.class);
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
        System.out.println("reqParam: " + reqParam);

        System.out.println("signature: " + signature);
        JsonObject jsonObject2 = new Gson().fromJson(reqParam.toString(), JsonObject.class);
        System.out.println("jsonObject2: " + jsonObject2.toString());

        boolean isVerifyOk = signatureManager.verifySignaturePub(jsonObject2.toString(), signature, "public.key");
        System.out.println("isVerifyOk: " + isVerifyOk);
    }
}
