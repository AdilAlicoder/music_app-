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

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class cetagerois_adaptar extends RecyclerView.Adapter<cetagerois_adaptar.ViewHolder>{
    private List<retrive_data> retriveData;
    private  Context context;
    public cetagerois_adaptar(Context applicationContext, List<retrive_data> retriveData) {
        this.retriveData = retriveData;
        this.context=applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cetagerois_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         retrive_data ld=retriveData.get(position);
         holder.txtid.setText(ld.getTitle());
        Picasso.get().load(ld.getImage()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,music_list.class);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return retriveData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtid;
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            txtid=(TextView)itemView.findViewById(R.id.txtc);
            imageView=itemView.findViewById(R.id.img);
        }
    }

}


