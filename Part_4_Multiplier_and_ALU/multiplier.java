package Project01TheBit;
//This file will allow multiplication of two longwords
public class multiplier {
    private multiplier(){} // private constructor

    public static longword multiply(longword a, longword b)
    {
        longword result = new longword();
        longword row = new longword();
        longword shifted = new longword();
        result.set(0);
        int i;
        int j;
        //int l;

        for (i = 31; i >= 0; i--) // goes bit by bit through a, multiplying each bit by all of b
        {
            for (j = 31; j >= 0; j--) // bit by bit through b
            {
                row.setBit(j, a.getBit(i).and(b.getBit(j))); // row = a[i] * b
            }
            shifted = row.leftShift(31 - i); // shifted to acount for position in string, anything pushed past index 0 is lost
            result = rippleAdder.add(result, shifted); //adds the multiplied rows together for one result
        }

        return result;
    }
}
