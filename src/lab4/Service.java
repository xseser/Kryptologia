package lab4;

public class Service {

    public void init() {
        long g, n;

        Diffie A = new Diffie(0, 0);

        A.generateStartValues();
        g = A.getG();
        n = A.getN();

        Diffie B = new Diffie(n, g);

        System.out.println("1. ustalone liczby n i g : ");
        A.printAll();
        long X = A.privateKey(1000000, 99999999);
        System.out.println("\n2. A oblicza klucz prywatny X: " + X);
        long x = A.returnRandom();
        System.out.println("2. A wybrana duza liczba calkowita x: " + x);
        long Y = B.privateKey(1000000, 99999999);
        System.out.println("3. B oblicza klucz prywatny Y: " + Y);
        long y  = B.returnRandom();
        System.out.println("3. B wybrana duza liczba calkowita y: " + y);
        long k1 = A.calculateSessionKey(Y, x);
        System.out.println("5. A oblicza klucz sesji: " + k1);
        long k2 = B.calculateSessionKey(X, y);
        System.out.println("6. B oblicza klucz sesji: " + k2);
    }
}
