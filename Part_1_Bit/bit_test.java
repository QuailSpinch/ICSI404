package Project01TheBit;
/*
This file contains a method that tests all the methods of the bit class
*/
public class bit_test {
    private bit_test(){
    }
    static void runTests(){ //tests all methods of bit.java
        String fromString;
        bit test = new bit();
        bit compare = new bit();
        bit result;
        test.set(1);

        System.out.println("Starting Bit tests:\n");
        //getValue test
        System.out.println("Starting getValue() test:");
        if (test.getValue() == 1){
            fromString = test.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Success.%n%n", fromString);
        }
        else {
            fromString = test.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }

        //set(int) zero test
        System.out.println("Starting set(int) zero test:");
        test.set(0);
        if (test.getValue() == 0){
            fromString = test.toString();
            System.out.printf("Bit set to %s, expected 0. Success.%n%n", fromString);
        }
        else {
            fromString = test.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }

        //set(int) one test
        System.out.println("Starting set(int) one test:");
        test.set(1);
        if (test.getValue() == 1){
            fromString = test.toString();
            System.out.printf("Bit set to %s, expected 1. Success.%n%n", fromString);
        }
        else {
            fromString = test.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }

        //set(int) out of range ( > 1)
        System.out.println("Starting set(int) above one (2) test:");
        test.set(2);
        if (test.getValue() > 1){
            fromString = test.toString();
            System.out.printf("Value is %s, out of range. Failed.%n%n", fromString);
        }
        else {
            fromString = test.toString();
            System.out.printf("Value is %s, expected value was 0 or 1. Success.%n%n", fromString);
        }

        //set(int) out of range ( < 0)
        System.out.println("Starting set(int) below zero (-1) test:");
        test.set(-1);
        if (test.getValue() < 0){
            fromString = test.toString();
            System.out.printf("Value is %s, out of range. Failed.%n%n", fromString);
        }
        else {
            fromString = test.toString();
            System.out.printf("Value is %s, expected value was 0 or 1. Success.%n%n", fromString);
        }

        //toggle zero to one
        System.out.println("Starting toggle() zero to one test:");
        test.set(0);
        test.toggle();
        if (test.getValue() == 1)
            System.out.println("Bit toggled to 1, expected value was 1. Success.\n\n");
        else {
            fromString = test.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }

        //toggle one to zero
        System.out.println("Starting toggel() one to zero test:");
        test.set(1);
        test.toggle();
        if (test.getValue() == 0)
            System.out.println("Bit toggled to 0, expected value 0. Success.\n\n");
        else {
            fromString = test.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }

        //set() test
        System.out.println("Starting set() test:");
        test.set(0);
        test.set();
        if (test.getValue() == 1)
            System.out.println("Bit set to 1, expected value was 1. Success.\n\n");
        else {
            fromString = test.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }

        //clear() test
        System.out.println("Starting clear() test:");
        test.set(1);
        test.clear();
        if (test.getValue() == 0)
            System.out.println("Bit cleared to 0, expected value 0. Success.\n\n");
        else {
            fromString = test.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }

        // AND tests
        System.out.println("Starting AND() tests:");
        //0 AND 0
        System.out.println("0 AND 0 test:");
        test.set(0);
        compare.set(0);
        result = test.and(compare);
        if (result.getValue() == 0){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }
        //0 AND 1
        System.out.println("0 AND 1 test:");
        test.set(0);
        compare.set(1);
        result = test.and(compare);
        if (result.getValue() == 0){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }
        //1 AND 0
        System.out.println("1 AND 0 test:");
        test.set(1);
        compare.set(0);
        result = test.and(compare);
        if (result.getValue() == 0){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }
        //1 AND 1
        System.out.println("1 AND 1 test:");
        test.set(1);
        compare.set(1);
        result = test.and(compare);
        if (result.getValue() == 1){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }

        //OR tests
        System.out.println("Starting OR() tests:");
        //0 OR 0
        System.out.println("0 OR 0 test:");
        test.set(0);
        compare.set(0);
        result = test.or(compare);
        if (result.getValue() == 0){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }
        //0 OR 1
        System.out.println("0 OR 1 test:");
        test.set(0);
        compare.set(1);
        result = test.or(compare);
        if (result.getValue() == 1){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }
        //1 OR 0
        System.out.println("1 OR 0 test:");
        test.set(1);
        compare.set(0);
        result = test.or(compare);
        if (result.getValue() == 1){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }
        //1 OR 1
        System.out.println("1 OR 1 test:");
        test.set(1);
        compare.set(1);
        result = test.or(compare);
        if (result.getValue() == 1){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }

        //XOR tests
        System.out.println("Starting XOR() tests:");
        //0 OR 0
        System.out.println("0 XOR 0 test:");
        test.set(0);
        compare.set(0);
        result = test.xor(compare);
        if (result.getValue() == 0){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }
        //0 XOR 1
        System.out.println("0 OR 1 test:");
        test.set(0);
        compare.set(1);
        result = test.xor(compare);
        if (result.getValue() == 1){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }
        //1 XOR 0
        System.out.println("0 OR 1 test:");
        test.set(1);
        compare.set(0);
        result = test.xor(compare);
        if (result.getValue() == 1){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }
        //1 XOR 1
        System.out.println("0 OR 1 test:");
        test.set(1);
        compare.set(1);
        result = test.xor(compare);
        if (result.getValue() == 0){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }

        System.out.println("Starting NOT() tests:");
        //NOT one test
        System.out.println("NOT 1 test:");
        test.set(1);
        result = test.not();
        if (result.getValue() == 0){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 0. Failed.%n%n", fromString);
        }
        
        //NOT zero test
        System.out.println("NOT 0 test:");
        test.set(0);
        result = test.not();
        if (result.getValue() == 1){
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Success.%n%n", fromString);
        }
        else {
            fromString = result.toString();
            System.out.printf("Bit value returned %s, Expected value was 1. Failed.%n%n", fromString);
        }

        System.out.println("Bit tests completed.\n\n");
    }
}
