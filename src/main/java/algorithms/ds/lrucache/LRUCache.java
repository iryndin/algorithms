package algorithms.ds.lrucache;


import java.util.HashMap;
import java.util.Map;

/**
 * LRU cache.
 *
 * Details here: https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> implements ILRUCache<K,V> {

    private static class ListNode<K,V> {
        private K key;
        private V value;
        public ListNode<K,V> next;
        public ListNode<K,V> prev;

        public ListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private final int maxCapacity;

    private Map<K, ListNode<K,V>> map = new HashMap<>();
    private ListNode<K,V> head;
    private ListNode<K,V> tail;

    public LRUCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public V put(K key, V value) {
        if (map.containsKey(key)) {
            ListNode<K, V> node = map.get(key);
            V oldValue = node.getValue();
            node.setValue(value);
            removeNode(node);
            addTop(node);
            return oldValue;
        } else {
            ListNode<K,V> node = new ListNode<>(key, value);
            if (map.size() < maxCapacity) {
                addTop(node);
            } else {
                map.remove(this.tail.key);
                removeNode(this.tail);
                addTop(node);
            }
            map.put(key, node);
            return null;
        }
    }

    @Override
    public V remove(K key) {
        if (map.containsKey(key)) {
            ListNode<K,V> node = map.remove(key);
            removeNode(node);
            return node.getValue();
        } else {
            return null;
        }
    }

    @Override
    public V get(K key) {
        if (map.containsKey(key)) {
            ListNode<K,V> node = map.get(key);
            removeNode(node);
            addTop(node);
            return node.getValue();
        } else {
            return null;
        }
    }

    private void addTop(ListNode<K, V> node) {
        node.next = this.head;
        node.prev = null;

        if (this.head != null) {
            this.head.prev = node;
        }
        this.head = node;

        if (this.tail == null) {
            this.tail = node;
        }
    }

    private void removeNode(ListNode<K, V> node) {
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            this.tail = node.prev;
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            this.head = node.next;
        }
    }

    @Override
    public int size() {
        return map.size();
    }
}
