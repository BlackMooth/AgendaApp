package com.example.agendaapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.agendaapp.entities.Contact;

import java.util.ArrayList;

public class DbContacts extends DbHelper {

    Context context;

    public DbContacts(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertContact(String name, String telephone, String email) {
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("telephone", telephone);
            values.put("email", email);

            return db.insert(TABLE_CONTACTS, null, values);
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Contact> showContacts() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contact> listContacts = new ArrayList<>();
        Contact contact = null;
        Cursor cursorContacts = null;

        cursorContacts = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);

        if (cursorContacts.moveToFirst()) {
            do {
                contact = new Contact();
                contact.setName(cursorContacts.getString(0));
                contact.setTelephone(cursorContacts.getString(1));
                contact.setEmail(cursorContacts.getString(2));
                listContacts.add(contact);
            } while(cursorContacts.moveToNext());
        }

        cursorContacts.close();

        return listContacts;
    }
}
