import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {
    private WordTracker tracker;
    private WinCondition winCondition;
    private Scanner sc;

    private final Pattern PATTERN;

    public UserInterface(String word, WinCondition winCondition, WordTracker tracker) {
        this.winCondition = winCondition;
        this.tracker = tracker;
        this.sc = new Scanner(System.in);

        PATTERN = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
    }

    public void askForLetter() {
        System.out.println();
        System.out.print("Enter a letter: ");
        char c = '\u0000';
        try {
            String s = sc.nextLine();
            Matcher matcher = PATTERN.matcher(s);
            if(s.equalsIgnoreCase("esc")) {
                this.winCondition.interrupt();
            }
            else if(s.length() == 1 && matcher.find()) {
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
        String s = "";
        for(char ch : lettersIncorrect) {
            s = s + ch + " ";
        }

        // print nothing if there are no incorrect letters yet
        if(s.length() != 0) {
            System.out.println();
            System.out.println(s);
        }
    }

    public void displayBoard() {
        this.displayCurrent();
        this.displayLives();
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