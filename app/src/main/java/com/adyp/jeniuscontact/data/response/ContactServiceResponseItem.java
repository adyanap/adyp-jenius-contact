package com.adyp.jeniuscontact.data.response;

import com.adyp.jeniuscontact.data.model.ContactItem;
import com.google.gson.annotations.SerializedName;


public class ContactServiceResponseItem {
    @SerializedName("message")
    private String message;
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("error")
    private String error;
    @SerializedName("data")

    private ContactItem contactItem = new ContactItem();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactItem getContactItem() {
        return contactItem;
    }

    public void setContactItem(ContactItem contactItem) {
        this.contactItem = contactItem;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
