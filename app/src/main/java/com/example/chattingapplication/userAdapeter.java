package com.example.chattingapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class userAdapeter extends RecyclerView.Adapter<userAdapeter.MyViewHolder>{
    private Context context;
    private List<userModel> userModelList;

    public userAdapeter(Context context) {
        this.context = context;
        userModelList=new ArrayList<>();
    }

    public void add(userModel usermodel){
        userModelList.add(usermodel);
        notifyDataSetChanged();
    }

    public void clear(){
        userModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       userModel usermodel=userModelList.get(position);
       holder.name.setText(usermodel.getUserName());
       holder.email.setText(usermodel.getUserEmail());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(context,chatActivity.class);
               intent.putExtra("id",usermodel.getUserid());
               context.startActivity(intent);

           }
       });
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
            private TextView name,email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.userName);
            email=itemView.findViewById(R.id.userEmail);
        }
    }
}
