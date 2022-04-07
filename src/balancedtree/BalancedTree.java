package balancedtree;

/**
 * @author Berkay Acar
 * @since 25.12.2021
 */

import java.io.BufferedReader;
import java.io.FileReader;
public class BalancedTree {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.dosyaOku();
        bst.insertTree();
        System.out.println(bst.isBalanced(bst.root));
    }
}
class Node {
    int key;
    Node left;
    Node right;

    public Node(int item)
    {
        key = item;
        left =null;
        right =null;
    }
}
class BST {
    static String[] split;
    Node root;

    public BST() {
        root = null;
    }

    public void dosyaOku() {
        try (BufferedReader okuyucu = new BufferedReader(new FileReader("input.txt"))) {
            String text;
            while ((text = okuyucu.readLine()) != null) {
                split = text.split(" ");
                return;//ilk satırı oku ve fonksiyonu sonlandır.
            }
        } catch (Exception e) {
            System.out.println("Dosya bulunamadı");
        }
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    public void insertTree() {
        for (int i = 0; i < split.length; i++) {
            insert(Integer.valueOf(split[i]));
        }
    }

    public Node insertRecursive(Node now, int value) {
        if (now == null) {//Root boşsa ağaca root ekle
            return new Node(value);
        }
        if (value < now.key) {//Yeni gelen değer şimdiki değerden küçükse sola ekle
            now.left = insertRecursive(now.left, value);
        } else if (value > now.key) {//Yeni gelen değer şimdiki değerden büyükse sağa ekle
            now.right = insertRecursive(now.right, value);
        } else {
            return now;
        }
        return now;
    }

    public boolean isBalanced(Node node) {
        int rightHeight;
        int leftHeight;
        if (node == null) // Eğer root yoksa ağaç dengeli sayılır.
            return true;

        leftHeight = height(node.left);//Sol çocugun yüksekliğini ölç
        rightHeight = height(node.right);//Sağ çocuğun yüksekliğini ölç

        //Önce root ardından çocuklarının balance olup olmadığını recursive ile kontrol et
        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.left) && isBalanced(node.right))
            return true;
        else
            return false;
    }
    public int height(Node node) {
        if (node == null) return 0; //Eğer node yoksa yüksekliği 0 dır.
        int max=0;
        if (height(node.left) > height(node.right)) {
            max=height(node.left);
        }else{
            max=height(node.right);
        }
        return 1 + max;//1 node un sağı veya soluna inerkenki yükseklik. Max ise o child node un yüksekliği.
    }
}
