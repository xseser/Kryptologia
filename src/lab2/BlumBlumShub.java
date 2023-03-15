package lab2;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        ArrayList<Integer> tab = findOriginalValue();
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

    public Integer returnDecimal() {
        return decimal;
    }

    public void serviceBBS() {
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
        //System.out.println("decimal = " + decimal);
    }















    //TESTS//////////////////////////
    public Boolean singleBitTestFor20000Bits() {
        int minValue = 9654; //value from pdf for 20.000 bits
        int maxValue = 10346; //value from pdf for 20.000 bits
        boolean test = false;

        int digit0 = 0;
        int digit1 = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') digit1++;
            else digit0++;
        }
        if (checkCompartments(minValue, maxValue, digit0) && checkCompartments(minValue, maxValue, digit1)) test = true;
        else System.out.println("Number of digit 1: " + digit1);
        return test;
    }

    public boolean checkCompartments(int minValue, int maxValue, int digit) {
        if (digit > minValue && digit < maxValue) return true;
        else return false;
    }

    public Boolean seriesTest() {
        ArrayList<Integer> valueTab = new ArrayList<>();
        valueTab.add(2733);
        valueTab.add(1421);
        valueTab.add(748);
        valueTab.add(402);
        valueTab.add(223);

        boolean test = true;
        int j = 0;

        for (int i = 1; i < binary.length(); i++) {
            if (i > 4) j = 4;
            if (findRepeats(binary, i, '1') > valueTab.get(j)) test = false;
            if (findRepeats(binary, i, '0') > valueTab.get(j)) test = false;
            j++;
        }
        return test;
    }

    public int findRepeats(String binary, int seriesLength, Character character) {
        int key = 0;
        int series = 0;
        for (int i = 0; i < binary.length() - 1; i++) {
            if (binary.charAt(i) == character) {
                key++;
            } else {
                key = 0;
            }
            if (key == seriesLength) {
                if (binary.charAt(i + 1) != character) {
                    key = 0;
                    series++;
                }
            }
        }
        return series;
    }

    public Boolean longSerialTest() {
        int maxLength = 26;
        int repeatition = 0;
        if (findRepeats(binary, maxLength, '1') != 0 && findRepeats(binary, maxLength, '0') != 0) repeatition++;
        return repeatition <= 0;
    }


    public Boolean pokerTest() {
        double minValue = 2.19;
        double maxValue = 74.17;
        double x = 0;
        double sum = 0;
        boolean test = true;

        for (int i = 0; i < 16; i++) {
            sum += Math.pow(calculateSi(binary, i), 2);
        }

        x = 0.0032 * sum - 5000;

        if (Double.compare(maxValue, x) < 0) test = false;
        if (Double.compare(x, minValue) < 0) test = false;

        if (!test) System.out.println(" poker test, x = " + x);
        return test;
    }

    public ArrayList<Integer> divideFor5000pieces(String binary) {
        ArrayList<String> tab = new ArrayList<>();
        String word = "";
        for (int i = 0; i < binary.length(); i++) {
            word += binary.charAt(i);
            if (i % 4 == 3) {
                tab.add(word);
                word = "";
            }
        }

        ArrayList<Integer> decimalValues = new ArrayList<>();
        for (String string : tab) {
            decimalValues.add(binaryToDecimal2(string));
        }

//        for(Integer integer : decimalValues){
//            System.out.println(integer);
//        }
        return decimalValues;
    }

    public Integer calculateSi(String binary, int i) {
        int count = 0;
        ArrayList<Integer> decimalValues = divideFor5000pieces(binary);
        for (int j = 0; j < decimalValues.size(); j++) {
            if (decimalValues.get(j) == i) {
                count++;
            }
        }
//        System.out.println("\n" +  count);
        return count;
    }

    public int binaryToDecimal2(String binary) {
        int decimal = 0;
        int power = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            int bit = binary.charAt(i) - '0';
            decimal += bit * Math.pow(2, power);
            power++;
        }
        return decimal;
    }
}
