package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lucky.ooec_mobile.Adapters.PlayerAdapter;
import com.example.lucky.ooec_mobile.Adapters.TeamAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.Player;
import com.example.lucky.ooec_mobile.Models.Team;
import com.example.lucky.ooec_mobile.Services.PlayerServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.example.lucky.ooec_mobile.Services.TeamServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPlayerActivity extends AppCompatActivity {

    Button btnAddPlayer;
    Button btnGetPlayerList;
    ListView listView;

    PlayerServices playerServices;
    TokenManager tokenManager;
    List<Player> list = new ArrayList<Player>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_player);

        setTitle("Player");

        btnAddPlayer = (Button) findViewById(R.id.btnAddPlayer);
        btnGetPlayerList = (Button) findViewById(R.id.btnGetPlayerList);
        listView = (ListView) findViewById(R.id.listView);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(MainPlayerActivity.this, LoginActivity.class));
            finish();
        }
        Bundle extras = getIntent().getExtras();
        final String teamId = extras.getString("teamId");
        playerServices = RetrofitClient.createServiceWithAuth(PlayerServices.class,tokenManager);

        btnGetPlayerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayerList(Integer.parseInt(teamId));
            }
        });

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPlayerActivity.this, PlayerActivity.class);
                intent.putExtra("name", "");
                intent.putExtra("position", "");
                intent.putExtra("age", "");
                intent.putExtra("teamId", String.valueOf(teamId));
                startActivity(intent);
            }
        });
    }

    public void getPlayerList(int teamId){
        Call<List<Player>> call = playerServices.getPlayers(teamId);
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new PlayerAdapter(MainPlayerActivity.this, R.layout.list_player, list));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
