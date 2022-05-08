package com.example.agendaapp.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    /** Version of the database. **/
    private static final int DB_VERSION = 3;
    /** Name of the database. **/
    private static final String DB_NAME = "agenda.db";
    /** Name of the table of Contacts of the database. **/
    public static final String TABLE_CONTACTS = "t_contacts";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + "(" +
                "name TEXT PRIMARY KEY NOT NULL," +
                "telephone TEXT NOT NULL," +
                "email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE" + TABLE_CONTACTS);
        onCreate(db);
    }
}
