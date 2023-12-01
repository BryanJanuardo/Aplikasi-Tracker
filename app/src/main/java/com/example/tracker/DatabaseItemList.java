package com.example.tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseItemList extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Tracker.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "ItemList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ITEMNAME = "ItemName";
    private static final String COLUMN_TOTALCALORIES = "TotalCalories";
    private static final String COLUMN_WEIGHTVOLUME = "WeightVolume";
    private static final String COLUMN_ITEMTYPE = "ItemType";

    public DatabaseItemList(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_ITEMNAME + " TEXT, " +
                        COLUMN_ITEMTYPE + " TEXT, " +
                        COLUMN_TOTALCALORIES + " DOUBLE, " +
                        COLUMN_WEIGHTVOLUME + " DOUBLE);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addItem(String ItemName, String ItemType, Double TotalCalories, Double WeightVolume){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ITEMNAME, ItemName);
        cv.put(COLUMN_ITEMTYPE, ItemType);
        cv.put(COLUMN_TOTALCALORIES, TotalCalories);
        cv.put(COLUMN_WEIGHTVOLUME, WeightVolume);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}
