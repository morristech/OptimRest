package com.iagocanalejas.optimrest;

import com.iagocanalejas.optimrest.interfaces.Parser;
import com.iagocanalejas.optimrest.logging.Logger;

/**
 * Created by Iagocanalejas on 06/01/2017.
 */

public class Loader<T> {

    private final Logger mLogger;
    private final Parser<T> mParser;

    /**
     * Default constructor
     *
     * @param parser {@link Parser}
     * @param logger {@link Logger}
     */
    private Loader(Parser<T> parser, Logger logger) {
        this.mParser = parser;
        this.mLogger = logger;
    }

    /**
     * Provides all the configuration parameters to create a new {@link Loader}
     */
    public class Builder<B> {

        private boolean mLoggingEnabled = false;
        private Parser<B> mParser;

        /**
         * Default Constructor
         */
        public Builder() {
        }

        /**
         * Create a new {@link Loader} with all the configuration parameters
         *
         * @return full configured {@link Loader}
         */
        public Loader<B> build() {
            return new Loader<>(mParser, new Logger(mLoggingEnabled));
        }

        /**
         * Set the {@link Parser} to use when saving the object in cache
         *
         * @param parser for new {@link Loader}
         * @return the {@link Loader.Builder} with {@link Parser} configured
         */
        public Builder<B> withParser(Parser<B> parser) {
            this.mParser = parser;
            return this;
        }

        /**
         * Set logging to True so all logs could be showed
         *
         * @return the {@link Loader.Builder} with {@link Logger} configured
         */
        public Builder<B> enableLog() {
            this.mLoggingEnabled = true;
            return this;
        }

    }

}
