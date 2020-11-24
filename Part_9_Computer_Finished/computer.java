package Project01TheBit;
//the begining framework of the computer
public class computer 
{
    private memory mem = new memory();
    bit halted = new bit();
    longword pc = new longword(); 
    longword[] registers = new longword[16];
    longword op1 = new longword();
    longword op2 = new longword();
    longword value = new longword();
    bit[] opCode = new bit[4];
    longword result = new longword();
    longword currentInstruction = new longword();
    bit[] comparison = new bit[2]; //bit 1 is on if equal, bit 0 is on if greater. i.e. equal is 01. less than is 00.
    bit[] compare = new bit[2];
    bit branch = new bit();
    longword sp = new longword(); //stack pointer
    bit[] stackOp = new bit[2];

    public computer()
    {
        pc.set(0);
        sp.set(1020);
        halted.set(1);
        int i;
        for (i = 0; i < 16; i ++)
        {
            registers[i] = new longword();
        }
        for (i = 0; i < 4; i++)
        {
            opCode[i] = new bit();
        }
        for (i = 0; i < 2; i++)
        {
            comparison[i] = new bit();
            compare[i] = new bit();
            stackOp[i] = new bit();
        }
    }

    public void fetch()
    {
        int bytes = 2;
        longword increment = new longword();
        currentInstruction.copy(mem.read(pc));
        currentInstruction = currentInstruction.rightShift(16); // shifts so only the instruction is left, 16-31
        increment.set(bytes); //2 bytes = 16 bits = size of instruction
        pc.copy(rippleAdder.add(pc, increment));
    }

    public void decode()
    {
        longword mask = new longword();
        longword regInd = new longword(); //register index
        if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 0 && 
          currentInstruction.getBit(18).getValue() == 0 && 
          currentInstruction.getBit(19).getValue() == 0) // halting, don't need rest of instruction
        {
            //do nothing here
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 0 && 
          currentInstruction.getBit(18).getValue() == 0 && 
          currentInstruction.getBit(19).getValue() == 1) // move
        {
            //set target register
            mask.set(3840); //0000 0000 0000 0000 0000 1111 0000 0000
            regInd.copy((currentInstruction.and(mask))); // only has the op 1 segment of currentInstruction
            regInd = regInd.rightShift(8); // moves op1 to the first four bits for get signed
            op1.copy(regInd); //op1 holds register to move data to

            //set value for register
            mask.set(255); // 0000 0000 0000 0000 0000 0000 1111 1111
            regInd.copy((currentInstruction.and(mask))); // value to be assigned
            if (regInd.getBit(24).getValue() == 1) //sign extend negative
            {
                value.set(-1); // 1111 1111 1111 1111 1111 1111 1111 1111
            }
            else
            {
                value.set(0);
            }
            int i;
            for (i = 24; i < 32; i++)
            {
                value.setBit(i, regInd.getBit(i));
            }
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 0 && 
          currentInstruction.getBit(18).getValue() == 1 && currentInstruction.getBit(19).getValue() == 0) // interrupt
        {
            //do nothing here
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 0 && 
        currentInstruction.getBit(18).getValue() == 1 && currentInstruction.getBit(19).getValue() == 1) // jump
        {
            //nothing here
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 1 && 
        currentInstruction.getBit(18).getValue() == 0 && currentInstruction.getBit(19).getValue() == 0) //compare
        {
            //Register 1
            mask.set(240); // 0000 0000 0000 0000 0000 0000 1111 0000
            regInd.copy(currentInstruction.and(mask));
            regInd = regInd.rightShift(4);
            op1.copy(registers[regInd.getSigned()]);

            //Register 2
            mask.set(15); // 0000 0000 0000 0000 0000 0000 0000 1111
            regInd.copy((currentInstruction.and(mask))); //Doesn't need to be shifted
            op2.copy(registers[regInd.getSigned()]);
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 1 && 
        currentInstruction.getBit(18).getValue() == 0 && currentInstruction.getBit(19).getValue() == 1) //Branch
        {
            //condition
            mask.set(3072); //0000 0000 0000 0000 0000 1100 0000 0000
            regInd.copy(currentInstruction.and(mask)); //reusing regInd for ease
            regInd = regInd.rightShift(10); //condition in two right most bits
            compare[0].set(regInd.getBit(30).getValue());
            compare[1].set(regInd.getBit(31).getValue());

            //branch
            mask.set(1023); //0000 0000 0000 0000 0000 0011 1111 1111
            regInd.copy(currentInstruction.and(mask));
            if (regInd.getBit(22).getValue() == 1) //sign extend
                value.set(-1);
            else
                value.set(0);
            int i;
            for (i = 22; i < 32; i++)
            {
                value.setBit(i, regInd.getBit(i));
            }
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 1 && 
        currentInstruction.getBit(18).getValue() == 1 && currentInstruction.getBit(19).getValue() == 0) //Stack
        {
            //stack op
            stackOp[0] = currentInstruction.getBit(20);
            stackOp[1] = currentInstruction.getBit(21);

            if(stackOp[0].getValue() == 0) //push or pop, needs register
            {
                if (stackOp[1].getValue() == 0)//push, get val from register to push
                {
                    mask.set(15); //0000 0000 0000 0000 0000 0000 0000 1111
                    regInd.copy(currentInstruction.and(mask));
                    op1.copy(registers[regInd.getSigned()]);
                }
                else //pop, get register to pop to
                {
                    mask.set(15); //0000 0000 0000 0000 0000 0000 0000 1111
                    regInd.copy(currentInstruction.and(mask));
                    op1.copy(regInd);
                }
            }
            else 
            {
                if(stackOp[1].getValue() == 0) //10, CALL needs address value
                {
                    mask.set(2047); //last 12 bits
                    value = currentInstruction.and(mask);
                }
            }
        }

        else //for all ALU
        {
            //get op 1
            mask.set(3840); //0000 0000 0000 0000 0000 1111 0000 0000
            regInd.copy((currentInstruction.and(mask))); // only has the op 1 segment of currentInstruction
            regInd = regInd.rightShift(8); // moves op1 to the first four bits for get signed
            op1.copy(registers[regInd.getSigned()]); // gets register index, register longword,
                //puts longword from register into op1
            
            //get op 2
            mask.set(240); // 0000 0000 0000 0000 0000 0000 1111 0000
            regInd.copy((currentInstruction.and(mask)));
            regInd = regInd.rightShift(4);
            op2.copy(registers[regInd.getSigned()]);
        }
    }

    public void execute()
    {
        int i;

        for (i = 0; i < 4; i++)
        {
            opCode[i].set(currentInstruction.getBit((16 + i)).getValue()); // puts opcode bits into opCode[] for ALU
        }

        if (opCode[0].getValue() == 0 && opCode[1].getValue() == 0 && 
          opCode[2].getValue() == 0 && opCode[3].getValue() == 0)
        {
            halted.set(); //halt instruction
        }

        else if (opCode[0].getValue() == 0 && opCode[1].getValue() == 0 && 
          opCode[2].getValue() == 0 && opCode[3].getValue() == 1) // move
        {
            //do nothing here
        } 

        else if (opCode[0].getValue() == 0 && opCode[1].getValue() == 0 && 
          opCode[2].getValue() == 1 && opCode[3].getValue() == 0) // interrupt
        {
            String fromString;
            if (currentInstruction.getBit(30).getValue() == 0) // print registers
            {
                System.out.println("Printing registers:\n");
                for (i = 0; i < registers.length; i++)
                {
                    fromString = registers[i].toString();
                    System.out.printf("Register %d: %s = %d%n", i, fromString, registers[i].getSigned());
                }
            }

            else // print memory
            {
                longword pcReader = new longword();
                longword fourByte = new longword();
                longword increment = new longword();
                bit[] byte1 = new bit[8];
                for (i = 0; i < byte1.length; i++)
                    byte1[i] = new bit();
                pcReader.set(0);
                increment.set(1);
                int j;
                System.out.println("\nPrinting all bytes of memory:\n");
                for (i = 0; i < 1020; i++)
                {
                    fourByte.copy(mem.read(pcReader));
                    for (j = 0; j < 8; j++)
                    {
                        byte1[j].set(fourByte.getBit(j).getValue()); // gets bits of byte i
                    }
                    System.out.print("Byte " + i +": ");
                    for (j = 0; j < 8; j++)
                    {
                        System.out.print(byte1[j].getValue() + ", "); //print the 8 bits of byte
                    }
                    if (i % 4 == 3)
                        System.out.println(); //four bytes per line
                    pcReader = rippleAdder.add(pcReader, increment);
                }
                for (i = 0; i < 4; i++)
                {
                    for (j = 0; j < 8; j++)
                    {
                        byte1[j].set(fourByte.getBit(((i * 8) + j)).getValue()); // gets bits of byte i
                    }
                    System.out.print("Byte " + (i + 1020) +": ");
                    for (j = 0; j < 8; j++)
                    {
                        System.out.print(byte1[j].getValue() + ", "); //print the 8 bits of byte
                    }
                    if (i % 4 == 3)
                        System.out.println(); //four bytes per line
                }
            }
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 0 && 
        currentInstruction.getBit(18).getValue() == 1 && currentInstruction.getBit(19).getValue() == 1) //jump
        {
            //nothing here
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 1 && 
        currentInstruction.getBit(18).getValue() == 0 && currentInstruction.getBit(19).getValue() == 0) //compare
        {
            bit[] sub = new bit[4];
            for (i = 0; i < 4; i++)
                sub[i] = new bit();
            for (i = 0; i < 4; i++)
                sub[i].set(); // sub becomes 1111, opCode for subtract
            result.copy(ALU.doOp(sub, op1, op2));
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 1 && 
        currentInstruction.getBit(18).getValue() == 0 && currentInstruction.getBit(19).getValue() == 1) //Branch
        {
            bit[] mul = new bit[4];
            for (i = 0; i < 4; i++)
                mul[i] = new bit();
            for (i = 1; i < 4; i++)
                mul[i].set(); // 0111 - opcode for multiply
            longword size = new longword();
            size.set(2); //size of instruction
            value = ALU.doOp(mul, value, size); // adjust line value to instructional size value
            if (compare[0].getValue() == 0) 
            {
                if (compare[1].getValue() == 0) // 00 - Less Than
                {
                    if (comparison[0].getValue() == 0 && comparison[1].getValue() == 0) //Both must be off
                        branch.set(); //set to branch in store
                }
                else // 01 - Equal
                {
                    if (comparison[1].getValue() == 1) // index 1 is equal bit. If on, they are equal
                        branch.set();
                }
            }
            else 
            {
                if (compare[1].getValue() == 0) // 10 - Greater Than
                {
                    if (comparison[0].getValue() == 1) // index 1 is greater bit. If on, it greater than
                        branch.set();
                }
                else // 11 Greater Than Or Equal
                {
                    if (comparison[0].getValue() == 1 || comparison[1].getValue() ==1) // Either index 0 or 1 can be on
                        branch.set();
                }
            }
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 1 && 
        currentInstruction.getBit(18).getValue() == 1 && currentInstruction.getBit(19).getValue() == 0) //Stack
        {
            if (stackOp[0].getValue() == 1 && stackOp[1].getValue() == 0) //10 CALL
            {
                longword size = new longword();
                size.set(2);
                bit[] mul = new bit[4];
                for (i = 0; i < 4; i++)
                    mul[i] = new bit();
                for (i = 1; i < 4; i++)
                    mul[i].set(); //0111 - multiply
                value.copy(ALU.doOp(mul, value, size)); //Adjusts value address from line count to instruction count
            }
        }

        else
        {
            result.copy(ALU.doOp(opCode, op1, op2)); // preforms operation
        }
    }

    public void store()
    {
        if (opCode[0].getValue() == 0 && opCode[1].getValue() == 0 && 
          opCode[2].getValue() == 0 && opCode[3].getValue() == 0)
        {
            halted.set(); //halt instruction
        }

        else if (opCode[0].getValue() == 0 && opCode[1].getValue() == 0 && 
          opCode[2].getValue() == 0 && opCode[3].getValue() == 1) // move
        {
            registers[op1.getSigned()].set(value.getSigned()); // puts value into target register
        }

        else if (opCode[0].getValue() == 0 && opCode[1].getValue() == 0 && 
          opCode[2].getValue() == 1 && opCode[3].getValue() == 0) // interrupt
        {
            //do nothing here
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 0 && 
        currentInstruction.getBit(18).getValue() == 1 && currentInstruction.getBit(19).getValue() == 1) // jump
        {
            int jump;
            longword mask = new longword();
            mask.set(4095); //last 12 bits
            currentInstruction = currentInstruction.and(mask); //gets only the value of the line to jump to
            jump = (int) currentInstruction.getUnsigned();
            pc.set(jump*2); // *2 as each instruction ("Line") is 2 bytes
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 1 && 
        currentInstruction.getBit(18).getValue() == 0 && currentInstruction.getBit(19).getValue() == 0) //compare
        {
            if (result.getSigned() == 0) //Rx and Ry are equal
            {
                comparison[0].clear();
                comparison[1].set(); // 01 is EQUALS
            }
            else if (result.getSigned() > 0) //Rx is greater than Ry
            {
                comparison[0].set();
                comparison[1].clear(); // 10 is GREATER THAN
            }
            else // Rx is less than Ry
            {
                comparison[0].clear();
                comparison[1].clear(); // 00 is LESS THAN (0 for not greater, 0 for not equal)
            }
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 1 && 
        currentInstruction.getBit(18).getValue() == 0 && currentInstruction.getBit(19).getValue() == 1) //Branch
        {
            int i;
            bit[] add = new bit[4];
            for (i = 0; i < 4; i++)
                add[i] = new bit();
            for (i = 0; i < 3; i++)
                add[i].set(); //1110 opCode for add
            if (branch.getValue() == 1) //branch
            {
                pc = ALU.doOp(add, pc, value);
                branch.clear(); // reset 
            } // do nothing if else
        }

        else if (currentInstruction.getBit(16).getValue() == 0 && currentInstruction.getBit(17).getValue() == 1 && 
        currentInstruction.getBit(18).getValue() == 1 && currentInstruction.getBit(19).getValue() == 0) //Stack
        {
            longword increment = new longword();
            increment.set(4); // stack ops change stack pointer by 4 bytes -size of register
            longword clear = new longword();
            clear.set(0); // for cleaning stack
            if (stackOp[0].getValue() == 0)
            {
                if (stackOp[1].getValue() == 0) //00 — PUSH
                {
                    mem.write(sp, op1); //writes value from register to stack
                    sp.copy(rippleAdder.subtract(sp, increment)); //moves back 4 to fit space for next push
                }
                else //01 — POP
                {
                    sp.copy(rippleAdder.add(sp, increment)); //must move forward first to get value to pop
                    value.copy(mem.read(sp));
                    mem.write(sp, clear); //Removes value from the stack.
                    registers[op1.getSigned()].copy(value); // puts value popped into register
                }
            }
            else
            {
                longword RN = new longword();
                if (stackOp[1].getValue() == 0) // 10 — CALL
                {
                    RN.copy(pc); // Address to return to after call
                    mem.write(sp, RN);
                    sp.copy(rippleAdder.subtract(sp, increment)); // pushes return address onto stack
                    pc.copy(value); //Sets pc to address of called fnc. 
                }
                else // 11 — RETURN
                {
                    sp.copy(rippleAdder.add(sp, increment)); //must move forward first to pop address
                    RN.copy(mem.read(sp)); // RN gets adress to return to
                    mem.write(sp, clear); //Removes address from stack
                    pc.copy(RN); // Sets PC to return address
                    registers[15].set(0); //reset to 0, read for next function call
                }
            }
        }

        else
        {
            longword mask = new longword();
            mask.set(15); // 0000 0000 0000 0000 0000 0000 0000 1111
            registers[(currentInstruction.and(mask)).getSigned()].copy(result);
                //sets target register of the value of the last four bits of currentInstruction to the result
        }
    }

    public void preload(String[] program)
    {
        int count;
        int i;
        bit on = new bit();
        bit off = new bit();
        on.set();
        off.clear();
        count = program.length;
        String twoString; // two strings make a longword
        longword toWrite = new longword();
        longword increment = new longword();
        longword pcReader = new longword();
        pcReader.set(0);
        if (count % 2 == 0)
        {
            for (i = 0; i < count; i += 2)
            {
                int j;
                twoString = program[i].concat(program[i+1]); //adds two 16 bit string together
                twoString = twoString.replaceAll("\\s", ""); //gets rid of spaces
                for (j = 0; j < 32; j++) //converts to longwords
                {
                    if (twoString.charAt(j) == '0')
                        toWrite.setBit(j, off);
                    else
                        toWrite.setBit(j, on);
                }
                mem.write(pcReader, toWrite);
                increment.set(4);
                pcReader = rippleAdder.add(pcReader, increment); // moves to where the next set will be written
            }
        }
        else
        {
            int j;
            for (i = 0; i < (count - 1); i += 2)
            {
                twoString = program[i].concat(program[i+1]);
                twoString = twoString.replaceAll("\\s", "");
                for (j = 0; j < 32; j++)
                {
                    if (twoString.charAt(j) == '0')
                        toWrite.setBit(j, off);
                    else
                        toWrite.setBit(j, on);
                }
                mem.write(pcReader, toWrite);
                increment.set(4);
                pcReader = rippleAdder.add(pcReader, increment);
            }
            //only one string remaining
            twoString = program[count - 1];
            twoString = twoString.replaceAll("\\s", "");
            for (j = 0; j < 16; j++) // converts string to longword 0-15
            {
                if (twoString.charAt(j) == '0')
                    toWrite.setBit(j, off);
                else
                    toWrite.setBit(j, on);
            }
            for (j = 16; j < 32; j++)
                toWrite.setBit(j, off); //fills rest (16-31) with 0
            mem.write(pcReader, toWrite);
        }
    }

    public void run()
    {
        halted.clear();
        while (halted.getValue() == 0)
        {
            fetch();
            decode();
            execute();
            store();
        }
    }
    
}
