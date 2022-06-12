package com.example.agendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.agendaapp.db.DbContacts;
import com.example.agendaapp.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewActivity extends AppCompatActivity {

    EditText txtName;
    EditText txtTelephone;
    EditText txtEmail;
    Button btnSave;
    FloatingActionButton fabEdit;

    Contact contact;
    String name = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        txtName = findViewById(R.id.txtName);
        txtTelephone = findViewById(R.id.txtTelephone);
        txtEmail = findViewById(R.id.txtEmail);
        btnSave = findViewById(R.id.btnSave);
        fabEdit = findViewById(R.id.fabEdit);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                name = null;
            } else {
                name = extras.getString("Name");
            }
        } else {
            name = (String) savedInstanceState.getSerializable("Name");
        }

        DbContacts dbContacts = new DbContacts(ViewActivity.this);
        contact = dbContacts.viewContact(name);

        if (contact != null) {
            txtName.setText(contact.getName());
            txtTelephone.setText(contact.getTelephone());
            txtEmail.setText(contact.getEmail());

            btnSave.setVisibility(View.INVISIBLE);
            txtName.setInputType(InputType.TYPE_NULL);
            txtTelephone.setInputType(InputType.TYPE_NULL);
            txtEmail.setInputType(InputType.TYPE_NULL);
        }

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, EditActivity.class);
                intent.putExtra("Name", name);
                startActivity(intent);
            }
        });
    }
}