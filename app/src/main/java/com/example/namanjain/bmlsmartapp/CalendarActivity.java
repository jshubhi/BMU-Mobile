package com.example.namanjain.bmlsmartapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity {
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    ArrayList<String> eventname = new ArrayList<>();
    ArrayList<String> eventdate = new ArrayList<>();
    ArrayList<String> eventtime = new ArrayList<>();
    ArrayList<String> eventvenue = new ArrayList<>();
    ArrayList<String> eventdescription = new ArrayList<>();
    private String TAG="calendar activity";
    private String EVENT_JSON="";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Event Calendar");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        recyclerView = findViewById(R.id.recycler_view1);
        fetchFirebaseRemoteConfig();
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        EventsAdapter customAdapter = new EventsAdapter(this,eventname,eventdate,eventtime,eventvenue,eventdescription);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    private void fetchFirebaseRemoteConfig() {

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        // Define Firebase Remote Config Settings.
        FirebaseRemoteConfigSettings firebaseRemoteConfigSettings =
                new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(true).build();
        // Define default config values. Defaults are used when fetched config values are not
        // available. Eg: if an error occurred fetching values from the server.
        Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put("friendly_msg_length", 10L);
        // Apply config settings and default values.
        mFirebaseRemoteConfig.setConfigSettings(firebaseRemoteConfigSettings);
        mFirebaseRemoteConfig.setDefaults(defaultConfigMap);
        // Fetch remote config.
        fetchConfig();
    }

    public String loadJSONFromAsset(){
        String json = null;
        try {
            InputStream is = getAssets().open("event.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public void fetchConfig() {
        long cacheExpiration = 3600; // 1 hour in seconds
        // If developer mode is enabled reduce cacheExpiration to 0 so that
        // each fetch goes to the server. This should not be used in release
        // builds.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }
        mFirebaseRemoteConfig.fetch(cacheExpiration).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Make the fetched config available via
                        // FirebaseRemoteConfig get<type> calls.
                        mFirebaseRemoteConfig.activateFetched();
                        applyRetrievedLengthLimit();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // There has been an error fetching the config
                        Toast.makeText(CalendarActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
                        applyRetrievedLengthLimit();
                    }
                });
        // print app's Instance ID token.
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()) {

                    Log.d(TAG, "Remote instance ID token: " + task.getResult().getToken());
                }
            }
        });

    }
    private void applyRetrievedLengthLimit() {

        String event = mFirebaseRemoteConfig.getString("event");
        Log.d(TAG, "event is: " + event);
        if(event!=null && event!=""){
        EVENT_JSON=event;}
        else{
            EVENT_JSON=loadJSONFromAsset();
            //Toast.makeText(this,"data", Toast.LENGTH_LONG).show();

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            // get JSONObject from JSON file
            //JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONObject obj = new JSONObject(EVENT_JSON);
            JSONArray events = obj.getJSONArray("events");
            // implement for loop for getting users list data
            for (int i = 0; i < events.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject userDetail = events.getJSONObject(i);
                eventname.add(userDetail.getString("eventname"));
                eventdate.add(userDetail.getString("eventdate"));
                eventtime.add(userDetail.getString("eventtime"));
                eventvenue.add(userDetail.getString("eventvenue"));
                eventdescription.add(userDetail.getString("eventdescription"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();

        }
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        EventsAdapter customAdapter = new EventsAdapter(this,eventname,eventdate,eventtime,eventvenue,eventdescription);
        recyclerView.setAdapter(customAdapter);

    }
}
