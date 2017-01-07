package com.iagocanalejas.optimrest_retrofit;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Iagocanalejas on 07/01/2017.
 * Wrap classes to work with {@link retrofit2.Retrofit}
 * and {@link com.iagocanalejas.optimrest.Loader}
 */
public abstract class Optimfit {

    /**
     * Adapt default retrofit {@link retrofit2.Callback} to work as our {@link Callback}.
     */
    public abstract class Callback<T> implements retrofit2.Callback<T>,
            com.iagocanalejas.optimrest.interfaces.Callback<T> {

        /**
         * Invoked for a received HTTP response.
         * <p>
         * Note: An HTTP response may still indicate an app-level failure such as a 404 or 500.
         * Call {@link Response#isSuccessful()} to determine if the response indicates success.
         */
        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                onLoadSuccess(response.body());
                return;
            }
            onLoadError(response.code(), response.message());
        }

        /**
         * Invoked when a network exception occurred talking to the server or when an unexpected
         * exception occurred creating the request or processing the response.
         */
        @Override
        public void onFailure(Call<T> call, Throwable t) {
            onServerError(t.getMessage());
        }

    }
}
