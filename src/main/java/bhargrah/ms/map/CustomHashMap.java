package bhargrah.ms.map;

import java.util.LinkedList;

class CustomHashMap {

    private class Entry {

        Integer key;
        Integer value;

        Entry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return key % bucketSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Entry entry = (Entry) obj;
            return key.equals(entry.key);
        }
    }

    private LinkedList<Entry>[] buckets;
    private int bucketSize;

    @SuppressWarnings("unchecked")
    public CustomHashMap(int bucketSize) {
        this.bucketSize = bucketSize;
        buckets = new LinkedList[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public Integer get(Integer key) {
        int bucketIndex = key % bucketSize;
        LinkedList<Entry> bucket = buckets[bucketIndex];
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null; // Key not found
    }

    public void put(Integer key, Integer value) {
        int bucketIndex = key % bucketSize;
        LinkedList<Entry> bucket = buckets[bucketIndex];
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value; // Update value if key already exists
                return;
            }
        }
        bucket.add(new Entry(key, value)); // Add new entry if key doesn't exist
    }

    public void remove(Integer key) {
        int bucketIndex = key % bucketSize;
        LinkedList<Entry> bucket = buckets[bucketIndex];
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                return;
            }
        }
    }

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap(10);
        map.put(1, 10);
        map.put(2, 20);
        map.put(11, 110);
        System.out.println(map.get(1));  // Output: 10
        System.out.println(map.get(2));  // Output: 20
        System.out.println(map.get(11)); // Output: 110
        map.remove(1);
        System.out.println(map.get(1));  // Output: null
    }
}
