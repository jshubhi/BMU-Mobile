package com.example.namanjain.bmlsmartapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class EmergencyContacts extends AppCompatActivity {

    String[] nameArray = {"Fire and Safety Officer","Medical Officer","Medeor Hospital Medical Officer","Security Supervisor","Security Supervisor","Warden (Girls)","Warden (Boys)","Help-desk","Reception" };
    String[] infoArray = {"9718323957", "8572820602","9711601358", "9729442263", "8580506765","9530567172", "9811678067", "8572820605","8295929411"};
    ListView listView;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Emergency Contacts");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        final EmergencyListAdapter list1 = new EmergencyListAdapter(this, nameArray, infoArray);
        listView = findViewById(R.id.list1);
        listView.setAdapter(list1);
        builder = new AlertDialog.Builder(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final String SUB=infoArray[position];
                builder.setMessage("Do you want to make an emergency call ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+SUB));// Initiates the Intent
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Emergency Call");
                alert.show();
                }
                });



    }

}
