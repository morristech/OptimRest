package com.iagocanalejas.optimrest.interfaces;

/**
 * Created by Iago on 06/01/2017.
 * Interface containing the methods required
 * to handle Caching for V objects with K keys
 */
public interface Cache<K, V> {

    /**
     * Try to find the given key in the cache
     *
     * @param key to find
     * @return found value or null
     */
    V get(K key);

    /**
     * Put a new value in the cache
     *
     * @param key   for the value
     * @param value to add
     * @return added value or null
     */
    V put(K key, V value);

    /**
     * Delete given key from the cache
     *
     * @param key to delete
     * @return deleted value or null
     */
    V delete(K key);

    /**
     * Remove all data in cache
     */
    void clear();

}
