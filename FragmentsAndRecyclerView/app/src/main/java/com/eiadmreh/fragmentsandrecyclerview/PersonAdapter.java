package com.eiadmreh.fragmentsandrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<Person>People;
    ItemSelected activity;
    public interface ItemSelected{
        void onItemClicked(int Index);
    }
    public PersonAdapter(Context context,ArrayList<Person> list){
        this.People=list;
        activity=(ItemSelected)context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName =itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(People.indexOf(view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(People.get(i));
        viewHolder.tvName.setText(People.get(i).getName());
    }


    @Override
    public int getItemCount() {
        return People.size();
    }

}
