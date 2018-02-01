package com.example.ganesh.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.ganesh.retrofitexample.models.Item;
import com.example.ganesh.retrofitexample.models.SOAnswerResponse;
import com.example.ganesh.retrofitexample.models.remote.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AnswerAdapter answerAdapter;
    private SOService service;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = ApiUtils.getSOService();
        recyclerView = findViewById(R.id.recycler_view);
        answerAdapter = new AnswerAdapter(new ArrayList<Item>(0),this, new AnswerAdapter.PostItemListener() {
            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this,"eyah hfwsf" + id, Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(answerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        
        loadAnswer();
    }

    private void loadAnswer() {
        service.getAnswers().enqueue(new Callback<SOAnswerResponse>() {
            @Override
            public void onResponse(Call<SOAnswerResponse> call, Response<SOAnswerResponse> response) {
                if (response.isSuccessful()){
                    answerAdapter.updateAnswers(response.body().getItems());
                }else {
                    int statusCode = response.code();
                    //handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswerResponse> call, Throwable t) {
                showErrorMessage();
                Log.d(getLocalClassName(),"error Bitch");
            }
        });
    }

    private void showErrorMessage() {
        Toast.makeText(this,"Error again, Bitch",Toast.LENGTH_LONG).show();
    }
}
