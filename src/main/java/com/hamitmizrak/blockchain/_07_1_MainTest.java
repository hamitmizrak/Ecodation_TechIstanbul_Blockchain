package com.hamitmizrak.blockchain;

// ℹ️
// ❌
// ✅
public class _07_1_MainTest {

    // psvm
    public static void main(String[] args) {
        // ✅ 1-Blockchain oluşturalım.
        _04_Blockchain blockchain = new _04_Blockchain();

        // ✅ 2- Kullanıcı cüzdanları oluştur
        _05_Wallet.createWallet("Hamit");
        _05_Wallet.createWallet("Hulusi");
        _05_Wallet.createWallet("Habibe");

        // ✅ 3-Smart Contract (Akıllı sözleşmeler)
        _06_SmartContract smartContract = new _06_SmartContract(blockchain);


        // ✅ 4- Bazı işlemlerimizi gönderelim (Transfer)
        System.out.println(smartContract.executeTransfer("Hamit", "Hulusi", 20));
        System.out.println(smartContract.executeTransfer("Hulusi", "Habibe", 10));
        System.out.println(smartContract.executeTransfer("Habibe", "Hamit", 5));

        System.out.println(smartContract.executeTransfer("Hamit", "Habibe", 50));
        System.out.println(smartContract.executeTransfer("Habibe", "Hulusi", 15));
        System.out.println(smartContract.executeTransfer("Hulusi", "Hamit", 25));

        // ✅ 5- Blockchain zinciri yazdırılıyor
        System.out.println("\n ================ Blockchain Yapısı ================");
        System.out.println(blockchain.printBlockChain());

        // ✅ 6- Kullanıcı Bakiyelerini göstersin
        System.out.println("\n ================ Cüzdan Bakiyesi ================");
        _05_Wallet.getBalances().forEach(
                (user, balance) -> System.out.println(user + " bakiyesi " + balance)
        );

        // 7- Zincirin geçerlilik kontrolü
        System.out.println("\nZincir Geçerli mi ? "+(blockchain.isChainValid() ? "✅ Evet": "❌ Hayır"));

    }
}
