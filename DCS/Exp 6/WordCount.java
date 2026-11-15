import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordCount {

    // Mapper function
    static Map<String, List<Integer>> map(String document) {

        Map<String, List<Integer>> mappedData = new HashMap<>();

        // Convert document to lowercase and split into words
        String[] words = document.toLowerCase().split("\\W+");

        // Generate key-value pairs
        for (String word : words) {

            if (!word.isEmpty()) {

                mappedData
                    .computeIfAbsent(word, k -> new ArrayList<>())
                    .add(1);
            }
        }

        return mappedData;
    }

    // Shuffle and Group function
    static void shuffle(
            Map<String, List<Integer>> finalMap,
            Map<String, List<Integer>> mappedData) {

        for (String word : mappedData.keySet()) {

            finalMap
                .computeIfAbsent(word, k -> new ArrayList<>())
                .addAll(mappedData.get(word));
        }
    }

    // Reducer function
    static Map<String, Integer> reduce(
            Map<String, List<Integer>> groupedData) {

        Map<String, Integer> result = new TreeMap<>();

        for (String word : groupedData.keySet()) {

            int total = 0;

            for (int count : groupedData.get(word)) {
                total += count;
            }

            result.put(word, total);
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, List<Integer>> finalMap = new HashMap<>();

        // Read number of documents
        System.out.print("Enter number of documents: ");
        int n = sc.nextInt();

        sc.nextLine();

        // Read documents and perform mapping
        for (int i = 1; i <= n; i++) {

            System.out.println("Enter Document " + i + ":");

            String document = sc.nextLine();

            Map<String, List<Integer>> mappedData = map(document);

            shuffle(finalMap, mappedData);
        }

        // Display intermediate output
        System.out.println("\nIntermediate MapReduce Output:");

        for (String word : new TreeSet<>(finalMap.keySet())) {
            System.out.println(word + " -> " + finalMap.get(word));
        }

        // Perform reduction
        Map<String, Integer> result = reduce(finalMap);

        // Display final word count
        System.out.println("\nFinal Word Count:");

        for (String word : result.keySet()) {
            System.out.println(word + " -> " + result.get(word));
        }

        sc.close();
    }
}