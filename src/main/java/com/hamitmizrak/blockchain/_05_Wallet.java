package com.hamitmizrak.blockchain;


// ============================================
// 🔧 _05_Wallet.java (Açıklamalı)
// ============================================

import lombok.Getter;
import lombok.Setter;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

// LOMBOK
@Getter
@Setter

/**
 * Wallet sınıfı, kullanıcı cüzdanlarını ve bakiyeleri yönetmek için kullanılır.
 * Her kullanıcıya başlangıç bakiyesi atanabilir ve transfer işlemleri yapılabilir.
 */

// ✅
// ℹ️
// ❌
public class _05_Wallet {

    // Kullanıcı adını (veya ID'sini) bakiye ile eşleyen map
    private static Map<String, Double> balances = new HashMap<>();

    // Wallet Map
    private static Map<String, _05_Wallet> walletMap = new HashMap<>();

    // İstatiksel analiz yapılması için, her cüzdanın yaptığı işlem sayısını  takip ederek bir transactionCounts yaparak
    // Kim kaç işlem yaptığını görebiliriz
    private static Map<String, Integer> transactionCounts = new HashMap<>();

    // Public-Private
    private KeyPair keyPair;

    // Constructor(Parametresiz)
    public _05_Wallet() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            this.keyPair= keyGen.generateKeyPair();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Yeni bir kullanıcı cüzdanı oluşturur.
     * Eğer kullanıcı zaten varsa, bakiye değiştirilmez.
     *
     * @param name Kullanıcı adı
     */
    public static void createWallet(String name) {
        // Yeni kullanıcıya varsayılan olarak 100 birim bakiyesi olsun
        // putIfAbsent: Eğer haritada (Map) belirtilen anahtar (key) yoksa, verilen değeri (value) ekler.
        // Varsa hiçbir şey yapmaz, eski değeri korur.
        balances.putIfAbsent(name, 100.0);

        //
        _05_Wallet wallet= new _05_Wallet();
        walletMap.put(name,wallet);
        // putIfAbsent: Eğer haritada (Map) belirtilen anahtar (key) yoksa, verilen değeri (value) ekler.
        // Varsa hiçbir şey yapmaz, eski değeri korur.
        transactionCounts.putIfAbsent(name,0);
    }

    // Belirtilen isme sahip wallet'ları getir
    public static _05_Wallet getWalletByName(String name){
        return walletMap.get(name);
    }

    /**
     * İki kullanıcı arasında transfer gerçekleştirir.
     * Gönderenin yeterli bakiyesi varsa işlem yapılır.
     *
     * @param from   Gönderen kullanıcı
     * @param to     Alıcı kullanıcı
     * @param amount Transfer miktarı
     * @return İşlem başarılıysa true, aksi halde false
     */
    public static boolean transfer(String from, String to, double amount) {
        if (balances.getOrDefault(from, 0.0) >= amount) {
            balances.put(from, balances.get(from) - amount);
            balances.put(to, balances.getOrDefault(to, 0.0) + amount);
            return true;
        }
        return false;
    }


    /**
     * Tüm kullanıcıların güncel bakiyelerini döner.
     *
     * @return Kullanıcı-bakiye eşlemeleri
     */
    public static Map<String, Double> getBalances() {
        return balances;
    }

    // Kullanıcıların biribirine transfer yaptığında ilgili kullanıcı işlem sayısı +1 artırsın
    public static void incrementTransactionCount(String name){
        transactionCounts.put(name,transactionCounts.getOrDefault(name,0)+1);
    }

    // Eğer kullanıcını parası Sıfr olursa, yeni miktar ekle
    public static void addBalance(String walletName, double amount) {
        double current = balances.getOrDefault(walletName,0.0);
        balances.put(walletName,current+amount);
    }

    // GETTER AND SETTER
    public static Map<String, Integer> getTransactionCounts() {
        return transactionCounts;
    }

    // Private-Key
    public PrivateKey getPrivateKey(){
        return keyPair.getPrivate();
    }

    // Private-Key
    public PublicKey getPublicKey(){
        return keyPair.getPublic();
    }

    // Encoder getPublicKeyString
    public String getPublicKeyString(){
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    // Encoder getPrivateKeyString
    public String getPrivateKeyString(){
        return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }

} // end _05_Wallet
