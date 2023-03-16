package lab4;

import java.util.HashSet;
import java.util.Set;

public class Diffie {
    long n, g;

    public Diffie(long n, long g) {
        this.n = n;
        this.g = g;
    }

    public void generateStartValues(){
        n = generateBigPrimeNumber(1000000, 99999999);
        g = findPrimitiveRoot((int) n);
    }

    public long getN() {
        return n;
    }

    public long getG() {
        return g;
    }

    public void setN(long n) {
        this.n = n;
    }

    public void setG(long g) {
        this.g = g;
    }

    public long generateBigPrimeNumber(long min, long max) {
        int variable = (int) (Math.random() * (max - min) + min);
        if (!checkIfNumberIsPrime(variable)) {
            return generateBigPrimeNumber(min, max);
        }
        return variable;
    }

    public boolean checkIfNumberIsPrime(int variable) {
        for (int i = 2; i < variable; i++) {
            if (variable % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void printAll() {
        System.out.println("n: " + n);
        System.out.println("g: " + g);
    }

    public long powMod(long a, long b, long c) {
        long result = 1;
        a %= c;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % c;
            }
            b >>= 1;
            a = (a * a) % c;
        }
        return result;
    }

    public int findPrimitiveRoot(int n) {
        Set<Integer> factors = new HashSet<>();
        int phi = n - 1;
        int temp = phi;
        for (int i = 2; i * i <= temp; i++) {
            if (temp % i == 0) {
                factors.add(i);
                while (temp % i == 0) {
                    temp /= i;
                }
            }
        }
        if (temp > 1) {
            factors.add(temp);
        }
        for (int i = 2; i <= n; i++) {
            boolean ok = true;
            for (int factor : factors) {
                if (powMod(i, phi / factor, n) == 1) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return i;
            }
        }
        return -1; // pierwiastek pierwotny nie istnieje
    }

    public long privateKey(long min, long max){
        long random = (long) (Math.random()*(max-min)+min);
        return powMod(g, random, n);
    }



}

