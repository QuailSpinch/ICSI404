package Project01TheBit;

public class rippleAdder_test {
    private rippleAdder_test(){}

    static void runTests()
    {
        longword test = new longword();
        longword test_2 = new longword();
        //longword result = new longword();
        bit off = new bit();
        bit on = new bit();
        off.clear();
        on.set();

        System.out.println("Starting Ripple Adder Tests:");
        System.out.println("Starting addition tests");
        //test 1 (minimal carry)
        test.set(0);
        test_2.set(0);

        // test.setBit(16, on);
        // test.setBit(19, on);
        // test.setBit(21, on);
        // test.setBit(22, on);
        // test.setBit(24, on);
        // test.setBit(26, on);
        // test.setBit(27, on); 
        test.set(38576); //0000 0000 0000 0000 1001 0110 1011 0000 = 38576

        // test_2.setBit(10, on);
        // test_2.setBit(13, on);
        // test_2.setBit(16, on);
        // test_2.setBit(19, on);
        // test_2.setBit(25, on);
        // test_2.setBit(28, on);
        // test_2.setBit(29, on);
        // test_2.setBit(30, on);
        // test_2.setBit(31, on); 
        test_2.set(2396239); // 0000 0000 0010 0100 1001 0000 0100 1111 = 2396239

        longword result = rippleAdder.add(test, test_2);
        System.out.println("Adding 38576 and 2396239:");
        System.out.println(test.getSigned() + " + " + test_2.getSigned() + " = " + result.getSigned() + "\n\n");

        //test 2(more carry)
        test.set(0);
        test_2.set(0);

        // test.setBit(25, on);
        // test.setBit(27, on);
        // test.setBit(28, on);
        // test.setBit(29, on); 
        test.set(92); // 0000 0000 0000 0000 0000 0000 0101 1100 = 92

        // test_2.setBit(24, on);
        // test_2.setBit(25, on);
        // test_2.setBit(26, on);
        // test_2.setBit(28, on);
        // test_2.setBit(29, on);
        // test_2.setBit(30, on); 
        test_2.set(238); // 0000 0000 0000 0000 0000 0000 1110 1110 = 238

        result = rippleAdder.add(test, test_2);
        System.out.println("Adding 92 and 238:");
        System.out.println(test.getSigned() + " + " + test_2.getSigned() + " = " + result.getSigned() + "\n\n");


        System.out.println("Starting subtraction tests");
        //test 1
        test.set(0);
        test_2.set(0);
        
        // test.setBit(16, on);
        // test.setBit(19, on);
        // test.setBit(21, on);
        // test.setBit(22, on);
        // test.setBit(24, on);
        // test.setBit(26, on);
        // test.setBit(27, on); 
        test.set(38576); //0000 0000 0000 0000 1001 0110 1011 0000 = 38576

        // test_2.setBit(10, on);
        // test_2.setBit(13, on);
        // test_2.setBit(16, on);
        // test_2.setBit(19, on);
        // test_2.setBit(25, on);
        // test_2.setBit(28, on);
        // test_2.setBit(29, on);
        // test_2.setBit(30, on);
        // test_2.setBit(31, on); 
        test_2.set(2396239); // 0000 0000 0010 0100 1001 0000 0100 1111 = 2396239

        result = rippleAdder.subtract(test_2, test);
        System.out.println("Subtracting 38576 from 2396239:");
        System.out.println(test_2.getSigned() + " - " + test.getSigned() + " = " + result.getSigned() + "\n\n");

        //test 2
        test.set(0);
        test_2.set(0);

        // test.setBit(25, on);
        // test.setBit(27, on);
        // test.setBit(28, on);
        // test.setBit(29, on);
        test.set(92); // 0000 0000 0000 0000 0000 0000 0101 1100 = 92

        // test_2.setBit(24, on);
        // test_2.setBit(25, on);
        // test_2.setBit(26, on);
        // test_2.setBit(28, on);
        // test_2.setBit(29, on);
        // test_2.setBit(30, on); 
        test_2.set(238);// 0000 0000 0000 0000 0000 0000 1110 1110 = 238

        result = rippleAdder.subtract(test_2, test);
        System.out.println("subtracting 92 from 238:");
        System.out.println(test_2.getSigned() + " - " + test.getSigned() + " = " + result.getSigned() + "\n\n");

        //test 3 (going negative)
        test.set(0);
        test_2.set(0);

        // test.setBit(25, on);
        // test.setBit(27, on);
        // test.setBit(28, on);
        // test.setBit(29, on); 
        test.set(92); // 0000 0000 0000 0000 0000 0000 0101 1100 = 92

        // test_2.setBit(24, on);
        // test_2.setBit(25, on);
        // test_2.setBit(26, on);
        // test_2.setBit(28, on);
        // test_2.setBit(29, on);
        // test_2.setBit(30, on);
        test_2.set(238); // 0000 0000 0000 0000 0000 0000 1110 1110 = 238

        result = rippleAdder.subtract(test, test_2);
        System.out.println("subtracting 238 from 92:");
        System.out.println(test.getSigned() + " - " + test_2.getSigned() + " = " + result.getSigned() + "\n\n");
    }
}
