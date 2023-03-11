import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class BlumBlumShubTest {

    @Disabled
    @Test
    public void singleBitTestFor20000Bits() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(300, 400, 24);
        blumBlumShub.serviceBBS();
        String binary = blumBlumShub.returnBinary();

        int minValue = 9725; //value from pdf for 20.000 bits
        int maxValue = 10275; //value from pdf for 20.000 bits
        boolean test = false;

        int digit0 = 0;
        int digit1 = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') digit1++;
            else digit0++;
        }
        if (checkCompartments(minValue, maxValue, digit0) && checkCompartments(minValue, maxValue, digit1)) {
            test = true;
        }
        assertTrue(test);
    }

    public boolean checkCompartments(int minValue, int maxValue, int digit) {
        if (digit > minValue && digit < maxValue) return true;
        else return false;
    }

    @Disabled
    @Test
    public void seriesTest() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(300, 400, 24);
        blumBlumShub.serviceBBS();
        String binary = blumBlumShub.returnBinary();

        ArrayList<Integer> valueTab = new ArrayList<>();
        valueTab.add(2685);
        valueTab.add(1386);
        valueTab.add(723);
        valueTab.add(384);
        valueTab.add(209);

        boolean test = true;
        int j = 0;

        for (int i = 1; i < 10; i++) {
            if (i > 4) j = 4;
            if (findRepeats(binary, i, '1') > valueTab.get(j)) test = false;
            if (findRepeats(binary, i, '0') > valueTab.get(j)) test = false;
            j++;
        }
//        System.out.println(test);
//        System.out.println(findRepeats(binary, 1, '1'));
//        System.out.println(findRepeats(binary, 2, '1'));
//        System.out.println(findRepeats(binary, 3, '1'));
//        System.out.println(findRepeats(binary, 4, '1'));
//        System.out.println(findRepeats(binary, 5, '1'));
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

    @Disabled
    @Test
    public void longSerialTest() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(300, 400, 24);
        blumBlumShub.serviceBBS();
        String binary = blumBlumShub.returnBinary();

        int maxLength = 26;
        int repeatition = 0;
        if (findRepeats(binary, maxLength, '1') != 0 && findRepeats(binary, maxLength, '0') != 0) repeatition++;
        assertEquals(0, repeatition);
    }

    @Test
    public void pokerTest() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(300, 400, 24);
        blumBlumShub.serviceBBS();
        String binary = blumBlumShub.returnBinary();

        divideFor5000pieces(binary);
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
        return decimalValues;
    }

    public void calculateSi(){

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