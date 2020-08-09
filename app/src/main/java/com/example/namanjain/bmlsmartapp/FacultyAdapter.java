package com.example.namanjain.bmlsmartapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> implements Filterable {
    List list = new ArrayList();
    private Context cntx; //need context to inflate layout
    private List<Faculty> facultyList;
    private List<Faculty> facultyListFull;


    public FacultyAdapter(Context context, List<Faculty> facultyList) {
        this.cntx = context;
        this.facultyList = facultyList;
        facultyListFull=new ArrayList<>(facultyList);
    }


    @NonNull
    @Override
    public FacultyAdapter.FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cntx);
        View view = inflater.inflate(R.layout.facultylistitems, parent,false);
        return new FacultyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyAdapter.FacultyViewHolder holder, int position) {
        Faculty faculty = facultyList.get(position);
        holder.name.setText(faculty.getName());
        holder.course.setText(faculty.getCourse());
        holder.email.setText(faculty.getEmail());
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    class FacultyViewHolder extends RecyclerView.ViewHolder {
        TextView name, course, email;

        FacultyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            course = itemView.findViewById(R.id.course);
            email = itemView.findViewById(R.id.email);
        }
    }

    @Override
    public Filter getFilter() {
        return facultyFilter;
    }
    private Filter facultyFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Faculty> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(facultyListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Faculty item : facultyListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            facultyList.clear();
            facultyList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
