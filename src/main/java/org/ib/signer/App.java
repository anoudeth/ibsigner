package org.ib.signer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        SignatureManager signatureManager = new SignatureManager();
        String signature;
        Gson gson = new Gson();

        if (args.length == 1) {

            JsonObject jsonObject = gson.fromJson(args[0], JsonObject.class);
            signature = signatureManager.genSignatureWithPrivate(jsonObject.toString(), "private.key");
            System.out.println(signature);

//            switch (args[0]) {
//                case "SIGN" -> {
//                    JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
//                    System.out.println("jsonObject: " + jsonObject.toString());
//                    signature = signatureManager.genSignatureWithPrivate(jsonObject.toString(), privateKey);
//                    System.out.println(signature);
//                }
//                case "VERIFY" -> System.out.println("VERIFY");
//                default -> System.out.println("invalid args");
//            }

        } else {
            System.out.println("invalid args");
        }
    }
}
