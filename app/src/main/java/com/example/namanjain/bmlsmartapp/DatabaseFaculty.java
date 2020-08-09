package com.example.namanjain.bmlsmartapp;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFaculty extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FacultyAdapter facultyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_faculty);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Faculty Directory");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        try
        {
            DatabaseHelper db = new DatabaseHelper(this);
            db.open();
            db.insertdata("Mr. Koshtuba Nand", "Program Manager(SOET)", "koshtuba.nand@bmu.edu.in");
            db.insertdata("Ms. Veeneta Jamwal", "Academic Coordinator (B.Tech-SOET)", "veeneta.jamwal@bmu.edu.in");
            db.insertdata("Mr. Indrajeet Singh	","Academic Coordinator (B.Tech-SOET)",	"indrajeet.singh@bmu.edu.in");
            db.insertdata("Ms. Sapna Sharma","Program Manager(SOM)","sapna.sharma@bmu.edu.in");
            db.insertdata("Dr. Sudip Sanyal","Director","sudip.sanyal@bmu.edu.in");
            db.insertdata("Dr. Jaskiran Arora","Assistant Dean","jaskiran.arora@bmu.edu.in");
            db.insertdata("Dr. Sanjay Kashyap","	Assistant Professor","sanjay.kashyap@bmu.edu.in");

            List<Faculty> facultyList = new ArrayList<>();
            String[][] tableData = db.getAllData();
            if (tableData != null && tableData.length > 0) {
                String[] mRowData = new String[tableData[0].length];
                facultyList.clear();
                for (int i = 0; i < tableData.length; i++) {
                    for (int j = 0; j < tableData[0].length; j++) {
                        String Row_Data = tableData[i][j];
                        mRowData[j] = Row_Data;
                    }
                    String name = mRowData[0];
                    String course = mRowData[1];
                    String email = mRowData[2];
                    facultyList.add(new Faculty(name, course, email));
                }
            }
           facultyAdapter = new FacultyAdapter(DatabaseFaculty.this, facultyList);
            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            //setting adapter to recycler view
            recyclerView.setAdapter(facultyAdapter);
            db.close();
        }
        catch(android.database.SQLException e){
            Toast.makeText(DatabaseFaculty.this,e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.facultysearch,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();
        //sets ime to query text field
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        //Input Method Editor (return button to done button)

        // listening to search query text change
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                facultyAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }

}


