package org.ib.signer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {

        if (args.length == 1) {
            SignatureManager signatureManager = new SignatureManager();
            JsonObject jsonObject = new Gson().fromJson(args[0], JsonObject.class);
            String signature = signatureManager.genSignatureWithPrivate(jsonObject.toString(), "private1.key");
            System.out.println(signature);

        } else {
            System.out.println("invalid args");
        }
    }
}
