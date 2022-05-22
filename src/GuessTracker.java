package src;
import java.util.HashSet;

public class GuessTracker {
    HashSet<Character> lettersIncorrect;
    HashSet<Character> lettersCorrect;

    public GuessTracker() {
        lettersIncorrect = new HashSet<Character>();
        lettersCorrect = new HashSet<Character>();
    }

    public void addIncorrect(char c) {
        lettersIncorrect.add(c);
    }

    public void addCorrect(char c) {
        lettersCorrect.add(c);
    }

    public HashSet<Character> getLettersIncorrect() {
        return lettersIncorrect;
    }

    // returns true if letter was already guessed
    public boolean contains(char c) {
        if(lettersIncorrect.contains(c) || lettersCorrect.contains(c)) {
            return true;
        }
        return false;
    }

}
