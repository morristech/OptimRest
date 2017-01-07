package com.iagocanalejas.optimrest.interfaces;

/**
 * Created by Iagocanalejas on 07/01/2017.
 * Interface containing the methods required
 * to handle Loader callbacks for T objects.
 */
public interface Callback<T> {

    /**
     * Handles successful data load.
     *
     * @param data loaded objects.
     */
    void onLoadSuccess(T data);

    /**
     * Handles error on data load.
     *
     * @param errorCode    send by the server.
     * @param errorMessage send by the server.
     */
    void onLoadError(int errorCode, String errorMessage);

    /**
     * Handles server or network errors.
     *
     * @param errorMessage description.
     */
    void onServerError(String errorMessage);

}
