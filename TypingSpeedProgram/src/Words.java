import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Words {
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;
    private ArrayList<String> dictionary = new ArrayList<>();
    private int low = 0, high, index, i = 0;
    private String txtLocation;


    public void initializeDictionary(String txtFile) throws IOException {
        try {
            File file = new File(txtFile);
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

    public String[] generateRandomWords(int length, String language) throws IOException {
        Set<String> list = new HashSet<>();
        Random r = new Random();
        if (language.equalsIgnoreCase("english")) {
            high = 996;
            txtLocation = "words.txt";
        } else if (language.equalsIgnoreCase("turkish")) {
            high = 580;
            txtLocation = "kelimeler.txt";
        }

        initializeDictionary(txtLocation);

        while (list.size() < length) {
            index = r.nextInt(high - low) + low;
            if (dictionary.get(index).trim().length() > 1) {
                list.add(dictionary.get(index));
            }

        }
        String randomWords[] = new String[length];
        int j = 0;
        for (String s : list) {
            randomWords[j++] = s;
        }
        return randomWords;
    }

}
