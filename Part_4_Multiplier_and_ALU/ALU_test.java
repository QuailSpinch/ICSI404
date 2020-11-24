package Project01TheBit;
public class ALU_test {
    private ALU_test(){}

    public static void runTests()
    {
        longword test = new longword();
        longword test_2 = new longword();
        longword result = new longword();
        bit on = new bit();
        bit off = new bit();
        bit[] operation = new bit[4];
        on.set();
        off.clear();

        System.out.println("Starting ALU Tests:");

        //AND test
        operation[0] = on;
        operation[1] = off;
        operation[2] = off;
        operation[3] = off; //1000 AND
        test.set(0);
        test_2.set(0);
        // test.setBit(3, on);
        // test.setBit(7, on);
        // test.setBit(9, on);
        // test.setBit(15, on);
        // test.setBit(16, on);
        // test.setBit(19, on);
        // test.setBit(22, on);
        // test.setBit(25, on);
        // test.setBit(29, on);
        // test.setBit(30, on);
        test.set(578916934); // 0001 0001 0100 0001 1001 0010 0100 0110
        // test_2.setBit(3, on);
        // test_2.setBit(4, on);
        // test_2.setBit(6, on);
        // test_2.setBit(9, on);
        // test_2.setBit(13, on);
        // test_2.setBit(14, on);
        // test_2.setBit(16, on);
        // test_2.setBit(20, on);
        // test_2.setBit(23, on);
        // test_2.setBit(25, on);
        // test_2.setBit(28, on);
        // test_2.setBit(30, on);
        // test_2.setBit(31, on); 
        test_2.set(440830283);// 0001 1010 0100 0110 1000 1001 0100 1011
        System.out.println("Starting ALU test for AND functions:");
        result = ALU.doOp(operation, test, test_2);
        System.out.println(test.toString() +" AND \n" + test_2.toString() + "Result is: ");
        System.out.println(result.toString());

        //OR Test
        operation[3] = on; // 1001 OR
        System.out.println("\nStarting ALU test for OR functions:");
        result = ALU.doOp(operation, test, test_2);
        System.out.println(test.toString() +" OR \n" + test_2.toString() + "Result is: ");
        System.out.println(result.toString());

        // XOR Test
        operation[2] = on;
        operation[3] = off; // 1010 XOR
        System.out.println("\nStarting ALU test for XOR functions:");
        result = ALU.doOp(operation, test, test_2);
        System.out.println(test.toString() +" XOR \n" + test_2.toString() + "Result is: ");
        System.out.println(result.toString());

        // NOT Test
        operation[3] = on; // 1011 NOT
        System.out.println("\nStarting ALU test for NOT functions:");
        result = ALU.doOp(operation, test, test_2);
        System.out.println(test.toString() + "NOT result is: ");
        System.out.println(result.toString());

        // Add Test
        // test_2.setBit(3, off);
        // test_2.setBit(4, off); 
        test_2.set(38177099); // 0000 0010 0100 0110 1000 1001 0100 1011
        operation[1] = on;
        operation[3] = off; // 1110 add
        System.out.println("\nStarting ALU test for add operation:");
        result = ALU.doOp(operation, test, test_2);
        System.out.println(test.toString() +" + \n" + test_2.toString() + "Result is: ");
        System.out.println(result.toString());
        System.out.println(test.getSigned() + " + " + test_2.getSigned() + " = " + result.getSigned());

        // Subtract Test
        operation[3] = on; // 1111 sub
        System.out.println("\nStarting ALU test for subtract operation:");
        result = ALU.doOp(operation, test, test_2);
        System.out.println(test.toString() +" - \n" + test_2.toString() + "Result is: ");
        System.out.println(result.toString());
        System.out.println(test.getSigned() + " - " + test_2.getSigned() + " = " + result.getSigned());

        // Multiplication Test
        test_2.set(0);
        // test_2.setBit(28, on);
        // test_2.setBit(30, on); 
        test_2.set(10); // 0000 0000 0000 0000 0000 0000 0000 1010 == 10
        // test.setBit(3, off);
        // test.setBit(7, off);
        // test.setBit(31, on);
        test.set(4297286); // 0000 0000 0100 0001 1001 0010 0100 0110
        operation[0] = off; // 0111 multiply
        System.out.println("\nStarting ALU test for multiply operation:");
        result = ALU.doOp(operation, test, test_2);
        System.out.println(test.toString() +" * \n" + test_2.toString() + "Result is: ");
        System.out.println(result.toString());
        System.out.println(test.getSigned() + " * " + test_2.getSigned() + " = " + result.getSigned());

        //Left Shift Test
        // test.setBit(3, on);
        // test.setBit(7, on); 
        test.set(289509959); // 0001 0001 0100 0001 1001 0010 0100 0111
        operation[0] = on;
        operation[2] = off;
        operation[3] = off; // 1100 Left Shift
        System.out.println("\nStarting ALU test for left shift operation:");
        result = ALU.doOp(operation, test, test_2);
        System.out.println(test.toString() +" << " + test_2.getUnsigned() + " Result is: ");
        System.out.println(result.toString());

        //Right Shift Test
        test.set(1363251783);
        test.setBit(0, on);
        // test.setBit(1, on);
        operation[3] = on; // 1101 Right Shift
        System.out.println("\nStarting ALU test for left shift operation:");
        result = ALU.doOp(operation, test, test_2);
        System.out.println(test.toString() +" >> " + test_2.getUnsigned() + " Result is: ");
        System.out.println(result.toString());

        System.out.println("\n ALU Testing Complete");
    }
    
}
