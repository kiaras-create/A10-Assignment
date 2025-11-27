import java.io.File;
import java.util.Scanner;

public class HuffTree {

    /**
     * Reads a Huffman tree from a given filename 
     * @param filename the file that contains Huffman code lines
     * @return BinaryTree<Character> the node of the Huffman tree
     */
    public static BinaryTree<Character> readHuffTree(String filename){
        BinaryTree<Character> tree = null; // start with empty tree

        try {
            Scanner scanner = new Scanner (new File(filename));

            // read file line by line
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();

                // skip empty lines
                if (line.isEmpty()) continue; 

                // split line into bit pattern and ASCII value
                String[] parts = line.split ("\\s+");
                String pattern = parts[0];
                int ascii = Integer.parseInt(parts[1]);

                // convert ASCII to char
                char ch = (char) ascii;

                // insert the character into the tree following the bit pattern
                tree = insertIntroTree(tree, pattern, ch);
            
            }

            // close the file scanner
            scanner.close();
        
        } catch (Exception e){
            System.out.println("Error reading Huffman tree:" + e);
        }
        return tree;

    }

    /**
     * This method is for inserting a character into the Huffman tree
     * Using its bit pattern (0=left, 1=right)
     * 
     * @param tree the current Huffman tree
     * @param pattern the bit pattern representing the path to the character
     * @param ch the character to insert
     * @return BinaryTree<Character> the updated tree
     */
    private static BinaryTree<Character> insertIntroTree(BinaryTree<Character> tree, String pattern, char ch){
        // if tree is empty, create a root node
        if (tree == null){
            tree = new BinaryTree<Character>((Character)null); // root has null data initially
        }
        }

        BinaryTree<Character> current = tree; // start at root

        // go through the pattern bit by bit
        for (int i = 0; i < pattern.length(); i++) {
            char bit = pattern.charAt(i);

            if (bit == '0') {

                // go left, create node if needed
                if (current.getLeft() == null){
                    current.setLeft(new BinaryTree<Character>((Character)null));
                }
                current = current.getLeft();
            }else {

                // go right, create node if needed
                if (current.getRight() == null){
                    current.setRight(new BinaryTree<Character>((Character)null));
                }
                current = current.getRight();
            }
        }
        // once we reach the leaf then we store the character here
        current.setData(ch);
        return tree;
    }
}
    

