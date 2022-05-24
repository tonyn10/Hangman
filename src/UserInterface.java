package src;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {
    private WordTracker tracker;
    private WinCondition winCondition;
    private Scanner sc;

    private Hanger hanger;

    public UserInterface(String word, WinCondition winCondition, WordTracker tracker, Hanger hanger) {
        this.winCondition = winCondition;
        this.tracker = tracker;
        this.sc = new Scanner(System.in);

        this.hanger = hanger;
    }

    public void askForLetter() {
        System.out.println();
        System.out.print("Enter a letter: ");
        char c = '\u0000';
        try {
            String s = sc.nextLine();
            if(s.equalsIgnoreCase("esc")) {
                this.winCondition.interrupt();
            }
            else if(UserInterface.isLetter(s)) {
                c = s.charAt(0);
            }
            else {
                throw new InputMismatchException();
            }
        }
        catch(InputMismatchException e) {
            System.out.println("Not a proper character.");
        }

        if(c != '\u0000') {
            this.winCondition.receiveInput(c);
        }
    }

    // returns true if string is a single letter
    public static boolean isLetter(String s) {
        if(s.length() == 1) {
            Pattern p = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            return m.find();
        }
        return false;
    }

    private void displayCurrent() {
        String current = tracker.getCurrent();
        System.out.println("\n");
        for(int i = 0; i < current.length(); i++) {
            System.out.print(current.charAt(i) + " ");
        }
        System.out.println();
    }

    private void displayLives() {
        System.out.println();
        System.out.println("Lives: " + this.winCondition.getLives());
    }

    public void displayLettersIncorrect() {
        HashSet<Character> lettersIncorrect = this.tracker.getGuessTracker().getLettersIncorrect();
        // only display it if have letters guessed
        if(lettersIncorrect.size() >= 1) {
            System.out.println();
            System.out.print("Incorrect guesses: ");
            for(char c : lettersIncorrect) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public void displayBoard() {
        System.out.println();   // provide line space from previous board
        System.out.println(this.hanger.toString());
        this.displayCurrent();
    }

    public void displayResults() {
        System.out.println();
        System.out.println("Game end.");
        if(this.winCondition.isWin()) {
            System.out.println("You win!");
        }
        else if(this.winCondition.isLose()) {
            System.out.println("You lose!");
            System.out.println("Word: " + this.tracker.getAnswer());
        }
        else {
            System.out.println("Game unfinished.");
        }
    }

    public void closeScanner() {
        this.sc.close();
    }

}