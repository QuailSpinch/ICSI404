package Project01TheBit;

import java.util.*;

public class Assembler {

    public static List<String> lexicalAnalyzer(String instruction) //breaks assembly into tokens
    {
        ArrayList<String> instruct = new ArrayList<>();
        int i;
        char current;
        char next = ' ';
        String token;
        StringBuilder sb = new StringBuilder(); //used to put tokens together, char by char
        for (i = 0; i < instruction.length(); i++)
        {
            current = instruction.charAt(i);
            if (i != (instruction.length() - 1))
                next = instruction.charAt(i + 1);
            else
                next = '\0';

            if (Character.isLetterOrDigit(current) || current == '-') //part of current token
            {
                sb.append(current);
                if ( next == '\0') //nothing else to grab
                {
                    token = sb.toString(); 
                    instruct.add(token); //adds the token to 
                    token = ""; 
                    sb.delete(0, sb.length()); //cleans out for next token
                }
            }
            else if (Character.isWhitespace(current)) //reached end of token
            {
                token = sb.toString();
                instruct.add(token);
                token = "";
                sb.delete(0, sb.length());
            }
        }
        return instruct;
    }

    public static String regConverter(String register)
    {
        int reg;
        String bitReg;
        StringBuilder sb = new StringBuilder(register);
        sb.deleteCharAt(0); // get rid of the R at begining of register names
        register = sb.toString();
        reg = Integer.parseInt(register);
        longword convert = new longword();
        convert.set(reg);
        bitReg = convert.toString();
        bitReg = bitReg.substring(28);
        return bitReg;
    }

    public static String threeReg(List<String> instruction, String op)
    {
        String reg1 = instruction.get(1);
        String reg2 = instruction.get(2);
        String reg3 = instruction.get(3);
        reg1 = regConverter(reg1);
        reg2 = regConverter(reg2);
        reg3 = regConverter(reg3);
        String full;
        full = op;
        full = full.concat(" " + reg1).concat(" " + reg2).concat(" " + reg3);
        return full;
    }

    public static String oneReg(List<String> instruction, String op)
    {
        String reg1 = instruction.get(1);
        reg1 = regConverter(reg1);
        String full;
        full = op;
        full = full.concat(reg1);
        return full;
    }

    public static String interrupt(List<String> instruction, String op)
    { //Used to form the two different types of interrupts
        int type;
        String rest;
        String full;
        type = Integer.parseInt(instruction.get(1));
        if (type == 0)
            rest = " 0000 0000 0000";
        else
            rest = " 0000 0000 0010";
        
        full = op;
        full = full.concat(rest);
        return full;
    }

    public static String not(List<String> instruction, String op)
    {
        String reg1 = instruction.get(1);
        String reg3 = instruction.get(2);
        reg1 = regConverter(reg1);
        reg3 = regConverter(reg3);
        String reg2 = "0000";
        String full;
        full = op;
        full = full.concat(" " + reg1).concat(" " + reg2).concat(" " + reg3);
        return full;
    }

    public static String move(List<String> instruction, String op)
    {
        String reg = instruction.get(1);
        reg = regConverter(reg);
        String bitVal;
        int value = Integer.parseInt(instruction.get(2));
        longword convert = new longword();
        convert.set(value);
        bitVal = convert.toString();
        bitVal = bitVal.substring(24);
        String full;
        full = op;
        full = full.concat(" " + reg).concat(" " + bitVal);
        return full;
    }

    public static String twelveVal(List<String> instruction, String op) //turns value to tweleve bits
    {
        String bitVal;
        int value = Integer.parseInt(instruction.get(1));
        longword convert = new longword();
        convert.set(value);
        bitVal = convert.toString();
        bitVal = bitVal.substring(20);
        String full;
        full = op;
        full = full.concat(" " + bitVal);
        return full;
    }



    public static String comp(List<String> instruction, String op)
    {
        String reg2 = instruction.get(1);
        String reg3 = instruction.get(2);
        reg2 = regConverter(reg2);
        reg3 = regConverter(reg3);
        String reg1 = "0000";
        String full;
        full = op;
        full = full.concat(" " + reg1).concat(" " + reg2).concat(" " + reg3);
        return full;
    }

    public static String tenVal(List<String> instruction, String op)
    {
        String bitVal;
        int value = Integer.parseInt(instruction.get(1));
        longword convert = new longword();
        convert.set(value);
        bitVal = convert.toString();
        bitVal = bitVal.substring(22); // saaaaaaaaa = sa aaaa aaaa (or aa aaaa aaaa for CALL)
        String full;
        full = op; //0101 xx or 0110 10
        full = full.concat(bitVal); // 0101 xxsaaaaaaaaa = 0101 xxsa aaaa aaaa
        return full;
    }

    public static String parse(List<String> instruction) // turns the instruction to bits
    {
        String bitOp;
        String bitInstruction = "";
        String op = instruction.get(0); // first token
        op = op.toUpperCase();
        switch (op) // first token dictates how to form rest of the instruction
        {
            case "MOVE":
                bitOp = "0001";
                bitInstruction = move(instruction, bitOp);
                break;
            case "MUL":
                bitOp = "0111";
                bitInstruction = threeReg(instruction, bitOp);
                break;
            case "AND":
                bitOp = "1000";
                bitInstruction = threeReg(instruction, bitOp);
                break;
            case "OR":
                bitOp = "1001";
                bitInstruction = threeReg(instruction, bitOp);
                break;
            case "XOR":
                bitOp = "1010";
                bitInstruction = threeReg(instruction, bitOp);
                break;
            case "NOT": // special case where only two registers needed
                bitOp = "1011";
                bitInstruction = not(instruction, bitOp);
                break;
            case "LS":
                bitOp = "1100";
                bitInstruction = threeReg(instruction, bitOp);
                break;
            case "RS":
                bitOp = "1101";
                bitInstruction = threeReg(instruction, bitOp);
                break;
            case "ADD":
                bitOp = "1110";
                bitInstruction = threeReg(instruction, bitOp);
                break;
            case "SUB":
                bitOp = "1111";
                bitInstruction = threeReg(instruction, bitOp);
                break;
            case "INTR":
                bitOp = "0010";
                bitInstruction = interrupt(instruction, bitOp);
                break;
            case "HALT": //No helper needed, all 0's
                bitInstruction = "0000 0000 0000 0000";
                break;
            case "JUMP":
                bitOp = "0011";
                bitInstruction = twelveVal(instruction, bitOp);
                break;
            case "COMP":
                bitOp = "0100";
                bitInstruction = comp(instruction, bitOp);
                break;
            case "BNLES": //BraNch if LESs
                bitOp = "0101 00";
                bitInstruction = tenVal(instruction, bitOp);
                break;
            case "BNEQ": //BraNch if EQual
                bitOp = "0101 01";
                bitInstruction = tenVal(instruction, bitOp);
                break;
            case "BNGR": //BraNch if GReater
                bitOp = "0101 10";
                bitInstruction = tenVal(instruction, bitOp);
                break;
            case "BNGREQ": //BraNch if GReater or EQual
                bitOp = "0101 11";
                bitInstruction = tenVal(instruction, bitOp);
                break;
            case "PUSH":
                bitOp = "0110 0000 0000 ";
                bitInstruction = oneReg(instruction, bitOp);
                break;
            case "POP":
                bitOp = "0110 0100 0000 ";
                bitInstruction = oneReg(instruction, bitOp);
                break;
            case "CALL":
                bitOp = "0110 10";
                bitInstruction = tenVal(instruction, bitOp);
                break;
            case "RTRN":
                bitInstruction = "0110 1100 0000 0000"; //no variable bits, can send this direct
                break;
            default: //print an error and assign all 0's
                System.out.println("Token " + op + "not recognised");
                bitInstruction = "0000 0000 0000 0000";
        }
        return bitInstruction;
    }
    
    public static String[] assemble(String[] program)
    {
        List<String> instruction = new ArrayList<>();
        List<String> bitLoad = new ArrayList<>();
        String bitString;
        int i;
        //String[] output = new String[2];
        for (i = 0; i < program.length; i++) //loops through assembly program
        {
            instruction.clear();
            instruction = Assembler.lexicalAnalyzer(program[i]); // tokenized instruction
            bitString = Assembler.parse(instruction); // parsed to bits instruction
            bitLoad.add(bitString); // list of bit instructions
        }
        String[] load = new String[bitLoad.size()];
        bitLoad.toArray(load); //turns list into string, ready for preloading
        return load;
    }
}