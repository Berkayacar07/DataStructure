package Huffman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author admin
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String str = readFile("a.txt");
        HashMap<Character, Integer> freq = findFreq(str);
        printMap(freq);

        Huffman huffman = new Huffman(freq.size());
        huffman.createTree(freq);
        huffman.constructCoding(huffman.root, "");
        String replaced = replace(str, huffman.encoder);
        System.out.print("\n" + str + " -> " + replaced);
    }



    public static String replace(String str, HashMap<Character, String> encoder) {
        String replaced = str;
        for (Map.Entry<Character, String> entry : encoder.entrySet()) {
            replaced = replaced.replaceAll(String.valueOf(entry.getKey()), entry.getValue());
        }
        return replaced;
    }

    public static void printMap(HashMap<Character, Integer> freq) {
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println();
    }

    public static HashMap<Character, Integer> findFreq(String str) {
        HashMap<Character, Integer> tempMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (tempMap.containsKey(c))
                tempMap.put(c, tempMap.get(c) + 1);
            else
                tempMap.put(c, 1);
        }
        return tempMap;
    }

    public static String readFile(String path) throws IOException {
        String res;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        res = reader.readLine();
        return res;
    }

}

