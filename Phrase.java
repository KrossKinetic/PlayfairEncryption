/**
 * Defines a Phrase queue by extending LinkedList and queue interface.
 */

import java.util.LinkedList;

public class Phrase extends LinkedList<Bigram>{
    /**
    * Adds a bigram to the end of the phrase.
    *
    * @param b The bigram to be added to the phrase.
    * @custom.precondition The 'b' object should not be null.
    * @custom.postcondition The provided bigram is added to the end of the phrase.
    */
    public void enqueue(Bigram b){
        this.offer(b);
    }

    /**
    * Removes and returns the bigram at the front of the phrase.
    *
    * @return The bigram at the front of the phrase, or null if the phrase is empty.
    * @custom.postcondition The bigram at the front of the phrase is removed and returned, or null is returned if the phrase is empty.
    */
    public Bigram dequeue(){
        return this.poll();
    }

    /**
     * Builds a phrase from a given string for encryption, handling duplicates and padding.
    *
    * @param s The string to build the phrase from.
    * @return The phrase built from the given string, ready for encryption.
    * @custom.precondition The 's' string should not be null.
    * @custom.postcondition A new Phrase object is created and returned, containing bigrams built from the input string.
    */
    public static Phrase buildPhraseFromStringforEnc(String s){
        Phrase p = new Phrase();
        Bigram b;
        s = s.toUpperCase();
        s = s.replace("J", "I");
        s = s.replaceAll("(.)\\1", "$1X$1");
        s = s.replaceAll("[^A-Z]+", "");
        if (s.length()%2 != 0){
            s = s + "X";
        }

        char[] new_s = s.toCharArray();  
        for (int i = 0; i < new_s.length; i+=2){
            b = new Bigram();
            b.setFirst(new_s[i]);
            b.setSecond(new_s[i+1]);
            p.enqueue(b);
        }
        return p;
    }

    /**
     * Builds a phrase from a given string for decryption.
    *
    * @param s The string to build the phrase from.
    * @return The phrase built from the given string, ready for decryption.
    * @custom.precondition The 's' string should not be null.
    * @custom.postcondition A new Phrase object is created and returned, containing bigrams built from the input string.
    */
    public static Phrase buildPhraseFromStringforDec(String s){
        Phrase p = new Phrase();
        Bigram b;
        char[] new_s = s.toUpperCase().toCharArray();  
        for (int i = 0; i < new_s.length; i+=2){
            b = new Bigram();
            b.setFirst(new_s[i]);
            b.setSecond(new_s[i+1]);
            p.enqueue(b);
        }

        return p;
    }

    /**
     * Encrypts the phrase using the Playfair cipher with the given key table.
    *
    * @param key The key table to use for encryption.
    * @return The encrypted phrase.
    * @custom.precondition The 'key' object should not be null.
    * @custom.postcondition A new Phrase object is created and returned, containing the encrypted bigrams of the phrase using the key table.
    */
    public Phrase encrypt(KeyTable key){
        Phrase encrypted = new Phrase();
        char[][] key_table = key.getKeyTable();
        while (true){
            Bigram b  = this.dequeue();
            if (b == null) break;

            int first_row = key.findRow(b.getFirst());
            int first_col = key.findCol(b.getFirst());
            int second_row = key.findRow(b.getSecond());
            int second_col = key.findCol(b.getSecond());
            
            Bigram temp = new Bigram();
            
            if (first_row == second_row){
                temp.setFirst(key_table[first_row][(((first_col+1) % 5) + 5) % 5]);
                temp.setSecond(key_table[first_row][(((second_col+1) % 5) + 5) % 5]);
            } else if (first_col == second_col){
                temp.setFirst(key_table[(((first_row+1) % 5) + 5) % 5][first_col]);
                temp.setSecond(key_table[(((second_row+1) % 5) + 5) % 5][second_col]);
            } else {
                temp.setFirst(key_table[first_row][second_col]);
                temp.setSecond(key_table[second_row][first_col]);
            }
            encrypted.enqueue(temp);
        }

        return encrypted;
    }

    /**
    * Decrypts the phrase using the Playfair cipher with the given key table.
    *
    * @param key The key table to use for decryption.
    * @return The decrypted phrase.
    * @custom.precondition The 'key' object should not be null.
    * @custom.postcondition A new Phrase object is created and returned, containing the decrypted bigrams of the phrase using the key table.
    */
    public Phrase decrypt(KeyTable key){
        Phrase decrypted = new Phrase();
        char[][] key_table = key.getKeyTable();
        while (true){
            Bigram b = this.dequeue();
            if (b == null) break;

            int first_row = key.findRow(b.getFirst()), first_col = key.findCol(b.getFirst());
            int second_row = key.findRow(b.getSecond()), second_col = key.findCol(b.getSecond());
            
            Bigram temp = new Bigram();
            if (first_row == second_row){
                temp.setFirst(key_table[first_row][(((first_col-1) % 5) + 5) % 5]);
                temp.setSecond(key_table[second_row][(((second_col-1) % 5) + 5) % 5]);
            } else if (first_col == second_col){
                temp.setFirst(key_table[(((first_row-1) % 5) + 5) % 5][first_col]);
                temp.setSecond(key_table[(((second_row-1) % 5) + 5) % 5][second_col]);
            } else {
                temp.setFirst(key_table[first_row][second_col]);
                temp.setSecond(key_table[second_row][first_col]);
            }

            decrypted.enqueue(temp);
        }
        return decrypted;
    }

    /**
    * Returns the first element of queue without removing it.
    *
    * @return The first element of the queue.
    */
    public Bigram peek(){
        // Doesn't need implementation since its already being extended but hw4.pdf didn't say I can skip this if i am extending so im keeping it anyways.
        return this.peek();
    }

    /**
    * Returns a string representation of the phrase.
    *
    * @return The string representation of the phrase.
    * @custom.postcondition The string representation of the phrase is returned.
    */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Bigram b : this){
            sb.append(b.toString());
        }
        return sb.toString();
    }
} 