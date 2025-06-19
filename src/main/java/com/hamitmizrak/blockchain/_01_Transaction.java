package com.hamitmizrak.blockchain;

import lombok.*;

/***
 * _01_Transaction class, bir kullanıdan başka bir kullanıcya belirli miktarda varlık(token, para vb) gönderilmesini temsil eder.
 * Her işlemde, gönderen ve alıcı, tutar (Bilgi) metadata mevcuttur
 */


// LOMBOK
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Builder

public class _01_Transaction {

    // FIELD
    // Gönderen kişinin adı veya Cüzdan ID'si
    public String from;

    // Alıcı kişinin adı veya cüzdan ID'si
    public String to;

    // Transfer edilen miktar
    public double amount;

    // CONSTRUCTOR (Parametreli Constructor)
    /**
     * Kurucu metot (Constructor):
     * @param from: Gönderen
     * @param to: Alıcı
     * @param amount: Transfer miktarı
     * */
    public _01_Transaction(String from, String to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "_01_Transaction{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }
} // end _01_Transaction
