package com.example.namanjain.bmlsmartapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder>{
    private Context cntx; //need context to inflate layout
    private List<Club_desc> ClubsList;

    ClubAdapter(Context cntx, List<Club_desc> clubsList) {
        this.cntx = cntx;
        ClubsList = clubsList;
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cntx);
        View view = inflater.inflate(R.layout.cardview_club,parent,false);
        return new ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        //binds the data
        Club_desc club=ClubsList.get(position);
        holder.club_desc.setText(club.getClub_desc());
        holder.club_name.setText(club.getClub_name());
        holder.logo_club.setImageResource(club.getClub_logo());
    }

    @Override
    public int getItemCount() {
        return ClubsList.size();
        //return size of the list
    }
    //here we have view but we have card view as layout file
    // therefore to create view object inflater and to create layout inflater
    //we need to create a context object
    class ClubViewHolder extends RecyclerView.ViewHolder{
        ImageView logo_club;
        TextView club_name,club_desc;
         ClubViewHolder(View itemView) {
            super(itemView);
            logo_club=itemView.findViewById(R.id.imageView4);
            club_name=itemView.findViewById(R.id.textView4);
            club_desc=itemView.findViewById(R.id.textView5);
        }
    }
}

