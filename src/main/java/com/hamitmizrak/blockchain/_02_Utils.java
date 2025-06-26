// ============================================
// 🔧 2. _02_Utils.java
// ============================================
package com.hamitmizrak.blockchain;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.*;

// LOMBOK
@Getter
@Setter

/**
 * _02_Utils sınıfı, çeşitli yardımcı metodları içeren bir utility (araç) sınıfıdır.
 * Bu sınıfta yer alan applySHA256 metodu, verilen bir girdinin SHA-256 algoritması kullanılarak kriptografik özetinin alınmasını sağlar.
 * Bu yöntem, verinin bütünlüğünü kontrol etmek ve blockchain güvenliğini sağlamak için kullanılır.
 */

// ✅
// ℹ️
// ❌
public class _02_Utils {

    // SHA-256 algoritma adı sabiti
    private final static String ALGORITHM_256 = "SHA-256";

    // PublicKey - PrivateKey
    private final static String SHA256_WITH_RSA = "SHA256withRSA";

    // Karakter kodlaması sabiti (UTF-8 Türkçe karakter desteği)
    private final static String UTF8 = "UTF-8";

    /**
     * SHA-256 algoritması ile verilen string girdinin hash (özet) değerini hesaplar.
     * Bu hash değeri, genellikle blok verilerinin bütünlüğünü sağlamak amacıyla kullanılır.
     *
     * @param input Hash değeri alınacak giriş verisi
     * @return SHA-256 ile şifrelenmiş hexadecimal formatında string
     */
    public static String applySHA256(String input) {
        try {
            // SHA-256 için MessageDigest nesnesi oluşturulur
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_256);

            // Giriş verisi byte dizisine dönüştürülür (UTF-8 karakter seti kullanılarak)
            byte[] hash = digest.digest(input.getBytes(UTF8));

            // Hexadecimal formatta string'e çevrilmek üzere StringBuilder kullanılır
            StringBuilder hexSting = new StringBuilder();

            // Her byte değeri hexadecimal'e çevrilir
            for (byte temp : hash) {
                String hex = Integer.toHexString(0xff & temp);
                // Tek karakterli sonuçların başına 0 eklenir (örneğin 'a' yerine '0a')
                if (hex.length() == 1) {
                    hexSting.append('0');
                }
                hexSting.append(hex);
            }

            // Oluşan tüm hexadecimal karakterler birleştirilerek geri döndürülür
            return hexSting.toString();
        } catch (Exception exception) {
            // Herhangi bir hata oluşursa runtime exception fırlatılır
            throw new RuntimeException(exception);
        }
    } // end applySHA256

    // Private Key (sign)
    public static byte[] sign (String data, PrivateKey privateKey)  {
        try{
            Signature privateSignature = Signature.getInstance(SHA256_WITH_RSA);
            privateSignature.initSign(privateKey);
            privateSignature.update(data.getBytes(StandardCharsets.UTF_8)); // üğşçöI
            return privateSignature.sign();
        }catch (Exception exception){ // throws NoSuchAlgorithmException
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    } // end sign

    // Public Key (Verify)
    public static boolean verify(String data, byte [] signature, PublicKey publicKey){
        try{
            Signature publicSignature = Signature.getInstance(SHA256_WITH_RSA);
            publicSignature.initVerify(publicKey);
            publicSignature.update(data.getBytes(StandardCharsets.UTF_8)); // üğşçöI
            return publicSignature.verify(signature);
        }catch (Exception exception){ // throws NoSuchAlgorithmException
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    } // end verify


} // end _02_Utils
