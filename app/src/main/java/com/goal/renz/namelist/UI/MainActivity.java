package com.goal.renz.namelist.UI;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.goal.renz.namelist.DataAdapter.Adapter;
import com.goal.renz.namelist.DataSet.Names;
import com.goal.renz.namelist.Database.DatabaseAdapter;
import com.goal.renz.namelist.Database.DatabaseHelper;
import com.goal.renz.namelist.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;

    DatabaseHelper databaseHelper;
    List<Names> namesList;

    RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;

    public LinearLayoutManager linearLayoutManager;
    ImageView circleImageView;



    Names names = new Names();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        namesList = new ArrayList<Names>();
        namesList = databaseHelper.getData();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setItemAnimator(null);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.invalidate();
        adapter = new Adapter(this, namesList);
        adapter.notifyItemRangeInserted(0, namesList.size());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



        //initializeAdapter();

        circleImageView = (ImageView) findViewById(R.id.profilepic);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AddAssignment.class));

            }
        });



       // initializeData();

    }

    /*
    public void initializeData(){

        namesList = new ArrayList<>();
        namesList.add(new Names(R.id.profilepic, "ADDBASE", "No Title", "Create a simple database", "Aug 8"));
        namesList.add(new Names(R.drawable.profilepic, "COPRO1", "No Title", "Gagawa ng hello world", "Jun 10"));
        namesList.add(new Names(R.drawable.profilepic, "COPRO2","No Title", "Aaralin ang object oriented programming", "Aug 11"));
        namesList.add(new Names(R.drawable.profilepic, "THEODBS", "No Title","Aaralin ang mga query sa sql", "Sept 9"));
        namesList.add(new Names(R.drawable.profilepic, "MOBIDEV","No Title", "Masterin ang android programmning", "Nov 15"));
        namesList.add(new Names(R.drawable.profilepic, "LOGSIGN", "No Title","Assignment, gumawa ng logic gates", "Oct 8"));
        namesList.add(new Names(R.drawable.profilepic, "WEBPROG","No Title", "Dami assignment, wala ako ma gets", "May 8"));


    }
*/

    /*
    public void initializeAdapter(){
      //  adapter = new Adapter(namesList);8
        adapter = new Adapter(this, namesList);
        adapter.notifyItemRangeInserted(0, namesList.size());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    */




}
