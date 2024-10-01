package org.example;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

public class Decryptor {

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            System.out.println("Usage: java -jar my-decryptor.jar <jks-file-path> <alias> <keystore-password> <input-file> <output-file>");
            return;
        }

        String jksFilePath = args[0];
        String alias = args[1];
        String keystorePassword = args[2];
        String inputFilePath = args[3];
        String outputFilePath = args[4];

        // Load the keystore and retrieve the private key
        KeyStore keyStore = KeyStore.getInstance("JKS");
        FileInputStream fis = new FileInputStream(jksFilePath);
        keyStore.load(fis, keystorePassword.toCharArray());

        Key privateKey = keyStore.getKey(alias, keystorePassword.toCharArray());

        // Initialize cipher for decryption
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPwithSHA1andMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // Process the input file and write the decrypted output to the output file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String encryptedSecret;
            while ((encryptedSecret = reader.readLine()) != null) {
                try {
                    // Decode and decrypt the secret
                    byte[] decodedBytes = Base64.getDecoder().decode(encryptedSecret);
                    byte[] decryptedBytes = cipher.doFinal(decodedBytes);

                    String decryptedText = new String(decryptedBytes);

                    // Write the result to the output file
                    writer.write(encryptedSecret + " -> " + decryptedText);
                    writer.newLine();
                } catch (Exception e) {
                    System.err.println("Failed to decrypt: " + encryptedSecret + ". Error: " + e.getMessage());
                }
            }
        }

        System.out.println("Decryption complete. Output written to: " + outputFilePath);
    }
}
