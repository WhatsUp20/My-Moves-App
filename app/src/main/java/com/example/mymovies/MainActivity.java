package com.example.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymovies.data.Movie;
import com.example.mymovies.utils.JSONUtils;
import com.example.mymovies.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Switch switchSort;
    private RecyclerView recyclerViewPosters;
    private MovieAdapter movieAdapter;
    private TextView textViewTopRated;
    private TextView getTextViewPopularity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewPosters = findViewById(R.id.recyclerViewPosters);
        switchSort = findViewById(R.id.switchSort);
        textViewTopRated = findViewById(R.id.textViewTopRated);
        getTextViewPopularity = findViewById(R.id.textViewPopularity);
        recyclerViewPosters.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter();

        recyclerViewPosters.setAdapter(movieAdapter);
        //add listener
        switchSort.setChecked(true);
        switchSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setMethodOfSort(isChecked);
            }
        });
        switchSort.setChecked(false);
        movieAdapter.setClickListener(new MovieAdapter.OnPosterClickListener() {
            @Override
            public void onPosterClick(int position) {
                Toast.makeText(MainActivity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        movieAdapter.setOnReachEndListener(new MovieAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                Toast.makeText(MainActivity.this, "Конец списка", Toast.LENGTH_SHORT).show();
            }
        });
    }

            public void onClickSetPopularity(View view) {
                setMethodOfSort(false);
                switchSort.setChecked(false);
            }

            public void onClockTopRated(View view) {
                setMethodOfSort(true);
                switchSort.setChecked(true);
            }

            private void setMethodOfSort(boolean isTopRated) {
                int methodOfSort;
                if (!isTopRated) {
                    textViewTopRated.setTextColor(getResources().getColor(R.color.white_color));
                    getTextViewPopularity.setTextColor(getResources().getColor(R.color.colorAccent));
                    methodOfSort = NetworkUtils.TOP_RATED;
                } else {
                    textViewTopRated.setTextColor(getResources().getColor(R.color.colorAccent));
                    getTextViewPopularity.setTextColor(getResources().getColor(R.color.white_color));
                    methodOfSort = NetworkUtils.POPULARITY;
                }
                JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(methodOfSort, 1);
                ArrayList<Movie> movies = JSONUtils.getMoviesFromJson(jsonObject);
                movieAdapter.setMovies(movies);
    }
}