Machine Coding Question: Dynamic Multilevel Caching System
Objective:
Design and implement a dynamic multilevel caching system that efficiently manages
data across multiple cache levels. The system should support dynamic addition of cache
levels, eviction policies, and data retrieval across these levels.
Requirements:
1. Cache Levels:
○ The system should allow multiple cache levels (L1, L2, ..., Ln).
○ Each cache level can have its own size (i.e., number of entries it can
hold).
○ Data should be retrieved from the highest-priority cache level (L1) first. If
the data is not found in L1, it should be fetched from lower levels
sequentially until found, or return a cache miss.
2. Eviction Policies:
○ Implement support for at least one of these eviction policies:
■ Least Recently Used (LRU): Evict the least recently accessed
item.
■ Least Frequently Used (LFU): Evict the least frequently
accessed item.
○ Each cache level will have the same eviction policy.
3. Data Retrieval and Insertion:
○ When retrieving data:
■ If the data is found in any lower cache level, move the data up to
higher cache levels (L1, L2, etc.), following the defined eviction
policy.
■ If the data is not present in any cache, simulate fetching it from the
main memory and store it in L1 cache.
○ When inserting new data:
■ Always insert the data at the L1 cache level, and evict items if
necessary.
4. Dynamic Cache Level Management:
○ The system should allow adding/removing cache levels dynamically at
runtime.
○ Each new cache level should be able to specify its own size and eviction
policy.
5. Concurrency (Bonus):
○ Implement the system with thread-safety, allowing concurrent reads and
writes to the cache.
6. Performance Considerations:
○ The system should ensure efficient lookups and minimize cache misses.
○ Optimize data movement across cache levels when data is found in lower
levels.
Expected Functions:
1. addCacheLevel(size: number, evictionPolicy: string): void
○ Adds a new cache level with the specified size and eviction policy (LRU or
LFU).
2. get(key: string): string | null
○ Retrieves the data corresponding to the key. If not found, return null.
3. put(key: string, value: string): void
○ Inserts the key-value pair into the L1 cache.
4. removeCacheLevel(level: number): void
○ Removes a cache level by specifying its index (L1, L2, ..., Ln).
5. displayCache(): void
○ Prints the current state of each cache level, showing the keys and values.
Example:
Input:
addCacheLevel(3, 'LRU') # Add L1 cache with size 3 and LRU
policy
addCacheLevel(2, 'LFU') # Add L2 cache with size 2 and LFU
policy
put("A", "1")
