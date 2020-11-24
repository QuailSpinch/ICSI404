package Project01TheBit;
//The ALU for all the arithmatic created in the other classes
public class ALU {
    private ALU(){}

    public static longword doOp(bit[] operation, longword a, longword b) {
        longword result = new longword();
        String opError = "Operation bit string not recognised! Returning -1!";

        if (operation[0].getValue() == 0) 
        {
            if (operation[1].getValue() == 1) 
            {
                if (operation[2].getValue() == 1) 
                {
                    if (operation[3].getValue() == 1) // 0111 - multiply
                    {
                        result = multiplier.multiply(a, b);
                        return result;
                    } 
                    else
                    {
                        System.out.println(opError);
                        result.set(-1);
                        return result;
                    }
                }
                else
                {
                    System.out.println(opError);
                    result.set(-1);
                    return result;
                }
            }
            else
            {
                System.out.println(opError);
                result.set(-1);
                return result;
            }
        }
        else //1
        {
            if (operation[1].getValue() == 0) //10
            {
                if (operation[2].getValue() == 0) //100
                {
                    if (operation[3].getValue() == 0) // 1000 - AND
                    {
                        result = a.and(b);
                        return result;
                    }
                    else // 1001
                    {
                        result = a.or(b);
                        return result; 
                    }
                }
                else // 101
                {
                    if (operation[3].getValue() == 0) // 1010
                    {
                        result = a.xor(b);
                        return result;
                    }
                    else // 1011
                    {
                        result = a.not();
                        return result;
                    }
                }
            }
            else // 11
            {
                if (operation[2].getValue() == 0) // 110
                {
                    if (operation[3].getValue() == 0) // 1100
                    {
                        if (b.getSigned() < 0 || b.getSigned() > 31)
                        {
                            System.out.println("Passed second longstring our of range for left shift, returning -1");
                            result.set(-1);
                            return result;
                        }
                        else
                        {
                            result = a.leftShift(b.getSigned());
                            return result;
                        }  
                    }
                    else // 1101
                    {
                        if (b.getSigned() < 0 || b.getSigned() > 31)
                        {
                            System.out.println("Passed second longstring our of range for right shift, returning -1");
                            result.set(-1);
                            return result;
                        }
                        else
                        {
                            result = a.rightShift(b.getSigned());
                            return result;
                        }
                    }
                }
                else // 111
                {
                    if (operation[3].getValue() == 0) // 1110
                    {
                        result = rippleAdder.add(a, b);
                        return result;
                    }
                    else // 1111
                    {
                        result = rippleAdder.subtract(a, b);
                        return result;
                    }
                }
            }
        }
    }
}