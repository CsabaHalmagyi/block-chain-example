package com.example.blockchainexample.csabacoin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Blockchain {

    List<Block> chain;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.chain.add(createGenesisBlock());
    }

    public Block getLatestBlock(){
        return chain.get(chain.size()-1);
    }

    public void addBlock(Block newBlock){
        newBlock.setPreviousHash(this.getLatestBlock().getHash());
        newBlock.setHash(newBlock.calculateHash());
        chain.add(newBlock);
    }


    public boolean isChainValid(){
        if (chain.size() == 1) return true;
        for(int i=1; i<chain.size(); i++){
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i-1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) return false;
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) return false;
        }
        return true;
    }
    private Block createGenesisBlock(){
        return new Block(0, "GENESIS BLOCK", "0");
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
