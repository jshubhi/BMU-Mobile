package com.example.namanjain.bmlsmartapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EmergencyListAdapter extends ArrayAdapter {
    private final Context context;
    private final String[] nameArray;
    private final String[] contactArray;

     EmergencyListAdapter(Context context, String[] nameArrayParam, String[] contactArrayParam){

        super(context,R.layout.list_items,nameArrayParam);

        this.context=context;
        this.nameArray = nameArrayParam;
        this.contactArray = contactArrayParam;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.list_items, parent,false);
        //this code gets references to objects in the list view_row.xml file
        TextView nameTextField =  rowView.findViewById(R.id.name1);
        TextView infoTextField = rowView.findViewById(R.id.contact1);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        infoTextField.setText(contactArray[position]);
        return rowView;

    }





}
