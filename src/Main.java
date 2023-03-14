public class Main {
    public static void main(String[] args) {
        BlumBlumShub blumBlumShub = new BlumBlumShub(169999, 271111, 20000);
        blumBlumShub.printAll();

        System.out.println("\n");
        System.out.println(blumBlumShub.singleBitTestFor20000Bits());
        System.out.println(blumBlumShub.seriesTest());
        System.out.println(blumBlumShub.longSerialTest());
        System.out.println(blumBlumShub.pokerTest());
    }



}