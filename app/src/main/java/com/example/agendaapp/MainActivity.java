package com.example.agendaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.agendaapp.adapters.ListContactsAdapter;
import com.example.agendaapp.db.DbContacts;
import com.example.agendaapp.entities.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewContacts = findViewById(R.id.listContacts);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));

        DbContacts dbContacts = new DbContacts(MainActivity.this);

        ListContactsAdapter adapter = new ListContactsAdapter(dbContacts.showContacts());
        recyclerViewContacts.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNew:
                newRegistry();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void newRegistry() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }
}