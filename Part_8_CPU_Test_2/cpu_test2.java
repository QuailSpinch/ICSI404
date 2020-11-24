package Project01TheBit;

public class cpu_test2 
{
    public static void runTests()
    {
        /* A note on Branching:
        When branching, the number of lines to move should be
        one LESS THAN the number of lines counted between origin
        line and designation line. This offsets the fact that when
        the branch line is read the pc incrementer moves to the 
        next instruction. So moving 6 lines should be written as
        <branch> 5. Going back 3 lines (-3) should be written as
        <branch> -4.
        */
        String move0 = "MOVE R0 0"; //L0 R0 = count - Should end at 312
        String move1 = "MOVE R1 0"; //L1 R1 = i - Should end at 8
        String move2 = "MOVE R2 1"; //L2 R2 = increment - Should stay 1
        String move3 = "MOVE R3 8"; //L3 R3 = limit - Should stay 8
        String move4 = "MOVE R4 39"; //L4 R4 = value to add to count while looping - Should stay 39
        String comp1 = "COMP R3 R1"; //L5 R3 vs R1 
        String brnc1 = "BNGR 1"; //L6 R3 > R1 move forward 2 - Branch forward
        String brnc2 = "BNEQ 3"; //L7 R3 = R1 move forward 5 
        //brnc1
        String add1 = "ADD R0 R4 R0"; //L8 count += value
        String incr = "ADD R1 R2 R1"; //L9 R1++ (i++)
        String loop = "JUMP 5"; //L10 loop back to top (Line 5) & Test JUMP
        //End brnc1 - Start brnc2
        String move5 = "MOVE R5 -5"; //L11 R5 = temp - Should end at 1
        String move6 = "Move R6 0"; //L12 R6 = freeze - Should stay 0
        //brnc3 when branch
        String add2 = "ADD R5 R2 R5"; //L13 R5++ (Temp++)
        String comp2 = "COMP R6 R5"; //L14 R6 vs R5
        String brnc3 = "BNGREQ -3"; //L15 R6 >= R5 move back 2 - Branch backward
        String brnc4 = "BNEQ 1"; //L16 R6 != R5 move forward 2 
        String move7 = "MOVE R7 8739"; //L17 Shouldn't happen
        String move8 = "MOVE R8 10"; //L18 Should happen
        String move9 = "Move R9 39"; //L19 Should happen

        //add some testing for BNLES
        String move10 = "MOVE R10 12"; //L20
        String add3 = "ADD R10 R2 R10"; // L21, R10++
        String comp3 = "COMP R10 R9"; //L22, starts as less than
        String brnc5 = "BNLES -3"; //L23, Loop until R10 = R9 (39)

        String reg = "INTR 0"; //L24 Print registers
        String mem = "INTR 1"; //L25 Print instructions
        String halt = "HALT"; //L26 Stop Computer

        String[] instructions = {move0, move1, move2, move3, move4, comp1, brnc1, brnc2,
            add1, incr, loop, move5, move6, add2, comp2, brnc3, brnc4, move7, move8, move9,
            move10, add3, comp3, brnc5, reg, mem, halt};

        String[] load;
        load = Assembler.assemble(instructions);
        computer cpu = new computer();
        cpu.preload(load);
        cpu.run();
    }
}
