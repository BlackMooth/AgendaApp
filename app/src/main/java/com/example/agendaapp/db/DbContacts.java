package com.example.agendaapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

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
}
