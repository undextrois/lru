import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The LRUCache class implements a Least Recently Used (LRU) cache.
 * It stores a fixed number of key-value pairs, evicting the least recently
 * accessed item when the cache reaches its capacity.
 *
 * This implementation uses a LinkedHashMap to maintain the order of elements
 * based on access, ensuring that the most recently accessed items are kept in
 * the cache. A ReentrantLock is used to ensure thread safety for concurrent
 * access to the cache.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;
    private final ReentrantLock lock;

    /**
     * Constructs an LRUCache with the specified capacity.
     *
     * @param capacity the maximum number of items the cache can hold
     * @throws IllegalArgumentException if the capacity is less than or equal to zero
     */
    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.capacity = capacity;
        cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
        lock = new ReentrantLock();
    }

    /**
     * Retrieves a value from the cache. If the value is present, it is moved
     * to the end of the cache to mark it as recently used.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key is not present
     */
    public V get(K key) {
        try {
            lock.lock();
            return cache.get(key);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Adds a key-value pair to the cache. If the cache is at capacity, the least
     * recently used item is evicted.
     *
     * @param key   the key to be inserted
     * @param value the value to be associated with the key
     * @throws IllegalArgumentException if the key or value is null
     */
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null");
        }
        try {
            lock.lock();
            cache.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Main method to demonstrate the usage of the LRUCache class.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        System.out.println(cache.get("A")); // Output: 1 (A is accessed, moved to the end)
        cache.put("D", 4); // C is evicted (LRU)
        System.out.println(cache.get("C")); // Output: null (C is no longer in the cache)
        System.out.println(cache.get("B")); // Output: 2
        System.out.println(cache.get("D")); // Output: 4
    }
}
