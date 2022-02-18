package txt;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    public static void writeSortedNumbersToTxt(String fileName, List<Integer> list) {

        try {
            FileWriter writer = new FileWriter(fileName);

            for (int i = 0; i < list.size(); i++) {
                writer.write(String.valueOf(list.get(i)) + "\n");
            }
            writer.close();
            System.out.println("Dosyaya yazma işlemi tamamlandı..");
        } catch (FileNotFoundException e) {
            System.out.println("Dosyayı oluştururken bir sorun çıktı:" + e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void writeRandomNumbersToTxt(int lines, String fileName) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < lines; i++) {
                Random r = new Random();
                int low = 0;
                int high = lines;
                int result = r.nextInt(high - low) + low;
                String s = String.valueOf(result);
                writer.write(s + "\n");

            }
            writer.close();
            System.out.println("Dosyaya yazma işlemi tamamlandı..");
        } catch (FileNotFoundException e) {
            System.out.println("Dosyayı oluştururken bir sorun çıktı:" + e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static ArrayList<Integer> readFromTxt(String file) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                list.add(Integer.valueOf(line));
                line = reader.readLine();

            }
            reader.close();
            System.out.println("Dosyadan okuma işlemi başarılı.");

        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı:" + e);
        } catch (Exception e) {
            System.out.println("Okuma sırasında hata:" + e);
        }
        return list;

    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        writeRandomNumbersToTxt(500, "random.txt");
        list = readFromTxt("random.txt");
        Collections.sort(list);
        writeSortedNumbersToTxt("sorted.txt", list);

    }
}
