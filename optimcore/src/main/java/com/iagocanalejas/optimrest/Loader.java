package com.iagocanalejas.optimrest;

import com.iagocanalejas.optimrest.logging.Logger;

/**
 * Created by Iagocanalejas on 06/01/2017.
 */

public class Loader<T> {

    private final Logger mLogger;

    public Loader(Logger logger) {
        mLogger = logger;
    }

    /**
     * Provides all the configuration parameters to create a new {@link Loader}
     */
    public class Builder {

        private boolean mLoggingEnabled = false;

        public Builder() {
            /**
             * Default Constructor
             */
        }

        /**
         * Create a new {@link Loader} with all the configuration parameters
         *
         * @return full configured {@link Loader}
         */
        public Loader<T> build() {
            return new Loader<>(new Logger(mLoggingEnabled));
        }

        /**
         * Set logging to True so all logs could be showed
         *
         * @return the {@link Loader.Builder}
         */
        public Builder enableLog() {
            mLoggingEnabled = true;
            return this;
        }

    }

}
