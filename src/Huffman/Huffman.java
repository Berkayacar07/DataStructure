package Huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author admin
 */
public class Huffman {
    PriorityQueue<Node> pq;
    Node root;
    HashMap<Character, String> encoder;

    public Huffman(int size) {
        pq = new PriorityQueue<>(size, new MyComparator());
        encoder = new HashMap<>();
    }

    public void createTree(HashMap<Character, Integer> freq) {
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            Node n = new Node(entry.getKey(), entry.getValue());
            pq.add(n);
        }
        while (pq.size() > 1) {
            Node x = pq.peek();
            pq.poll();
            Node y = pq.peek();
            pq.poll();

            Node newNode = new Node();
            newNode.freq = x.freq + y.freq;
            newNode.c = '-';
            newNode.left = x;
            newNode.right = y;

            pq.add(newNode);
        }
        root = pq.peek();
    }

    public void constructCoding(Node node, String s) {
        if (node.left == null && node.right == null) {
            System.out.println(node.c + " : " + s);
            encoder.put(node.c, s);
            return;
        }
        constructCoding(node.left, s + '0');
        constructCoding(node.right, s + '1');
    }

    class MyComparator implements Comparator<Node> {
        @Override
        public int compare(Node x, Node y) {
            return x.freq - y.freq;
        }
    }

    private class Node {
        int freq;
        char c;
        Node left, right;

        Node(char c, int freq) {
            this.freq = freq;
            this.c = c;
            left = null;
            right = null;
        }

        Node() {
        }
    }
}
