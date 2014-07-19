package com.examplesqlproject.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.examplesqlproject.app.GettingVegetableData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 19/07/2014.
 */
public class DatabaseConnection extends SQLiteOpenHelper
{
    //the version of the database this has to be incremented any time you change something in order
    //for the change to happen.
    private static final int DATABASE_VERSION = 3;

    //Name of database
    private static final String DATABASE_NAME = "imAnExample";

    //Name of table
    private static final String TABLE_VEGETABLES = "vegetables";

    //Name of the columns for the table
    private static final String KEY_ID = "id";
    private static final String KEY_VEG_TYPE = "type";


    public DatabaseConnection(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_VEGETABLES_TABLE = "CREATE TABLE " + TABLE_VEGETABLES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_VEG_TYPE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_VEGETABLES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VEGETABLES);
        onCreate(sqLiteDatabase);
    }

    // Adding new vegetable to the table
    void addVegetable(GettingVegetableData vegetableData)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VEG_TYPE, vegetableData.getVegetableName()); // vegetable Name

        // Inserting Row
        db.insert(TABLE_VEGETABLES, null, values);
        db.close(); // Closing database connection
    }

    // Getting All vegetables
    public List<GettingVegetableData> gettingVegetableDataList()
    {
        List<GettingVegetableData> listOfVegetables = new ArrayList<GettingVegetableData>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_VEGETABLES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                GettingVegetableData vegetableData = new GettingVegetableData();
                vegetableData.setId(Integer.parseInt(cursor.getString(0)));
                vegetableData.setVegetableName(cursor.getString(1));

                // Adding vegetableData to list
                listOfVegetables.add(vegetableData);
            }
            while (cursor.moveToNext());
        }

        // return contact list
        return listOfVegetables;
    }

    // Deleting single vegetable by id
    public void deleteVegetableByID(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VEGETABLES, KEY_ID + "= " + id, null);
        db.close();
    }

    // Deleting single vegetable by name
    public void deleteVegetableByName(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VEGETABLES, KEY_VEG_TYPE + "= " + name, null);
        db.close();
    }

    // Getting contacts Count
    public int getVegetableCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCount= db.rawQuery("SELECT count(*) FROM vegetables", null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;
    }

}
