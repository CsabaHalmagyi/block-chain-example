package com.example.blockchainexample.csabacoin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Blockchain {

    private List<Block> chain;
    private Integer difficulty;
    private List<Transaction> pendingTransactions;
    private int miningReward;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.chain.add(createGenesisBlock());
        this.pendingTransactions = new ArrayList<>();
        this.difficulty = 4;
        this.miningReward = 100;
    }

    public Block getLatestBlock(){
        return chain.get(chain.size()-1);
    }

    public void minePendingTransactions(String miningRewardAddress){
        Block block = new Block(this.pendingTransactions, "");
        block.mineBlock(this.difficulty);
        log.info("Block successfully mined.");
        block.setPreviousHash(this.getLatestBlock().getHash());
        block.setHash(block.calculateHash());
        this.chain.add(block);
        this.pendingTransactions = new ArrayList<>();
        pendingTransactions.add(new Transaction("", miningRewardAddress, this.miningReward));
    }

    public void createTransaction(Transaction transaction){
        this.pendingTransactions.add(transaction);
    }

    public int getBalanceByAddress(String address){
        int balance = 0;
        for(Block block:this.chain){
            for(Transaction transaction:block.getTransactions()){
                if(transaction.getFromAddress().equals(address))
                    balance -= transaction.getAmount();

                if(transaction.getToAddress().equals(address))
                    balance += transaction.getAmount();
            }
        }
        return balance;
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
        Transaction firstTransaction = new Transaction("", "", 0);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(firstTransaction);
        return new Block(transactions, "0");
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}