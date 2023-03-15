package lab3;


import java.util.ArrayList;

//d i e sa swoimi odwrotnosciami.
public class RSA {
    public int minValue;
    public int maxValue;
    public long e, d, phi, p, q, n;

    public RSA(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        p = generateValue();
        q = generateValue();
        n = p * q;
        phi = (p - 1) * (q - 1);
        findValueOfE();
        findValueOfD();
    }

    public int generateValue() {
        int variable = 0;
        variable = (int) Math.floor(Math.random() * (maxValue - minValue + 1) + minValue);
        if (!checkIfNumberIsPrime(variable)) {
            return generateValue();
        }
        if (variable % 4 == 3) {
            return variable;
        } else return generateValue();

    }

    public boolean checkIfNumberIsPrime(int variable) {
        for (int i = 2; i < variable; i++) {
            if (variable % i == 0) {
                return false;
            }
        }
        return true;
    }

    public long generateValueOfe() {
        return (long) (Math.random() * (phi)) + 1;
    }

    public void printAll() {
        System.out.println("p = " + p);                 // generated value -> prime number
        System.out.println("q = " + q);                 // generated value -> prime number
        System.out.println("n = " + n);                 // p*q
        System.out.println("minValue = " + minValue);   // user gives that
        System.out.println("maxValue = " + maxValue);   // user gives that
        System.out.println("phi = " + phi);
        System.out.println("e = " + e);
        System.out.println("d = " + d);
        //System.out.println("decimal = " + decimal);
    }

    public int najwiekszyWspolnyDzielnik(int a, double b) {
        while (b != 0) {
            int temp = (int) b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public void findValueOfE() {
        e = generateValueOfe();
        if (najwiekszyWspolnyDzielnik((int) e, phi) != 1) {
            findValueOfE();
        }
    }

    public void findValueOfD() {
        for (int i = 1; i < phi; i += 2) {
            if ((e * i) % phi == 1) {
                d = i;
                return;
            }
        }
    }

    public ArrayList<Character> arrayToCharParser(String string) {
        ArrayList<Character> tab = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            tab.add(string.charAt(i));
        }
//        for (Character character : tab) {
//            System.out.println((int) character);
//        }
        return tab;
    }

    public ArrayList<String> encryptMessage(ArrayList<Character> tab) {
        ArrayList<String> message = new ArrayList<>();
        int c;
        for (Character character : tab) {
            c = (int) powMod(character, e, n);
            message.add(String.valueOf(c));
        }
        return message;
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

    public String decryptMessage(ArrayList<String> strings) {
        int m;
        String message = "";
        for (String string : strings) {
            m = (int) powMod(Long.parseLong(string), d, n);
            message += (char) m;
        }
        return message;
    }
}
