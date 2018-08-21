package algorithms.ds.lrucache;

public interface ILRUCache<K,V> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
}
