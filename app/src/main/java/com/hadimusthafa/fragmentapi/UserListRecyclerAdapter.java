package com.hadimusthafa.fragmentapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListRecyclerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<User> dataModelArrayList;
    private Context mContext;
    private  ItemClickListener itemClickListener;

    public UserListRecyclerAdapter(Context ctx, ArrayList<User> dataModelArrayList, ItemClickListener itemClickListener) {
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
        this.itemClickListener = itemClickListener;
        mContext = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item_user, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameT.setText(mContext.getString(R.string.name)+" "+dataModelArrayList.get(position).getName());
        holder.emailT.setText(mContext.getString(R.string.email)+" "+dataModelArrayList.get(position).getEmail());
        holder.genderT.setText(mContext.getString(R.string.gender)+" "+dataModelArrayList.get(position).getGender());
        holder.statusT.setText(mContext.getString(R.string.status)+" "+dataModelArrayList.get(position).getStatus());
        holder.idT.setText(mContext.getString(R.string.id)+" "+dataModelArrayList.get(position).getId() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.passId(dataModelArrayList.get(holder.getAdapterPosition()).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView idT, nameT, emailT, genderT, statusT;

        public MyViewHolder(final View itemView) {
            super(itemView);

            idT =  itemView.findViewById(R.id.idT);
            nameT = itemView.findViewById(R.id.nameT);
            emailT = itemView.findViewById(R.id.emailT);
            genderT = itemView.findViewById(R.id.genderT);
            statusT = itemView.findViewById(R.id.statusT);
        }
    }

    public interface ItemClickListener {
        void passId(int id);
    }
}
