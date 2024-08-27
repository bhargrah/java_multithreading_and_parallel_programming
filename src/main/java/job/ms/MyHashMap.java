package job.ms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 23/08/24
 */

class Element {

    int key;
    int value;

    public Element(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Element{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

public class MyHashMap {

    List<List<Element>> bucket = new ArrayList<>(BUCKET_SIZE);
    public static Integer BUCKET_SIZE = 10;

    public MyHashMap(int size) {
        for (int i = 0; i < size; i++) {
            bucket.add(i, new ArrayList<>());
        }
    }

    public void put(int key, int value) {
        boolean isPresent = false;
        int keyHash = hashcode(key);
        List<Element> internalList = bucket.remove(keyHash);

        // check if same key is present and replace the value
        for (int i = 0; i < internalList.size(); i++) {
            Element element = internalList.get(i);
            if (element.key == key) {
                element.value = value;
                isPresent = true;
            }
        }

        if (!isPresent) internalList.add(new Element(key, value));

        bucket.add(keyHash, internalList);
    }

    public Integer get(int key) {
        int keyHash = hashcode(key);
        List<Element> internalList = bucket.get(keyHash);

        for (Element element : internalList) {
            if (element.key == key)
                return element.value;
        }
        return -1;
    }

    public Boolean remove(int key) {
        int indexTopRemove = -1;
        int keyHash = hashcode(key);
        List<Element> list = bucket.remove(keyHash);

        for (int i = 0; i < list.size(); i++) {
            Element element = list.get(i);
            if (element.key == key) {
                indexTopRemove = i;
            }
        }

        if (indexTopRemove != -1) list.remove(indexTopRemove);

        bucket.add(keyHash, list);

        return (indexTopRemove != -1);
    }

    public int hashcode(int key) {
        return key % BUCKET_SIZE;
    }

    public int equals() {
        return -1;//Integer.compare();
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "bucket=" + bucket +
                '}';
    }

    public static void main(String[] args) {

        MyHashMap map = new MyHashMap(BUCKET_SIZE);
        map.put(2, 9);
        map.put(1, 5);
        map.put(10, -1);
        map.put(20, -2);


        System.out.println(map);

        System.out.println(map.get(10));

        System.out.println(map.remove(10));

        System.out.println(map);


    }


}
