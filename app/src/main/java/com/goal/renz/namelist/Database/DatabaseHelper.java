package com.goal.renz.namelist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.goal.renz.namelist.DataAdapter.Adapter;
import com.goal.renz.namelist.DataSet.Names;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renz on 5/9/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    Context context;

    SQLiteDatabase sqLiteDatabase;

    List<Names> namesList;

    public static final String dbname = "dbAssignment";
    public static final String tblName = "assignmentList";
    public static final  String Col_1 = "ID";
    public static final  String Col_2 = "Title";
    public static final  String Col_3 = "Subject";
    public static final  String Col_4 = "Description";
    public static final  String Col_5 = "Date";

    private static final String CREATE = "create table "+ tblName +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Subject TEXT, Description TEXT, Date TEXT )";

   //DATABASE ADAPTER
    public DatabaseHelper(Context context) {
        super(context, dbname, null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tblName);

        onCreate(db);
    }


    //OPEN DB



    public DatabaseHelper openDB(){
        try {
            sqLiteDatabase = this.getWritableDatabase();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }

    public void closeDB(){
        try {
            this.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insertData(String title, String subject, String description, String date){

        //open connection for database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //use content values to add from table;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, title);
        contentValues.put(Col_3, subject);
        contentValues.put(Col_4, description);
        contentValues.put(Col_5, date);


        //insert the content values to table
        sqLiteDatabase.insert(tblName, null, contentValues);

        sqLiteDatabase.close();

        Toast.makeText(context, "Assignment/Task added", Toast.LENGTH_LONG).show();

    }

    public List<Names> getData(){

        List<Names> namesList = new ArrayList<Names>();
        String query = "SELECT * FROM " + tblName ;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Names model = new Names();
                model.setTitle(cursor.getString(1));
                model.setSubject(cursor.getString(2));
                model.setDescription(cursor.getString(3));
                model.setDate(cursor.getString(4));

                namesList.add(model);

            }while (cursor.moveToNext());
        }

        return namesList;

    }

}
