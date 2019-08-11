package com.adyp.jeniuscontact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.domain.contract.ContactServiceContract;
import com.adyp.jeniuscontact.domain.presenter.ContactServiceDelete;
import com.adyp.jeniuscontact.domain.presenter.ContactServiceItem;
import com.bumptech.glide.Glide;

public class ContactDetailActivity extends AppCompatActivity
        implements
        ContactServiceContract.ContactServiceItemView,
        View.OnClickListener,
        DialogInterface.OnClickListener,
        ContactServiceContract.ContactServiceExec {

    private ImageView imgContactDetail;
    private TextView tvNameDetail;
    private TextView tvAgeDetail;
    private ContactItem contactItem;

    private ContactServiceItem presenter;
    private ContactServiceDelete presenterDel;

    private FrameLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        contactItem = getIntent().getParcelableExtra("contact_item");

        initView();

        presenter = new ContactServiceItem(this);
        presenter.getContactServiceItem(contactItem.getId());

        presenterDel = new ContactServiceDelete(this);
    }

    private void initView() {
        imgContactDetail = (ImageView) findViewById(R.id.img_contact_detail);
        tvNameDetail = (TextView) findViewById(R.id.tv_name_detail);
        tvAgeDetail = (TextView) findViewById(R.id.tv_age_detail);

        tvNameDetail.setText(contactItem.getFirstName() + " " + contactItem.getLastName());
        tvAgeDetail.setText("Age: " + contactItem.getAge());
        Glide.with(this).load(contactItem.getPhotoPath()).into(imgContactDetail);

        Button btnEdit = (Button) findViewById(R.id.btn_edit_contact);
        btnEdit.setOnClickListener(this);

        Button btnDelete = (Button) findViewById(R.id.btn_delete_contact);
        btnDelete.setOnClickListener(this);

        progressBar = (FrameLayout) findViewById(R.id.progress_overlay);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_edit_contact:
                Intent intentContactEdit = new Intent(getApplicationContext(), ContactEditActivity.class);
                intentContactEdit.putExtra("contact_item", contactItem);
                startActivity(intentContactEdit);
                break;

            case R.id.btn_delete_contact:
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete Contact");
                builder.setMessage("Are you sure you want to delete this contact?");

                // add a button
                builder.setPositiveButton("Delete", this);
                builder.setNegativeButton("Cancel", null);

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

                //Toast.makeText(getApplicationContext(),"Delete", Toast.LENGTH_LONG).show();
                break;

            default:
                break;

        }
    }


    @Override
    public void showInitialLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInitialLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showContactService(ContactItem contactItem) {
        tvNameDetail.setText(contactItem.getFirstName() + " " + contactItem.getLastName());
        tvAgeDetail.setText("Age: " + contactItem.getAge());
        Glide.with(this).load(contactItem.getPhotoPath()).into(imgContactDetail);

    }

    @Override
    public void showRequestError(String errorMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(errorMessage);

        // add a button
        builder.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();


        //Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleSuccessAction(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ContactDetailActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case DialogInterface.BUTTON_POSITIVE:
                //Yes button clicked
                presenterDel.actionContactService(contactItem);

                break;

            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                break;
        }

    }


//    public void refreshActivity() {
//        Intent i = new Intent(this, ContactDetailActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(i);
//        finish();
//
//    }
//
//    @Override
//    protected void onRestart() {
//        refreshActivity();
//        super.onRestart();
//    }

}
