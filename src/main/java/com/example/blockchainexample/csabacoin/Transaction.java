package com.example.blockchainexample.csabacoin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    private String fromAddress;
    private String toAddress;
    private int amount;
}
