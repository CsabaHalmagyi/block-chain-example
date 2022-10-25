
package com.example.blockchainexample.csabacoin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class Demo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("Starting blockchain demo.");

        //1. blockchain demo with transactions and proof-of-work

        Blockchain csabaCoin = new Blockchain();

        csabaCoin.createTransaction(new Transaction("address1", "address2", 25));
        csabaCoin.createTransaction(new Transaction("address1", "address2", 50));
        csabaCoin.createTransaction(new Transaction("address2", "address3", 20));


        log.info("Mining block 1:");
        csabaCoin.minePendingTransactions("csaba-address");
        log.info("Mining block 2:");
        csabaCoin.minePendingTransactions("csaba-address");

        /*
        //2. get balance
        log.info("Csaba's balance: {}",csabaCoin.getBalanceByAddress("csaba-address"));
        System.out.println(csabaCoin);
        */

    }

}