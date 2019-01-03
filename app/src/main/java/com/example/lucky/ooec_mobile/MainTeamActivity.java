package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ListView;

import com.example.lucky.ooec_mobile.Adapters.TeamAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.Team;

import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.example.lucky.ooec_mobile.Services.TeamServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainTeamActivity extends AppCompatActivity {

    Button btnAddTeam;
    Button btnGetTeamList;
    ListView listView;

    TeamServices teamServices;
    TokenManager tokenManager;
    List<Team> list = new ArrayList<Team>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_team);

        setTitle("Team");

        btnAddTeam = (Button) findViewById(R.id.btnAddTeam);
        btnGetTeamList = (Button) findViewById(R.id.btnGetTeamList);
        listView = (ListView) findViewById(R.id.listView);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(MainTeamActivity.this, LoginActivity.class));
            finish();
        }
        Bundle extras = getIntent().getExtras();
        final String tournamentId = extras.getString("tournamentId");
        teamServices = RetrofitClient.createServiceWithAuth(TeamServices.class,tokenManager);

        btnGetTeamList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTeamList(Integer.parseInt(tournamentId));
            }
        });

        btnAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainTeamActivity.this, TeamActivity.class);
                intent.putExtra("teamName", "");
                intent.putExtra("tag", "");
                intent.putExtra("numberOfPlayers", "");
                startActivity(intent);
            }
        });
    }

    public void getTeamList(int tournamentId){
        Call<List<Team>> call = teamServices.getTeams(tournamentId);
        call.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new TeamAdapter(MainTeamActivity.this, R.layout.list_team, list));
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
