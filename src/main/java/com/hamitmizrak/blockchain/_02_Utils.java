package com.hamitmizrak.blockchain;

import java.security.MessageDigest;

public class _02_Utils {

    // FIELD
    private final static String ALGORITHM_256 = "SHA-256";
    private final static String UTF8 = "UTF-8";

    // Hash (throws NoSuchAlgorithmException )
    public static String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_256);

            // Byte Dizi
            byte[] hash = digest.digest(input.getBytes(UTF8)); // üğşçö

            // Hexadecimal (16) 0123456789abcdef
            StringBuilder hexSting = new StringBuilder();

            // forEach
            for (byte temp : hash) {
                String hex = Integer.toHexString(0xff & temp);
                // Conditional
                if (hex.length() == 1) {
                    hexSting.append('0');
                }
                hexSting.append(hex);
            }
            return hexSting.toString();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    } // end applySHA256
} // end _02_Utils
