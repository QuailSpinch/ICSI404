package Project01TheBit;

public class cpu_test1 {

    public static void runTests()
    {
        computer comp1 = new computer();
        System.out.println("Starting test 1, math tests:");
        String r0_22 = "0001 0000 0001 0110"; //Move R0 22
        String r1_44 = "0001 0001 0010 1100"; //Move R1 44
        String r2_Neg22 = "0001 0010 1110 1010"; // Move R2 -22
        String add_r0_r1_r3 = "1110 0000 0001 0011"; //Add R0 R1 R3
        String sub_r1_r0_r4 = "1111 0001 0000 0100"; // Sub R1 R0 R4 
        String mul_r0_r1_r5 = "0111 0000 0001 0101"; // Mul R0 R1 R5
        String add_r0_r2_r6 = "1110 0000 0010 0110"; // Add R0 R2 R6
        String sub_r1_r2_r7 = "1111 0001 0010 0111"; // Sub R1 R2 R7
        String mul_r0_r2_r8 = "0111 0000 0010 1000"; // Mul R0 R2 R8
        String registers1 = "0010 0000 0000 0000"; // interrupt print registers
        String allMem1 = "0010 0000 0000 0010"; // interrupt print all 1024 bytes
        String halt1 = "0000 0000 0000 0000"; //halt computer loop
        String[] prog1 = {r0_22, r1_44, r2_Neg22, add_r0_r1_r3, sub_r1_r0_r4, mul_r0_r1_r5, add_r0_r2_r6,
            sub_r1_r2_r7, mul_r0_r2_r8, registers1, allMem1, halt1};

        comp1.preload(prog1);
        comp1.run();

        computer comp2 = new computer();
        System.out.println("\nStarting test 2, Bit operation tests:");
        String r0_127 = "0001 0000 0111 1111"; // Move R0 127
        String r1_127 = "0001 0001 0111 1111"; // Move R1 127
        String r2_8 = "0001 0010 0000 1000"; // move R2 8
        String mul_r0_r1_r3 = "0111 0000 0001 0011"; // Mul R0 R1 R3
        String mul_r0_r3_r4 = "0111 0000 0011 0100";//MUL R0 R2 R4=>2048383=>0000 0000 0001 1111 0100 0001 0111 1111
        String and_r4_r3_r5 = "1000 0100 0011 0101"; // AND R4 R3 R5
        String or_r4_r5_r6 = "1001 0100 0101 0110"; // OR R4 R5 R6
        String xor_r4_r3_r7 = "1010 0100 0011 0111"; // XOR R4 R3 R7
        String not_r4_r8 = "1011 0100 0000 1000"; // NOT R4 R8 *Second Register doesn't matter for NOT
        String ls_r4_r2_r9 = "1100 0100 0010 1001"; // LS R4 R2 R9 (left shift R4 by R2, store in R9)
        String rs_r4_r4_r10 = "1101 0100 0010 1010"; // RS R4 R2 R10 (right shift as above)
        String registers2 = "0010 0000 0000 0000"; 
        String allMem2 = "0010 0000 0000 0010";
        String halt2 = "0000 0000 0000 0000";
        String[] prog2 = {r0_127, r1_127, r2_8, mul_r0_r1_r3, mul_r0_r3_r4, and_r4_r3_r5, or_r4_r5_r6,
            xor_r4_r3_r7, not_r4_r8, ls_r4_r2_r9, rs_r4_r4_r10, registers2, allMem2, halt2};

        comp2.preload(prog2);
        comp2.run();
    }
    
}
