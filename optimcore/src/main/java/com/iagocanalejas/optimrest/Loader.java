package com.iagocanalejas.optimrest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.iagocanalejas.optimrest.cache.RamCache;
import com.iagocanalejas.optimrest.cache.RamSerializedCache;
import com.iagocanalejas.optimrest.interfaces.Cache;
import com.iagocanalejas.optimrest.interfaces.Parser;
import com.iagocanalejas.optimrest.interfaces.SizeOf;
import com.iagocanalejas.optimrest.logging.Logger;

/**
 * Created by Iagocanalejas on 06/01/2017.
 * Class grouping all required methods for load.
 *
 * @param <T> Type we want to load
 */
public class Loader<T> {
    private static final String TAG = Loader.class.getSimpleName();

    private final Logger mLogger;
    private final Parser<T> mParser;
    private final Cache<String, T> mCache;

    /**
     * Default constructor.
     *
     * @param parser {@link Parser}.
     * @param cache  {@link Cache}.
     * @param logger {@link Logger}.
     */
    private Loader(Parser<T> parser, Cache<String, T> cache, Logger logger) {
        this.mParser = parser;
        this.mCache = cache;
        this.mLogger = logger;
    }

    /**
     * Provides all the configuration parameters to create a new {@link Loader}.
     */
    public class Builder<B> {

        private final Context mContext;
        private Parser<B> mParser;
        private SizeOf<B> mSizeOf;

        private Integer mRamCacheSize;
        private boolean mLoggingEnabled = false;

        /**
         * Default Constructor.
         *
         * @param context required for configuration.
         */
        public Builder(Context context) {
            this.mContext = context;
        }

        //region Loader Configuration

        /**
         * Create a new {@link Loader} with all the configuration parameters.
         *
         * @return full configured {@link Loader}.
         */
        public Loader<B> build() {
            if (mRamCacheSize == null) {
                /**
                 * Set default RAM size as a quarter of max app RAM.
                 */
                mRamCacheSize = getMaxApplicationRam(mContext) / 4;
            }
            return new Loader<>(mParser, getRamCache(), new Logger(mLoggingEnabled));
        }

        /**
         * Set the {@link Parser} to use when saving a serialized object in cache.
         *
         * @param parser for new {@link Loader}.
         * @return the {@link Loader.Builder} with {@link Parser} configured.
         */
        public Builder<B> withParser(Parser<B> parser) {
            this.mParser = parser;
            return this;
        }

        /**
         * Set the {@link SizeOf} to use when saving the object in cache.
         *
         * @param sizeOf for new {@link Loader}.
         * @return the {@link Loader.Builder} with {@link Parser} configured.
         */
        public Builder<B> withSizeOf(SizeOf<B> sizeOf) {
            this.mSizeOf = sizeOf;
            return this;
        }

        /**
         * Set the maximum size our cache can use.
         * If {@link Loader.Builder#withSizeOf} wasn't called this size mark
         * the number of entities our cache can hold. Otherwise this is the
         * number of Bytes of RAM our cache can use.
         *
         * @param ramCacheSize max cache size.
         * @return the {@link Loader.Builder} with RAM size configured.
         */
        public Builder<B> withMaxRamSize(int ramCacheSize) {
            this.mRamCacheSize = ramCacheSize;
            return this;
        }

        /**
         * Set logging to True so all logs could be showed.
         *
         * @return the {@link Loader.Builder} with {@link Logger} configured.
         */
        public Builder<B> enableLog() {
            this.mLoggingEnabled = true;
            return this;
        }
        //endregion

        //region Builder Utils

        /**
         * Compute the amount of RAM the app can use.
         *
         * @param context required app context
         * @return max RAM the app can use
         */
        private int getMaxApplicationRam(Context context) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE))
                    .getMemoryInfo(new ActivityManager.MemoryInfo());
            return (int) memoryInfo.availMem;
        }

        /**
         * Create the required {@link Cache} depending on the {@link Loader.Builder} configuration.
         *
         * @return {@link RamSerializedCache} if serialization is configured,
         * {@link RamCache} otherwise
         */
        private Cache<String, B> getRamCache() {
            if (mParser != null) {
                return new RamSerializedCache<>(mParser, mRamCacheSize);
            }
            return new RamCache<>(mSizeOf, mRamCacheSize);
        }

        //endregion

    }

}
