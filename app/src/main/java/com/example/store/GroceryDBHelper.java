package com.example.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import com.example.store.GroceryContract.*;

public class GroceryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "grocery;ist.db";
    public static final int DATABASE_VERSION = 1;

    public GroceryDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERY_LIST_TABLE = "CREATE TABLE " + GroceryEntry.TABLE_NAME +
                " (" + GroceryEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GroceryEntry.COLUMN_NAME + "TEXT NOT NULL, " +
                GroceryEntry.COLUMN_AMOUNT + "INTEGER NOT NULL, " +
                GroceryEntry.COLUMN_TIMESTAMP + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_GROCERY_LIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GroceryEntry.TABLE_NAME);
        onCreate(db);

    }
}
