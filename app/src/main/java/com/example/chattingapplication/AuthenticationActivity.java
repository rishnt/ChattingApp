package com.example.chattingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.chattingapplication.databinding.ActivityAuthenticationBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthenticationActivity extends AppCompatActivity {

    ActivityAuthenticationBinding binding;
    String name,email,password;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference= FirebaseDatabase.getInstance().getReference("users");

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=binding.email.getText().toString();
                password=binding.password.getText().toString();

                login();
            }
        });

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=binding.email.getText().toString();
                email=binding.email.getText().toString();
                password=binding.password.getText().toString();

                signup();
            }
        });
    }

    private void login(){
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(email.trim(),password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(AuthenticationActivity.this,MainActivity.class));
                        finish();
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(AuthenticationActivity.this,MainActivity.class));
        }
    }

    private void signup(){
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(email.trim(),password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                        firebaseUser.updateProfile(userProfileChangeRequest);
                        userModel usermodel=new userModel(FirebaseAuth.getInstance().getUid(),name,email,password);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(usermodel);
                        startActivity(new Intent(AuthenticationActivity.this,MainActivity.class));
                        finish();
                    }
                });

    }
}