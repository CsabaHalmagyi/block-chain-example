package com.example.blockchainexample.csabacoin;

import com.google.common.hash.Hashing;
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

        //1. how hashing works:
        System.out.println("Hash of the word 'example': "+calculateHash("example"));
        System.out.println("Hash of the word 'example': "+calculateHash("example"));
        System.out.println("Hash of the word 'example1': "+calculateHash("example1"));


        //2. blockchain demo
        /*
        Blockchain csabaCoin = new Blockchain();
        csabaCoin.addBlock(new Block(1, "Amount: 7", ""));
        csabaCoin.addBlock(new Block(2, "Amount: 21", ""));
        System.out.println(csabaCoin);

        System.out.println("Is chain valid: "+csabaCoin.isChainValid());


        //3. tampering with the blockchain
        csabaCoin.getChain().get(1).setData("Amount: 100");
        System.out.println("Is chain valid after tampering: "+csabaCoin.isChainValid());
        csabaCoin.getChain().get(1).setHash(csabaCoin.getChain().get(1).calculateHash());
        System.out.println("Is chain valid after hash was recalculated: "+csabaCoin.isChainValid());
        System.out.println(csabaCoin);

         */
    }


    public String calculateHash(String s){
        return Hashing.sha256()
                .hashString(s, StandardCharsets.UTF_8).toString();
    }
}
