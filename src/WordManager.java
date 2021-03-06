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

    // returns whether the file was successfully acquired
    private File getFile() {
        String[] fileNames = {"TextFiles\\CountryWords.txt", "TextFiles\\LeagueWords.txt", "TextFiles\\StatesWords.txt"};
        // get random file name
        File f = new File(fileNames[(int) (Math.random() * fileNames.length)]);
        return f;
    }

    private String getWord() {
        String chosenWord = "";

        File file = this.getFile();

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // ArrayList has constant time insertion and access
        ArrayList<String> possibleWords = null;
        if(fileScanner != null) {
            this.topic = fileScanner.nextLine(); // first line of the text file is the topic

            possibleWords = new ArrayList<String>();
            while(fileScanner.hasNextLine()) {
                possibleWords.add(fileScanner.nextLine());
            }

            fileScanner.close();
        }
        
        if(possibleWords != null)  {
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
