package com.india.audio_music;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class cricket extends Fragment {
    RecyclerView recyclerView;
    List<retrive_data> retriveData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cricket, container, false);
        recyclerView=view.findViewById(R.id.rec);
        retriveData=new ArrayList<retrive_data>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("cetagerois");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    retrive_data listData1=dataSnapshot.getValue(retrive_data.class);
                    retriveData.add(listData1);
                }
                cetagerois_adaptar cetageroisAdaptar=new cetagerois_adaptar(getActivity(),retriveData);
                recyclerView.setAdapter(cetageroisAdaptar);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}