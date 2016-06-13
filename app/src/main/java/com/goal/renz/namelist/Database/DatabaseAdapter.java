package com.goal.renz.namelist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Renz on 5/13/2016.
 */
public class DatabaseAdapter  {

    Context context;

    SQLiteDatabase sqLiteDatabase;

    DatabaseHelper databaseHelper = new DatabaseHelper(context);

    public DatabaseAdapter(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }


  //OPEN DB
    public DatabaseAdapter openDB()
    {
        try {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }


    public void closeDB()
    {
        try
        {
            databaseHelper.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //Another Insert Method
    public long insertData(String title, String subject, String description, String date){

        try
        {
            ContentValues contentValues =  new ContentValues();
            contentValues.put(databaseHelper.Col_2, title);
            contentValues.put(databaseHelper.Col_3, subject);
            contentValues.put(databaseHelper.Col_4, description);
            contentValues.put(databaseHelper.Col_5, date);

            return sqLiteDatabase.insert(databaseHelper.tblName, null, contentValues);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    public Cursor getAllData(){
        String [] colums = {"Title","Subject","Description","Date"};

        return sqLiteDatabase.query(databaseHelper.tblName, colums, null,null,null,null,null,null);
    }


}
