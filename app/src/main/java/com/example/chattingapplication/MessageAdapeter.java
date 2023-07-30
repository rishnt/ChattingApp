package com.example.chattingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapeter extends RecyclerView.Adapter<MessageAdapeter.MyViewHolder>{
    private Context context;
    private List<MessageModel> messageModelList;

    public MessageAdapeter(Context context) {
        this.context = context;
        messageModelList=new ArrayList<>();
    }

    public void add(MessageModel usermodel){
        messageModelList.add(usermodel);
        notifyDataSetChanged();
    }

    public void clear(){
        messageModelList.clear();
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
       MessageModel messagemodel=messageModelList.get(position);
       holder.msg.setText(messagemodel.getMessage());
      if(messagemodel.getSenderId().equals(FirebaseAuth.getInstance().getUid())){

          holder.main.setBackgroundColor(context.getResources().getColor(R.color.teal_700,null));
          holder.msg.setTextColor(context.getResources().getColor(R.color.white,null));
      }
      else{
          holder.main.setBackgroundColor(context.getResources().getColor(R.color.black,null));
          holder.msg.setTextColor(context.getResources().getColor(R.color.white,null));

      }
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
            private TextView msg;
            private LinearLayout main;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            msg=itemView.findViewById(R.id.message);
            main=itemView.findViewById(R.id.mainMessageLayout);
        }
    }
}
