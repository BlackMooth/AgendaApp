package com.example.agendaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaapp.db.DbContacts;
import com.example.agendaapp.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {

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

        // Disable the visibility of this buttons
        FloatingActionButton fabEdit = findViewById(R.id.fabEdit);
        FloatingActionButton fabDelete = findViewById(R.id.fabDelete);
        fabEdit.setVisibility(View.INVISIBLE);
        fabDelete.setVisibility(View.INVISIBLE);

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

        DbContacts dbContacts = new DbContacts(EditActivity.this);
        contact = dbContacts.viewContact(name);

        if (contact != null) {
            txtName.setText(contact.getName());
            txtTelephone.setText(contact.getTelephone());
            txtEmail.setText(contact.getEmail());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean contactUpdated = false;

                if (!txtName.getText().toString().equals("") && !txtTelephone.getText().toString().equals("")) {
                    contactUpdated = dbContacts.editContact(name, txtName.getText().toString(), txtTelephone.getText().toString(), txtEmail.getText().toString());

                    if (contactUpdated) {
                        Toast.makeText(EditActivity.this, "CONTACT UPDATED", Toast.LENGTH_LONG).show();
                        viewContact();
                    } else {
                        Toast.makeText(EditActivity.this, "ERROR MODIFYING THE CONTACT", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditActivity.this, "YOU MUST FILL THE MANDATORY FIELDS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void viewContact() {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("Name", name);
        startActivity(intent);
    }
}
