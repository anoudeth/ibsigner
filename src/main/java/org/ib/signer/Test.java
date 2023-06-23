package org.ib.signer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.security.Signature;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.signAndVerify();
        test.verify();
    }

    private void signAndVerify() {
        SignatureManager signatureManager = new SignatureManager();
        String signature;

        System.out.println("===> sign");
        String jsonStr = "{\"merchantId\":    \"KKL\",\"mobileNo\"    :\"2055555555\"}";
//            String aaa = "{\n" +
//                    "    \"merchantId\": \"KKL\",\n" +
//                    "    \"mobileNo\":       \"2055555555x\"\n" +
//                    "}";
        System.out.println("jsonStr: " + jsonStr);
        JsonObject jsonObject = new Gson().fromJson(jsonStr, JsonObject.class);
        System.out.println("jsonObject: " + jsonObject.toString());
        signature = signatureManager.genSignatureWithPrivate(jsonObject.toString(), "private.key");
        System.out.println("signature: " + signature);

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

    private void verify() {
        System.out.println("===> test verify");
        SignatureManager signatureManager = new SignatureManager();
//        String text = "vong5918";
//        String signature = "91c9d20a4af7a23b8bd39a499c265b56ee0bac39bdfd5370fcf5e5a562d61ba00dbbe02aa29609fc6cef541fcd56018108fcf761e900aa6a58bf6bfa5a1035ad";

        String text = "{\"merchantId\":\"KKL\",\"mobileNo\":\"2055555555\"     }";
        System.out.println("text: " + text);

        JsonObject jsonObj = new Gson().fromJson(text, JsonObject.class);
        System.out.println("jsonObj: " + jsonObj.toString());

        String signature = "b0479cc48ba5f61680cb1ba9b3b9c1142167ea1ba5804de8bce6de0de7f9c21a752e7230f0d04d3a51b58c1c0ac91e303121973807fe5d7b42ced4576e491a41";
        boolean isVerifyOk = signatureManager.verifySignaturePub(jsonObj.toString(), signature, "public.key");

        System.out.println("isVerifyOk: " + isVerifyOk);
    }
}
