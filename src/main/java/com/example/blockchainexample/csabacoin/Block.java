package com.example.blockchainexample.csabacoin;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

@Data
public class Block {

    private String dateCreated;
    private String data;
    private String previousHash;
    private String hash;


    public Block(String data, String previousHash){
        this.dateCreated = new Timestamp(System.currentTimeMillis()).toString();
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return Hashing.sha256()
                .hashString( this.previousHash + this.dateCreated + this.data,
                        StandardCharsets.UTF_8).toString();
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
