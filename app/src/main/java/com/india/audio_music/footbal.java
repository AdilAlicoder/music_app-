package com.india.audio_music;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class footbal extends Fragment {
    sqlitedatabase_use data;
    List<fav_model> listData;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_footbal, container, false);
        data=new sqlitedatabase_use(getActivity());
        listData=new ArrayList<fav_model>();
        recyclerView=view.findViewById(R.id.rec1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        Cursor cursor=data.readdata();
        if(cursor.getCount()==0){
        }
        else{
            while (cursor.moveToNext()){
                fav_model list= new fav_model(cursor.getString(1),cursor.getString(2));
                listData.add(list);
            }
            fav_adaptar adaptar=new fav_adaptar(getActivity(),listData);
            recyclerView.setAdapter(adaptar);
        }
        return view;
    }
}