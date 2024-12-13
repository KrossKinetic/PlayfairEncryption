/**
 * Represents the main engine that uses Phrase, Bigram and KeyTable java files to encrypt given text.
 */

import java.util.Scanner;

public class PlayfairEncryptionEngine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter key phrase: ");
        String key_s = scanner.nextLine();
        KeyTable key = KeyTable.buildFromString(key_s);
        System.out.println("Generation success!");

        System.out.println("Menu:");
        System.out.println(
            "(CK) - Change key\n" +
            "(PK) - Print key\n" +
            "(EN) - Encrypt\n" +
            "(DE) - Decrypt\n" +
            "(Q) - Quit"
        );
        
        Boolean keepGoing = true;
        Phrase phrase = new Phrase();
        while (keepGoing){
            System.out.print("Please select an option: ");
            String opn = scanner.nextLine();
            if (opn.equalsIgnoreCase("Q")){
                keepGoing = false;
                System.out.println("Program terminating...");
                continue;
            }

            if (opn.equalsIgnoreCase("PK")){
                System.out.println(key.toString());
            } else if(opn.equalsIgnoreCase("CK")){
                System.out.print("Enter key phrase: ");
                key_s = scanner.nextLine();
                key = KeyTable.buildFromString(key_s);
                System.out.println("Generation success!");
            } else if(opn.equalsIgnoreCase("EN")){
                System.out.print("Please enter a phrase to encrypt: ");
                String s = scanner.nextLine();
                phrase = Phrase.buildPhraseFromStringforEnc(s);
                phrase = phrase.encrypt(key);
                System.out.println("Encrypted text is: " + phrase.toString());
            } else if(opn.equalsIgnoreCase("DE")){
                System.out.print("Please enter a phrase to decrypt: ");
                String s = scanner.nextLine();
                phrase = Phrase.buildPhraseFromStringforDec(s);
                phrase = phrase.decrypt(key);
                System.out.println("Decrypted text is: " + phrase.toString());
            } else {
                System.out.println("Wrong option. Try again.");
                continue;
            }
        }
        scanner.close();
    }
}
