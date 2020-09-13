package com.india.audio_music;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class music_list extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ListData> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        recyclerView=findViewById(R.id.rec1);
        listData=new ArrayList<ListData>();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("music");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    ListData listData1=dataSnapshot.getValue(ListData.class);
                    listData.add(listData1);
                }
                Adaptar_music adaptar_music=new Adaptar_music(getApplicationContext(),listData);
                recyclerView.setAdapter(adaptar_music);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}


