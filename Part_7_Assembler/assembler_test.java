package Project01TheBit;

public class assembler_test {
    
    //these are the same two sets of tests from cpu_test to show translationworked properly
    public static void runTests(){
        String move1 = "MOVE R0 22";
        String move2 = "MOVE R1 44";
        String move3 = "MOVE R2 -22";
        String add1 = "ADD R0 R1 R3";
        String sub1 = "SUB R1 R0 R4";
        String mul1 = "MUL R0 R1 R5";
        String add2 = "ADD R0 R2 R6";
        String sub2 = "SUB R1 R2 R7";
        String mul2 = "MUL R0 R2 R8";
        String reg = "INTR 0";
        String mem = "INTR 1";
        String halt = "HALT";

        String[] instructions = {move1, move2, move3, add1, sub1, mul1, add2, sub2, mul2,
            reg, mem, halt};
        String[] load;
        load = Assembler.assemble(instructions);
        computer cpu = new computer();

        cpu.preload(load);
        cpu.run();

        String move4 = "MOVE R0 127";
        String move5 = "MOVE R1 127";
        String move6 = "MOVE R2 8";
        String mul3 = "MUL R0 R1 R3";
        String mul4 = "MUL R0 R2 R4";
        String and = "AND R4 R3 R5";
        String or = "OR R4 R5 R6";
        String xor = "XOR R4 R3 R7";
        String not = "NOT R4 R8";
        String ls = "LS R4 R2 R9";
        String rs = "RS R4 R2 R10";

        String[] instructions2 = {move4, move5, move6, mul3, mul4, and, or, xor, not, ls,
            rs, reg, mem, halt};
        String[] load2;
        load2 = Assembler.assemble(instructions2);
        computer cpu2 = new computer();

        cpu2.preload(load2);
        cpu2.run();
    }

}
