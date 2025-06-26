package com.hamitmizrak.blockchain;

// ====================================================
// 🔧 _06_SmartContract.java (Açıklamalı)
// ====================================================
// ℹ️
// ❌
// ✅

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// LOMBOK
@Getter
@Setter

/**
  SmartContract sınıfı, blok zinciri üzerinde işlemleri yöneten akıllı sözleşme simülasyonudur.
  İşlem havuzu tutar, işlemleri bloklara dönüştürür ve zincire ekler.
 */
public class _06_SmartContract {

    // FIELD
    // Bağlı olduğu blockchain
    private _04_Blockchain blockchain;

    // Henüz blog haline gelmemiş işlemler
    private List<_01_Transaction> pendingTransactions = new ArrayList<>();

    // Her blokta maksimum İşlem sayısı
    private final int BLOCK_SIZE = 3;

    /**
     * Kurucu metod: Bağlı blockchain örneği alır.
     * @param blockchain Kullanılacak blockchain
     */
    public _06_SmartContract(_04_Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    /**
     * Transfer işlemi gerçekleştirmeye çalışır.
     * Başarılıysa işlem havuza alınır, blok boyutuna ulaşıldığında yeni blok oluşur.
     *
     * @param from   Gönderen
     * @param to     Alıcı
     * @param amount Miktar
     * @return İşlem sonucu mesajı
     */
    public String executeTransfer(String from, String to, double amout) {
        if (_05_Wallet.transfer(from, to, amout)) {
            _01_Transaction transactionData = new _01_Transaction(from, to, amout);
            pendingTransactions.add(transactionData);

            // Havuz dolduysa block oluştur
            // public _03_Block(int index, List<_01_Transaction> transactions, String previousHash)
            if (pendingTransactions.size() >= BLOCK_SIZE) {
                _03_Block block = new _03_Block(
                        blockchain.getChain().size(),
                        new ArrayList<>(pendingTransactions),
                        blockchain.getLatestBlock().getHash() // getPreviousHash()
                );
                blockchain.addBlock(block);
                pendingTransactions.clear();
                return "\n✅ Block oluşturuldu ve işlem zinciri eklendi";
            }
            return "ℹ️ İşlem havuza eklendi. Yeni bir blok için kalan işlem ==> " + (BLOCK_SIZE - pendingTransactions.size());
        } else {
            return "❌ Bakiye yetersiz";
        }
    } // end executeTransfer
} // end _06_SmartContract