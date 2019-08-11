package com.adyp.jeniuscontact.domain.presenter;

import com.adyp.jeniuscontact.data.api.callback.OnEditContactServiceCallback;
import com.adyp.jeniuscontact.data.api.request.ContactServiceRequest;
import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.domain.contract.ContactServiceContract;

public class ContactServiceEdit implements ContactServiceContract.ContactServiceExecPresenter, OnEditContactServiceCallback {

    private ContactServiceRequest request;
    private ContactServiceContract.ContactServiceExec view;

    public ContactServiceEdit(ContactServiceContract.ContactServiceExec view){
        this.view = view;
        request = new ContactServiceRequest();
    }

    @Override
    public void onEditContactServiceSuccess(String Message) {
        view.hideInitialLoading();
        view.handleSuccessAction(Message);
    }

    @Override
    public void onEditContactServiceFailed(String ErrorMessage) {
        view.hideInitialLoading();
        view.showRequestError(ErrorMessage);

    }

    @Override
    public void actionContactService(ContactItem item) {
        view.showInitialLoading();
        request.editContactService(item, this);


    }
}
