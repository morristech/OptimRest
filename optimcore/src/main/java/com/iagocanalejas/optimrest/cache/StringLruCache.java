package com.iagocanalejas.optimrest.cache;

import android.util.LruCache;

import com.iagocanalejas.optimrest.interfaces.Cache;

/**
 * Created by Iago on 06/01/2017.
 * Default cache for saving serialized objects
 */
public class StringLruCache extends LruCache<String, String> implements Cache<String, String> {
    private static final String TAG = StringLruCache.class.getSimpleName();

    /**
     * @param maxSize The maximum sum of the sizes of the entries in this cache.
     */
    public StringLruCache(int maxSize) {
        super(maxSize);
    }

    /**
     * Compute the size of an entry
     *
     * @param key   for the entry
     * @param value of the entry
     * @return The size of an entry = (key.length() + value.length()) * {@link Character#SIZE}
     */
    @Override
    protected int sizeOf(String key, String value) {
        return (key.length() + value.length()) * Character.SIZE;
    }

    /**
     * {@link Cache#delete}
     */
    @Override
    public String delete(String key) {
        return super.remove(key);
    }

    /**
     * {@link Cache#clear}
     */
    @Override
    public void clear() {
        super.evictAll();
    }
}
