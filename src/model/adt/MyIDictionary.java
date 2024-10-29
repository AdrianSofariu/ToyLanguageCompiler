package model.adt;

import exceptions.KeyNotFoundException;

import java.util.Set;

public interface MyIDictionary<K, V> {

    void insert(K key, V value);
    void remove(K key) throws KeyNotFoundException;
    boolean contains(K key);
    V get(K key) throws KeyNotFoundException;
    Set<K> getKeys();
}
