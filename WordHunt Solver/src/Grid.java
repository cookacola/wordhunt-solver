import java.awt.*;
import java.util.PriorityQueue;
import java.util.Set;
import javax.swing.*;

public class Grid extends JFrame {
    static SingleCharacterTextField[][] textFields;

    public Grid() {
        setTitle("Graph Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(5, 4));

        textFields = new SingleCharacterTextField[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                textFields[i][j] = new SingleCharacterTextField();
                textFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                add(textFields[i][j]);
            }
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> printWords());
        add(submitButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            resetGrid(); // Reset the grid
        });
        add(resetButton);


        setVisible(true);
    }

    public static void printWords() {
        char[][] graph = new char[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String input = textFields[i][j].getText().trim();
                char value = input.isEmpty() ? ' ' : input.charAt(0);
                graph[i][j] = value;
            }
        }

        System.out.println("Graph:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }



        WordHuntSolver.setGraph(graph);
        WordHuntSolver.setDimensions();

        // Specify the path to the word list file
        String wordListFilePath = "C:\\Users\\teklo\\IdeaProjects\\WordHunt Solver\\src\\words";

        // Read the words from the file
        Set<String> wordList = FileWordReader.readFile(wordListFilePath);

        // Search for words in the graph
        PriorityQueue<String> foundWords = WordHuntSolver.searchWords(wordList);

        System.out.println("Found words:");
        while (!foundWords.isEmpty()) {
            System.out.println(foundWords.poll());
        }
    }

    public void resetGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                textFields[i][j].setText(""); // Clear the text in each text field
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Grid::new);
    }
}
