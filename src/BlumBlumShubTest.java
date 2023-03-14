import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class BlumBlumShubTest {
    @Test
    public void singleBitTestFor20000Bits() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(169999, 371111, 20000);
        String binary = blumBlumShub.returnBinary();

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
        assertTrue(test);
    }

    public boolean checkCompartments(int minValue, int maxValue, int digit) {
        if (digit > minValue && digit < maxValue) return true;
        else return false;
    }

    @Test
    public void seriesTest() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(169999, 371111, 20000);
        String binary = blumBlumShub.returnBinary();

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
        assertTrue(test);
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

    @Test
    public void longSerialTest() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(169999, 371111, 20000);
        String binary = blumBlumShub.returnBinary();

        int maxLength = 26;
        int repeatition = 0;
        if (findRepeats(binary, maxLength, '1') != 0 && findRepeats(binary, maxLength, '0') != 0) repeatition++;
        if(repeatition > 0) System.out.println("long serial Test repetitions = " + repeatition);
        assertEquals(0, repeatition);
    }

    @Test
    public void pokerTest() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(169999, 371111, 20000);
        String binary = blumBlumShub.returnBinary();

//        calculateSi(binary, 4);

        double minValue = 2.19;
        double maxValue = 74.17;
        double x = 0;
        double sum = 0;
        boolean test = true;

        for (int i = 0; i < 16; i++) {
            sum += Math.pow(calculateSi(binary, i), 2);
//            System.out.println(Math.pow(calculateSi(binary, i), 2));
        }

        x = 0.0032 * sum - 5000;

        if (Double.compare(maxValue, x) < 0) test = false;
        if (Double.compare(x, minValue) < 0) test = false;

        if(!test) System.out.println(" poker test, x = " + x);
        assertTrue(test);

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
            decimalValues.add(binaryToDecimal(string));
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

    public int binaryToDecimal(String binary) {
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