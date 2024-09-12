package bhargrah.ms.ds;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 02/09/24
 */

record Element(Integer key, Integer value) {

}

record Elements(Integer key, Integer value) implements Comparable<Integer> {

    @Override
    public int compareTo(Integer key) {
        return this.key.compareTo(key);
    }
}

public class PriorityQueueTest {

    public static void main(String[] args) {

        PriorityQueue<Integer> dequeWithNaturalOrder = new PriorityQueue<>(Comparator.naturalOrder());
        dequeWithNaturalOrder.offer(10);
        dequeWithNaturalOrder.offer(11);
        dequeWithNaturalOrder.offer(8);
        System.out.println(dequeWithNaturalOrder);
        dequeWithNaturalOrder.poll();
        System.out.println(dequeWithNaturalOrder);

        PriorityQueue<Integer> dequeWithReverseOrder = new PriorityQueue<>(Comparator.reverseOrder());
        dequeWithReverseOrder.offer(10);
        dequeWithReverseOrder.add(11);
        dequeWithReverseOrder.offer(8);
        System.out.println(dequeWithReverseOrder);


        System.out.println("Sum (int stream) : " + dequeWithReverseOrder.stream().mapToInt(e -> e).sum());
        System.out.println("Sum (identity) : " + dequeWithReverseOrder.stream().reduce(0, (e1, e2) -> e1 + e2));
        System.out.println("Sum : " + dequeWithReverseOrder.stream().reduce((e1, e2) -> e1 + e2));
        System.out.println("Min : " + dequeWithReverseOrder.stream().mapToInt(e -> e).min());
        System.out.println("Max : " + dequeWithReverseOrder.stream().mapToInt(e -> e).max());
        System.out.println(dequeWithReverseOrder.stream().reduce((e1, e2) -> e1 + e2).isPresent());

        PriorityQueue<Element> deque = new PriorityQueue<>(Comparator.comparingInt(Element::key));
        deque.offer(new Element(2, 6));
        deque.offer(new Element(21, 7));
        deque.offer(new Element(23, 9));
        deque.offer(new Element(1, 9));
        System.out.println(deque);

        Map<Integer, String> dict = new TreeMap<>(Comparator.comparingInt(k1 -> k1));
        dict.put(11, "a");
        dict.put(1, "r");
        System.out.println(dict);


        TreeSet<Element> tSet = new TreeSet(Comparator.naturalOrder());
        TreeMap<Element, Integer> tMap = new TreeMap(Comparator.naturalOrder());

        tSet = new TreeSet(Comparator.comparingInt(Element::key));
        tMap = new TreeMap(Comparator.comparingInt(Element::value));

    }

}
