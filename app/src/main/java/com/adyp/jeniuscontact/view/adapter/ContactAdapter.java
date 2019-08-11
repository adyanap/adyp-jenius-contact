package com.adyp.jeniuscontact.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adyp.jeniuscontact.R;
import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.util.CustomOnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<ContactItem> list;
    private Activity activity;

    private OnContactItemClickListener onContactItemClickListener;

    public OnContactItemClickListener getOnContactItemClickListener() {
        return onContactItemClickListener;
    }

    public void setOnContactItemClickListener(OnContactItemClickListener onContactItemClickListener) {
        this.onContactItemClickListener = onContactItemClickListener;
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvContactName;
        TextView tvAge;
        ImageView imgContact;
        LinearLayout cardContact;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContact = (ImageView) itemView.findViewById(R.id.img_contact);
            tvContactName = (TextView) itemView.findViewById(R.id.tv_contactname);
            tvAge = (TextView) itemView.findViewById(R.id.tv_age);
            cardContact = (LinearLayout) itemView.findViewById(R.id.card_contact);
        }
    }

    public ContactAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setList(ArrayList<ContactItem> list) {
        this.list = list;
    }

    public ArrayList<ContactItem> getList() {
        return list;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        ContactViewHolder holder = new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        final ContactItem contactItem = getList().get(position);
        String photoPath = contactItem.getPhotoPath();

//        if (photoPath != null || photoPath != "N/A")
//        {
//            photoPath += "?w=360";
//        }
//        else photoPath = null;

        RequestOptions options = new RequestOptions();
        options.circleCrop();
        //options.centerCrop();

        Glide.with(activity).load(photoPath).fitCenter().apply(options).into(holder.imgContact);
        holder.tvContactName.setText(contactItem.getFirstName() + " " + contactItem.getLastName());
        holder.tvAge.setText("Age: " + contactItem.getAge());

        holder.cardContact.setOnClickListener(
                new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemCallback(int position, View view) {
                        getOnContactItemClickListener().OnContactItemClicked(contactItem);
                    }
                }

                )
        );

    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public interface OnContactItemClickListener {
        void OnContactItemClicked(ContactItem contactItem);
    }


}
