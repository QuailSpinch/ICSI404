package Project01TheBit;
//test for the memory class' methods
public class memory_test 
{
    public static void runTests()
    {
        memory test = new memory();
        longword address = new longword();
        longword send = new longword();
        longword retrieve = new longword();
        bit on = new bit();
        bit off = new bit();
        on.set();
        off.clear();

        // address.set(0);
        // address.setBit(26, on); 
        address.set(32); //32
        // send.set(0);
        // send.setBit(4, on);
        // send.setBit(8, on);
        // send.setBit(13, on);
        // send.setBit(14, on);
        // send.setBit(18, on);
        // send.setBit(28, on);
        // send.setBit(29, on); 
        send.set(143007756); // 0000 1000 1000 0110 0010 0000 0000 1100 = 143,007,756

        test.write(address, send);
        // address.setBit(25, on); 
        address.set(96);//96
        test.write(address, send); // 143007756 is in both memory spaces 1 and 3 (where 0-31 is space 0)
        // send.setBit(4, off);
        // send.setBit(8, off); 
        send.set(401420); // 0000 0000 0000 0110 0010 0000 0000 1100 = 401,420
        // address.setBit(26, off); 
        address.set(64); //64
        test.write(address, send); // second memory space

        //Tests: try to write to improper memory space 
        System.out.println("Testing address limits:");
        // address.setBit(31, on); 
        address.set(65); // 65, is not divisible by 8 so should print an error
        test.write(address, send);
        // address.set(0);
        // address.setBit(5, on); 
        address.set(67108864); // = 67,108,864, way out of range
        test.write(address, send);
        // address.set(1);
        // address.setBit(31, off); 
        address.set(-2); // -2, below range
        test.write(address, send);
        System.out.println("Address limit testing complete.\n");

        //Read Tests (also tests the write method from earlier)
        // address.set(0);
        // address.setBit(26, on); 
        address.set(32); //32
        retrieve = test.read(address);
        System.out.println("Expected value: 143007756\nValue from address " + address.getSigned() + " is " + retrieve.getSigned() + "\n");
        // address.setBit(25, on); 
        address.set(96);//96
        retrieve = test.read(address);
        System.out.println("Expected value: 143007756\nValue from address " + address.getSigned() + " is " + retrieve.getSigned() + "\n");
        // address.setBit(26, off); 
        address.set(64); //64
        retrieve = test.read(address);
        System.out.println("Expected value: 401420\nValue from address " + address.getSigned() + " is " + retrieve.getSigned() + "\n");

        //test read from unwritten memory
        System.out.println("Testing read from uninitiated memory (should be an error)");
        address.set(0); //0
        retrieve = test.read(address);
        System.out.println("Expected value: -1\nValue from address " + address.getSigned() + " is " + retrieve.getSigned() + "\n");
    }
}
