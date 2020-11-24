package Project01TheBit;
//This file tests the various cases of multiplication
public class multiplier_test {
    private multiplier_test(){}

    public static void runTests(){

        System.out.println("Starting multiplier tests: ");
        longword test = new longword();
        longword test_2 = new longword();
        longword result = new longword();
        bit off = new bit();
        bit on = new bit();
        off.clear();
        on.set();
        test.set(0);
        test_2.set(0);
        // test.setBit(31, on);
        // test.setBit(30, on);
        // test.setBit(28, on);
        // test.setBit(27, on);
        // test.setBit(24, on);
        test.set(155); // 0000 0000 0000 0000 0000 0000 1001 1011 == 155
        // test_2.setBit(30, on);
        // test_2.setBit(29, on);
        // test_2.setBit(27, on);
        // test_2.setBit(25, on); 
        test_2.set(86); // 0000 0000 0000 0000 0000 0000 0101 0110 == 86

        System.out.println("Testing two small positive values:");
        result = multiplier.multiply(test, test_2);
        System.out.println(test.getUnsigned() + " * " + test_2.getUnsigned() + " = " + result.getUnsigned() + "\n");

        // test.setBit(20, on);
        // test.setBit(18, on);
        // test.setBit(17, on);
        // test.setBit(14, on);
        // test.setBit(12, on);
        // test.setBit(11, on);
        // test.setBit(10, on);
        // test.setBit(8, on);
        // test.setBit(7, on);
        // test.setBit(5, on);
        test.set(364538011); // 0001 0101 1011 1010 0110 1000 1001 1011 == 364,538,011
        // test_2.set(0);
        // test_2.setBit(26, on);
        // test_2.setBit(28, on);
        // test_2.setBit(29, on);
        test_2.set(44); // 0000 0000 0000 0000 0000 0000 0010 1100 == 44

        System.out.println("Testing result just under unsigned limit:");
        result = multiplier.multiply(test, test_2);
        System.out.println(test.getUnsigned() + " * " + test_2.getUnsigned() + " = " + result.getUnsigned() + "\n");

        // test_2.setBit(20, on);
        // test_2.setBit(31, on); 
        test_2.set(2093); // 0000 0000 0000 0000 0000 1000 0010 1101 == 2093
        System.out.println("Testing over unsigned limit:");
        result = multiplier.multiply(test, test_2);
        System.out.println(test.getUnsigned() + " * " + test_2.getUnsigned() + " = " + result.getUnsigned());
        System.out.println("As expected, returns an inccorect number.\n");

        // test.set(1);
        // test.setBit(30, off);
        // test.setBit(28, off);
        // test.setBit(27, off);
        // test.setBit(24, off); 
        test.set(-155); // 1111 1111 1111 1111 1111 1111 0110 0101 == -155
        // test_2.set(1);
        // test_2.setBit(31, off);
        // test_2.setBit(29, off);
        // test_2.setBit(27, off);
        // test_2.setBit(25, off);
        test_2.set(-86);// 1111 1111 1111 1111 1111 1111 1010 1010 == -86

        System.out.println("Testing negative numbers:");
        result = multiplier.multiply(test, test_2);
        System.out.println(test.getSigned() + " * " + test_2.getSigned() + " = " + result.getSigned() + "\n");

        // test_2.set(0);
        // test_2.setBit(30, on);
        // test_2.setBit(29, on);
        // test_2.setBit(27, on);
        // test_2.setBit(25, on); 
        test_2.set(86); // 0000 0000 0000 0000 0000 0000 0101 0110 == 86

        System.out.println("Testing positive times negative:");
        result = multiplier.multiply(test, test_2);
        System.out.println(test.getSigned() + " * " + test_2.getSigned() + " = " + result.getSigned() + "\n");

        System.out.println("Multiplier Testing Complete\n");

    }
}
