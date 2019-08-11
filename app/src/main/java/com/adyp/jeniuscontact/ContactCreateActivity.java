package com.adyp.jeniuscontact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.domain.contract.ContactServiceContract;
import com.adyp.jeniuscontact.domain.presenter.ContactServiceCreate;

public class ContactCreateActivity extends AppCompatActivity implements ContactServiceContract.ContactServiceExec {
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etAge;
    private EditText etPhotoURL;

    private Button btCreateContact;

    private FrameLayout progressBar;

    ContactItem contactItem;

    private ContactServiceCreate presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_create);

        presenter = new ContactServiceCreate(this);

        initView();
    }

    private void createContact()
    {
        hideKeyboard();
        contactItem = new ContactItem();
        contactItem.setFirstName(etFirstName.getText().toString());
        contactItem.setLastName(etLastName.getText().toString());
        contactItem.setAge(etAge.getText().toString());
        contactItem.setPhotoPath(etPhotoURL.getText().toString());

        presenter.actionContactService(contactItem);
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

    private void initView()
    {
        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etAge = (EditText) findViewById(R.id.et_age);
        etPhotoURL = (EditText) findViewById(R.id.et_photo);

        btCreateContact = (Button) findViewById(R.id.btn_create_contact);
        btCreateContact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createContact();
                    }
                }

        );

        progressBar = (FrameLayout) findViewById(R.id.progress_overlay);
        progressBar.setVisibility(View.GONE);
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
        //Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleSuccessAction(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intentContactMain = new Intent(ContactCreateActivity.this, MainActivity.class);
        intentContactMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentContactMain);
        finish();
    }
}
