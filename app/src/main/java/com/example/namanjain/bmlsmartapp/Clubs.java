package com.example.namanjain.bmlsmartapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Clubs extends AppCompatActivity {

    List<Club_desc> clubList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Student Clubs");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        clubList=new ArrayList<>();
        clubList.add(new Club_desc("TSEC","The Entrepreneurship Club aims to provide a conduit by which " +
                "students can access entrepreneurial resources, network with community entrepreneurs, " +
                "and share ideas. ",R.drawable.tsec));

        clubList.add(new Club_desc("PAC","Photography and Cinematography Club",R.drawable.pac_logo));

        clubList.add(new Club_desc("STROKES","Strokes club provides an opportunity to the students to " +
                "let their imagination run wild and provides them with the sight to see things in a different " +
                "way. ",R.drawable.strokes));

        clubList.add(new Club_desc("SAVERA","SAVERA (Students As Volunteers in Education to Reach and Assist), " +
                "is a club that aims to provide education and support to the students of weaker sections" +
                " of the society.",R.drawable.savera));

        clubList.add(new Club_desc("SATA","The SATA club came into existence with the vision of creating" +
                " expertise in technical knowledge and achieve an in-depth understanding of latest technological " +
                "trends.",R.drawable.science));

        clubList.add(new Club_desc("SIERRA","Sierra has been fundamental in organising major flagship " +
                "events in the University. " + "This includes TEDx, Hult Prize, and MUN.",R.drawable.sierra));

        clubList.add(new Club_desc("NSS","NSS Unit-I, BML Munjal University celebrated International " +
                "Girl Child Day with the students of Government senior secondary school, Sidhrawali, " +
                "Haryana.",R.drawable.nss));

        clubList.add(new Club_desc("CULINARY CLUB","The Culinary Club provides an environment for students to, " +
                "socialize, be creative, learn cooking skills, and have fun. ",R.drawable.culinary));

        clubList.add(new Club_desc("SPEAKEASY","The Speakeasy Club strives to make each participant a confident individual with a" +
                " magnetic persona embellished with the corporate etiquette’s and" +
                " the art of soft skills. ",R.drawable.speak));

        ClubAdapter adapter = new ClubAdapter(this, clubList);


        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //setting adapter to recycler view
        recyclerView.setAdapter(adapter);

    }
}

