package lab4;

import lab3.RSA;

public class Main {
    public static void main(String[] args) {
        long g, n;

        Diffie A = new Diffie(0,0);

        A.generateStartValues();
        g = A.getG();
        n = A.getN();

        Diffie B = new Diffie(n,g);

        int X = (int) A.privateKey(1000000, 99999999);
        int Y = (int) B.privateKey(1000000, 99999999);

        B.printAll();
        A.printAll();
    }
}
