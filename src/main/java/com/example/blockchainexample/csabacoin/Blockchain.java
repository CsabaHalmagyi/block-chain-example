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
        this.difficulty = 5;
        this.miningReward = 100;
    }

    public Block getLatestBlock(){
        return chain.get(chain.size()-1);
    }

    public void blokkotHozzaAd(Block block){
        block.setElozoHash(this.getLatestBlock().getHash());
        block.setHash(block.calculateHash());
        this.chain.add(block);
    }

    public void minePendingTransactions(String miningRewardAddress){
        Block block = new Block(this.pendingTransactions, "");
        block.mineBlock(this.difficulty);
        log.info("Block successfully mined.");
        block.setElozoHash(this.getLatestBlock().getHash());
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
            for(Transaction tranzakcio:block.getTranzakciok()){
                if(tranzakcio.getKitol().equals(address))
                    balance -= tranzakcio.getOsszeg();

                if(tranzakcio.getKinek().equals(address))
                    balance += tranzakcio.getOsszeg();
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
            if (!currentBlock.getElozoHash().equals(previousBlock.getHash())) return false;
        }
        return true;
    }
    private Block createGenesisBlock(){
        Transaction firstTransaction = new Transaction("", "", 0);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(firstTransaction);
        return new Block(transactions, " ".repeat(64));
    }

    @Override
    public String toString() {
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //return gson.toJson(this);
        String chainString = "";
        int c = 1;
        for(Block b:chain){
            chainString += "Blokk "+c+"\n";
            chainString += b.toString();
            c++;
        }
        return chainString;
    }
}