package com.hamitmizrak.blockchain;

import java.time.LocalDateTime;
import java.util.List;

/**
 Bu sınıf bir blok temsilini sağlayacağız.

 Her blok;
 - Zincirdeki konumu (index)
 - Oluşturma zamanı
 - İşlemler manipülasyonu (transactions)
 - Önceki bloğun hash(previous hashing)
 - Kendisinin hash
  */

public class _03_Block {

    // FIELD

    // Zincirdeki sıra sayısını tutar. Genesis: Block 0 olur, bundan sonraki yapılarda 1,2,3..
    private int index;

    // Block'un oluşturulma zamanı (tarih -saat)
    private String timestamp;

    // Blok içindeki yer alan işlemlerin (transfer, veri vs) listelendi
    private List<_01_Transaction> transactions;

    // Bu bloktan önce gelen bloğun hash değeri
    // Önceki bloğun SHA-256 hash değerini tutacaktır.
    private String previousHash;

    // Bu bloğun kendi hash değeri olarak karşımıza geliyor(Blog kimliği)
    private String hash;

    // CONSTRUCTOR

    public _03_Block(int index, List<_01_Transaction> transactions, String previousHash) {
        this.index = index;
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timestamp= LocalDateTime.now().toString();
        this.hash = calculateHash(); // Hash hesaplansın ve atansın
    }


    // METHOD

    // calculateHash
    private String calculateHash() {
        //
        StringBuilder txData= new StringBuilder();
        for(_01_Transaction temp : transactions){
            txData.append(temp.toString()); // Her transaction ekle
        }

        // Hash girdisi : index + zaman + işlem verisi+ önceki hash
        String input = index +timestamp +txData + previousHash;

        // 1.YOL (MANUEL)
        return _02_Utils.applySHA256(input);

        // 2.YOL (LIBRARIES )
        // SHA-256 gibi hash fonksiyonları için Apache Commons Codec
        // String hash = DigestUtils.sha256Hex(input);
        // return hash;   // Yardımcı sınıf ile hash hesaplanan
    }

    // Blok hash içeriğinde verilerin tutarlı olup olmadığını doğrulasın.
    public boolean isValid(){
        return hash.equals(calculateHash());
    }

    // GETTER/SETTER
    // Bloktaki işlemleri dönder
    public List<_01_Transaction> getTransactions() {
        return transactions;
    }
} // end _03_Block
