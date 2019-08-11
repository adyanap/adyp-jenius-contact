package com.adyp.jeniuscontact.data.api.base;
import com.loopj.android.http.AsyncHttpClient;

public class BaseApiClient {
    public AsyncHttpClient getAsyncHttpClient(){
        return new AsyncHttpClient();
    }
}
