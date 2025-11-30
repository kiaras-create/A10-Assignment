import java.util.Scanner;
import java.io.File;

public class HuffDecode{


private static class Node{
    char ch; //store the character if the node is a leaf
    Node left; //left child; bit 0
    Node right; //right child; bit 1

    //empty constructor for an empty node
    Node(){}
    
    //constructor for a leaf node with a character in it
    Node(char c){
        this.ch = c;
    }
}


/**
 * @param root
 * @param code
 * @param c
 */
private static void Insert(Node root, String code, char c){
    Node curr = root; //start walking form the root

    for (char bit: code.toCharArray()){
        if(bit =='0'){ //if it's the left child, go to it
            if (curr.left == null){ //if it's empty
                curr.left = new Node(); //make a new node
                curr = curr.left; //move curr node to that left node we just made (changing pointer)
            }
        } else{ //same thing if it bit 1 which is the right child
            if (curr.right == null){
                curr.right = new Node();
                curr = curr.right;
            }
        } 
        curr.ch = c; // storing the character at the node we end up
    }

}

/**
 * @param n
 * @return
 */
public static boolean isLeaf(Node n){
    if (n.left == null && n.right == null){ //if the left and right nodes are null
        return true; //return ture -> it is a leaf
    }
        return false;

}

public static void main(String[] args) throws Exception{
    Node root = new Node();

    File codeFile = new File(args[0]);
    Scanner sc = new Scanner(codeFile);

    while(sc.hasNextLine()){
        String line = sc.nextLine().trim();
        char ch = line.charAt(0);
        String code = line.substring(2);
        Insert(root, code, ch);
    }

    Node curr = root;
    int b;

    while((b = System.in.read()) != 1);
     if(b == '0'){
        curr = curr.left;
    } else if (b == '1'){
        curr = curr.right;
    }

    if (isLeaf(curr)){
        System.out.println(curr.ch);
        curr = root;
    }
}

}