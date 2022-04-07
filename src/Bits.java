import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bits {
    static final int INT_LENGTH = 32;
    static final int INT_TYPE = 0;

    public static void main(String[] args) {
//        String firstStr = convertStringToBinary("akdeniz");
//        String secondStr = convertStringToBinary("universitesi");
//        String[] temp = equalize(firstStr, secondStr);
//        firstStr = temp[0];
//        secondStr = temp[1];
//        String opStr = "&";
//        String opResult = BitWise1.and(firstStr, secondStr);
//        String str = Arrays.stream(pretty(opResult).split(" "))
//                .map(binary -> Integer.parseInt(binary, 2))
//                .map(Character::toString)
//                .collect(Collectors.joining());
//        String result = pretty(firstStr) + " " + opStr + "\n" +
//                pretty(secondStr) + "\n" +
//                "------------------------------------\n" +
//                pretty(opResult) + "  = " + str;
//        System.out.println(result);

        menu();
    }

    public static String pretty(String str) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            if (i % 8 == 7)
                temp.append(" ");
        }
        return temp.toString();
    }

    public static String padding(String str, int type) {
        StringBuilder result = new StringBuilder(str);
        if (type == INT_TYPE) {
            for (int i = 0; i < INT_LENGTH - str.length(); i++) {
                result.insert(0, "0");
            }
        }
        return result.toString();
    }

    public static String[] equalize(String first, String second) {
        StringBuilder temp1;
        StringBuilder temp2;
        int diff = Math.abs(first.length() - second.length());
        temp1 = new StringBuilder(first);
        temp2 = new StringBuilder(second);
        if (first.length() > second.length()) {
            for (int i = 0; i < diff; i++) {
                temp2.insert(0, 0);
            }
        } else {
            for (int i = 0; i < diff; i++) {
                temp1.insert(0, 0);
            }
        }
        first = temp1.toString();
        second = temp2.toString();

        return new String[]{first, second};
    }

    public static String doubleToBitString(final double d) {
        final char[] bit = new char[64];
        final long dd = Double.doubleToLongBits(d);
        long mask = 1L;
        for (int i = 0; i < 64; i++) {
            final long bitVal = dd & mask;
            if (bitVal == 0) {
                bit[63 - i] = '0';
            } else {

                bit[63 - i] = '1';
            }
            mask <<= 1;
        }
        return String.valueOf(bit);
    }

    public static String convertStringToBinary(String input) {
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar)).replaceAll(" ", "0")
            );
        }
        return result.toString();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give the type of input? int=0, string=1, double=2");
        int choice = scanner.nextInt();
        int operation;
        String firstStr, secondStr, result, opResult = "";
        switch (choice) {
            case 0 -> {//int
                System.out.println("Give the type of input? or=0, and=1, xor=2, complement=3, right shift=4, left shift=5");
                operation = scanner.nextInt();
                int firstInt, secondInt, shift;
                if (operation == 0 || operation == 1 || operation == 2) {
                    System.out.println("First Integer Number : ");
                    firstInt = scanner.nextInt();
                    System.out.println("Second Integer Number : ");
                    secondInt = scanner.nextInt();
                    firstStr = padding(Integer.toBinaryString(firstInt), INT_TYPE);
                    secondStr = padding(Integer.toBinaryString(secondInt), INT_TYPE);
                    String opStr = "";
                    switch (operation) {
                        case 0 -> {//or
                            opResult = padding(BitWise1.or(firstStr, secondStr), INT_TYPE);
                            opStr = "|";
                        }
                        case 1 -> {//and
                            opResult = padding(BitWise1.and(firstStr, secondStr), INT_TYPE);
                            opStr = "&";
                        }
                        case 2 -> {//xor
                            opResult = padding(BitWise1.xor(firstStr, secondStr), INT_TYPE);
                            opStr = "^";
                        }
                    }
                    result = pretty(firstStr) + " " + opStr + "\n" +
                            pretty(secondStr) + "\n" +
                            "------------------------------------\n" +
                            pretty(opResult) + "  = " + (int) Long.parseLong(opResult, 2);
                    System.out.println(result);
                } else {
                    switch (operation) {
                        case 3 -> {//comp
                            System.out.println("Integer Number : ");
                            firstInt = scanner.nextInt();
                            firstStr = padding(Integer.toBinaryString(firstInt), INT_TYPE);
                            opResult = padding(BitWise1.comp(firstStr), INT_TYPE);
                            result = "~" + pretty(firstStr) + "  = " + pretty(opResult) + "  = " + (int) Long.parseLong(opResult, 2);
                            System.out.println(result);
                        }
                        case 4 -> {//rs
                            System.out.println("Integer Number : ");
                            firstInt = scanner.nextInt();
                            System.out.println("Shift Number : ");
                            shift = scanner.nextInt();
                            firstStr = padding(Integer.toBinaryString(firstInt), INT_TYPE);
                            opResult = padding(BitWise1.rs(firstStr, shift), INT_TYPE);
                            result = firstInt + " >> " + shift + " = " + pretty(opResult) + "  = " + (int) Long.parseLong(opResult, 2);
                            System.out.println(result);
                        }
                        case 5 -> {//ls
                            System.out.println("Integer Number : ");
                            firstInt = scanner.nextInt();
                            System.out.println("Shift Number : ");
                            shift = scanner.nextInt();
                            firstStr = padding(Integer.toBinaryString(firstInt), INT_TYPE);
                            opResult = padding(BitWise1.ls(firstStr, shift), INT_TYPE);
                            result = firstInt + " << " + shift + " = " + pretty(opResult) + "  = " + (int) Long.parseLong(opResult, 2); //Integer.parseInt(opResult, 2);
                            System.out.println(result);
                        }
                        default -> {
                        }
                    }
                }
            }
            case 1 -> {//string
                System.out.println("Give the type of input? or=0, and=1, xor=2, complement=3, right shift=4, left shift=5");
                operation = scanner.nextInt();

                System.out.println("First String : ");
                firstStr = scanner.next();
                System.out.println("Second String : ");
                secondStr = scanner.next();

                firstStr = convertStringToBinary(firstStr);
                secondStr = convertStringToBinary(secondStr);
                String[] temp = equalize(firstStr, secondStr);
                firstStr = temp[0];
                secondStr = temp[1];

                String opStr = "";
                switch (operation) {
                    case 0 -> {//or
                        opResult = BitWise1.or(firstStr, secondStr);
                        opStr = "|";
                    }
                    case 1 -> {//and
                        opResult = BitWise1.and(firstStr, secondStr);
                        opStr = "&";
                    }
                    case 2 -> {//xor
                        opResult = BitWise1.xor(firstStr, secondStr);
                        opStr = "^";
                    }
                    default -> {
                    }
                }
                String str = Arrays.stream(pretty(opResult).split(" "))
                        .map(binary -> Integer.parseInt(binary, 2))
                        .map(Character::toString)
                        .collect(Collectors.joining());
                result = pretty(firstStr) + " " + opStr + "\n" +
                        pretty(secondStr) + "\n" +
                        "------------------------------------\n" +
                        pretty(opResult) + "  = " + str;
                System.out.println(result);
            }
            case 2 -> {//double
                System.out.println("Give the type of input? or=0, and=1, xor=2, complement=3, right shift=4, left shift=5");
                operation = scanner.nextInt();
                double firstDbl, secondDbl;
                System.out.println("First Double Number : ");
                firstDbl = scanner.nextDouble();
                System.out.println("Second Double Number : ");
                secondDbl = scanner.nextDouble();
                firstStr = doubleToBitString(firstDbl);
                secondStr = doubleToBitString(secondDbl);
                String opStr = "";
                switch (operation) {
                    case 0 -> {//or
                        opResult = BitWise1.or(firstStr, secondStr);
                        opStr = "|";
                    }
                    case 1 -> {//and
                        opResult = BitWise1.and(firstStr, secondStr);
                        opStr = "&";
                    }
                    case 2 -> {//xor
                        opResult = BitWise1.xor(firstStr, secondStr);
                        opStr = "^";
                    }
                    default -> {
                    }
                }
                result = pretty(firstStr) + " " + opStr + "\n" +
                        pretty(secondStr) + "\n" +
                        "------------------------------------\n" +
                        pretty(opResult);
                System.out.println(result);
            }
            default -> {
            }
        }
    }
}

class BitWise1 {
    public static String or(String first, String second) {
        StringBuilder result = new StringBuilder();
        for (int i = first.length() - 1; i >= 0; i--) {
            if (first.charAt(i) == '1' || second.charAt(i) == '1')
                result.insert(0, '1');
            else
                result.insert(0, '0');
        }
        return result.toString();
    }

    public static String and(String first, String second) {
        StringBuilder result = new StringBuilder();
        for (int i = first.length() - 1; i >= 0; i--) {
            if (first.charAt(i) == '0' || second.charAt(i) == '0')
                result.insert(0, '0');
            else
                result.insert(0, '1');
        }
        return result.toString();
    }

    public static String xor(String first, String second) {
        StringBuilder result = new StringBuilder();
        for (int i = first.length() - 1; i >= 0; i--) {
            if (first.charAt(i) == second.charAt(i))
                result.insert(0, '0');
            else
                result.insert(0, '1');
        }
        return result.toString();
    }

    public static String comp(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '0')
                result.append('1');
            else
                result.append('0');
        }
        return result.toString();
    }

    public static String rs(String str, int shift) {
        String result;
        result = "0".repeat(Math.max(0, shift)) + str.substring(0, str.length() - shift);
        return result;
    }

    public static String ls(String str, int shift) {
        String result;
        String temp = "0".repeat(Math.max(0, shift));
        result = str.substring(shift) + temp;
        return result;
    }
}


