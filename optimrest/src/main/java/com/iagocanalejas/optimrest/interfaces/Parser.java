package com.iagocanalejas.optimrest.interfaces;

/**
 * Created by Iagocanalejas on 06/01/2017.
 * Interface containing the methods required
 * to handle Serialization and Deserialization
 * for T objects.
 */
public interface Parser<T> {

    /**
     * Serialize an object to String.
     *
     * @param object to serialize.
     * @return serialized object.
     */
    String write(T object);

    /**
     * Deserialize an String to object.
     *
     * @param data String to deserialize.
     * @return deserialized object.
     */
    T read(String data);

}
