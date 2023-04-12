package com.example.marsfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;

public class MarsImageAdapter extends RecyclerView.Adapter<MarsImageAdapter.MarsViewHolder> {

    @NonNull
    @Override
    public MarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mars_image_card,parent,false);
        return new MarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarsViewHolder holder, int position) {
        holder.sol_days.setText("2000");
        holder.cameraName.setText("FHAZ");
        holder.cameraFullName.setText("Full Hazard Avoidance Camera");
        holder.earth_date.setText("17/04/2023");
        holder.roverName.setText("Curiosity");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MarsViewHolder extends RecyclerView.ViewHolder{
        TextView sol_days;
        TextView cameraName;
        TextView cameraFullName;
        TextView earth_date;
        TextView roverName;
        public MarsViewHolder(@NonNull View itemView) {
            super(itemView);
            sol_days = itemView.findViewById(R.id.sol_days);
            cameraName = itemView.findViewById(R.id.camera_name);
            cameraFullName = itemView.findViewById(R.id.full_camera_name);
            earth_date = itemView.findViewById(R.id.earth_date);
            roverName = itemView.findViewById(R.id.rover_name);

        }
    }
}
