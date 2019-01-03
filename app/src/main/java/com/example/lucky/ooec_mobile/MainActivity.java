package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Adapters.TournamentAdapter;
import com.example.lucky.ooec_mobile.Models.Tournament;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.example.lucky.ooec_mobile.Services.TournamentServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddTournament;
    Button btnGetTournamentsList;
    Button btnStream;
    Button btnProTeam;
    Button btnLogout;
    ListView listView;

    TournamentServices tournamentServices;
    TokenManager tokenManager;
    List<Tournament> list = new ArrayList<Tournament>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("OOEC");

        btnAddTournament = (Button) findViewById(R.id.btnAddTournament);
        btnGetTournamentsList = (Button) findViewById(R.id.btnGetTournamentsList);
        btnStream=(Button) findViewById(R.id.btnStream);
        btnProTeam=(Button) findViewById(R.id.btnProTeam);
        btnLogout=(Button) findViewById(R.id.btnLogout);
        listView = (ListView) findViewById(R.id.listView);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        tournamentServices = RetrofitClient.createServiceWithAuth(TournamentServices.class,tokenManager);

        btnGetTournamentsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTournamentsList();
            }
        });

        btnStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StreamActivity.class);
                startActivity(intent);
            }
        });

        btnProTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProTeamsActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        btnAddTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TournamentActivity.class);
                intent.putExtra("tournamentName", "");
                intent.putExtra("identityId", "");
                intent.putExtra("place", "");
                intent.putExtra("type", "");
                intent.putExtra("prizePool", "");
                intent.putExtra("dateStart", "");
                intent.putExtra("dateEnd", "");
                intent.putExtra("description", "");
                intent.putExtra("game", "");
                startActivity(intent);
            }
        });
    }

    public void getTournamentsList(){
        Call<List<Tournament>> call = tournamentServices.getTournaments();
        call.enqueue(new Callback<List<Tournament>>() {
            @Override
            public void onResponse(Call<List<Tournament>> call, Response<List<Tournament>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new TournamentAdapter(MainActivity.this, R.layout.list_tournament, list));
                }
            }

            @Override
            public void onFailure(Call<List<Tournament>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
