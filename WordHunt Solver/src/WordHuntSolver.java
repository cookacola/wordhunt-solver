import java.util.*;

public class WordHuntSolver{
    private static char[][] graph;
    private static boolean[][] visited;
    private static int rows;
    private static int columns;
    private static Set<String> foundWords;

    public static void main(String[] args) {
        // Sample graph
        char[][] sampleGraph = {
                {'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'},
                {'J', 'K', 'L'}
        };

        // Set the graph and its dimensions
        setGraph(sampleGraph);
        setDimensions();
        searchWords(FileWordReader.readFile("C:\\Users\\teklo\\IdeaProjects\\WordHunt Solver\\src\\words"));

        // Print the found words
        System.out.println("Found words:");
        for (String word : foundWords) {
            System.out.println(word);
        }
    }

    public static void setGraph(char[][] inputGraph) {
        rows = inputGraph.length;
        columns = inputGraph[0].length;
        graph = new char[rows][columns];
        visited = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                graph[i][j] = inputGraph[i][j];
            }
        }
    }

    public static void setDimensions() {
        rows = graph.length;
        columns = graph[0].length;
        visited = new boolean[rows][columns];
    }

    public static PriorityQueue<String> searchWords(Set<String> words) {
        foundWords = new HashSet<>();

        for (String word : words) {
            // Skip words with length <= 2
            if (word.length() <= 2) {
                continue;
            }

            // Reset visited array for each new word
            resetVisitedArray();

            // Search for the word in the graph
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (searchWord(word, i, j, 0)) {
                        foundWords.add(word);
                        break;
                    }
                }
            }
        }

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(String::length));
        priorityQueue.addAll(foundWords);

        return priorityQueue;
    }

    private static void resetVisitedArray() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                visited[i][j] = false;
            }
        }
    }

    private static boolean searchWord(String word, int row, int col, int index) {
        if (index == word.length()) {
            return true; // Base case: the word has been found
        }

        if (row < 0 || row >= rows || col < 0 || col >= columns) {
            return false; // Base case: out of bounds
        }

        if (visited[row][col] || graph[row][col] != word.charAt(index)) {
            return false; // Base case: already visited or character mismatch
        }

        visited[row][col] = true; // Mark the current vertex as visited

        // Search in all 8 adjacent vertices
        boolean result = searchWord(word, row - 1, col - 1, index + 1)
                || searchWord(word, row - 1, col, index + 1)
                || searchWord(word, row - 1, col + 1, index+1)
                || searchWord(word, row, col - 1, index + 1)
                || searchWord(word, row, col + 1, index + 1)
                || searchWord(word, row + 1, col - 1, index + 1)
                || searchWord(word, row + 1, col, index + 1)
                || searchWord(word, row + 1, col + 1, index + 1);
        visited[row][col] = false; // Reset visited flag for backtracking

        return result;
    }

}
