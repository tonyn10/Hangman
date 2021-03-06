package src;
public class Main {
    public static void main(String[] args) {
        System.out.println("Type \"esc\" to exit the game.");
        WordManager manager = new WordManager();
        String word = manager.getAnswer();
        System.out.println("Topic: " + manager.getTopic());

        WordTracker tracker = new WordTracker(word);
        Hanger hanger = new Hanger();
        WinCondition winCondition = new WinCondition(word, tracker, hanger);
        UserInterface ui = new UserInterface(word, winCondition, tracker, hanger);

        while(!winCondition.isGameEnd()) {
            ui.displayBoard();
            ui.displayLettersIncorrect();
            ui.askForLetter();
            winCondition.checkWinLoseCondition();
        }
        ui.displayBoard();  // one last time for end results
        ui.closeScanner();

        ui.displayResults();
    }
}