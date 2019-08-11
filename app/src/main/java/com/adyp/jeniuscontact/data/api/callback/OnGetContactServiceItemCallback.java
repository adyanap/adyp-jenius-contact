package com.adyp.jeniuscontact.data.api.callback;

import com.adyp.jeniuscontact.data.model.ContactItem;

import java.util.ArrayList;

public interface OnGetContactServiceItemCallback {
    void onGetContactServiceItemSuccess(ContactItem contactItem);
    void onGetContactServiceItemFailed(String ErrorMessage);
}
