package com.example.mymovies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
=======
import android.widget.ImageView;
>>>>>>> 9f93e5a0e18f6b70e47347a275326df279c406f9
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.R;
import com.example.mymovies.data.Trailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TralerViewHolder> {

    List<Trailer> trailers;

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }
    private OnTrailerClickListener listener;

    public void setListener(OnTrailerClickListener listener) {
        this.listener = listener;
    }

    public interface OnTrailerClickListener {
        void onClick(String url);
    }

    @NonNull
    @Override
    public TralerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item,parent,false);
        return new TralerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TralerViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.textViewNameOfVideo.setText(trailer.getName());
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    class TralerViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNameOfVideo;

        public TralerViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNameOfVideo = itemView.findViewById(R.id.textViewNameOfVideo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(trailers.get(getAdapterPosition()).getKey());
                    }
                }
            });
        }
    }
}
