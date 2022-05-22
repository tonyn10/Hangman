package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordManager {
    private String topic;   // topic of the word
    private String word;

    public WordManager() {
        this.word = getWord();
    }

    private String getWord() {
        String chosenWord = "";

        // TODO: add more text files with different topics (randomizing selection of them)
        File file = new File("TextFiles\\CountryWords.txt");

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // ArrayList is best for insertion
        ArrayList<String> possibleWords = null;
        if(fileScanner != null) {
            this.topic = fileScanner.nextLine(); // first line of the text file is the topic

            possibleWords = new ArrayList<String>();
            while(fileScanner.hasNextLine()) {
                possibleWords.add(fileScanner.nextLine());
            }

            fileScanner.close();
        }
        
        if(possibleWords != null) {
            // choosing a random index is inefficient
            int chosenIndex = (int) (Math.random() * possibleWords.size());
            chosenWord = possibleWords.get(chosenIndex);
        }
        
        return chosenWord;
    }

    public String getAnswer() {
        return this.word;
    }

    public String getTopic() {
        return this.topic;
    }
}
