package com.adyp.jeniuscontact.domain.presenter;

import com.adyp.jeniuscontact.data.api.callback.OnDeleteContactServiceCallback;
import com.adyp.jeniuscontact.data.api.request.ContactServiceRequest;
import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.domain.contract.ContactServiceContract;

public class ContactServiceDelete implements ContactServiceContract.ContactServiceExecPresenter, OnDeleteContactServiceCallback {

    private ContactServiceRequest request;
    private ContactServiceContract.ContactServiceExec view;

    public ContactServiceDelete(ContactServiceContract.ContactServiceExec view) {
        this.view = view;
        request = new ContactServiceRequest();
    }

    @Override
    public void OnDeleteContactServiceSuccess(String Message) {
        view.hideInitialLoading();
        view.handleSuccessAction(Message);
    }

    @Override
    public void OnDeleteContactServiceFailed(String Message) {
        view.hideInitialLoading();
        view.showRequestError(Message);
    }

    @Override
    public void actionContactService(ContactItem item) {
        view.showInitialLoading();
        request.deleteContactService(item.getId(), this);
    }
}
