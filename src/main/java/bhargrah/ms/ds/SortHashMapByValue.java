package bhargrah.ms.ds;

import java.util.*;

public class SortHashMapByValue {

    public static void main(String[] args) {
        // Create a HashMap with some data
        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 40);
        map.put("banana", 10);
        map.put("cherry", 30);
        map.put("date", 20);

        // Step 1: Convert the HashMap into a list of Map.Entry
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        

        // Step 2: Sort the list using a custom Comparator
        list.sort(Map.Entry.comparingByValue());

        // If you want to sort in reverse order, use:
        // list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Step 3: Create a LinkedHashMap to store the sorted entries
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // Print the sorted map
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}