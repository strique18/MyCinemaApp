package com.example.backyardcinema;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.movieHolder> {

    ArrayList<Movie> mMovies;

    public movieAdapter(ArrayList<Movie> movies){

        mMovies = movies;
    }

    @NonNull
    @Override
    public movieAdapter.movieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        return new movieAdapter.movieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull movieAdapter.movieHolder holder, int position) {
        // 1. retrieve a movie object based on the position in the list
        Movie movie = mMovies.get(position);
        // 2. take the details the movie object and pass to the movie holder
        holder.mTitle.setText(movie.getTitle());
        Glide.with(holder.mPoster.getContext()).load(movie.getPoster()).into(holder.mPoster);
        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("movieAdapter","movie item was clicked");
            }
        });

    }

    @Override
    public int getItemCount() {

        return mMovies.size();
    }

    static class movieHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        ImageView mPoster;
        ConstraintLayout mItem;

        public movieHolder(View itemView) {
            super(itemView);
            mItem = itemView.findViewById(R.id.movie_item);
            mPoster = itemView.findViewById(R.id.movie_item_image);
            mTitle = itemView.findViewById(R.id.movie_item_title);
        }
    }
}
