import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class BlumBlumShubTest {

    @Test
    public void singleBitTestFor20000Bits() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(300, 400, 20000);
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

    @Test
    public void seriesTest() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(300, 400, 20000);
        blumBlumShub.serviceBBS();
        blumBlumShub.printAll();
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
            if (subString(binary, i+1, '1') > valueTab.get(j)) test = false;
            if (subString(binary, i+1, '0') > valueTab.get(j)) test = false;
            j++;
        }
        System.out.println(test);
        System.out.println("dlugosc serii 1 dla znaku '1' " + subString(binary, 2, '1'));
        System.out.println("Zero, które występuje w ciągu co najmniej 2 razy: " + subString(binary, 2, '0'));
        System.out.println("dlugosc serii 2 dla znaku '1'  " + subString(binary, 3, '1'));
        System.out.println("Zero, które występuje w ciągu co najmniej 3 razy: " + subString(binary, 3, '0'));
        System.out.println("dlugosc serii 3 dla znaku '1'  " + subString(binary, 4, '1'));
        System.out.println("Zero, które występuje w ciągu co najmniej 4 razy: " + subString(binary, 4, '0'));
        System.out.println("dlugosc serii 4 dla znaku '1'  " + subString(binary, 5, '1'));
        System.out.println("dlugosc serii 4 dla znaku '0' " + subString(binary, 5, '0'));
        System.out.println("dlugosc serii 5 dla znaku '1'  " + subString(binary, 6, '1'));
        System.out.println("dlugosc serii 5 dla znaku '0'  " + subString(binary, 6, '0'));
        System.out.println("dlugosc serii 6 dla znaku '1'  " + subString(binary, 7, '1'));
        System.out.println("dlugosc serii 6 dla znaku '0'  " + subString(binary, 7, '0'));
        assertTrue(test);
    }

    public int subString(String binary, int seriesLength, Character character) {
        int count = 0;
        int repeat = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == character) repeat++;
            else repeat = 0;
            if (repeat == seriesLength) {
                repeat = 0;
                count++;
            }
        }
        return count;
    }

    @Test
    public void longSerialTest() {
        BlumBlumShub blumBlumShub = new BlumBlumShub(600, 700, 20000);
        blumBlumShub.serviceBBS();
        String binary = blumBlumShub.returnBinary();

        int maxLength = 26;
        int repeatition = 0;
        if (subString(binary, maxLength, '1') != 0 && subString(binary, maxLength, '0') != 0) repeatition++;
        assertEquals(0, repeatition);
    }
}