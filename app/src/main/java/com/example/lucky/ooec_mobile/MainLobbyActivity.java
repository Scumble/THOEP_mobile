package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lucky.ooec_mobile.Adapters.LobbyAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Adapters.TournamentAdapter;
import com.example.lucky.ooec_mobile.Models.Lobby;
import com.example.lucky.ooec_mobile.Models.Tournament;
import com.example.lucky.ooec_mobile.Services.LobbyServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.example.lucky.ooec_mobile.Services.TournamentServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLobbyActivity extends AppCompatActivity {
    Button btnAddLobby;
    Button btnGetLobbyList;
    ListView listView;
    LobbyServices lobbyServices;
    TokenManager tokenManager;
    List<Lobby> list = new ArrayList<Lobby>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lobby);

        setTitle("Lobby");

        btnAddLobby = (Button) findViewById(R.id.btnAddLobby);
        btnGetLobbyList = (Button) findViewById(R.id.btnGetLobbyList);
        listView = (ListView) findViewById(R.id.listView);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(MainLobbyActivity.this, LoginActivity.class));
            finish();
        }
        Bundle extras = getIntent().getExtras();
        final String tournamentId = extras.getString("tournamentId");
        lobbyServices = RetrofitClient.createServiceWithAuth(LobbyServices.class,tokenManager);

        btnGetLobbyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLobbyList(Integer.parseInt(tournamentId));
            }
        });

        btnAddLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLobbyActivity.this, LobbyActivity.class);
                intent.putExtra("scoreWinner", "");
                intent.putExtra("scoreLoser", "");
                intent.putExtra("winner", "");
                intent.putExtra("dateStart", "");
                intent.putExtra("tournamentId", String.valueOf(tournamentId));
                intent.putExtra("radiant_team_name", "");
                intent.putExtra("dire_team_name", "");
                startActivity(intent);
            }
        });
    }

    public void getLobbyList(int tournamentId){
        Call<List<Lobby>> call = lobbyServices.getLobbies(tournamentId);
        call.enqueue(new Callback<List<Lobby>>() {
            @Override
            public void onResponse(Call<List<Lobby>> call, Response<List<Lobby>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new LobbyAdapter(MainLobbyActivity.this, R.layout.list_lobby, list));
                }
            }

            @Override
            public void onFailure(Call<List<Lobby>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
