package com.iagocanalejas.optimrest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

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

        private final Context mContext;
        private Parser<B> mParser;

        private Long mRamCacheSize;
        private boolean mLoggingEnabled = false;

        /**
         * Default Constructor
         */
        public Builder(Context context) {
            this.mContext = context;
        }

        //region Loader Configuration

        /**
         * Create a new {@link Loader} with all the configuration parameters
         *
         * @return full configured {@link Loader}
         */
        public Loader<B> build() {
            if (mRamCacheSize == null) {
                mRamCacheSize = getMaxApplicationRam(mContext) / 4;
            }
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
         * Set the maximum amount of RAM our cache can use
         *
         * @param ramCacheSize max cache size
         * @return the {@link Loader.Builder} with RAM size configured
         */
        public Builder<B> withMaxRamUssage(long ramCacheSize) {
            this.mRamCacheSize = ramCacheSize;
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
        //endregion

        //region Builder Utils

        /**
         * Compute the amount of RAM the app can use
         *
         * @param context required app context
         * @return max RAM the app can use
         */
        private long getMaxApplicationRam(Context context) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE))
                    .getMemoryInfo(new ActivityManager.MemoryInfo());
            return memoryInfo.availMem;
        }
        //endregion

    }

}
