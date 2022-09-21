package hangman;


import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Hangman game!\nTry to guess the hidden word.");

        boolean keepPlaying = true;

        while (keepPlaying) {

            Hangman game = new Hangman();
            do {
                System.out.println();
                System.out.println(game.drawPicture());

                System.out.println(game.getCurrentGuess());

                System.out.print("Make a character guess:");
                char guess = sc.next().toLowerCase().charAt(0);

                while (game.isGuessed(guess)) {
                    System.out.println("Make another guess.You have already chosed that.");
                    guess = sc.next().toLowerCase().charAt(0);
                }

                if (game.checkGuess(guess)) {
                    System.out.println("You found one.");
                } else {
                    System.out.println("Isn't in the word..");
                }
            }
            while (!game.gameOver());


            System.out.println("\nDo want to play again?");
            System.out.print("Write yes or no:");

            String answer = sc.next();
            if (answer.trim().equalsIgnoreCase("yes")) {
                System.out.println("One more round...");
            } else if (answer.trim().equalsIgnoreCase("no")) {
                System.out.println("Thanks for playing.");
                keepPlaying = false;
            } else {
                System.out.println("Wrong input.");
                keepPlaying = false;
            }
        }
    }
}
