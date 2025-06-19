package com.hamitmizrak.blockchain;

import com.hamitmizrak.utils.SpecialColor;

import java.util.ArrayList;
import java.util.List;

public class _04_Blockchain {

    // FIELD
    private List<_03_Block> chain = new ArrayList<>();

    // CONSTRUCTOR
    public  _04_Blockchain(){
        chain.add(createGenesisBlock());
    }

    //
    private _03_Block createGenesisBlock() {
        List<_01_Transaction> genesisTx = new ArrayList<>();
        // String from, String to, double amount
        genesisTx.add(new _01_Transaction("Genesis","System",0));
        // int index, List<_01_Transaction> transactions, String previousHash
        return new _03_Block(0,genesisTx,"0");
    }

    //
    private _03_Block getLatestBlock(){
        return chain.get(chain.size()-1);
    }

    //
    private void addBlock(_03_Block newBlock){
        if(newBlock.getPreviousHash().equals(getLatestBlock().getHash())){
            chain.add(newBlock);
        }
    }

    //
    public boolean isChainValid(){
        for (int i = 1; i <chain.size(); i++) {
            _03_Block current= chain.get(i);
            _03_Block prevous = chain.get(i-1);
            //Conditional
            if(!current.getHash().equals(current.calculateHash()))
                return false;
            if(!current.getPreviousHash().equals(prevous.calculateHash()))
                return false;
        }
        return true;
    }

    //
    public List<_03_Block> getChain(){
        return chain;
    }

    //
    public String printBlockChain(){
        StringBuilder builder = new StringBuilder();
        for(_03_Block block : chain){
            builder
                    .append(SpecialColor.BLUE +"Block"+SpecialColor.RESET).append(block.getIndex()).append("\n")
                    .append(SpecialColor.BLUE +"Time"+SpecialColor.RESET).append(block.getTimestamp()).append("\n")
                    .append(SpecialColor.BLUE +"Hash"+SpecialColor.RESET).append(block.getHash()).append("\n")
                    .append(SpecialColor.BLUE +"Prev"+SpecialColor.RESET).append(block.getPreviousHash()).append("\n")
                    .append(SpecialColor.YELLOW +"Transaction \n"+SpecialColor.RESET);
            for(_01_Transaction transaction :block.getTransactions()){
                builder.append(".").append(transaction.toString()).append("\n");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

} // end _04_Blockchain
