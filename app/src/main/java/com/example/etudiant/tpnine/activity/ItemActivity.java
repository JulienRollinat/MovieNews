package com.example.etudiant.tpnine.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.etudiant.tpnine.model.Movie;
import com.squareup.picasso.Picasso;
import com.example.etudiant.tpnine.R;
import com.example.etudiant.tpnine.manager.WsManager;
import com.example.etudiant.tpnine.model.Item;
import com.google.gson.Gson;


public class ItemActivity extends AppCompatActivity implements WsManager.Listener {

    private RecyclerView recyclerView;
    private Gson gson = new Gson();
    private Item item = new Item();
    private TextView title;
    private TextView description;
    private ImageView imageView;
    private RatingBar ratingNote;
    private TextView releaseDate;
    private TextView duration;
    private TextView averageNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_details_4item);

        title = findViewById(R.id.movieTitle);
        description = findViewById(R.id.movieDescription);
        imageView = findViewById(R.id.movieImage);
        ratingNote = findViewById(R.id.ratingNote);
        releaseDate = findViewById(R.id.ReleaseDate);
        duration = findViewById(R.id.durationText);
        averageNote = findViewById(R.id.notationText);

        long MovieId = getIntent().getLongExtra(ListMovieActivity.SELECTED_MOVIE, 1);
        System.out.println("l'id du movie est de " + MovieId);
        WsManager wsManager = new WsManager();
        wsManager.executeGetMovie(Long.toString(MovieId),this);
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("TPResto", "error: " + errorMessage);
    }

    @Override
    public void onSuccess(String content) {
        Log.d("TPResto", "success: " + content);

        try {
            item = gson.fromJson(content, Item.class);

            title.setText(item.getTitle());
            description.setText(item.getOverview());
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + item.getPoster_path()).into(imageView);
            duration.setText(Float.toString(item.getRuntime()) + " min");

            String date =  item.getRelease_date();
            String[] arrDate =  date.split("-");
            date = arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0];

            releaseDate.setText(date);
            averageNote.setText(Float.toString(item.getVote_average()));
            ratingNote.setRating(item.getVote_average());
            this.getSupportActionBar().setTitle(title.getText());
            averageNote.setText(Float.toString(item.getVote_average()));


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
