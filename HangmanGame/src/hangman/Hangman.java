package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
    private String hiddenWord;
    private StringBuilder currentGuess;
    private ArrayList<Character> previousGuesses = new ArrayList<>();

    private int maxTryCount = 6;
    private int currentTryCount = 0;

    private ArrayList<String> dictionary = new ArrayList<>();

    private static FileReader fileReader;
    private static BufferedReader bufferedReader;

    public Hangman() throws IOException {
        initializeDictionary();
        hiddenWord = pickRandomWord();
        currentGuess = initializeCurrentGuess();
    }

    public void initializeDictionary() throws IOException {
        try {
            File file = new File("dictionary.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();

            while (currentLine != null) {
                dictionary.add(currentLine);
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException io) {
            System.out.println("Couldn't open the txt file.");
        }
    }

    public String pickRandomWord() {
        Random random = new Random();
        int index = Math.abs(random.nextInt() % dictionary.size());
        return dictionary.get(index);
    }

    public StringBuilder initializeCurrentGuess() {
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < hiddenWord.length() * 2; i++) {
            if (i % 2 == 0) {
                current.append("_");
            } else {
                current.append(" ");
            }
        }

        return current;
    }

    public boolean isGuessed(char guess) {
        return previousGuesses.contains(guess);
    }

    public boolean checkGuess(char guess) {
        boolean control = false;
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hiddenWord.charAt(i) == guess) {
                currentGuess.setCharAt(i * 2, guess);
                control = true;
                previousGuesses.add(guess);
            }
        }

        if (!control) {
            currentTryCount++;
        }

        return control;
    }

    public String getCurrentGuess() {
        return "Current Guess:" + currentGuess.toString();
    }

    public String drawPicture() {

        switch (currentTryCount) {
            case 0:
                return startPosition();
            case 1:
                return addHead();
            case 2:
                return addBody();
            case 3:
                return addOneArm();
            case 4:
                return addOtherArm();
            case 5:
                return addLeg();
            default:
                return fullPerson();

        }
    }

    private String addLeg() {
        return " - - - - -\n" +
                "|        |\n" +
                "|        O\n" +
                "|      / | \\ \n" +
                "|        |\n" +
                "|       /  \n" +
                "|\n" +
                "|\n";
    }

    private String fullPerson() {
        return " - - - - -\n" +
                "|        |\n" +
                "|        O\n" +
                "|      / | \\ \n" +
                "|        |\n" +
                "|       / \\ \n" +
                "|\n" +
                "|\n";
    }

    private String addOtherArm() {
        return " - - - - -\n" +
                "|        |\n" +
                "|        O\n" +
                "|      / | \\ \n" +
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String addOneArm() {
        return " - - - - -\n" +
                "|        |\n" +
                "|        O\n" +
                "|      / |  \n" +
                "|        |\n" +
                "|       \n" +
                "|\n" +
                "|\n";
    }

    private String addBody() {
        return " - - - - - \n" +
                "|        |\n" +
                "|        O\n" +
                "|        |  \n" +
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String addHead() {
        return " - - - - - -\n" +
                "|        |\n" +
                "|        O\n" +
                "|      \n" +
                "|        \n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String startPosition() {
        return " - - - - - \n" +
                "|        |\n" +
                "|        \n" +
                "|       \n" +
                "|        \n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    public boolean gameOver() {
        if (didWeWin()) {
            System.out.println("You won...");
            System.out.println("It was \"" + hiddenWord + "\"");
            return true;
        } else if (didWeLose()) {
            System.out.println("You lost...");
            System.out.println("The word was:" + hiddenWord);
            return true;
        }

        return false;
    }

    private boolean didWeLose() {
        return currentTryCount >= maxTryCount;
    }

    private boolean didWeWin() {
        String guessWord = guessWord();
        return guessWord.equals(hiddenWord);
    }

    private String guessWord() {
        String guess = currentGuess.toString();
        return guess.replace(" ", "");

    }


}
