package com.adyp.jeniuscontact.data.api.request;

import com.adyp.jeniuscontact.data.api.ApiConstant;
import com.adyp.jeniuscontact.data.api.base.BaseApiClient;
import com.adyp.jeniuscontact.data.api.callback.OnCreateContactServiceCallback;
import com.adyp.jeniuscontact.data.api.callback.OnDeleteContactServiceCallback;
import com.adyp.jeniuscontact.data.api.callback.OnEditContactServiceCallback;
import com.adyp.jeniuscontact.data.api.callback.OnGetContactServiceCallback;
import com.adyp.jeniuscontact.data.api.callback.OnGetContactServiceItemCallback;
import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.data.response.ContactServiceResponse;
import com.adyp.jeniuscontact.data.response.ContactServiceResponseItem;
import com.adyp.jeniuscontact.domain.presenter.ContactServiceDelete;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class ContactServiceRequest {
    private BaseApiClient baseApiClient;
    private Gson gson;
    private static AsyncHttpClient client;

    public ContactServiceRequest() {
        this.baseApiClient = new BaseApiClient();
        gson = new Gson();
    }

    //Request Contact Service List
    public void getContactService(final OnGetContactServiceCallback callback) {
        baseApiClient.getAsyncHttpClient().get(ApiConstant.GET_CONTACT_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //parsing to java obj
                ContactServiceResponse contactServiceResponse = gson.fromJson(new String(responseBody), ContactServiceResponse.class);

                ArrayList<ContactItem> list = contactServiceResponse.getListContact();

                //replace http with https, because glide only works with https??? check later.
                int i = 0;
                for (ContactItem item : list) {
                    String newUrl = item.getPhotoPath().replaceFirst("http://", "https://");
                    item.setPhotoPath(newUrl);
                    list.set(i, item);
                    i++;
                }

                callback.onGetContactServiceSuccess(contactServiceResponse.getListContact());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onGetContactServiceFailed("Unable to connect to server");

            }
        });

    }

    //Create Contact Service List
    public void createContactService(ContactItem contactItem, final OnCreateContactServiceCallback callback) {
        RequestParams params = new RequestParams();
        params.put("firstName", contactItem.getFirstName());
        params.put("lastName", contactItem.getLastName());
        params.put("age", contactItem.getAge());
        params.put("photo", contactItem.getPhotoPath());

        baseApiClient.getAsyncHttpClient().post(ApiConstant.CREATE_CONTACT_URL, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        //parsing to java obj
                        ContactServiceResponse contactServiceResponse = gson.fromJson(new String(responseBody), ContactServiceResponse.class);
                        callback.onCreateContactServiceSuccess(contactServiceResponse.getMessage());
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        ContactServiceResponse contactServiceResponse = gson.fromJson(new String(responseBody), ContactServiceResponse.class);
                        callback.onCreateContactServiceFailed(contactServiceResponse.getMessage());
                    }
                }
        );
    }

    //Request Contact Service Item
    public void getContactServiceItem(String id, final OnGetContactServiceItemCallback callback) {

        String URL = ApiConstant.GET_CONTACT_URL + "/" + id;

        baseApiClient.getAsyncHttpClient().get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //parsing to java obj
                ContactServiceResponseItem contactServiceResponseItem = gson.fromJson(new String(responseBody), ContactServiceResponseItem.class);

                ContactItem contactItem = contactServiceResponseItem.getContactItem();

                //replace http with https, because glide only works with https??? check later.
                String newUrl = contactItem.getPhotoPath().replaceFirst("http://", "https://");
                contactItem.setPhotoPath(newUrl);

                callback.onGetContactServiceItemSuccess(contactItem);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onGetContactServiceItemFailed("Unable to connect to server");

            }
        });

    }

    //Edit Contact Service
    public void editContactService(ContactItem contactItem, final OnEditContactServiceCallback callback) {
        RequestParams params = new RequestParams();
        params.put("firstName", contactItem.getFirstName());
        params.put("lastName", contactItem.getLastName());
        params.put("age", contactItem.getAge());
        params.put("photo", contactItem.getPhotoPath());

        String URL = ApiConstant.EDIT_CONTACT_URL + "/" + contactItem.getId();

        if(contactItem != null && contactItem.getId() != "") {
            baseApiClient.getAsyncHttpClient().put(URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    //parsing to java obj
                    ContactServiceResponseItem contactServiceResponse = gson.fromJson(new String(responseBody), ContactServiceResponseItem.class);
                    callback.onEditContactServiceSuccess(contactServiceResponse.getMessage());
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    ContactServiceResponseItem contactServiceResponse = gson.fromJson(new String(responseBody), ContactServiceResponseItem.class);
                    callback.onEditContactServiceFailed(contactServiceResponse.getMessage());
                }
            });
        }
        else {
            callback.onEditContactServiceFailed("ID not found");
        }


    }

    //Delete Contact Service
    public void deleteContactService(String id, final OnDeleteContactServiceCallback callback){
        String URL = ApiConstant.DELETE_CONTACT_URL + "/" + id;

        baseApiClient.getAsyncHttpClient().delete(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ContactServiceResponseItem contactServiceResponseItem = gson.fromJson(new String(responseBody), ContactServiceResponseItem.class);
                callback.OnDeleteContactServiceSuccess(contactServiceResponseItem.getMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ContactServiceResponseItem contactServiceResponseItem = gson.fromJson(new String(responseBody), ContactServiceResponseItem.class);
                callback.OnDeleteContactServiceFailed(contactServiceResponseItem.getMessage());
                //callback.OnDeleteContactServiceSuccess(contactServiceResponseItem.getMessage());
            }
        });

    }


}
