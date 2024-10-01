package com.cache.system;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

public class LFUCache implements Cache {
    private final int capacity;  // Renamed for clarity
    private final Map<String, String> cache;
    private final Map<String, Integer> freqMap;
    private final TreeMap<Integer, LinkedHashSet<String>> freqTreeMap;
    private int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;  // Use capacity instead of size for clarity
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.freqTreeMap = new TreeMap<>();
        this.minFreq = 0;
    }

    @Override
    public String get(String key) {
        if (!cache.containsKey(key)) {
            System.out.println(key + " not found in LFU cache");
            return null;
        }
        int freq = freqMap.get(key);
        freqMap.put(key, freq + 1);

        // Update frequency structures
        freqTreeMap.get(freq).remove(key);
        if (freqTreeMap.get(freq).isEmpty()) {
            freqTreeMap.remove(freq);
            if (freq == minFreq) {
                minFreq++;
            }
        }
        freqTreeMap.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key);

        System.out.println("Retrieved " + key + " from LFU cache with frequency " + (freq + 1));
        return cache.get(key);
    }

    @Override
    public void put(String key, String value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            System.out.println("Updated " + key + " in LFU cache");
            get(key);
            return;
        }
        if (cache.size() >= capacity) {
            String evict = freqTreeMap.get(minFreq).iterator().next();
            freqTreeMap.get(minFreq).remove(evict);
            if (freqTreeMap.get(minFreq).isEmpty()) {
                freqTreeMap.remove(minFreq);
            }
            cache.remove(evict);
            freqMap.remove(evict);
            System.out.println("Evicted " + evict + " from LFU cache");
        }
        cache.put(key, value);
        freqMap.put(key, 1);
        freqTreeMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFreq = 1;

        System.out.println("Inserted " + key + " with value " + value + " into LFU cache");
    }

    @Override
    public void display() {
        System.out.println("Cache: " + cache);
    }
}
