
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Words words = new Words();
        Scanner scan = new Scanner(System.in);
        int length = 48;
        String language = "english";
        System.out.print("What language do you want to use(press 1 for Turkish and 2 for English)=");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                language = "turkish";
                break;
            case 2:
                language = "english";
                break;
            default:
                System.out.println("Wrong input.");
        }
        System.out.print("The standard  word count is 50. \n" +
                "Press 0 to change.If you don't want to change , press something else =");
        choice = scan.nextInt();
        if (choice == 0) {
            System.out.print("Enter the word count= ");
            length = scan.nextInt();

        }
        String[] generatedWords = words.generateRandomWords(length, language);

        System.out.println("You have to enter each word in order.If you can't write the word, press the space and skip the current word." +
                "\nPress enter at the end of the line.");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Be ready starts in \n3 seconds...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2 seconds...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1 seconds...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("GOO!");

        int newLine = length / 3 + 1;
        for (int i = 0; i < length; i++) {
            if (i % newLine == 0) {
                System.out.println();
            }
            System.out.print(generatedWords[i] + " ");
        }

        System.out.println("\n\n");
        scan.nextLine();

        double start = LocalTime.now().toNanoOfDay();
        String inputWord1 = scan.nextLine();
        String inputWord2 = scan.nextLine();
        String inputWord3 = scan.nextLine();
        double end = LocalTime.now().toNanoOfDay();

        double duration = (end - start) / 1000000000.0;

        String finalInput = inputWord1.trim() + " " + inputWord2.trim() + " " + inputWord3.trim();
        String[] inputWords = finalInput.split(" ");

        int trueWordCount = 0;
        int wordCount = inputWords.length;
        int letterCount = 0;
        int trueLetterCount = 0;

        for (int i = 0; i < length; i++) {
            if (generatedWords[i].equals(inputWords[i])) {
                trueWordCount++;
                trueLetterCount += generatedWords[i].length();
            }
            letterCount += generatedWords[i].length();
        }
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        System.out.println("\nRESULTS:");
        System.out.println("You writed " + trueWordCount + " words out of " + length + " correctly.");
        System.out.println("It took you a total of " + duration + " seconds to type these words.");
        System.out.println("You wrote a total of " + letterCount + " letters.");
        System.out.println("Average word length was " + df.format((double) letterCount / length) + " letters.");
        System.out.println("On average you type " + df.format(trueWordCount / duration) + " words and " + df.format(trueLetterCount / duration)
                + " letters correctly in one second.");
        System.out.println("Rate of spelling words correctly:%" + df.format((trueWordCount / (double) length) * 100));


    }
}
