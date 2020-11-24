package Project01TheBit;
//test for all the methods of a longword
public class longword_test {
    private longword_test(){}
    static void runTests(){
        System.out.println("Starting longword tests:");
        String fromString;
        longword test = new longword();
        longword test_2 = new longword();
        longword result = new longword();
        bit fromBit;
        bit on = new bit();
        bit off = new bit();
        on.set(1);
        off.set(0);
        
        System.out.println("Starting toString() test");
        test.set(2147483647); 
        test.setBit(0, on);
        fromString = test.toString();
        System.out.println("Expected: 32 bit string of all 1's");
        System.out.println(fromString + "\n\n");

        System.out.println("Starting get() bit 1 test:");
        test.set(67108864);
        fromBit = test.getBit(5);
        fromString = fromBit.toString();
        if (fromBit.getValue() == 1)
            System.out.println("Expected value was 1, recieved value was " + fromString + ". Success\n\n");
        else
            System.out.println("Expected value was 1, recieved value was " + fromString + ". Failed\n\n");

        System.out.println("Starting get() bit 0 test:");
        test.set(0);
        fromBit = test.getBit(5);
        fromString = fromBit.toString();
        if (fromBit.getValue() == 0)
            System.out.println("Expected value was 0, recieved value was " + fromString + ". Success\n\n");
        else
            System.out.println("Expected value was 0, recieved value was " + fromString + ". Failed\n\n");
        
        System.out.println("Starting setBit() 1 test:");
        test.setBit(5, on);
        fromBit = test.getBit(5);
        fromString = fromBit.toString();
        if (fromBit.getValue() == 1)
            System.out.println("Expected value was 1, recieved value was " + fromString + ". Success\n\n");
        else
            System.out.println("Expected value was 1, recieved value was " + fromString + ". Failed\n\n");

        System.out.println("Starting setBit() bit 0 test:");
        test.setBit(5, off);
        fromBit = test.getBit(5);
        fromString = fromBit.toString();
        if (fromBit.getValue() == 0)
            System.out.println("Expected value was 0, recieved value was " + fromString + ". Success\n\n");
        else
            System.out.println("Expected value was 0, recieved value was " + fromString + ". Failed\n\n");

        System.out.println("Starting and() test:");
        test.set(269484032);
        // test.setBit(3, on);
        // test.setBit(11, on);
        test_2.set(2146435071);
        test_2.setBit(0, on);
        // test_2.setBit(11, off);
        result = test.and(test_2);
        fromString = result.toString();
        System.out.println("Expected string is only twentyninth (fourth from left) bit on (1)");
        System.out.println(fromString + "\n\n");

        System.out.println("Starting or() test:");
        test.set(269484032);
        // test.setBit(3, on);
        // test.setBit(11, on);
        test_2.set(2129657855);
        test_2.setBit(0, on);
        // test_2.setBit(7, off);
        // test_2.setBit(11, off);
        result = test.or(test_2);
        fromString = result.toString();
        System.out.println("Expected string is only twentyfith (eighth from left) bit off (0)");
        System.out.println(fromString + "\n\n");

        System.out.println("Starting xor() test:");
        test.set(269484032);
        // test.setBit(3, on);
        // test.setBit(11, on);
        test_2.set(2129657855);
        test_2.setBit(0, on);
        // test_2.setBit(7, off);
        // test_2.setBit(11, off);
        result = test.xor(test_2);
        fromString = result.toString();
        System.out.println("Expected string is twentyninth (fourth from left) and twentyfith (eighth from left) bits off (0)");
        System.out.println(fromString + "\n\n");

        System.out.println("Starting not() test:");
        test.set(269484032);
        // test.setBit(3, on);
        // test.setBit(11, on);
        result = test.not();
        fromString = result.toString();
        System.out.println("Expected string is twentyninth (fourth from left) and twentyfirst (twelfth from left) bits off (0)");
        System.out.println(fromString + "\n\n");

        System.out.println("Starting right shift (8) test:");
        test.set(1798020533);
        // test.setBit(0, off);
        // test.setBit(3, off);
        // test.setBit(5, off);
        // test.setBit(8, off);
        // test.setBit(9, off);
        // test.setBit(11, off);
        // test.setBit(13, off);
        // test.setBit(17, off);
        // test.setBit(18, off);
        // test.setBit(22, off);
        // test.setBit(25, off);
        // test.setBit(28, off);
        // test.setBit(30, off);
        // test.setBit(31, off); // 0110 1011 0010 1011 1001 1101 1011 0100
        result = test.rightShift(8);
        System.out.println("Expected value is 0000 0000 0110 1011 0010 1011 1001 1101");
        fromString = result.toString();
        System.out.println(fromString + "\n\n");

        System.out.println("Starting left shift (8) test:");
        test.set(1798020532);
        // test.setBit(0, off);
        // test.setBit(3, off);
        // test.setBit(5, off);
        // test.setBit(8, off);
        // test.setBit(9, off);
        // test.setBit(11, off);
        // test.setBit(13, off);
        // test.setBit(17, off);
        // test.setBit(18, off);
        // test.setBit(22, off);
        // test.setBit(25, off);
        // test.setBit(28, off);
        // test.setBit(30, off);
        // test.setBit(31, off); // 0110 1011 0010 1011 1001 1101 1011 0100 //1110 0111 0110 1101 0000 0000 0000 0000
        result = test.leftShift(8);
        System.out.println("Expected value is 0010 1011 1001 1101 1011 0100 0000 0000");
        fromString = result.toString();
        System.out.println(fromString + "\n\n");
        
        System.out.println("Starting get unsigned test:");
        test.set(5);
        // test.setBit(31, on);
        // test.setBit(29, on);
        test_2.set(2147483647);
        test_2.setBit(0, on);
        long long_sum;
        long long_sum_2;
        long_sum = test.getUnsigned();
        long_sum_2 = test_2.getUnsigned();
        System.out.println("Expected values are 5 and 4,294,967,295:");
        System.out.printf("Values are %d and %d%n%n", long_sum, long_sum_2);
        
        System.out.println("starting get signed test:");
        test.set(-7);
        // test.setBit(30, off);
        // test.setBit(29, off); //-7
        test_2.set(5);
        // test_2.setBit(31, on);
        // test_2.setBit(29, on);
        int sum;
        int sum_2;
        sum = test.getSigned();
        sum_2 = test_2.getSigned();
        System.out.println("Expected values are -7 and 5:");
        System.out.printf("Values are %d and %d%n%n", sum, sum_2);
        
        System.out.println("Starting copy() test");
        test.set(1798020532);
        // test.setBit(0, off);
        // test.setBit(3, off);
        // test.setBit(5, off);
        // test.setBit(8, off);
        // test.setBit(9, off);
        // test.setBit(11, off);
        // test.setBit(13, off);
        // test.setBit(17, off);
        // test.setBit(18, off);
        // test.setBit(22, off);
        // test.setBit(25, off);
        // test.setBit(28, off);
        // test.setBit(30, off);
        // test.setBit(31, off);
        result.copy(test);
        System.out.println("Expected value is 0110 1011 0010 1011 1001 1101 1011 0100");
        fromString = result.toString();
        System.out.println(fromString + "\n\n");
        
        test.set(15);
        System.out.println("Expect 0000 0000 0000 0000 0000 0000 0000 1111");
        fromString = test.toString();
        System.out.println(fromString + "\n\n");

        System.out.println("longword testing complete.\n\n");
    }
    
}
