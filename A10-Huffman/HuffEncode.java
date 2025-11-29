import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class HuffEncode {

    /**
     * hashmap that will hold character keys and its corresponding bit pattern
     */
    public static HashMap<Character, String> encodeMap = new HashMap<>();

    /**
     * creates a hashmap based off of characters and its corresponding ASCII values
     * 
     * @param filename, the filename of characters and its ASCII values
     * @return nothing
     */
    public static void buildMap(String filename) {
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot locate file.");
            // exits the system, flagging that program ended bcs of an error/exception
            System.exit(-1);
        }

        // while the file has a line, go through the loop
        while (file.hasNextLine()) {
            // create a string line based off of next line in file
            String line = file.nextLine();

            // if the line is empty, continue
            if (line.isEmpty()) {
                continue;
            }

            // create an array by splitting the line based off of spaces
            String[] split = line.split("\\s+");

            // stores the bit pattern (at index 0) as an string called pattern
            String pattern = split[0];
            // stores the ASCII value as an integer called ascii
            int ascii = Integer.parseInt(split[1]);
            // converts the ascii value into its corresponding character
            char ch = (char) ascii;

            // stores the character and its corresponding bit pattern value into the hashmap
            encodeMap.put(ch, pattern);
        }
        file.close();

    }

    /**
     * converts plain text to encoded text by using hashmap/hash table
     * 
     * @param filename, the file name containing text we want to encode
     * @param table,    the hashmap/hashtable containing characters and its
     *                  corresponding ASCII values
     * @return the encoded text
     */
    public static String encodeText(String filename, Map<Character, String> table) {
        Scanner file = null;
        // will store the bit pattern values
        StringBuilder encodedText = new StringBuilder();


        // creates a scanner based off founded file
        try {
            file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot locate file.");
            // exits the system, flagging that program ended bcs of an error/exception
            System.exit(-1);
        }

        // loops through each line in the file
        while (file.hasNextLine()) {
            // store the next line in attribute, "line"
            String line = file.nextLine();
            // loops through the string/line for each character
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                // finds the character key in the hashmap (ascii)
                // appends the key's value to encodedText
                encodedText.append(encodeMap.get(c));
            }

        }
        file.close();

        System.out.println("This is the encoded text " + encodedText);
        return encodedText.toString();

    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java HuffEncode <codeFile> <plaintextFile>");
            return;
        }

        String codeFile = args[0];
        String plaintextFile = args[1];

        // build the encoding table
        buildMap("DefaultTree.txt");

        // encode the text from the file
        encodeText("Original.txt", encodeMap);

    }
}
