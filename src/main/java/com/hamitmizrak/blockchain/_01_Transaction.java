package com.hamitmizrak.blockchain;

import lombok.*;

// LOMBOK
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class _01_Transaction {

    // FIELD
    public String from;
    public String to;
    public double amount;

    // CONSTRUCTOR (Parametreli Constructor)
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
