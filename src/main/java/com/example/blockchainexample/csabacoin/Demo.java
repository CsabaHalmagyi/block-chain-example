
package com.example.blockchainexample.csabacoin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Demo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("Demo elkezdve.");

        demo1();
        //demo2();
        //demo3();

    }



    private void demo1(){
        Blockchain csabaCoin = new Blockchain();

        List<Transaction> tranzakciok1 = new ArrayList<>();
        tranzakciok1.add(new Transaction("andras-cime", "bela-cime", 25));
        tranzakciok1.add(new Transaction("andras-cime", "bela-cime", 50));
        tranzakciok1.add(new Transaction("bela-cime", "csaba-cime", 25));

        List<Transaction> tranzakciok2 = new ArrayList<>();
        tranzakciok2.add(new Transaction("erika-cime", "bela-cime", 100));
        tranzakciok2.add(new Transaction("erika-cime", "csaba-cime", 10));
        tranzakciok2.add(new Transaction("gergo-cime", "andras-cime", 35));

        csabaCoin.blokkotHozzaAd(new Block(tranzakciok1, ""));
        csabaCoin.blokkotHozzaAd(new Block(tranzakciok2, ""));

        System.out.println(csabaCoin);
        System.out.println("\nA blokklánc hibátlan: "+csabaCoin.isChainValid());
    }

    private void demo2(){
        Blockchain csabaCoin = new Blockchain();

        List<Transaction> tranzakciok1 = new ArrayList<>();
        tranzakciok1.add(new Transaction("andras-cime", "bela-cime", 25));
        tranzakciok1.add(new Transaction("andras-cime", "bela-cime", 50));
        tranzakciok1.add(new Transaction("bela-cime", "csaba-cime", 25));

        csabaCoin.blokkotHozzaAd(new Block(tranzakciok1, ""));

        List<Transaction> tranzakciok2 = new ArrayList<>();
        tranzakciok2.add(new Transaction("erika-cime", "bela-cime", 100));
        tranzakciok2.add(new Transaction("erika-cime", "csaba-cime", 10));
        tranzakciok2.add(new Transaction("gergo-cime", "andras-cime", 35));

        csabaCoin.blokkotHozzaAd(new Block(tranzakciok2, ""));

        System.out.println("\nA blokklánc a létrehozást követően hibátlan: "+csabaCoin.isChainValid());

        //Béla nem 25, hanem 55 coint utalt Csabának
        csabaCoin.getChain().get(1).getTranzakciok().get(2).setOsszeg(55);
        System.out.println("\nA blokklánc hibátlan miután megváltoztattuk az összeget: "+csabaCoin.isChainValid());
        //csabaCoin.getChain().get(1).setHash(csabaCoin.getChain().get(1).calculateHash());
        //System.out.println("\nA blokklánc hibátlan miután megváltoztattuk az összeget és újrageneráltuk a hash-t: "+csabaCoin.isChainValid());
        System.out.println(csabaCoin);
    }


    private void demo3(){
        Blockchain csabaCoin = new Blockchain();

        csabaCoin.createTransaction(new Transaction("andras-cime", "bela-cime", 25));
        csabaCoin.createTransaction(new Transaction("andras-cime", "bela-cime", 50));
        csabaCoin.createTransaction(new Transaction("bela-cime", "csaba-cime", 25));


        System.out.println("Első blokk bányászása 1:");
        csabaCoin.minePendingTransactions("csaba-cime");
        System.out.println("Második blokk bányászása:");
        csabaCoin.minePendingTransactions("csaba-cime");
        System.out.println("Csaba egyenlege: "+ csabaCoin.getBalanceByAddress("csaba-cime"));

    }

}