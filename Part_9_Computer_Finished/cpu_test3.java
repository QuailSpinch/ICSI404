package Project01TheBit;
/*Final test for computer, testing the stack. Output should be
R0 = 3, R1 = 4, R2 = 4, R3 = 3, R4 = 7, R5 = 7, R15 = 0
*/
public class cpu_test3 {

    public static void runTests()
    {
        //This will run the example from the assignment, test case 1
        String main = "JUMP 8"; //L0 Jumps to Start of main
        //add — starts at Line 1 Stack: 3 4 RN
        String popadr = "POP R15"; //L1 pops address of return to R15; Stack: 3 4
        String pop1 = "POP R2"; //L2 Stack: 3
        String pop2 = "POP R3"; //L3 Stack: empty
        String add = "ADD R2 R3 R4"; //L4
        String push1 = "PUSH R4"; //L5 Stack: 7
        String pushRN = "PUSH R15"; //L6 Stack: 7 RN
        String rtrn = "RTRN"; //L7 Stack: 7

        //main - Stack is empty
        String move1 = "MOVE R0 3"; //L8
        String move2 = "MOVE R1 4"; //L9
        String push2 = "PUSH R0"; //L10 Stack: 3
        String push3 = "PUSH R1"; //L11 Stack: 3 4
        String callAdd = "CALL 1"; //L12 Stack: 3 4 RN
        //Return from calling add; Stack: 7
        String pop3 = "POP R5"; //L13 Stack: Empty

        String reg = "INTR 0"; //L14 Print registers
        String mem = "INTR 1"; //L15 Print instructions
        String halt = "HALT"; //L16 Stop Computer

        String[] instructions = {main, popadr, pop1, pop2, add, push1, pushRN, rtrn,
            move1, move2, push2, push3, callAdd, pop3, reg, mem, halt};
        
        String[] load;
        load = Assembler.assemble(instructions);
        computer cpu = new computer();
        cpu.preload(load);
        cpu.run();

        //Test Case 2
        //R0 = 4, R1 = 8, R2 = 5, R3 = 5, R4 = 8, R5 = 4, R6 = 40
        //R7 = 160, R8 = 160, R15 = 0
        String main2 = "Jump 10"; //L0 Jumps to main
        //Volume of cube/rectangular prism //Stack: 4 8 5 RN
        String popadr2 = "POP R15"; //L1 Stack: 4 8 5
        String pop21 = "POP R3"; //L2 Stack: 4 8
        String pop22 = "POP R4"; //L3 Stack: 4
        String pop23 = "POP R5"; //L4 Stack: -
        String mul1 = "MUL R3 R4 R6"; //L5 Width x Height
        String mul2 = "MUL R5 R6 R7"; // L6 (Width x Height) x Length
        String push21 = "PUSH R7"; //L7 Stack: 160
        String pushRN2 = "PUSH R15"; //L8 Stack: 160 RN
        String rtrn2 = "RTRN"; //L9 Stack: 160
        //Main
        String move21 = "MOVE R0 4"; //L10 Length Stack: -
        String move22 = "MOVE R1 8"; //L11 Width
        String move23 = "MOVE R2 5"; //L12 Height
        String push22 = "PUSH R0"; //L13 Stack: 4
        String push23 = "PUSH R1"; //L14 Stack: 4 8
        String push24 = "PUSH R2"; //L15 Stack 4 8 5
        String callVol = "CALL 1"; //L16 Stack: 4 8 5 RN
        //Return from calling volume; Stack: 160
        String pop24 = "POP R8";//L17 Stack: -
        //Use reg, mem, and halt from above

        String[] instructions2 = {main2, popadr2, pop21, pop22, pop23, mul1, mul2, push21,
            pushRN2, rtrn2, move21, move22, move23, push22, push23, push24, callVol,
            pop24, reg, mem, halt};
        
        String[] load2;
        load2 = Assembler.assemble(instructions2);
        computer cpu2 = new computer();
        cpu2.preload(load2);
        cpu2.run();
    }
}
