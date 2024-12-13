/**
 * KeyTable holds the key for encryption and decryption in a 2D Array. Utilizes LinkedHashSet to remove duplicates.
 */

import java.util.LinkedHashSet; 

public class KeyTable{
    private char[][] key = new char[5][5];
    
    /**
     * Constructs an empty KeyTable object.
     */
    public KeyTable(){}

    /**
     * Constructs a KeyTable object with the given 2D character array as the key table.
     * 
     * @param key The 2D character array representing the key table.
     * @custom.precondition The 'key' array should not be null.
     */
    public KeyTable(char[][] key){
        this.key = key;
    }

    /**
     * Builds a KeyTable from a given string phrase.
     * 
     * @param phrase The string to build the KeyTable from.
     * @return The KeyTable generated.
     * @custom.precondition The 'phrase' string should not be null.
     * @custom.postcondition A new KeyTable object is created and returned.
     * @throws IllegalArgumentException if the input phrase is null.
     */
    public static KeyTable buildFromString(String phrase){
        if (phrase == null){
            throw new IllegalArgumentException();
        }

        String new_string = "";
        phrase = phrase.toUpperCase();
        
        phrase = phrase.replace(" ", "");
        phrase = phrase.replace("J", "");
        phrase = phrase.replaceAll("[^A-Z]+", "");
        
        LinkedHashSet<Character> seen = new LinkedHashSet<>();
        for (char c : phrase.toCharArray()) seen.add(c);
        StringBuilder sb = new StringBuilder(seen.size());
        for (Character c : seen) sb.append(c);
        phrase = sb.toString();

        for (char c = 'A'; c <= 'Z'; c++){
            if (!contains(phrase,c)){
                if (c == 'J') continue;
                new_string = new_string + c;
            } 
        }

        new_string = phrase + new_string;
        char[][] key = new char[5][5];
        int count = 0;
        for (int j = 0; j < 5; j++){
            for (int k = 0; k < 5; k++){
                key[j][k] = new_string.charAt(count);
                count++;
            }
        }

        return new KeyTable(key);
    }

    /**
     * Checks if a given character is present in a string.
     * 
     * @param s The string to search in.
     * @param a The character to search for.
     * @return True if the character is found in the string, false if not.
     */
    private static boolean contains(String s, char a){
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == a){
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for the key table.
     * 
     * @return The key table.
     */
    public char[][] getKeyTable() {
        return key;
    }

    /**
     * Finds the row index of a character in the key table.
     * 
     * @param c The character to search for.
     * @return The row index of the character in the key table.
     * @throws IllegalArgumentException if the character is not found in the key table.
     */
    public int findRow(char c){
        for (int j = 0; j < 5; j++){
            for (int k = 0; k < 5; k++){
                if (key[j][k] == c){
                    return j;
                }
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     * Finds the column index of a character in the key table.
     * 
     * @param c The character to search for.
     * @return The column index of the character in the key table.
     * @throws IllegalArgumentException if the character is not found in the key table.
     */
    public int findCol(char c){
        for (int j = 0; j < 5; j++){
            for (int k = 0; k < 5; k++){
                if (key[j][k] == c){
                    return k;
                }
            }
        }
        
        throw new IllegalArgumentException();
    }

    /**
     * Returns a string representation of the KeyTable.
     * 
     * @return The string representation of the KeyTable.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[i].length; j++) {
                sb.append(key[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }    

}
