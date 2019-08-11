package com.adyp.jeniuscontact.data.response;

import com.adyp.jeniuscontact.ContactDetailActivity;
import com.adyp.jeniuscontact.data.model.ContactItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactServiceResponse {
    @SerializedName("message")private String message;
    @SerializedName("data")private ArrayList<ContactItem> listContact = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ContactItem> getListContact() {
        return listContact;
    }

    public void setListContact(ArrayList<ContactItem> listContact) {
        this.listContact = listContact;
    }

}