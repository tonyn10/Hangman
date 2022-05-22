package src;
import java.util.ArrayList;
import java.util.HashMap;

public class WordTracker {
    private String word;
    private HashMap<Character, ArrayList<Integer>> includedLetters;
    private int lettersCorrect;
    private String currentWord;
    private StringBuilder sb;

    private GuessTracker guessTracker;

    public WordTracker(String word) {
        this.word = word;
        this.initCurrentWord(word);

        this.includedLetters = getIncludedLetters(word);
        this.sb = new StringBuilder(currentWord);
        this.guessTracker = new GuessTracker();
        this.lettersCorrect = 0;
    }

    private void initCurrentWord(String word) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++) {
            sb.append("_");
        }
        this.currentWord = sb.toString();
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

    private HashMap<Character, ArrayList<Integer>> getIncludedLetters(String word) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<Character, ArrayList<Integer>>();

        for(int i = 0; i < word.length(); i++) {
            char c = WordTracker.getLowerCase(word.charAt(i)); // use lower case letters as keys
            if(!map.containsKey(c)) {
                // first index, so make new list
                ArrayList<Integer> listOfIndices = new ArrayList<Integer>();
                listOfIndices.add(i);
                map.put(c, listOfIndices);
            }
            else {
                map.get(c).add(i); // add the index to the already existing list
            }
        }

        return map;
    }

    public boolean areAllLettersCorrect() {
        return (this.lettersCorrect == this.word.length());
    }

    private boolean isCharIncluded(char c) {
        if(this.includedLetters.containsKey(c)) {
            return true;
        }
        return false;
    }

    private void fillOut(char c) {
        // fill out current word
        ArrayList<Integer> indices = this.includedLetters.get(c);
        for(int i : indices) {
            this.sb.setCharAt(i, this.word.charAt(i));
            this.lettersCorrect++;
        }
        this.currentWord = this.sb.toString();
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
