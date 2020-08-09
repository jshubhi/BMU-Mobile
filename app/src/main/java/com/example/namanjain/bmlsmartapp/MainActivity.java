package com.example.namanjain.bmlsmartapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab_plus, fab_facebook, fab_insta;
    Animation fabopen, fabclose, plusclock, plusanticlock;
    Boolean isopen = false;
    BottomNavigationView b_nav;
    CardView news ,emergency_contact, events, faculty, shuttle, clubs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("BMU Mobile");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        b_nav = findViewById(R.id.bottom_nav);
        b_nav.setOnNavigationItemSelectedListener(navlistener);

        news = findViewById(R.id.card_campus);
        clubs = findViewById(R.id.card_club);
        emergency_contact = findViewById(R.id.card_emergency);
        events = findViewById(R.id.card_events);
        faculty = findViewById(R.id.card_faculty);
        shuttle = findViewById(R.id.card_shuttle);

        news.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.bml.edu.in/media-room-news/"));
                startActivity(browserIntent);
            }
        });
        emergency_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emergency_intent = new Intent(MainActivity.this, EmergencyContacts.class);
                startActivity(emergency_intent);

            }
        });

        shuttle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shuttle_intent = new Intent(MainActivity.this, Shuttle.class);
                startActivity(shuttle_intent);

            }
        });
        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent club_intent = new Intent(MainActivity.this, Clubs.class);
                startActivity(club_intent);

            }
        });

        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent club_intent = new Intent(MainActivity.this, DatabaseFaculty.class);
                startActivity(club_intent);

            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(haveNetworkConnection()){

                    Intent club_intent = new Intent(MainActivity.this, CalendarActivity.class);
                    startActivity(club_intent);

                }else{
                    Toast.makeText(MainActivity.this, "Internet Connection Required", Toast.LENGTH_SHORT).show();

                }

            }
        });



        fab_plus = findViewById(R.id.fabplus);
        fab_facebook = findViewById(R.id.fabfacebook);
        fab_insta = findViewById(R.id.fabinsta);
        fabopen = AnimationUtils.loadAnimation(this, R.anim.fabin);
        fabclose = AnimationUtils.loadAnimation(this, R.anim.fabout);
        plusclock = AnimationUtils.loadAnimation(this, R.anim.clock);
        plusanticlock = AnimationUtils.loadAnimation(this, R.anim.anticlock);
        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isopen) {
                    fab_plus.startAnimation(plusanticlock);
                    fab_facebook.startAnimation(fabclose);
                    fab_insta.startAnimation(fabclose);
                    fab_facebook.setClickable(false);
                    fab_insta.setClickable(false);
                    isopen = false;
                    fab_facebook.setVisibility(View.INVISIBLE);
                    fab_insta.setVisibility(View.INVISIBLE);

                } else {

                    fab_plus.startAnimation(plusclock);
                    fab_facebook.startAnimation(fabopen);
                    fab_insta.startAnimation(fabopen);
                    fab_facebook.setVisibility(View.VISIBLE);
                    fab_insta.setVisibility(View.VISIBLE);
                    fab_facebook.setClickable(true);
                    fab_insta.setClickable(true);
                    isopen = true;

                }
            }
        });

        fab_facebook.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.facebook.com/BMLMunjalUniversity/"));
                        startActivity(browserIntent);
                    }
                });

        fab_insta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/bmlmunjaluniversity/?hl=en"));
                startActivity(browserIntent);
            }
        });

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener= new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            break;
                        case R.id.nav_about:
                            Intent about_intent = new Intent(MainActivity.this, About.class);
                            startActivity(about_intent);
                            break;
                    }
                    return true;
                }
            };
    @Override
    protected void onResume() {
        super.onResume();
        b_nav.getMenu().getItem(0).setChecked(true);
    }
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


}
