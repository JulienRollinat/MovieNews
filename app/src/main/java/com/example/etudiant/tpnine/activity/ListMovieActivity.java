package com.example.etudiant.tpnine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.etudiant.tpnine.R;
import com.example.etudiant.tpnine.adapter.MovieAdapter;
import com.example.etudiant.tpnine.manager.WsManager;
import com.example.etudiant.tpnine.model.Movie;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListMovieActivity extends AppCompatActivity implements WsManager.Listener, MovieAdapter.ItemClickListener  {

    private Gson gson = new Gson();
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    public static final String SELECTED_MOVIE= "SELECTED_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_items);
        recyclerView = findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        WsManager wsManager = new WsManager();
        wsManager.executeGetList(this);
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("TPResto", "error: " + errorMessage);
    }

    @Override
    public void onSuccess(String content) {
        Log.d("TPResto", "success: " + content);

        JSONObject json = null;
        try {
            json = new JSONObject(content);
            System.out.println(content.toString());
            String results = json.getString("results");
            System.out.println(results);
            Movie[] founderArray = gson.fromJson(results, Movie[].class);
            movieList = Arrays.asList(founderArray);
            Log.d("MovieNews", movieList.toString());

            MovieAdapter adapter = new MovieAdapter(movieList, this);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClickListener(int position) {
        System.out.println("movie selected : " + position);
        Movie movie = movieList.get(position);
        Log.d("MovieNews", "movie selected : " + movie);
        System.out.println("movie selected : " + movie);
        Intent intent = new Intent(ListMovieActivity.this, ItemActivity.class);
        intent.putExtra(SELECTED_MOVIE, movie.getId());
        startActivity(intent);

    }

}
