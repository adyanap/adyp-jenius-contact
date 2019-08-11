package com.adyp.jeniuscontact.domain.presenter;


import com.adyp.jeniuscontact.data.api.callback.OnGetContactServiceItemCallback;
import com.adyp.jeniuscontact.data.api.request.ContactServiceRequest;
import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.domain.contract.ContactServiceContract;


public class ContactServiceItem implements ContactServiceContract.ContactServiceItemPresenter, OnGetContactServiceItemCallback {

    private ContactServiceRequest request;
    private ContactServiceContract.ContactServiceItemView view;

    public ContactServiceItem(ContactServiceContract.ContactServiceItemView view) {
        this.view = view;
        request = new ContactServiceRequest();
    }

    @Override
    public void onGetContactServiceItemSuccess(ContactItem contactItem) {
        view.hideInitialLoading();
        view.showContactService(contactItem);

    }

    @Override
    public void onGetContactServiceItemFailed(String ErrorMessage) {
        view.hideInitialLoading();
        view.showRequestError(ErrorMessage);
    }

    @Override
    public void getContactServiceItem(String id) {
        view.showInitialLoading();
        request.getContactServiceItem(id, this);
    }
}
