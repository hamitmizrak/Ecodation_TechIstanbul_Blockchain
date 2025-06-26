package com.hamitmizrak.blockchain;

import com.hamitmizrak.utils.SpecialColor;

import java.util.Arrays;
import java.util.Scanner;

public class _07_2_MainTest {

    // Scanner
    private static final Scanner scanner = new Scanner(System.in);

    // Running
    private static boolean running = true;

    // UserData
    private static final String[] userData = new String[3];

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

    // Chooise  InputMismatchException
    /*
    Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:939)
	at java.base/java.util.Scanner.next(Scanner.java:1594)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2258)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2212)
	at com.hamitmizrak/com.hamitmizrak.blockchain._07_2_MainTest.userChooise(_07_2_MainTest.java:72)
	at com.hamitmizrak/com.hamitmizrak.blockchain._07_2_MainTest.main(_07_2_MainTest.java:211)
    * */
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
            int chooise = scanner.nextInt();
            scanner.nextLine(); // dummy enter

            switch (chooise) {
                // 1. 💸 Transfer Yap
                case 1:
                    //Arrays.asList(userData).forEach(System.out::print);
                    Arrays.asList(userData).forEach(user -> System.out.print(user + " "));
                    // scanner.nextLine(); //dummy enter

                    // Gönderen Wallet(user) eşleşmiyorsa hata versin
                    String from;
                    do {
                        System.out.print("\n👤 Gönderen: ");
                        from = scanner.nextLine();
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
                    System.out.println("Dijital İmza Ve Anahtar Yönetimi Testi");
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
                    System.out.println("\n Toplam Transfer Edilen Miktar" + totalAmount);

                    // 3 - Her blockta kaç işlem var ?
                    System.out.println("\n Blok bazlı işlem sayısı");
                    int i = 0;
                    for (_03_Block block : blockchain.getChain()) {
                        System.out.println("Block " + i + ": " + block.getTransactions().size() + " işlem");
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
