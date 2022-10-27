package com.example.blockchainexample.csabacoin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    private String kitol;
    private String kinek;
    private int osszeg;

}
