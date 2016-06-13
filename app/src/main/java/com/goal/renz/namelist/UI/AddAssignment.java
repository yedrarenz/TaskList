package com.goal.renz.namelist.UI;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.goal.renz.namelist.DataAdapter.Adapter;
import com.goal.renz.namelist.DataSet.Names;
import com.goal.renz.namelist.Database.DatabaseAdapter;
import com.goal.renz.namelist.Database.DatabaseHelper;
import com.goal.renz.namelist.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.TimeZone;

public class AddAssignment extends AppCompatActivity {



    DatabaseHelper databaseHelper;
    List<Names> namesArrayList;

    RecyclerView.Adapter adapter;

    Date datenow = new Date();

    EditText txtTitle,txtSubject,txtDescription;
    String date = (String) android.text.format.DateFormat.format("MMM d", datenow);

    MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtSubject = (EditText) findViewById(R.id.txtSubject);
        txtDescription = (EditText) findViewById(R.id.txtDescription);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Compose");

        namesArrayList = new ArrayList<Names>();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                 break;

            case R.id.done_black:

                namesArrayList.clear();

                String title = txtTitle.getText().toString();
                String subject = txtSubject.getText().toString();
                String description = txtDescription.getText().toString();

                if (title.equals("") || subject.equals("") || description.equals("") || date.equals("")){
                    Toast.makeText(AddAssignment.this, "Please complete all the fields", Toast.LENGTH_SHORT).show();

                }

                else {
                    databaseHelper = new DatabaseHelper(AddAssignment.this);
                    databaseHelper.insertData(title, subject, description, date);

                }

                txtTitle.setText("");
                txtSubject.setText("");
                txtDescription.setText("");


                databaseHelper = new DatabaseHelper(this);
                namesArrayList = new ArrayList<Names>();
                namesArrayList = databaseHelper.getData();

                adapter = new Adapter(this, namesArrayList);
                adapter.notifyItemInserted(-1);



                finish();





                break;
    }

        return super.onOptionsItemSelected(item);

    }



}
