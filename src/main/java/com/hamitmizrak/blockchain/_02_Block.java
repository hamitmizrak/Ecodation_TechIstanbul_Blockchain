package com.hamitmizrak.blockchain;

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

public class _02_Block {

    // FIELD

    // Zincirdeki sıra sayısını tutar. Genesis: Block 0 olur, bundan sonraki yapılarda 1,2,3..
    private int index;

    // Block'un oluşturulma zamanı
    private String timestamp;

    // Blok içindeki yer alan işlemlerin (transfer, veri vs) listelendi
    private List<_01_Transaction> transactions;

    // ;Önceki bloğun SHA-256 hash değerini tutacaktır.
    private String previousHash;

    // Bu bloğun kendi hash değeri olarak karşımıza geliyor.
    private String hash;

    // CONSTRUCTOR

    // METHOD

    // GETTER/SETTER

}
