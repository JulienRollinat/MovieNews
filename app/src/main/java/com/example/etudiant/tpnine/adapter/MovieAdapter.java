package com.example.etudiant.tpnine.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import com.example.etudiant.tpnine.R;
import com.example.etudiant.tpnine.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> movies;
    private MovieAdapter.ItemClickListener itemClickListener;

    public MovieAdapter(List<Movie> movies, MovieAdapter.ItemClickListener listener) {
        this.movies = movies;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_and_texts, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view, itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Movie movie = movies.get(position);
        myViewHolder.photoTitleTextView.setText(movie.getTitle());
        myViewHolder.noteView.setRating(movie.getVote_average());
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path()).into(myViewHolder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        TextView photoTitleTextView;
        ImageView photoImageView;
        RatingBar noteView;
        private MovieAdapter.ItemClickListener itemClickListener;

        MyViewHolder(View v, ItemClickListener itemClickListener) {
            super(v);
            this.itemClickListener = itemClickListener;
            photoTitleTextView = v.findViewById(R.id.movieTitle);
            photoImageView = v.findViewById(R.id.movieImage);
            noteView =  v.findViewById(R.id.rating);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d("tpNine", "onClick " + position);
            if(itemClickListener != null) {
                itemClickListener.onClickListener(position);
            }
        }
    }

    public interface ItemClickListener {
        void onClickListener(int position);
    }

}


