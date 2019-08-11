package com.adyp.jeniuscontact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.domain.contract.ContactServiceContract;
import com.adyp.jeniuscontact.domain.presenter.ContactServiceEdit;

public class ContactEditActivity extends AppCompatActivity implements ContactServiceContract.ContactServiceExec {

    ContactItem contactItem;

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etAge;
    private EditText etPhotoURL;

    private FrameLayout progressBar;

    private Button btEditContact;

//    ContactServiceRequest request;
    private ContactServiceEdit presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        contactItem = getIntent().getParcelableExtra("contact_item");
        presenter = new ContactServiceEdit(this);

        getSupportActionBar().setTitle("Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initView();
    }

    private void editContact(){
        hideKeyboard();

        contactItem.setFirstName(etFirstName.getText().toString());
        contactItem.setLastName(etLastName.getText().toString());
        contactItem.setAge(etAge.getText().toString());
        contactItem.setPhotoPath(etPhotoURL.getText().toString());

//        request = new ContactServiceRequest();
//        request.editContactService(contactItem);
        presenter.actionContactService(contactItem);
    }

    private void initView()
    {
        etFirstName = (EditText)findViewById(R.id.et_edit_first_name);
        etLastName = (EditText)findViewById(R.id.et_edit_last_name);
        etAge = (EditText)findViewById(R.id.et_edit_age);
        etPhotoURL = (EditText) findViewById(R.id.et_edit_photo);

        etFirstName.setText(contactItem.getFirstName());
        etLastName.setText(contactItem.getLastName());
        etAge.setText(contactItem.getAge());
        etPhotoURL.setText(contactItem.getPhotoPath());

        btEditContact = (Button)findViewById(R.id.btn_edit_contact);
        btEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editContact();
            }
        });

        progressBar = (FrameLayout) findViewById(R.id.progress_overlay);
        progressBar.setVisibility(View.GONE);
    }

    private void hideKeyboard(){
        //hide keyboard
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
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
    public void showRequestError(String errorMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(errorMessage);

        // add a button
        builder.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        //Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleSuccessAction(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ContactEditActivity.this, ContactDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("contact_item", contactItem);
        startActivity(intent);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent intent = new Intent(ContactEditActivity.this, ContactDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("contact_item", contactItem);
                startActivity(intent);
                finish();
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ContactEditActivity.this, ContactDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("contact_item", contactItem);
        startActivity(intent);
        finish();
    }
}
