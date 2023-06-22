package org.ib.signer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
        }
    }
}
