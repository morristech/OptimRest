package com.iagocanalejas.optimrest.cache;

import com.iagocanalejas.optimrest.interfaces.Cache;
import com.iagocanalejas.optimrest.interfaces.Parser;
import com.iagocanalejas.optimrest.interfaces.SizeOf;

/**
 * Created by Iagocanalejas on 06/01/2017.
 * Default cache for saving serialized objects.
 *
 * @param <V> Type of the objects we want to save
 */
public class RamSerializedCache<V> implements Cache<String, V> {
    private static final String TAG = RamSerializedCache.class.getSimpleName();

    private final Parser<V> mParser;
    private final RamCache<String> mRamCache;

    /**
     * @param parser  given {@link Parser} to handle serialization and deserialization.
     * @param maxSize The maximum sum of the sizes of the entries in this cache.
     */
    public RamSerializedCache(Parser<V> parser, int maxSize) {
        this.mParser = parser;
        mRamCache = new RamCache<>(new SizeOf<String>() {
            @Override
            public int sizeOf(String object) {
                return object.length() * Character.SIZE / Byte.SIZE;
            }
        }, maxSize);
    }

    /**
     * Deserialize the retrieved object
     *
     * @param key to find.
     * @return Deserialized object
     */
    @Override
    public V get(String key) {
        return mParser.read(mRamCache.get(key));
    }

    /**
     * Serialize the object to catch
     *
     * @param key   for the value.
     * @param value to add.
     * @return previous existing object with given key
     */
    @Override
    public V put(String key, V value) {
        String previous = mRamCache.put(key, mParser.write(value));
        if (previous != null) {
            return mParser.read(previous);
        }
        return null;
    }

    /**
     * {@link Cache#delete}.
     */
    @Override
    public V delete(String key) {
        String previous = mRamCache.delete(key);
        if (previous != null) {
            return mParser.read(previous);
        }
        return null;
    }

    /**
     * {@link Cache#clear}.
     */
    @Override
    public void clear() {
        mRamCache.clear();
    }
}
