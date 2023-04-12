package com.example.marsfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MarsImageAdapter extends RecyclerView.Adapter<MarsImageAdapter.MarsViewHolder> {
    ArrayList<MarsImage> marsImageArrayList;
    Context context;
    public MarsImageAdapter(Context context,ArrayList<MarsImage> marsImageArrayList) {
        this.context = context;
        this.marsImageArrayList = marsImageArrayList;
    }

    @NonNull
    @Override
    public MarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mars_image_card,parent,false);
        return new MarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarsViewHolder holder, int position) {
        MarsImage currentImageobject = marsImageArrayList.get(position);


        Picasso.get()
//                .load(currentImageobject.image_url)
//                .load("http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG")
                .load("https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8&w=1000&q=80")
                .into(holder.imageView);
        holder.sol_days.setText(currentImageobject.solDay);
        holder.cameraName.setText(currentImageobject.cameraName);
        holder.cameraFullName.setText(currentImageobject.fullCameraName);
        holder.earth_date.setText(currentImageobject.earthDate);
        holder.roverName.setText(currentImageobject.roverName);
    }

    @Override
    public int getItemCount() {
        return marsImageArrayList.size();
    }

    public class MarsViewHolder extends RecyclerView.ViewHolder{
        TextView sol_days;
        TextView cameraName;
        TextView cameraFullName;
        TextView earth_date;
        TextView roverName;

        ImageView imageView;
        public MarsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            sol_days = itemView.findViewById(R.id.sol_days);
            cameraName = itemView.findViewById(R.id.camera_name);
            cameraFullName = itemView.findViewById(R.id.full_camera_name);
            earth_date = itemView.findViewById(R.id.earth_date);
            roverName = itemView.findViewById(R.id.rover_name);

        }
    }
}
