package com.iagocanalejas.optimrest.cache;

import android.util.LruCache;

import com.iagocanalejas.optimrest.interfaces.Cache;
import com.iagocanalejas.optimrest.interfaces.SizeOf;

/**
 * Created by Iagocanalejas on 06/01/2017.
 * Default cache for saving referenced objects.
 *
 * @param <V> Type of the objects we want to save
 */
public class RamCache<V> extends LruCache<String, V> implements Cache<String, V> {
    private static final String TAG = RamCache.class.getSimpleName();

    private final SizeOf<V> mSizeOf;

    /**
     * @param maxSize The maximum sum of the sizes of the entries in this cache.
     */
    public RamCache(int maxSize) {
        this(null, maxSize);
    }

    /**
     * @param sizeOf  will be in charge of compute the size of a V object.
     * @param maxSize The maximum sum of the sizes of the entries in this cache.
     */
    public RamCache(SizeOf<V> sizeOf, int maxSize) {
        super(maxSize);
        this.mSizeOf = sizeOf;
    }

    /**
     * Compute the size of an entry.
     *
     * @param key   for the entry.
     * @param value of the entry.
     * @return The size of an entry.
     */
    @Override
    protected int sizeOf(String key, V value) {
        return (mSizeOf != null)
                ? key.length() * Character.SIZE + mSizeOf.sizeOf(value)
                : super.sizeOf(key, value);
    }

    /**
     * {@link Cache#delete}.
     */
    @Override
    public V delete(String key) {
        return super.remove(key);
    }

    /**
     * {@link Cache#clear}.
     */
    @Override
    public void clear() {
        super.evictAll();
    }
}
