package com.aindri.practicevolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    Context context;
    ArrayList<UserModel> arrayList;

    public RecyclerAdapter(Context context, ArrayList<UserModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.singleview,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    UserModel currentitem=arrayList.get(position);
    int id=currentitem.getId();
    String email=currentitem.getEmail();
    String first_name=currentitem.getFirst_name();
    String last_name=currentitem.getLast_name();
    String avatar=currentitem.getAvatar();

    holder.txt_id.setText(String.valueOf(id));
    holder.txt_email.setText(email);
    holder.txt_firstname.setText(first_name);
    holder.txt_lastname.setText(last_name);

    Glide.with(context).load(avatar).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView img;
    TextView txt_firstname,txt_lastname,txt_id,txt_email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id=itemView.findViewById(R.id.txt_id);
            txt_email=itemView.findViewById(R.id.txt_email);
            txt_firstname=itemView.findViewById(R.id.txt_firstname);
            txt_lastname=itemView.findViewById(R.id.txt_lastname);
            img=itemView.findViewById(R.id.img);
        }
    }
    }

