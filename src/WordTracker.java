package src;
import java.util.Arrays;

public class WordTracker {
    private String word;
    private char[] sortedLetters;
    private int lettersCorrect;
    private String currentWord;
    private StringBuilder sb;

    private GuessTracker guessTracker;

    public WordTracker(String word) {
        this.word = word;
        this.initCurrentWord(word);
        this.sortedLetters = getSortedLetters(word);
        this.sb = new StringBuilder(currentWord);
        this.guessTracker = new GuessTracker();
        this.lettersCorrect = 0;
    }

    private void initCurrentWord(String word) {
        this.currentWord = "";
        for(int i = 0; i < word.length(); i++) {
            this.currentWord += "_";
        }
    }

    // returns boolean whether decrease lives or not
    // if it was already guessed, return true
    public boolean takeInput(char c) {
        c = getLowerCase(c);   // always treat letter as lowercase for convenience
        if(!this.guessTracker.contains(c)) {
            if(isCharIncluded(c)) {
                this.guessTracker.addCorrect(c);
                this.fillOut(c);
                return true;
            }
            else {
                this.guessTracker.addIncorrect(c);
                return false;
            }
        }
        else {
            return true;    // don't decrease lives if letter already guessed
        }
    }

    public static char getLowerCase(char c) {
        if(Character.isUpperCase(c)) {  // only convert it to lowercase if uppercase
            return Character.toLowerCase(c);
        }
        return c;
    }

    private char[] getSortedLetters(String word) {
        // TODO: more efficient way is to use LinkedHashMap, mapping letters with their indices

        // word is all lowercase for convenience
        char[] temp = word.toLowerCase().toCharArray(); 
        Arrays.sort(temp);
        return temp;
    }

    public boolean areAllLettersCorrect() {
        return (this.lettersCorrect == this.word.length());
    }

    private boolean isCharIncluded(char c) {
        if(Arrays.binarySearch(this.sortedLetters, c) >= 0) {
            return true;
        }
        return false;
    }

    private void fillOut(char c) {
        // fill out current word
        for(int i = 0; i < this.word.length(); i++) {
            if(getLowerCase(this.word.charAt(i)) == c) {    // charAt is constant time
                this.sb.setCharAt(i, this.word.charAt(i));
                this.lettersCorrect++;
            }
        }
        this.currentWord = sb.toString();
    }

    public int getLettersCorrect() {
        return this.lettersCorrect;
    }

    public String getCurrent() {
        return this.currentWord;
    }

    // reveal answer
    public String getAnswer() {
        return this.word;
    }

    public GuessTracker getGuessTracker() {
        return this.guessTracker;
    }
}
