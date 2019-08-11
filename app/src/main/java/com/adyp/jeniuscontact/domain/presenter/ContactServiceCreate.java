package com.adyp.jeniuscontact.domain.presenter;

import com.adyp.jeniuscontact.data.api.callback.OnCreateContactServiceCallback;
import com.adyp.jeniuscontact.data.api.request.ContactServiceRequest;
import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.domain.contract.ContactServiceContract;

public class ContactServiceCreate implements ContactServiceContract.ContactServiceExecPresenter, OnCreateContactServiceCallback {

    private ContactServiceRequest request;
    private ContactServiceContract.ContactServiceExec view;


    public ContactServiceCreate(ContactServiceContract.ContactServiceExec view){
        this.view = view;
        request = new ContactServiceRequest();
    }

    @Override
    public void onCreateContactServiceSuccess(String Message) {
        view.hideInitialLoading();
        view.handleSuccessAction(Message);
    }

    @Override
    public void onCreateContactServiceFailed(String ErrorMessage) {
        view.hideInitialLoading();
        view.showRequestError(ErrorMessage);
    }


    @Override
    public void actionContactService(ContactItem item) {
        view.showInitialLoading();
        request.createContactService(item, this);
    }
}
