

---

# LRU Cache Implementation in Java & JS

## Overview

The `LRUCache` class implements a Least Recently Used (LRU) cache in Java. This cache stores a fixed number of key-value pairs and evicts the least recently accessed item when the cache reaches its capacity. It is designed to be thread-safe, allowing concurrent access from multiple threads.

## Features

- **Fixed Capacity**: The cache has a fixed capacity specified at initialization.
- **Thread Safety**: Uses `ReentrantLock` to ensure thread-safe operations.
- **Constant Time Operations**: Both `get` and `put` operations are performed in O(1) time.
- **Access Order**: Maintains the order of elements based on access, ensuring the most recently used items are kept in the cache.

## Usage

### Initialization

```java
LRUCache<String, Integer> cache = new LRUCache<>(3);
```

### Operations

- **Put**: Insert or update a key-value pair in the cache.

  ```java
  cache.put("A", 1);
  cache.put("B", 2);
  cache.put("C", 3);
  ```

- **Get**: Retrieve the value associated with a key. Returns `null` if the key is not present.

  ```java
  System.out.println(cache.get("A")); // Output: 1
  ```

- **Eviction**: When the cache reaches its capacity, the least recently used item is evicted.

  ```java
  cache.put("D", 4); // "C" is evicted
  System.out.println(cache.get("C")); // Output: null
  ```

## Example

```java
public static void main(String[] args) {
    LRUCache<String, Integer> cache = new LRUCache<>(3);
    cache.put("A", 1);
    cache.put("B", 2);
    cache.put("C", 3);
    System.out.println(cache.get("A")); // Output: 1
    cache.put("D", 4); // "C" is evicted
    System.out.println(cache.get("C")); // Output: null
    System.out.println(cache.get("B")); // Output: 2
    System.out.println(cache.get("D")); // Output: 4
}
```

## Use Cases

- **Web Browsers**: Caching recently visited web pages.
- **Database Systems**: Caching query results.
- **Content Delivery Networks (CDNs)**: Caching static content.
- **Application Servers**: Managing user sessions and API rate limiting.
- **Machine Learning**: Caching computed features.
- **Operating Systems**: Managing memory pages.
- **Networking**: DNS caching.
- **Gaming**: Managing textures and assets.

## Benefits

- **Improved Performance**: Reduces access times by keeping frequently accessed data in memory.
- **Efficient Resource Management**: Ensures the most relevant data is readily available.
- **Scalability**: Reduces load on backend systems by serving requests from the cache.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

This `README.md` file provides a comprehensive overview of the `LRUCache` class, its features, usage, and potential use cases. It serves as a helpful guide for anyone looking to understand or implement the LRU Cache in their projects.
