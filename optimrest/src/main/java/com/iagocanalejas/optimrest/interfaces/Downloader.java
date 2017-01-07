package com.iagocanalejas.optimrest.interfaces;

import java.io.IOException;

/**
 * Created by Iago on 07/01/2017.
 */

public interface Downloader<T> {

    T download() throws IOException;

    void download(Callback<T> callback);

}
