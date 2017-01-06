package com.iagocanalejas.optimrest.logging;

import android.util.Log;

/**
 * Created by Iagocanalejas on 06/01/2017.
 * Handle library logging so all logs will have the format:
 * {@link Logger#DEFAULT_LOG_TAG}: TAG: message
 */
public class Logger {

    private static final String DEFAULT_LOG_TAG = "OptimREST";
    private final boolean mLogEnabled;

    public Logger(boolean logEnabled) {
        this.mLogEnabled = logEnabled;
    }

    /**
     * Default log info using tag {@link #DEFAULT_LOG_TAG}.
     *
     * @param tag caller class name
     * @param msg the msg to log.
     */
    void logInfo(String tag, String msg) {
        if (mLogEnabled) {
            Log.i(DEFAULT_LOG_TAG, tag + ": " + msg);
        }
    }

    /**
     * Log with level warning and tag {@link #DEFAULT_LOG_TAG}.
     *
     * @param tag caller class name
     * @param msg the msg to log.
     */
    void logWarning(String tag, String msg) {
        if (mLogEnabled) {
            Log.w(DEFAULT_LOG_TAG, tag + ": " + msg);
        }
    }

    /**
     * Log with level error and tag {@link #DEFAULT_LOG_TAG}.
     *
     * @param tag   caller class name
     * @param error the error to log.
     */
    void logError(String tag, Throwable error) {
        if (mLogEnabled) {
            Log.e(DEFAULT_LOG_TAG, tag + ": " + error);
        }
    }

    /**
     * Log with level error and tag {@link #DEFAULT_LOG_TAG}.
     *
     * @param tag   caller class name
     * @param error the error to log.
     */
    void logError(String tag, String error) {
        if (mLogEnabled) {
            Log.e(DEFAULT_LOG_TAG, tag + ": " + error);
        }
    }
}
