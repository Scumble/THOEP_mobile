package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lucky.ooec_mobile.Adapters.ProTeamPlayersAdapter;
import com.example.lucky.ooec_mobile.Adapters.ProTeamsAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.ProTeamMatches;
import com.example.lucky.ooec_mobile.Models.ProTeamPlayers;
import com.example.lucky.ooec_mobile.Models.ProTeams;
import com.example.lucky.ooec_mobile.Services.ProTeamsServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProTeamPlayersActivity extends AppCompatActivity {
    ProTeamsServices proTeamsServices;
    TokenManager tokenManager;
    ListView listView;
    Button btnProTeamMatches;
    List<ProTeamPlayers> list = new ArrayList<ProTeamPlayers>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Pro Players");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_team_players);
        listView = (ListView) findViewById(R.id.listView);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        btnProTeamMatches=(Button) findViewById(R.id.btnProTeamMatches);
        Bundle extras = getIntent().getExtras();
        final String proteamId = extras.getString("teamId");
        if(tokenManager.getToken() == null){
            startActivity(new Intent(ProTeamPlayersActivity.this, LoginActivity.class));
            finish();
        }
        proTeamsServices = RetrofitClient.createServiceWithAuth(ProTeamsServices.class,tokenManager);

        getProTeamPlayers(Integer.parseInt(proteamId));

        btnProTeamMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProTeamPlayersActivity.this, ProTeamMatchesActivity.class);
                intent.putExtra("teamId",String.valueOf(proteamId));
                startActivity(intent);
            }
        });

    }
    public void getProTeamPlayers(int proteamId){
        Call<List<ProTeamPlayers>> call = proTeamsServices.getTeamPlayers(proteamId);
        call.enqueue(new Callback<List<ProTeamPlayers>>() {
            @Override
            public void onResponse(Call<List<ProTeamPlayers>> call, Response<List<ProTeamPlayers>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ProTeamPlayersAdapter(ProTeamPlayersActivity.this, R.layout.list_proplayers, list));
                }
            }

            @Override
            public void onFailure(Call<List<ProTeamPlayers>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
