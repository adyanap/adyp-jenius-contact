package com.adyp.jeniuscontact.data.api.callback;

public interface OnCreateContactServiceCallback {
    void onCreateContactServiceSuccess(String Message);
    void onCreateContactServiceFailed(String ErrorMessage);
}
