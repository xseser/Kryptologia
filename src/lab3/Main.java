package lab3;

import lab3.RSA;

public class Main {
    public static void main(String[] args) {
        RSA rsa = new RSA(31000, 55000);
        //rsa.printAll();
        System.out.println(rsa.encryptMessage(rsa.arrayToCharParser("kryptografia")));
        System.out.println(rsa.decryptMessage(rsa.encryptMessage(rsa.arrayToCharParser("kryptografia"))));
    }







}