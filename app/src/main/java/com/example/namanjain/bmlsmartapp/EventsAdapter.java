package com.example.namanjain.bmlsmartapp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {
    ArrayList<String> eventname;
    ArrayList<String> eventdate;
    ArrayList<String> eventtime;
    ArrayList<String> eventvenue;
    ArrayList<String> eventdescription;
    Context context;

    public EventsAdapter(Context context, ArrayList<String> eventname, ArrayList<String> eventdate, ArrayList<String>  eventtime,ArrayList<String> eventvenue,ArrayList<String> eventdescription) {
        this.context = context;
        this.eventname =eventname;
        this.eventdate = eventdate;
        this.eventtime = eventtime;
        this.eventvenue =eventvenue;
        this.eventdescription = eventdescription;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_calendar_layout,parent,false);
        return new MyViewHolder(view);


    }



    @Override
    public void onBindViewHolder(MyViewHolder holder,int position) {
        // set the data in items
        holder.event_name.setText(eventname.get(position));
        holder.event_date.setText(eventdate.get(position));
        holder.event_time.setText(eventtime.get(position));
        holder.event_venue.setText(eventvenue.get(position));
        holder.event_desc.setText(eventdescription.get(position));

    }


    @Override
    public int getItemCount() {
        return eventname.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView event_name, event_date,event_time,event_venue,event_desc;

        MyViewHolder(View itemView) {
            super(itemView);
            event_name=itemView.findViewById(R.id.textView10);
            event_date=itemView.findViewById(R.id.textView6);
            event_time=itemView.findViewById(R.id.textView7);
            event_venue=itemView.findViewById(R.id.textView8);
            event_desc=itemView.findViewById(R.id.textView9);


        }
    }


}


