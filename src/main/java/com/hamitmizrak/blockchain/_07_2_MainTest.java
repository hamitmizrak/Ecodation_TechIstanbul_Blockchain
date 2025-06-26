package com.hamitmizrak.blockchain;

import com.hamitmizrak.utils.SpecialColor;

import java.util.*;

// Java 7, Oracle tarafından 28 Temmuz 2011 tarihinde resmî olarak yayımlandı.
public class _07_2_MainTest {

    // Scanner
    private static final Scanner scanner = new Scanner(System.in);

    // Running
    private static boolean running = true;

    // UserData
    private static final String[] userData = new String[3];

    // DoS (Denial of Service) : Saldırılarına karşı koruma
    private static Map<String, Long> lastTransactionTime = new HashMap<>();

    // 🧱 Blockchain ve smart Contract
    static _04_Blockchain blockchain = new _04_Blockchain();
    static _06_SmartContract smartContract = new _06_SmartContract(blockchain);

    // Kullanıcı ekle
    private static String[] addUser() {
        for (int i = 0; i < 3; i++) {
            System.out.print(SpecialColor.YELLOW + "Lütfen " + (i + 1) + " kullanıcıyı yazınız: " + SpecialColor.RESET);
            String temp = scanner.nextLine();
            userData[i] = temp;
        }
        // Kullanıcıları Göster
        for (String temp : userData) {
            System.out.print(temp + " ");
        }
        //scanner.close();
        return userData;
    } // end addUser

    // Wallet
    private static void walletAdd() {
        String[] user = addUser();

        // 1.YOL
        /*
        _05_Wallet.createWallet(user[0]);
        _05_Wallet.createWallet(user[1]);
        _05_Wallet.createWallet(user[2]);
         */

        // 2.YOL
        for (int i = 0; i < 3; i++) {
            _05_Wallet wallet = new _05_Wallet();
            _05_Wallet.getBalances().put(user[i], 1000.0);
            _05_Wallet.createWallet(user[i]);
        }
    } // end Wallet

    // Chooise
    private static void userChooise() {
        walletAdd();
        while (running) {
            System.out.println("\n========= 📋 Blockchain Menü =========");
            System.out.println("1. 💸 Transfer Yap");
            System.out.println("2. 📄 Blockchain Yazdır");
            System.out.println("3. 💼 Cüzdan Bakiyelerini Görüntüle");
            System.out.println("4. ✅ Zincir Geçerliliğini Kontrol Et");
            System.out.println("5. 🧱 Dijital İmza Ve Anahtar Yönetimi Testi");
            System.out.println("6. ✅ İstatiksel Gösterim");
            System.out.println("0. ❌ Çıkış");
            System.out.print("Seçiminiz: ");

            // Chooise
            int chooise ;
            for(;;){
                System.out.println("\n" +SpecialColor.GREEN+"Seçiminizi yapınız sadece sayı giriniz"+SpecialColor.RESET);
                if(scanner.hasNextInt()){ // sadece int
                    chooise = scanner.nextInt();
                    scanner.nextLine(); // Clean - dummy enter
                    break;
                }else{
                    System.out.println(SpecialColor.RED+"Hatalı giriş yaptınız Lütfen sadece tamsayı(int) giriniz"+SpecialColor.RESET);
                    scanner.nextLine(); // Geçersiz girdiyi temizle
                }
            }

            switch (chooise) {
                // 1. 💸 Transfer Yap
                case 1:
                    //Arrays.asList(userData).forEach(System.out::print);
                    Arrays.asList(userData).forEach(user -> System.out.print(user + " "));
                    // scanner.nextLine(); //dummy enter

                    // Kullanım(işlem gönderme sırasında)
                    long now = System.currentTimeMillis();

                    // Gönderen Wallet(user) eşleşmiyorsa hata versin
                    String from;
                    do {
                        System.out.print("\n👤 Gönderen: ");
                        from = scanner.nextLine();

                        if(lastTransactionTime.containsKey(from)){
                            long diff = now -lastTransactionTime.get(from);
                            if(diff<5000){ // 5 saniye geçikmeli
                                System.out.println("⚠️ Çok sık işlem gönderiyorsunuz ne yapıyorsun sen !!!!, Lütfen bekleyin");
                                return;
                            }
                        }
                        lastTransactionTime.put(from,now);
                        // Validation
                        if (!_05_Wallet.getBalances().containsKey(from)) {
                            System.out.println("⚠️ Geçersiz gönderen kullanıcı! Tekrar deneyin.");
                        }
                    } while (!_05_Wallet.getBalances().containsKey(from));


                    // Alıcı Wallet(user) eşleşmiyorsa hata versin
                    String to;
                    do {
                        System.out.print("👤 Alıcı: ");
                        to = scanner.nextLine();
                        if (!_05_Wallet.getBalances().containsKey(to)) {
                            System.out.println("⚠️ Geçersiz alıcı kullanıcı! Tekrar deneyin.");
                        }
                    } while (!_05_Wallet.getBalances().containsKey(to));

                    // Miktar (pozitif olmalıdır.)
                    double amount;
                    do {
                        System.out.print("💰 Miktar: ");
                        amount = scanner.nextDouble();
                        double senderBalance = _05_Wallet.getBalances().get(from);

                        // Eğer kullanıcının parası biterse (0) ek para ekle
                        if (senderBalance == 0) {
                            System.out.println("💸 Bakiyeniz 0TL. Yeni para eklemek ister misiniz ? (evet/hayır) ");
                            String request = scanner.nextLine().trim().toLowerCase();
                            if (request.equals("evet")) {
                                System.out.println("💰 Eklenek para miktarı");
                                double newAmount = scanner.nextDouble(); // Eklenecek para
                                scanner.nextLine(); // dummy enter(buffer temizliği)
                                _05_Wallet.addBalance(from, newAmount);
                                senderBalance = _05_Wallet.getBalances().get(from);
                                System.out.println("✅ Yeni bakiye: " + senderBalance + " TL");
                            } else {
                                System.out.println("Cüzdana para ekleme iptal edildi");
                            }
                        }

                        // Pozitif sayı giriniz
                        if (amount <= 0) {
                            System.out.println("⚠️ Miktar olarak sıfırdan büyük olmalıdır. Tekrar giriniz.");
                        } else if (amount > senderBalance) {
                            System.out.println("⚠️ Bakiye yetersiz! En fazla " + senderBalance + " kadar gönderebilirsiniz.");
                            amount = -1; // tekrar istemek için
                        }
                    } while (amount <= 0);
                    scanner.nextLine();

                    // Digital imza ile güvenli işlem 44444444
                    // Dijital imza ile ilem g羹venlii
                    // 1. C羹zdanlardan Anahtarlar Alınır:
                    // Gönderen ve alıcının c羹zdanları alınır.
                    //Bu c羹zdanlardan private ve public key eriilebilir hale gelir.
                    _05_Wallet sender = _05_Wallet.getWalletByName(from);
                    _05_Wallet receiver = _05_Wallet.getWalletByName(to);

                    // 2. ilem Oluturulur:
                    // ilem objesi, public key'ler ve miktar ile hazırlanır.
                    _01_Transaction signedTx = new _01_Transaction(
                            sender.getPublicKeyString(),
                            receiver.getPublicKeyString(),
                            amount
                    );
                    // 3. imza Oluturulur (Private Key ile):
                    // Gönderenin özel anahtarı (private key) ile bu ilem imzalanır.
                    //Bu aama, "gerçekten o c羹zdan sahibi mi" kontrol羹n羹n temelidir.
                    signedTx.signTransaction(sender.getPrivateKey());
                    boolean valid = signedTx.isSignatureValid(sender.getPublicKey());

                    // Daha sonra bu imzanın geçerli olup olmadıı gönderenin public key'i ile kontrol edilir.
                    //Eer ilem ya da imza sahte ise dorulama baarısız olur.
                    if (!valid) {
                        System.out.println(" işlem imzası geçersiz! Transfer iptal edildi.");
                        break;
                    }

                    // işlem sayısını artır
                    _05_Wallet.incrementTransactionCount(from);

                    // Zincire eklemek için yeni blok olutur
                    List<_01_Transaction> txs = new ArrayList<>();
                    txs.add(signedTx);
                    _03_Block newBlock = new _03_Block(blockchain.getChain().size(), txs, blockchain.getLatestBlock().getHash());
                    blockchain.addBlock(newBlock);

                    System.out.println("ilem baarıyla gerçekletirildi.");

                    System.out.println("\n Dijital imza Detayları:");
                    System.out.println("Gönderen Adı: " + from);
                    System.out.println(" Alıcı Adı: " + to);
                    System.out.println(" Miktar: " + amount);
                    System.out.println(" Gönderen Private Key: " + sender.getPrivateKeyString());
                    System.out.println(" Gönderen Public Key: " + sender.getPublicKeyString());
                    System.out.println(" imza Dorulama Sonucu: " + (valid?  "Geçerli" : "Geçersiz"));

                    // Transfer başlasın
                    System.out.println(smartContract.executeTransfer(from, to, amount));
                    break;

                // 2. 📄 Blockchain Yazdır"
                case 2:
                    System.out.println("\n=========Blockchain Yapısı========");
                    System.out.println(blockchain.printBlockChain());
                    break;

                // 3. 💼 Cüzdan Bakiyelerini Görüntüle
                case 3:
                    System.out.println("\n=========Cüzdan Bakiyesi========");
                    _05_Wallet.getBalances().forEach((user, balance) -> {
                        System.out.println(user + " ==> Bakiyesi: " + balance);
                    });
                    break;

                // 4. ✅ Zincir Geçerliliğini Kontrol Et
                case 4:
                    String result = blockchain.isChainValid() ? "✅ Evet" : "❌ Hayır";
                    System.out.println("\nZincir Geçerli mi ? " + (result));
                    break;

                case 5:
                    // Her kullanıcı için RSA public-private key çifti oluturuluyor.
                    System.out.println("===  Dijital imza ve Anahtar Yönetimi Testi ===");
                    _05_Wallet senderWallet = new _05_Wallet();
                    _05_Wallet receiverWallet = new _05_Wallet();
                    _01_Transaction transaction = new _01_Transaction(
                            senderWallet.getPublicKeyString(),
                            receiverWallet.getPublicKeyString(),
                            100
                    );
                    transaction.signTransaction(senderWallet.getPrivateKey());
                    boolean isValid = transaction.isSignatureValid(senderWallet.getPublicKey());

                    System.out.println("--- ilem Detayları ---");
                    System.out.println(" Gönderen Public Key: " + senderWallet.getPublicKeyString());
                    System.out.println(" Gönderen Private Key: " + senderWallet.getPrivateKeyString());
                    System.out.println(" Alıcı Public Key: " + receiverWallet.getPublicKeyString());
                    System.out.println(" Transfer Miktarı: 100");
                    System.out.println(" imza Geçerliliği: " + (isValid?  "Geçerli" : "Geçersiz"));
                    break;

                // 6. ✅ İstatiksel Gösterim
                case 6:
                    System.out.println("\n=========İstatiksel Gösterim======== ");
                    // 1- Her kullanıcının yaptığı işlem sayısı
                    System.out.println("Kullanıcı İşlem Sayısı");
                    _05_Wallet.getTransactionCounts().forEach((user, count) -> {
                        System.out.println(user + " kullanıcısı " + count + " işlem yaptı");
                    });

                    // 2- Toplam Transfer edilen miktar
                    double totalAmount = blockchain.getChain().stream()
                            .flatMap(block -> block.getTransactions().stream())
                            .filter(temp -> temp.getAmount() > 0) // Sadece gerçek transferlerde
                            .mapToDouble(_01_Transaction::getAmount)
                            .sum();
                    System.out.println("\nToplam Transfer Edilen Miktar: " +SpecialColor.YELLOW+ totalAmount+SpecialColor.RESET);

                    // 3 - Her blockta kaç işlem var ?
                    System.out.println("\nBlok bazlı işlem sayısı");
                    int i = 0;
                    for (_03_Block block : blockchain.getChain()) {
                        //System.out.println("Block " + (i+1) + ": " + block.getTransactions().size() + " işlem");
                        System.out.println("Block " + (i++) + ": " + block.getTransactions().size() + " işlem");
                    }
                    break;

                // 0. ❌ Çıkış
                case 0:
                    running = false;
                    System.out.println("Çıkış yapılıyor");
                    System.exit(0);
                    break;

                // 0<=X<=4 dışında
                default:
                    System.out.println("❌ Geçersiz seçim yaptınız tekrar deneyiniz ");
            }
        } // end while

        // Klavyeyi kapat
        scanner.close();
    }  // end userChooise

    // psvm
    public static void main(String[] args) {
        userChooise();
    } // end psvm
} // end class userChooise
