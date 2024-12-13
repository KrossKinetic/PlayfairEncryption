/**
 * Defines a Bigram which is a collection of 2 chars.
 */

public class Bigram {
    private char first;
    private char second;

    /**
     * Constructs a new Bigram object with default values.
     */
    public Bigram(){};

    /**
     * Constructs a new Bigram object with the specified characters.
     * @param a The first character of the bigram.
     * @param b The second character of the bigram.
     */
    public Bigram(char a, char b){
        this.first = a;
        this.second = b;
    }
    
    /**
     * Getters and Setters for char first and second.
     */
    public char getSecond() {
        return second;
    }
    public void setSecond(char second) {
        this.second = second;
    }
    public char getFirst() {
        return first;
    }
    public void setFirst(char first) {
        this.first = first;
    }
    
    /**
     * Returns a string representation of the bigram.
     * @return A string representation of the bigram in the format first followed by the second.
     */
    public String toString(){
        return ""+first+second;
    }
}
