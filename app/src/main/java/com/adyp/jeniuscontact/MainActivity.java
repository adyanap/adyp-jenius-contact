package com.adyp.jeniuscontact;

import android.content.Intent;
import android.os.Bundle;

import com.adyp.jeniuscontact.data.model.ContactItem;
import com.adyp.jeniuscontact.domain.contract.ContactServiceContract;
import com.adyp.jeniuscontact.domain.presenter.ContactServicePresenter;
import com.adyp.jeniuscontact.view.adapter.ContactAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;


    public class MainActivity extends AppCompatActivity implements ContactServiceContract.ContactServiceView, ContactAdapter.OnContactItemClickListener {

    private ContactServicePresenter presenter;

    private RecyclerView rvContact;
    private ProgressBar progressBar;

    private ContactAdapter adapter;
    private ArrayList<ContactItem> listContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setRecyclerView();

        presenter = new ContactServicePresenter(this);
        presenter.getContactService();

        //setupActionBar();
        FloatingActionButton fab = findViewById(R.id.fab_create);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentContactCreate = new Intent(MainActivity.this, ContactCreateActivity.class);
                startActivity(intentContactCreate);
            }
        });
    }

    private void setRecyclerView() {
        listContact = new ArrayList<>();
        adapter = new ContactAdapter(this);

        rvContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvContact.setAdapter(adapter);
    }

    private void initView() {
        rvContact = (RecyclerView) findViewById(R.id.rv_contact);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void showInitialLoading() {
        //start loading
        progressBar.setVisibility(View.VISIBLE);
        rvContact.setVisibility(View.GONE);
    }

    @Override
    public void hideInitialLoading() {
        //loading finish
        progressBar.setVisibility(View.GONE);
        rvContact.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContactService(ArrayList<ContactItem> list) {
        for (ContactItem item : list) {
            Log.d("Contact Name", item.getFirstName() + " " + item.getLastName());
        }
        //listContact.addAll(list);
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        adapter.setOnContactItemClickListener(this);
    }

    @Override
    public void showRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnContactItemClicked(ContactItem contactItem)
    {
        //Toast.makeText(this,contactItem.getId(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ContactDetailActivity.class);
        intent.putExtra("contact_item", contactItem);
        startActivity(intent);
    }


    public void refreshActivity() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();

    }

    @Override
    protected void onRestart() {
        refreshActivity();
        super.onRestart();
    }

    //    @Override
//    protected void onResume() {
//        refreshActivity();
//        super.onResume();
//    }

    //    private void setupActionBar() {
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle("JENIUS Contact");
//        }
//    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
