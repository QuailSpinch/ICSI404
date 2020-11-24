package Project01TheBit;
//the structure used for memory and its methods
public class memory {
    bit[] mem;
    public memory()
    {
        mem = new bit[8192];
        int i;
        for (i = 0; i < 8192; i++)
            mem[i] = new bit();
    }

    public longword read(longword address)
    {
        longword retrieve = new longword();
        if (address.getSigned() >= 0 && address.getSigned() <= 1020) 
        { // ^ 1020 because that is the last adress to be able to get a full four byte longword from
            int i;
            if (mem[address.getSigned() * 8] == null) // * 8 turns byte to bit
            {
                System.out.println("NullPo! Memory not written there, returning value 0");
                retrieve.set(0);
                return retrieve;
            }
            else
            {
                for (i = 0; i <= 31; i++)
                {
                    retrieve.setBit(i, mem[(address.getSigned() * 8) + i]);
                }
                return retrieve;
            }
        }
        else if (address.getSigned() >= 1021 && address.getSigned() <= 1023)
        {
            int i;
            int j;
            j = address.getSigned() - 1020; //Amount of bytes left to grab
            if (mem[address.getSigned() * 8] == null) // * 8 turns byte to bit
            {
                System.out.println("NullPo! Memory not written there, returning value 0");
                retrieve.set(0);
                return retrieve;
            }
            else
            {
                bit off = new bit();
                off.clear();
                for (i = 0; i < (j * 8); i++)
                {
                    retrieve.setBit(i, mem[(address.getSigned() * 8) + 1]);
                }
                for (i = (j * 8); i < 32; i++)
                {
                    retrieve.setBit(i, off);
                }
                return retrieve;
            }
        }
        else
        {
            System.out.println("Address not valid, returning 0 value!");
            retrieve.set(0);
            return retrieve;
        }
    }

    public void write(longword address, longword value)
    {
        if (address.getSigned() >= 0 && address.getSigned() <= 1020)
        {
            int i;
            for (i = 0; i <= 31; i++)
            {
                mem[i + (address.getSigned() * 8)].set(value.getBit(i).getValue());
            }
        }
        else if (address.getSigned() >= 1021 && address.getSigned() <= 1023)
        {
            int i;
            int j;
            j = address.getSigned() - 1020; // amount of bytes to NOT write
            for (i = (j * 8); i < 32; i++)
            {
                mem[i + (address.getSigned() * 8)].set(value.getBit(i).getValue());
            }
        }
        else
        {
            System.out.println("Address not valid, data not written!");
        }
    }
}
