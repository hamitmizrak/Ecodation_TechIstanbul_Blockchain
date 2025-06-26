// ============================================
// 🔰 PROJE ADI: JavaFX Smart Contract Blockchain Simulator
// ============================================

// ============================================
// 🔧 1. _01_Transaction.java
// ============================================
package com.hamitmizrak.blockchain;

import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;
import java.security.PublicKey;

// LOMBOK
// Lombok anotasyonları ile getter/setter, builder pattern gibi yardımcı yapılar otomatik oluşturulur.
@Getter
@Setter

/**
 * _01_Transaction sınıfı, bir kullanıcıdan başka bir kullanıcıya belirli miktarda varlık (örneğin token veya para) gönderimini temsil eder.
 * Her işlem üç ana bileşen içerir:
 * - Gönderen (from): İşlemi başlatan kişinin kimliği veya cüzdan adresi
 * - Alıcı (to): Transferi alan kişi ya da cüzdan adresi
 * - Tutar (amount): Gönderilen miktar
 * Bu sınıf, blockchain üzerindeki işlemleri temsil ederken aynı zamanda veri takibini de kolaylaştırır.
 */

// ✅
// ℹ️
// ❌
public class _01_Transaction {

    // Gönderen kişinin adı ya da cüzdan kimliği
    public String from;

    // Alıcı kişinin adı ya da cüzdan kimliği
    public String to;

    // Transfer edilen miktar (örneğin: 150.0 Token)
    public double amount;

    // PublicKey - PrivateKey
    private byte[] signature;

    /**
     * Parametreli kurucu metot.
     * Bu metod ile bir transfer işlemi oluşturulurken ilgili alanlar doğrudan tanımlanır.
     *
     * @param from   Gönderen kişi ya da cüzdan ID'si
     * @param to     Alıcı kişi ya da cüzdan ID'si
     * @param amount Gönderilecek miktar
     */
    public _01_Transaction(String from, String to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    /**
     * toString() metodu, nesnenin okunabilir biçimde yazdırılması için özelleştirilmiştir.
     * Örneğin: _01_Transaction{from='Ali', to='Veli', amount=100.0}
     */
    @Override
    public String toString() {
        return "_01_Transaction{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }

    // METHOD
    // PRIVATE-KEY
    public void signTransaction(PrivateKey privateKey) {
        String data = "gönderici: " + from + " alıcı: " + to + "Miktar" + amount;
        this.signature = _02_Utils.sign(data, privateKey);
    } // end signTransaction (Private)

    // PUBLIC-KEY
    public boolean isSignatureValid(PublicKey publicKey) {
        String data = "gönderici: " + from + " alıcı: " + to + "Miktar" + amount;
        return _02_Utils.verify(data, signature, publicKey);
    } // end isSignatureValid (Public)

} // end _01_Transaction