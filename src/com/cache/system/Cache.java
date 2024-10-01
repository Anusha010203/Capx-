package com.cache.system;
public interface Cache {
    String get(String key);
    void put(String key, String value);
    void display();
}
