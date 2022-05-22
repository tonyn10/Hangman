package src;
public class WinCondition {
    private int lives;
    private boolean win;
    private boolean lose;
    private boolean interrupted;
    private WordTracker tracker;

    public WinCondition(String word, WordTracker tracker) {
        this.tracker = tracker;
        this.lives = 6;

        this.win = false;
        this.lose = false;
        this.interrupted = false;
    }

    public void receiveInput(char c) {
        if(!this.tracker.takeInput(c)) {
            this.decreaseLives();
        }
    }

    private void decreaseLives() {
        this.lives--;
    }

    public void checkWinLoseCondition() {
        if(this.tracker.areAllLettersCorrect()) {
            this.win = true;
        }
        else if(this.lives == 0) {
            this.lose = true;
        }
    }

    public int getLives() {
        return this.lives;
    }

    public boolean isLose() {
        return this.lose;
    }

    public boolean isWin() {
        return this.win;
    }

    public boolean isInterrupted() {
        return this.interrupted;
    }

    public void interrupt() {
        this.interrupted = true;
    }

    public boolean isGameEnd() {
        if(this.lose || this.win || this.interrupted) {
            return true;
        }
        return false;
    }

}