package com.india.audio_music;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class fav_adaptar extends RecyclerView.Adapter<fav_adaptar.ViewHolder>{
    private List<fav_model>listData;
    private  Context context;
    public fav_adaptar(Context applicationContext, List<fav_model> listData) {
        this.listData = listData;
        this.context=applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_ui, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final fav_model ld=listData.get(position);
        holder.title.setText(ld.getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=ld.getKey();
                String title=ld.getTitle();
                Intent intent=new Intent(context,music_play.class);
                intent.putExtra("key",key);
                intent.putExtra("title",title);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.txt1);
        }
    }

}


