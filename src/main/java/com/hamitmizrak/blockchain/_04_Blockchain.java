package com.hamitmizrak.blockchain;

import com.hamitmizrak.utils.SpecialColor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// LOMBOK
@Getter
@Setter

/**
 * _04_Blockchain sınıfı, bloklardan oluşan zinciri (blockchain) yöneten temel sınıftır.
 * Sistemin başlangıç bloğu (Genesis Block) ile başlar ve her yeni blok önceki bloğa bağlı olarak zincire eklenir.
 * Blok zinciri yapısında veri bütünlüğü, doğrulama ve hash bağlantıları bu sınıf aracılığıyla kontrol edilir.
 */
public class _04_Blockchain {

    // Blockchain zincirini oluşturan blokların listesi
    private List<_03_Block> chain = new ArrayList<>();

    /**
     * Constructor: Blockchain oluşturulurken otomatik olarak Genesis Block (ilk blok) eklenir.
     */
    public _04_Blockchain() {
        chain.add(createGenesisBlock());
    }

    /**
     * Sistemin başlangıç bloğunu (Genesis Block) oluşturur.
     * Bu blok sabit değerler içerir ve manuel olarak tanımlanır.
     * @return Genesis bloğu
     */
    public _03_Block createGenesisBlock() {
        List<_01_Transaction> genesisTx = new ArrayList<>();
        genesisTx.add(new _01_Transaction("Genesis", "System", 0));
        return new _03_Block(0, genesisTx, "0");
    }

    /**
     * Zincirin en son bloğunu getirir.
     * @return Son blok
     */
    public _03_Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    /**
     * Yeni bir blok eklemeden önce, zincirle uyumlu olup olmadığı kontrol edilir.
     * Sadece geçerli bağlantıya sahip bloklar zincire dahil edilir.
     * @param newBlock Eklenmek istenen yeni blok
     */
    public void addBlock(_03_Block newBlock) {
        if (newBlock.getPreviousHash().equals(getLatestBlock().getHash())) {
            chain.add(newBlock);
        }
    }

    /**
     * Zincirin bütünlüğünü doğrular. Tüm bloklar hash açısından tutarlı mı kontrol edilir.
     * @return true -> zincir geçerli, false -> zincir bozulmuş olabilir
     */
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            _03_Block current = chain.get(i);
            _03_Block previous = chain.get(i - 1);

            if (!current.getHash().equals(current.calculateHash()))
                return false;

            if (!current.getPreviousHash().equals(previous.calculateHash()))
                return false;
        }
        return true;
    }

    /**
     * Tüm blok zincirini geri döner.
     * @return Blok listesi
     */
    public List<_03_Block> getChain() {
        return chain;
    }

    /**
     * Zincirdeki her bloğu okunabilir formatta string olarak döner.
     * Renkli yazım için özel karakterler kullanılmaktadır (SpecialColor).
     * @return Zincirin tüm içeriğini yazdırır.
     */
    public String printBlockChain() {
        StringBuilder builder = new StringBuilder();
        for (_03_Block block : chain) {
            builder.append(SpecialColor.BLUE).append("Block").append(SpecialColor.RESET).append(block.getIndex()).append("\n")
                    .append(SpecialColor.BLUE).append("\uD83D\uDD52 Time: ").append(SpecialColor.RESET).append(block.getTimestamp()).append("\n")
                    .append(SpecialColor.BLUE).append("\uD83D\uDD22 Hash: ").append(SpecialColor.RESET).append(block.getHash()).append("\n")
                    .append(SpecialColor.BLUE).append("↩\uFE0F Prev: ").append(SpecialColor.RESET).append(block.getPreviousHash()).append("\n")
                    .append(SpecialColor.YELLOW).append("\uD83D\uDCE6 Transaction \n").append(SpecialColor.RESET);

            for (_01_Transaction transaction : block.getTransactions()) {
                builder.append(".").append(transaction.toString()).append("\n");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
} // end _04_Blockchain