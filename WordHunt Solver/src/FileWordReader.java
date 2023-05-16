import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class FileWordReader {

    public static HashSet<String> readFile(String filePath) {
        HashSet<String> words = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split("\\s+"); // Split the line into words using whitespace as delimiter
                words.addAll(Arrays.asList(lineWords));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

    public static void main(String[] args) {
        FileWordReader wordReader = new FileWordReader();
        String filePath = "C:\\Users\\teklo\\IdeaProjects\\WordHunt Solver\\src\\words.txt";
        HashSet<String> words = wordReader.readFile(filePath);

        // Print the words in the HashSet
        for (String word : words) {
            System.out.println(word);
        }
    }
}
