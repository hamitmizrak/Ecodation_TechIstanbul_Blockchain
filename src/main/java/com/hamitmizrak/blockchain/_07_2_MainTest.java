package com.hamitmizrak.blockchain;

import com.hamitmizrak.utils.SpecialColor;

import java.util.Scanner;

public class _07_2_MainTest {
    // Scanner
    private static Scanner scanner = new Scanner(System.in);

    // Running
    private static boolean running =true;

    // Blockchain ve smart Contract
    static _04_Blockchain blockchain = new _04_Blockchain();
    static _06_SmartContract smartContract= new _06_SmartContract(blockchain);

    // Kullanıcı ekle
    private static String[] addUser() {
        String[] userData= new String[3];
        for (int i = 0; i < 3; i++) {
            System.out.print(SpecialColor.BLUE+"Lütfen " + (i+1)+ " kullanıcıyı yazınız: "+SpecialColor.RESET);
            String temp = scanner.nextLine();
            userData[i]=temp;
        }
        for (String temp: userData){
            System.out.print(temp+" ");
        }
        scanner.close();
        return userData;
    }

    // Wallet
    private static void walletAdd(){
        String[] user= addUser();
        _05_Wallet.createWallet(user[0]);
        _05_Wallet.createWallet(user[1]);
        _05_Wallet.createWallet(user[2]);
    }

    // Chooise
    private static void userChooise(){
        while (running){
            System.out.println("\n ========== Blockchain Menüsü =========");
            System.out.println("1- Transfer Yap");
            System.out.println("2- Blockchain yazdır");
            System.out.println("3- Cüzdan bakiyesini görüntüle");
            System.out.println("4- Zincir geçerliliğini kontrol");
            System.out.println("0- Çıkış");
            System.out.println("Seçiminiz");

            int chooise = scanner.nextInt();
            // dummy enter
            scanner.nextLine();

            switch (chooise){
                case 1:
                    System.out.print("Gönderen");
                    String from = scanner.nextLine();
                    System.out.print("Alıcı ");
                    String to = scanner.nextLine();
                    System.out.print("Miktar: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); //dummy enter
                    System.out.println(smartContract.executeTransfer(from,to,amount));
                    break;
            }
        }
    }

    // psvm
    public static void main(String[] args) {
        walletAdd();

    }
}
