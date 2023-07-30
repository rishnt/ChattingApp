package com.example.chattingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.chattingapplication.databinding.ActivityChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class chatActivity extends AppCompatActivity {
ActivityChatBinding binding;
DatabaseReference databaseReferenceSender,databaseReferenceReceiver;
String senderRoom,receiverRoom;
String receiverId;
MessageAdapeter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        receiverId=getIntent().getStringExtra("id");

        senderRoom= FirebaseAuth.getInstance().getUid()+receiverId;
        receiverRoom=receiverId+FirebaseAuth.getInstance().getUid();
        messageAdapter=new MessageAdapeter(this);
         binding.reyclerView.setAdapter(messageAdapter);
         binding.reyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReferenceSender= FirebaseDatabase.getInstance().getReference("chats").child(senderRoom);
        databaseReferenceReceiver= FirebaseDatabase.getInstance().getReference("chats").child(receiverRoom);

        databaseReferenceSender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageAdapter.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MessageModel messageModel=dataSnapshot.getValue(MessageModel.class);
                    messageAdapter.add(messageModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=binding.messageId.getText().toString();
                if(message.trim().length()>0){
                    sendMessage(message);
                }
            }
        });
    }



    private void sendMessage(String message){
        String messageid= UUID.randomUUID().toString();
        MessageModel messageModel=new MessageModel(messageid,FirebaseAuth.getInstance().getUid(),message);

        messageAdapter.add(messageModel);

        databaseReferenceSender
                .child(messageid)
                .setValue(messageModel);

        databaseReferenceReceiver
                .child(messageid)
                .setValue(messageModel);

    }
}