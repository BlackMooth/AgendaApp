package com.example.agendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agendaapp.db.DbContacts;

public class NewActivity extends AppCompatActivity {

    EditText txtName;
    EditText txtTelephone;
    EditText txtEmail;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        txtName = findViewById(R.id.txtName);
        txtTelephone = findViewById(R.id.txtTelephone);
        txtEmail = findViewById(R.id.txtEmail);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContacts dbContacts = new DbContacts(NewActivity.this);
                long id = dbContacts.insertContact(txtName.getText().toString(), txtTelephone.getText().toString(), txtEmail.getText().toString());

                if (id > 0) {
                    Toast.makeText(NewActivity.this, "REGISTRY SAVED", Toast.LENGTH_SHORT).show();
                clean();
                } else {
                    Toast.makeText(NewActivity.this, "ERROR SAVING THE REGISTRY", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clean() {
        txtName.setText("");
        txtTelephone.setText("");
        txtEmail.setText("");
    }
}