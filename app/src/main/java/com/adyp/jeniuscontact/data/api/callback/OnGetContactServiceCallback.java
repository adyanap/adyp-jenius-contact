package com.adyp.jeniuscontact.data.api.callback;
import com.adyp.jeniuscontact.data.model.ContactItem;
import java.util.ArrayList;

public interface OnGetContactServiceCallback {
    void onGetContactServiceSuccess(ArrayList<ContactItem> listContact);
    void onGetContactServiceFailed(String ErrorMessage);
}