package com.adyp.jeniuscontact.domain.contract;

import com.adyp.jeniuscontact.data.model.ContactItem;

import java.util.ArrayList;

public class ContactServiceContract {
    public interface ContactServiceView {
        void showInitialLoading();
        void hideInitialLoading();
        void showContactService(ArrayList<ContactItem> list);
        void showRequestError(String message);
    }

    public interface ContactServicePresenter {
        void getContactService();
    }


    public interface ContactServiceExec
    {
        void showInitialLoading();
        void hideInitialLoading();
        void showRequestError(String message);
        void handleSuccessAction(String message);
    }

    public interface ContactServiceExecPresenter{
        void actionContactService(ContactItem item);
    }

    public interface ContactServiceItemView {
        void showInitialLoading();
        void hideInitialLoading();
        void showContactService(ContactItem contactItem);
        void showRequestError(String message);
    }

    public interface ContactServiceItemPresenter {
        void getContactServiceItem(String id);
    }




}
