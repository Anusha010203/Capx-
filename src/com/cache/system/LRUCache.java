package com.cache.system;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache implements Cache {
    private final int capacity;  // Renamed for clarity
    private final LinkedHashMap<String, String> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;  // Use capacity instead of size for clarity
        this.cache = new LinkedHashMap<String, String>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                // Evict if the size exceeds capacity
                return size() > capacity;
            }
        };
    }

    @Override
    public String get(String key) {
        String value = cache.get(key);
        if (value != null) {
            System.out.println("Retrieved " + key + " from LRU cache");
        } else {
            System.out.println(key + " not found in LRU cache");
        }
        return value; // Return null if not found
    }

    @Override
    public void put(String key, String value) {
        System.out.println("Inserting " + key + " with value " + value + " into LRU cache");
        cache.put(key, value); // This will trigger eviction if necessary
    }

    @Override
    public void display() {
        System.out.println("Cache: " + cache);
    }
}
