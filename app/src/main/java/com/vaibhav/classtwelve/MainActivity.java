package com.vaibhav.classtwelve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> usersArrayList = new ArrayList<>();
    TextView textView;
    EditText editText;
    Button button;
    ImageView imageView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(usersArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        final OkHttpClient client = new OkHttpClient();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editText.getText().toString();

                final Request request = new Request.Builder().url("http://loklak.org/api/search.json?q=" + userName).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("TAG", "onFailure: " + e.getMessage() );
                        Log.e("TAG", "onFailure: " + call.request().method());
                        Log.e("TAG", "onFailure: " + call.request().url() );
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Log.d("TAG", "onResponse: " + call.request().url());
                        Log.d("TAG", "onResponse: " + result);
                        Gson gson = new Gson();

                        final Statuses users= gson.fromJson(result, Statuses.class);
                        Log.e("TAG", "onResponse: " + users.getUserArrayList().size() );

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                usersArrayList.addAll(users.getUserArrayList());
                                recyclerViewAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                });
            }
        });
    }
}
