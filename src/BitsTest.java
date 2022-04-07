import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BitsTest {
    public final PrintStream standardOut = System.out;
    public final InputStream stdin = System.in;
    public final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    String IntOr = "00000000000000000000000000001010|\n00000000000000000000000000001110\n00000000000000000000000000001110=14";
    String IntOr1 = "11111111111111111111111111001010|\n00000000000000000001111011011000\n11111111111111111111111111011010=-38";

    String IntAnd = "00000000000000000000000000001110&\n00000000000000000000000000001010\n00000000000000000000000000001010=10";
    String IntAnd1 = "00000000000000000001111011011000&\n11111111111111111111111111001010\n00000000000000000001111011001000=7880";

    String IntXor = "00000000000000000000000001111011^\n00000000000000000000000101000001\n00000000000000000000000100111010=314";
    String IntXor1 = "11111111111111111111111001010000^\n11111111111111101010011000010110\n00000000000000010101100001000110=88134";

    String IntComp = "~00000000000000000000000011101010=11111111111111111111111100010101=-235";
    String IntComp1 = "~11111111111111111111110101110010=00000000000000000000001010001101=653";

    String IntRs = "100>>4=00000000000000000000000000000110=6";

    String IntLs = "10<<5=00000000000000000000000101000000=320";


    ///////////////////////////////////////////////////////////

    String StringOr = "00000000000000000000000001100101011011010111001001100101|\n01100001011110010110010001101111011001110110000101101110\n01100001011110010110010001101111011011110111001101101111=aydooso";
    String StringAnd = "000000000000000000000000000000000000000001100001011010110110010001100101011011100110100101111010&\n011101010110111001101001011101100110010101110010011100110110100101110100011001010111001101101001\n000000000000000000000000000000000000000001100000011000110110000001100100011001000110000101101000=\0\0\0\0\0`c`ddah";
    ///////////////////////////////////////////////////////////


    String DoubleOr = "0011111111110100000000000000000000000000000000000000000000000000|\n0100000010010011010010100010101000110000010101010011001001100001\n0111111111110111010010100010101000110000010101010011001001100001";
    String DoubleOr1 = "1100000000101000110001111010111000010100011110101110000101001000|\n1100000001010000110000000000000000000000000000000000000000000000\n1100000001111000110001111010111000010100011110101110000101001000";

    String DoubleAnd = "0100000010000000111110001111101111100111011011001000101101000100&\n0100000000010100000000000000000000000000000000000000000000000000\n0100000000000000000000000000000000000000000000000000000000000000";
    String DoubleAnd1 = "1100000010111101111001100000000000000000000000000000000000000000&\n1100000000100010111000000000111011001100000110011100101100111000\n1100000000100000111000000000000000000000000000000000000000000000";

    String DoubleXor = "0011111111110100000000000000000000000000000000000000000000000000^\n0100000010010011010010100010101000110000010101010011001001100001\n0111111101100111010010100010101000110000010101010011001001100001";
    String DoubleXor1 = "1100000010111101111001100000000000000000000000000000000000000000^\n1100000000100010111000000000111011001100000110011100101100111000\n0000000010011111000001100000111011001100000110011100101100111000";

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    public void testIntOr() {
        String input = "0 0 10 14";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", IntOr, output);
        //assertTrue("", output.equals(IntOr));
    }

    @Test
    public void testIntOr1() {
        String input = "0 0 -54 7896";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", IntOr1, output);
        //assertTrue("", output.equals(IntOr1));
    }

    @Test
    public void testIntAnd() {
        String input = "0 1 14 10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", IntAnd, output);
        //assertTrue("", output.equals(IntAnd));
    }

    @Test
    public void testIntAnd1() {
        String input = "0 1 7896 -54";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", IntAnd1, output);
        //assertTrue("", output.equals(IntAnd1));
    }

    @Test
    public void testIntXor() {
        String input = "0 2 123 321";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", IntXor, output);
        //assertTrue("", output.equals(IntXor));
    }

    @Test
    public void testIntXor1() {
        String input = "0 2 -432 -88554";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", IntXor1, output);
        //assertTrue("", output.equals(IntXor1));
    }

    @Test
    public void testIntComp() {
        String input = "0 3 234";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[3].trim().replaceAll(" ", "");
        assertEquals("", IntComp, output);
        //assertTrue("", output.equals(IntComp));
    }

    @Test
    public void testIntComp1() {
        String input = "0 3 -654";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[3].trim().replaceAll(" ", "");
        assertEquals("", IntComp1, output);
        //assertTrue("", output.equals(IntComp1));
    }

    @Test
    public void testIntRs() {
        String input = "0 4 100 4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "");
        assertEquals("", IntRs, output);
        //assertTrue("", output.equals(IntRs));
    }


    @Test
    public void testIntLs() {
        String input = "0 5 10 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "");
        assertEquals("", IntLs, output);
        //assertTrue("", output.equals(IntLs));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test
    public void testStrOr() {
        String input = "1 0 emre aydogan";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", StringOr, output);
        //assertTrue("", output.equals(StringOr));
    }

    @Test
    public void testStrAnd() {
        String input = "1 1 akdeniz\n universitesi\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", StringAnd, output);
        //assertTrue("", output.equals(StringAnd));
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testDoubleOr() {
        String input = "2 0 1.25 1234.5412";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", DoubleOr, output);
        //assertTrue("", output.equals(DoubleOr));
    }

    @Test
    public void testDoubleOr1() {
        String input = "2 0 -12.39 -67";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", DoubleOr1, output);
        //assertTrue("", output.equals(DoubleOr1));
    }

    @Test
    public void testDoubleAnd() {
        String input = "2 1 543.123 5.0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", DoubleAnd, output);
        //assertTrue("", output.equals(DoubleAnd));
    }

    @Test
    public void testDoubleAnd1() {
        String input = "2 1 -7654 -9.4376128942";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", DoubleAnd1, output);
        //assertTrue("", output.equals(DoubleAnd1));
    }


    @Test
    public void testDoubleXor() {
        String input = "2 2 1.25 1234.5412";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", DoubleXor, output);
        //assertTrue("", output.equals(DoubleXor));
    }

    @Test
    public void testDoubleXor1() {
        String input = "2 2 -7654 -9.4376128942";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Bits.main(null);
        String output = outputStreamCaptor.toString().toLowerCase().trim();
        String[] temp = output.split("\n");
        output = temp[4].trim().replaceAll(" ", "") + "\n" + temp[5].trim().replaceAll(" ", "") + "\n" + temp[7].trim().replaceAll(" ", "");
        assertEquals("", DoubleXor1, output);
        //assertTrue("", output.equals(DoubleXor1));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(stdin);
    }
}
