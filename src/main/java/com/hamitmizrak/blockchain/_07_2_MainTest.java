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
    }

    // Wallet
    private static void walletAdd() {
        String[] user = addUser();
        _05_Wallet.createWallet(user[0]);
        _05_Wallet.createWallet(user[1]);
        _05_Wallet.createWallet(user[2]);
    }

    // Chooise
    private static void userChooise() {
        walletAdd();
        while (running) {
            System.out.println("\n========= 📋 Blockchain Menü =========");
            System.out.println("1. 💸 Transfer Yap");
            System.out.println("2. 📄 Blockchain Yazdır");
            System.out.println("3. 💼 Cüzdan Bakiyelerini Görüntüle");
            System.out.println("4. ✅ Zincir Geçerliliğini Kontrol Et");
            System.out.println("0. ❌ Çıkış");
            System.out.print("Seçiminiz: ");

            int chooise = scanner.nextInt();
            // dummy enter
            scanner.nextLine();

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
                        if (amount <= 0) {
                            System.out.println("⚠️ Miktar pozitif olmalıdır. Tekrar giriniz.");
                        } else if (amount > senderBalance) {
                            System.out.println("⚠️ Bakiye yetersiz! En fazla " + senderBalance + " kadar gönderebilirsiniz.");
                            amount = -1; // tekrar istemek için
                        }
                    } while (amount <= 0);
                    scanner.nextLine();

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

                 // 0. ❌ Çıkış
                case 0:
                    running = false;
                    System.out.println("Çıkış yapılıyor");
                    System.exit(0);
                    break;

                    // 0<=X<=4
                default:
                    System.out.println("❌ Geçersiz seçim yaptınız tekrar deneyiniz ");
            }
        }

        // Klavyeyi kapat
        scanner.close();
    }

    // psvm
    public static void main(String[] args) {
        userChooise();
    }
} // end class userChooise
