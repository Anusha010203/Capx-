package com.cache.system;

import java.util.ArrayList;
import java.util.List;

public class DynamicMultilevelCache {
    private final List<Cache> cacheLevels;

    public DynamicMultilevelCache() {
        this.cacheLevels = new ArrayList<>();
    }

    public void addCacheLevel(int size, String evictionPolicy) {
        if (evictionPolicy.equals("LRU")) {
            cacheLevels.add(new LRUCache(size));
            System.out.println("Added LRU cache with size " + size);
        } else if (evictionPolicy.equals("LFU")) {
            cacheLevels.add(new LFUCache(size));
            System.out.println("Added LFU cache with size " + size);
        }
    }

    public String get(String key) {
        for (int i = 0; i < cacheLevels.size(); i++) {
            String value = cacheLevels.get(i).get(key);
            if (value != null) {
                // Move the data up to higher levels
                for (int j = i - 1; j >= 0; j--) {
                    // Insert the value into the higher cache levels
                    System.out.println("Promoting " + key + " to L" + (j + 1) + " cache");
                    cacheLevels.get(j).put(key, value);
                }
                return value; // Return the value found
            }
        }
        return null; // Return null if not found in any cache
    }




    public void put(String key, String value) {
        if (!cacheLevels.isEmpty()) {
            System.out.println("Inserting " + key + " into L1 cache");
            cacheLevels.get(0).put(key, value);  // Insert into L1

            // Check if we need to also insert into L2
            if (cacheLevels.size() > 1) {
                System.out.println("Also adding " + key + " to L2 cache");
                cacheLevels.get(1).put(key, value);  // Insert into L2 (optional, based on your logic)
            }
        }
    }

    public void removeCacheLevel(int level) {
        if (level >= 0 && level < cacheLevels.size()) {
            System.out.println("Removing cache level L" + (level + 1));
            cacheLevels.remove(level);
        } else {
            System.out.println("Invalid cache level: " + (level + 1));
        }
    }

    public void displayCache() {
        for (int i = 0; i < cacheLevels.size(); i++) {
            System.out.print("L" + (i + 1) + " Cache: ");
            cacheLevels.get(i).display();
        }
    }
}
