package com.hamitmizrak.blockchain;


// ============================================
// 🔧 _05_Wallet.java (Açıklamalı)
// ============================================

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

// LOMBOK
@Getter
@Setter

/**
 * Wallet sınıfı, kullanıcı cüzdanlarını ve bakiyeleri yönetmek için kullanılır.
 * Her kullanıcıya başlangıç bakiyesi atanabilir ve transfer işlemleri yapılabilir.
 */
public class _05_Wallet {

    // Kullanıcı adını (veya ID'sini) bakiye ile eşleyen map
    private static Map<String, Double> balances = new HashMap<>();


    /**
     * Yeni bir kullanıcı cüzdanı oluşturur.
     * Eğer kullanıcı zaten varsa, bakiye değiştirilmez.
     *
     * @param user Kullanıcı adı
     */
    public static void createWallet(String user) {
        // Yeni kullanıcıya varsayılan olarak 100 birim bakiyesi olsun
        balances.putIfAbsent(user, 100.0);
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
     * @return Kullanıcı-bakiye eşlemeleri
     */
    public static Map<String, Double> getBalances(){
        return balances;
    }

} // end _05_Wallet
