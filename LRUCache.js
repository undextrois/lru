class LRUCache {
  constructor(capacity) {
    if (capacity <= 0) {
      throw new Error("Capacity must be greater than zero");
    }
    this.capacity = capacity;
    this.cache = new Map();
  }

  get(key) {
    if (!this.cache.has(key)) {
      return null;
    }
    const value = this.cache.get(key);
    // Refresh the key by deleting and setting it again
    this.cache.delete(key);
    this.cache.set(key, value);
    return value;
  }

  put(key, value) {
    if (key == null || value == null) {
      throw new Error("Key and value cannot be null");
    }
    if (this.cache.size >= this.capacity && !this.cache.has(key)) {
      // Evict the least recently used item
      const firstKey = this.cache.keys().next().value;
      this.cache.delete(firstKey);
    }
    // Refresh the key by deleting it if it exists
    if (this.cache.has(key)) {
      this.cache.delete(key);
    }
    this.cache.set(key, value);
  }

  clear() {
    this.cache.clear();
  }

  resize(newCapacity) {
    if (newCapacity <= 0) {
      throw new Error("New capacity must be greater than zero");
    }
    this.capacity = newCapacity;
    while (this.cache.size > newCapacity) {
      const firstKey = this.cache.keys().next().value;
      this.cache.delete(firstKey);
    }
  }
}

// Example usage
const cache = new LRUCache(3);
cache.put("A", 1);
cache.put("B", 2);
cache.put("C", 3);
console.log(cache.get("A")); // Output: 1
cache.put("D", 4); // "B" is evicted
console.log(cache.get("B")); // Output: null
console.log(cache.get("C")); // Output: 3
console.log(cache.get("D")); // Output: 4

// Resize the cache
cache.resize(2); // "C" is evicted
console.log(cache.get("C")); // Output: null
