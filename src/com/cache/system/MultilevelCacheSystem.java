package com.cache.system;

public class MultilevelCacheSystem {
    public static void main(String[] args) {
        DynamicMultilevelCache cacheSystem = new DynamicMultilevelCache();

        // Add cache levels
        cacheSystem.addCacheLevel(3, "LRU");  // Add L1 cache with size 3 and LRU policy
        cacheSystem.addCacheLevel(2, "LFU");  // Add L2 cache with size 2 and LFU policy

        // Insert key-value pairs into the cache
        cacheSystem.put("A", "1");
        cacheSystem.put("B", "2");
        cacheSystem.put("C", "3");

        // Retrieve data from the cache
        System.out.println("Get A: " + cacheSystem.get("A"));  // Should return "1"

        // Insert a new item to trigger eviction in L1
        cacheSystem.put("D", "4");  // L1 is full, evicts least recently used
        System.out.println("Get C: " + cacheSystem.get("C"));  // Fetches from L2 and promotes to L1

        // Display the current state of caches
        System.out.println("Current Cache State:");
        cacheSystem.displayCache();
    }
}
