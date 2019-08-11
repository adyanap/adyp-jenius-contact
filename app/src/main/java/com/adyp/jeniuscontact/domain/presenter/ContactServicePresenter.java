package com.adyp.jeniuscontact.domain.presenter;

import android.content.Intent;

import com.adyp.jeniuscontact.ContactCreateActivity;
import com.adyp.jeniuscontact.MainActivity;
import com.adyp.jeniuscontact.data.api.callback.OnCreateContactServiceCallback;
import com.adyp.jeniuscontact.data.api.callback.OnGetContactServiceCallback;
import com.adyp.jeniuscontact.data.api.request.ContactServiceRequest;
import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.domain.contract.ContactServiceContract;

import java.util.ArrayList;

public class ContactServicePresenter implements ContactServiceContract.ContactServicePresenter, OnGetContactServiceCallback
{
    private ContactServiceRequest request;
    private ContactServiceContract.ContactServiceView view;

    public ContactServicePresenter(ContactServiceContract.ContactServiceView view) {
        this.view = view;
        request = new ContactServiceRequest();
    }

    @Override
    public void onGetContactServiceSuccess(ArrayList<ContactItem> listContact) {
        view.hideInitialLoading();
        view.showContactService(listContact);
    }

    @Override
    public void onGetContactServiceFailed(String ErrorMessage) {
        view.hideInitialLoading();
        view.showRequestError(ErrorMessage);

    }

    @Override
    public void getContactService() {
        view.showInitialLoading();
        request.getContactService(this);
    }

}
