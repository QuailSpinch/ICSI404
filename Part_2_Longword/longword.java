package Project01TheBit;
// The structure and methods of longwords
public class longword implements ILongword{
    private bit[] lword = new bit[32];

    public longword()
    {
        int i;
        
        for (i = 0; i < lword.length; i++)
            lword[i] = new bit();
    }

    @Override
    public bit getBit(int i) {
        if (i > 31 || i < 0)
        {
            System.out.println("Out of range");
            return null;
        }
        else {
            bit get = new bit();
            get.set(lword[i].getValue()); // sets value of "get" to the value of the target bit to be returned.
            return get;
        }
    }

    @Override
    public void setBit(int i, bit value) {
        if (i < 0 || i > 31)
            System.out.println("Out of range");
        else 
            lword[i].set(value.getValue()); //= value; // Set target bit to passed bit
    }

    @Override
    public longword and(longword other) {
        int i;
        longword result = new longword();
        for(i = 0; i < 32; i++)
        {
            //ANDs target bits (called bit AND other bit) and puts it in a new longword
            result.lword[i] = lword[i].and(other.lword[i]); 
        }
        return result;
    }

    @Override
    public longword or(longword other) {
        int i;
        longword result = new longword();
        for(i = 0; i < 32; i++)
        {
            //ORs target bits (called bit OR other bit) and puts it in a new longword
            result.lword[i] = lword[i].or(other.lword[i]);
        }
        return result;
    }

    @Override
    public longword xor(longword other) {
        int i;
        longword result = new longword();
        for(i = 0; i < 32; i++)
        {
            //XORs target bits (called bit XOR other bit) and puts it in a new longword
            result.lword[i] = lword[i].xor(other.lword[i]);
        }
        return result;
    }

    @Override
    public longword not() {
        int i;
        longword result = new longword();
        for (i = 0; i < 32; i++)
        {
            //NOTs target bit and gets value to set result lword bit
            result.lword[i] = lword[i].not();
        }
        return result;
    }

    @Override
    public longword rightShift(int amount) {
        if (amount > 31){
            System.out.println("amount too large!");
            return null;
        }
        else if (amount < 0){
            System.out.println("amount too small!");
            return null;
        }
        else{
            longword result = new longword();
            result.set(0);
            int i;
            for (i = 0; i < amount; i++)
                result.lword[i].clear(); // set first "amount" to 0
            for (i = 0; i < 32 - amount; i++)
            {
                result.setBit(i + amount, this.lword[i]); // fills the rest of result with the 32-amount bits from called
            }
            return result;
        }
    }

    @Override
    public longword leftShift(int amount) {
        if (amount > 31){
            System.out.println("amount too large!");
            return null;
        }
        else if (amount < 0){
            System.out.println("amount too small!");
            return null;
        }
        else{
            longword result = new longword();
            int i;
            bit off = new bit();
            off.set(0);
            for (i = 0; i <= 31 - amount; i++)
            {
                result.setBit(i, this.lword[i + amount]); // fills first amount
            }
            for (i = 32 - amount; i <= 31; i++)
                result.setBit(i, off); // fills remainder with 0
            return result;
        }
    }
    
    @Override
    public String toString() {
        String string ="";
        String sub = "";
        int i;
        for (i = 0; i <= 31; i++)
        {
            sub = (lword[i].getValue() + ""); // ,val ,val ,val
            string = string.concat(sub);
        }
        return string;
    }

    @Override
    public long getUnsigned() {
        int i;
        long sum = 0;
        for (i = 0; i < 32; i++)
        {
            sum += lword[i].getValue() * (Math.pow(2,(31-i))); // 0 or 1 times 2 raised to digit
        }
        return sum;
    }

    @Override
    public int getSigned() {
        int i;
        int sum = 0;
        if (lword[0].getValue() == 0)
        {
            for (i = 1; i < 32; i++)
            {
                sum += lword[i].getValue() * (Math.pow(2,(31-i))); // 0 or 1 times 2 raised to digit
            }
        }
        else 
        {
            for (i = 1; i < 32; i++)
            {
                sum += lword[i].not().getValue() * (-Math.pow(2,(31-i)));
            }
            sum--;
        }
        return sum;
    }

    @Override
    public void copy(longword other) {
        int i;
        for (i = 0; i < 32; i++)
        {
            this.setBit(i, other.lword[i]);
        }
    }

    @Override
    public void set(int value) {
        if (value >= 0)
        {
            int i = 0; 
            int j = 31;
            int n;
            int max;
            max = 2147483647;
            n = value;
            while (max > n)
            {
                lword[i].set(0);
                max = max/2;
                if (i == 0)
                    max += 1; //makes max even and fixes error with some sets
                i++;
            }
            while (n > 0) { 
                // storing remainder in binary array 
                lword[j].set(n % 2); 
                n = n / 2; 
                i++; 
                j--;
            } 
        }
        else
        {
            int i = 0; 
            int j = 31;
            int n;
            int max;
            int remainder;
            max = -2147483647;
            n = value;
            while (max < n)
            {
                if ((max % 2) == 1)
                {
                    max = max -1;
                }
                lword[i].set(1);
                max = max/2;
                i++;
            }
            lword[i].set(1);
            while (n < 0) { 
                // storing remainder in binary array 
                remainder = (n % 2);
                if (remainder == 0)
                {
                    lword[j].set(1);
                }
                else
                {
                    lword[j].set(0);
                } 
                n = n / 2; 
                i++; 
                j--;
            }
            longword twoCompliment = new longword();
            twoCompliment.set(1); 
            longword sum = new longword();
            int k;
            for (k = 0; k < 32; k++)
            {
                sum.setBit(k, lword[k]);
            }
            sum = rippleAdder.add(sum, twoCompliment);
            for (k = 0; k < 32; k++)
            {
                lword[k] = sum.getBit(k);
            }
        }
    }
}
