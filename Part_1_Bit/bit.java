package Project01TheBit;
/*
This file defines a bit and its methods
*/
public class bit implements IBit{
    private int val;

    public bit()
    {
        val = 0;
    }

	@Override
	public void set(int value) {
        if (value > 1)
            System.out.println("Not a valid value");
        else if (value < 0)
            System.out.println("Not a valid value"); //chacks value to make sure it is 1 or 0
		else val = value;
	}

	@Override
	public void toggle() {
        if (val == 0)
            val = 1;  //0 -> 1
        else val = 0;  //1 -> 0
	}

	@Override
	public void set() {
		val = 1;
	}

	@Override
	public void clear() {
		val = 0;
	}

	@Override
	public int getValue() {
		return val;
	}

	@Override
	public bit and(bit other) {
        bit result = new bit();
        if (val == 0)
            result.set(0); // when one bit is 0 AND always returns 0.
        else // bit is 1
        {
            if (other.val == 0)
                result.set(0);
            else result.set(1); // Both bits are 1 AND gives 1
        }
        return result;
	}

	@Override
	public bit or(bit other) {
        bit result = new bit();
        if (val == 1)
            result.set(1); // When one bit is 1 OR always returns 1
        else
        {
            if (other.val == 1)
                result.set(1);
            else result.set(0); // Both bits are 0 OR gives 0
        }
        return result;
	}

	@Override
	public bit xor(bit other) { // XOR returns 1 if and only if one bit is 1. If both bits are the same return 0
        bit result = new bit();
        if (val == 0)
        {
            if (other.val == 0)
                result.set(0); //both 0
            else result.set(1);
        }
        else 
        {
            if (other.val == 0)
                result.set(1);
            else result.set(0); //both 1
        }
        return result;
	}

	@Override
	public bit not() {
        bit result = new bit();
        if (val == 0)
            result.set(1); //inverts 0 -> 1
        else result.set(0); // 1 -> 0
        return result;
    }
    
    public String toString() {
        if (val == 0) 
            return "0";
        else return "1";
    }
}
