package com.example.blockchainexample.csabacoin;

import com.google.common.hash.Hashing;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

@Data
@Slf4j
public class Block {

    private String letrehozasDatuma;
    private List<Transaction> tranzakciok;
    private String elozoHash;
    private String hash;
    private Integer nonce;


    public Block(List<Transaction> tranzakciok, String elozoHash){
        this.letrehozasDatuma = new Timestamp(System.currentTimeMillis()).toString();
        this.tranzakciok = tranzakciok;
        this.nonce = 0;
        this.elozoHash = elozoHash;
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return Hashing.sha256()
                .hashString(this.elozoHash + this.letrehozasDatuma + this.tranzakciok.toString() + this.nonce,
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
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //return gson.toJson(this);
        String tranzakciokLista = "";
        for(Transaction t:tranzakciok){
            tranzakciokLista +=
            "### Kitől: " + t.getKitol()+ "\n" +
            "### Kinek: " + t.getKinek() + "\n" +
            "### Összeg: " + t.getOsszeg() +"\n" +
            "###-----------------------\n";
        }
        return
                "########################################################################################\n" +
                "### Létrehozás dátuma: "+ letrehozasDatuma +"                                    \t ###\n" +
                "### Hash:\t\t"+ hash +"\t ###\n" +
                "### ElőzőHash:\t"+ elozoHash +"\t ###\n" +
                "########################################################################################\n"+
                tranzakciokLista;
    }
}
