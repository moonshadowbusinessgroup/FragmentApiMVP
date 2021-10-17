package com.hadimusthafa.fragmentapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DATABASE_TRAINING";
    private static final String TABLE_NAME = "TABLE_ORDER";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String GENDER = "gender";
    private static final String STATUS = "status";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER," +
                NAME + " TEXT," +
                EMAIL + " TEXT," +
                GENDER + " TEXT," +
                STATUS + " TEXT" + " )";

        db.execSQL(createDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addData(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(ID, user.getId());
        v.put(NAME, user.getName());
        v.put(EMAIL, user.getEmail());
        v.put(GENDER, user.getGender());
        v.put(STATUS, user.getStatus());

        long ID = db.insert(TABLE_NAME, null, v);
        return ID;
    }

    public User getData(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] query = new String[]{DBHelper.ID, NAME, EMAIL, GENDER, STATUS};
        Cursor cursor = db.query(TABLE_NAME, query, DBHelper.ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new User(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
    }

    public ArrayList<User> getAllData() {
        ArrayList<User> allModels = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setGender(cursor.getString(3));
                user.setStatus(cursor.getString(4));
                allModels.add(user);
            } while (cursor.moveToNext());
        }
        return allModels;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
