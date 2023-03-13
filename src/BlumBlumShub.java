import java.util.ArrayList;

import static java.lang.Math.pow;

public class BlumBlumShub {
    public int p, q, x, n, decimal;
    public int minValue, maxValue, numOfBits;
    String binary;

    public BlumBlumShub(int minValue, int maxValue, int numOfBits) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.numOfBits = numOfBits;
        findValueOfX();
        arrayToStringParser();
        binaryToDecimal();
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

    public int findRatio() {
        p = generateValue();
        q = generateValue();
        n = p * q;
        return n;
    }

    public int findValueOfX() {
        int ratio = findRatio();
        x = (int) Math.floor(Math.random() * (ratio) + 1);
        if (!checkIfNumberIsPrime(x)) {
            return findValueOfX();
        }
        return x;
    }

    public ArrayList<Integer> findOriginalValue() { //znajduje wartosc pierwotna generatora.
        ArrayList<Integer> tab = new ArrayList<>();
        int originValue = 0;
        int xi = x;
        for (int i = 0; i < numOfBits; i++) {
            xi = (int) (pow(xi, 2) % n);
            originValue = xi % 2;
            tab.add(originValue);
//            System.out.println("xi " + xi);
//            System.out.println("originValue " + originValue);
        }
        return tab;
    }

    public void arrayToStringParser() {
        ArrayList<Integer> tab = new ArrayList<>();
        tab = findOriginalValue();
        binary = "";
        for (Integer integer : tab) {
            binary += integer;
        }
    }

    public void binaryToDecimal() {
        decimal = 0;
        int power = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            int bit = binary.charAt(i) - '0';
            decimal += bit * Math.pow(2, power);
            power++;
        }
    }

    public String returnBinary() {
        return binary;
    }

    public Integer returnDecimal(){
        return decimal;
    }

    public void serviceBBS(){
//        findValueOfX();
//        arrayToStringParser();
//        binaryToDecimal();
    }

    public void printAll() {
        System.out.println("p = " + p);                 // generated value -> prime number
        System.out.println("q = " + q);                 // generated value -> prime number
        System.out.println("n = " + n);                 // p*q
        System.out.println("x = " + x);                 // random value from 1 to n
        System.out.println("minValue = " + minValue);   // user gives that
        System.out.println("maxValue = " + maxValue);   // user gives that
        System.out.println("binary = " + binary);
        System.out.println("decimal = " + decimal);
    }

    public void testFunction(){
        BlumBlumShubTest blumBlumShubTest = new BlumBlumShubTest();

    }
}
