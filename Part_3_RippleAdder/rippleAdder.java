package Project01TheBit;
// This file adds and subtracts longwords
public class rippleAdder 
{
    private rippleAdder(){
        //private constructor
    }

    public static longword add(longword a, longword b)
    {
        bit carry = new bit();
        bit off = new bit();
        bit on = new bit();
        int i;
        longword result = new longword();
        carry.clear();
        off.clear();
        on.set();

        for (i = 31; i >=0 ; i--)
        {
            if (a.getBit(i).getValue() == 0)
            {
                if(b.getBit(i).getValue() == 0)
                {
                    if(carry.getValue() == 0) // 0 0 0 = 0  -> 00
                    {
                        result.setBit(i, off);
                        carry.clear();
                    }
                    else //0 0 1 = 1  -> 01
                    {
                        result.setBit(i, on); 
                        carry.clear();
                    }
                }
                else
                {
                    if(carry.getValue() == 0) // 0 1 0 = 1  -> 01
                    {
                        result.setBit(i, on);
                        carry.clear();
                    }
                    else // 0 1 1 = 2  -> 10
                    {
                        result.setBit(i, off);
                        carry.set();
                    }
                }
            }
            else
            {
                if(b.getBit(i).getValue() == 0)
                {
                    if(carry.getValue() == 0) // 1 0 0 = 1  -> 01
                    {
                        result.setBit(i, on);
                        carry.clear();
                    }
                    else //1 0 1 = 2  -> 10
                    {
                        result.setBit(i, off); 
                        carry.set();
                    }
                }
                else
                {
                    if(carry.getValue() == 0) // 1 1 0 = 2  -> 10
                    {
                        result.setBit(i, off);
                        carry.set();
                    }
                    else // 1 1 1 = 3  -> 11
                    {
                        result.setBit(i, on);
                        carry.set();
                    }
                }
            }
        }
        return result;
    }

    public static longword subtract(longword a, longword b)
    {
        longword neg_b = b.not();
        longword result = new longword();
        longword plus_one = new longword();
        bit on = new bit();
        on.set();
        plus_one.set(0);
        plus_one.setBit(31, on); // 0000 ... 0001
        neg_b = add(neg_b, plus_one); //makes neg_b two's compliment of b (Negate b, add 1)
        result = add(a, neg_b);
        return result;
    }
}
