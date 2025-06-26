// ============================================
// 🔧 3. _03_Block.java
// ============================================
package com.hamitmizrak.blockchain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// LOMBOK
@Getter
@Setter

/**
 * _03_Block sınıfı, blockchain içerisindeki bir blok birimini temsil eder.
 * Her blok; işlem bilgilerini, zaman damgasını, zincirdeki sırasını ve önceki bloğun hash'ini içerir.
 * Bu bilgilerle birlikte kendi hash'i de üretilir ve zincir yapısında veri güvenliği sağlanmış olur.
 */

// ✅
// ℹ️
// ❌
public class _03_Block {

    // Blok zincirinde kaçıncı sırada olduğunu belirtir. Genesis block genellikle index 0’dır.
    private int index;

    // Blok'un oluşturulduğu zamanı (tarih ve saat) belirtir.
    private String timestamp;

    // Bu blok içerisinde yer alan işlemlerin listesi (transferler vb.).
    private List<_01_Transaction> transactions;

    // Bir önceki bloğun hash değeri. Bu alan zincirin bütünlüğü için kritik öneme sahiptir.
    private String previousHash;

    // Bu bloğun kendi hash değeri. Blok içeriğinden üretilir ve değişmezlik sağlar.
    private String hash;

    /**
     * Blok oluşturulurken, gerekli bilgiler alınır ve oluşturulma zamanı ve hash otomatik atanır.
     * @param index Blok sırası
     * @param transactions İşlemlerin listesi
     * @param previousHash Önceki bloğun hash değeri
     */
    public _03_Block(int index, List<_01_Transaction> transactions, String previousHash) {
        this.index = index;
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timestamp = LocalDateTime.now().toString();
        this.hash = calculateHash();
    }

    /**
     * Bu metod, bloğun tüm içeriğini kullanarak SHA-256 algoritması ile hash hesaplar.
     * @return Bu bloğa ait hash değeri (String)
     */
    public String calculateHash() {
        StringBuilder txData = new StringBuilder();
        for (_01_Transaction temp : transactions) {
            txData.append(temp.toString());
        }
        String input = index + timestamp + txData + previousHash;
        return _02_Utils.applySHA256(input);
    }

    /**
     * Bu metod, bloğun mevcut hash'inin içeriğe uygun olup olmadığını kontrol eder.
     * @return true -> blok geçerlidir; false -> blok bozulmuş veya sahte olabilir.
     */
    public boolean isValid() {
        return hash.equals(calculateHash());
    }

    /**
     * Blokta yer alan işlemleri dışarıya döndürür.
     * @return İşlem listesi
     */
    public List<_01_Transaction> getTransactions() {
        return transactions;
    }

    // toString
    @Override
    public String toString() {
        return "_03_Block {" +
                "index=" + index +
                ", timestamp='" + timestamp + '\'' +
                ", transactions=" + transactions +
                ", previousHash='" + previousHash + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
} // end _03_Block