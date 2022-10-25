package com.example.blockchainexample.csabacoin;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

@Data
@Slf4j
public class Block {

    private String dateCreated;
    private List<Transaction> transactions;
    private String previousHash;
    private String hash;
    private Integer nonce;


    public Block(List<Transaction> transactions, String previousHash){
        this.dateCreated = new Timestamp(System.currentTimeMillis()).toString();
        this.transactions = transactions;
        this.nonce = 0;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return Hashing.sha256()
                .hashString(this.previousHash + this.dateCreated + this.transactions.toString() + this.nonce,
                        StandardCharsets.UTF_8).toString();
    }

    Block mineBlock(int difficulty){
        String zeros = "0".repeat(difficulty);
        while(!this.hash.startsWith(zeros)){
            this.nonce++;
            this.hash = calculateHash();
        }
        log.info("Block mined: "+this.hash);
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
