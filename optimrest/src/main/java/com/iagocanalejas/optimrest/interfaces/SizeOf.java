package com.iagocanalejas.optimrest.interfaces;

/**
 * Interface used to describe how to compute the size of an object in RAM.
 *
 * @param <T> is the class of object on which this computation is done.
 */
public interface SizeOf<T> {

    /**
     * Compute the amount of RAM in bytes used by this object.
     * The most common method is to sum the size of all types in the object
     * (NUM_INTS * {@link Integer#SIZE} + NUM_LONGS * {@link Long#SIZE} ... ) / {@link Byte#SIZE}
     *
     * @param object is the instance against the computation has to be done.
     * @return the size in bytes of the object in RAM.
     */
    int sizeOf(T object);
}
