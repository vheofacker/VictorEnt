package com.example.victords.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.victords.R;
import com.example.victords.adapter.MyAdapter;
import com.example.victords.classes.MovieResults;
import com.example.victords.classes.Movies;
import com.example.victords.interfaces.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public  static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "e213ce02cbad41b6e3cc4353cbef97d5";
    public static String LANGUAGE = "pt-Br";
    public static  String CATEGORY = "upcoming";

    private TextView myTextView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recMovies);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();


        ApiInterface myinterface = retrofit.create(ApiInterface.class);

        Call<MovieResults> call =  myinterface.getMovies(CATEGORY, API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results =  response.body();
                List<Movies> movies = results.getResults();
                List<String> input = new ArrayList<>();

                for (int i=0; i<movies.size(); i++) {
                    input.add(movies.get(i).getTitle());

                }

                mAdapter = new MyAdapter(input);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}