package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.Lobby;
import com.example.lucky.ooec_mobile.Services.LobbyServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LobbyActivity extends AppCompatActivity {

    LobbyServices lobbyServices;
    TokenManager tokenManager;
    EditText edtLId;
    EditText edtScoreWinner;
    EditText edtScoreLoser;
    EditText edtWinner;
    EditText edtLDateStart;
    EditText edtTournamentId;
    EditText edtRadiantTeamName;
    EditText edtDireTeamName;
    Button btnSave;
    Button btnDel;
    TextView txtLId;
    TextView txtLTournamentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        setTitle(R.string.Lobbies);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtLId = (TextView) findViewById(R.id.txtLId);
        txtLTournamentId = (TextView) findViewById(R.id.txtLTournamentId);
        edtLId = (EditText) findViewById(R.id.edtLId);
        edtScoreWinner = (EditText) findViewById(R.id.edtScoreWinner);
        edtScoreLoser=(EditText) findViewById(R.id.edtScoreLoser);
        edtWinner = (EditText) findViewById(R.id.edtWinner);
        edtLDateStart= (EditText) findViewById(R.id.edtLDateStart);
        edtTournamentId = (EditText) findViewById(R.id.edtTournamentId);
        edtRadiantTeamName = (EditText) findViewById(R.id.edtRadiantTeamName);
        edtDireTeamName = (EditText) findViewById(R.id.edtDireTeamName);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(LobbyActivity.this, LoginActivity.class));
            finish();
        }
        lobbyServices = RetrofitClient.createServiceWithAuth(LobbyServices.class,tokenManager);


        Bundle extras = getIntent().getExtras();
        final String lobbyId = extras.getString("lobbyId");
        int scoreWinner = extras.getInt("scoreWinner");
        int scoreLoser=extras.getInt("scoreLoser");
        String winner = extras.getString("winner");
        String dateStart = extras.getString("dateStart");
        final String tournamentId = extras.getString("tournamentId");
        String radiantTeamName = extras.getString("radiant_team_name");
        String direTeamName = extras.getString("dire_team_name");

        edtLId.setText(lobbyId);
        edtScoreWinner.setText(String.valueOf(scoreWinner));
        edtScoreLoser.setText(String.valueOf(scoreLoser));
        edtWinner.setText(winner);
        edtLDateStart.setText(dateStart);
      //  edtTournamentId.setText(Integer.parseInt(tournamentId));
        edtRadiantTeamName.setText(radiantTeamName);
        edtDireTeamName.setText(direTeamName);

        if(lobbyId != null && lobbyId.trim().length() > 0 ){
            edtLId.setFocusable(false);
        } else {
            txtLId.setVisibility(View.INVISIBLE);
            edtLId.setVisibility(View.INVISIBLE);
            txtLTournamentId.setVisibility(View.INVISIBLE);
            edtTournamentId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lobby lobby = new Lobby();
                lobby.setScoreWinner(Integer.parseInt(edtScoreWinner.getText().toString()));
                lobby.setScoreLoser(Integer.parseInt(edtScoreLoser.getText().toString()));
                lobby.setWinner(edtWinner.getText().toString());
                lobby.setDateStart(edtLDateStart.getText().toString());
                lobby.setTournamentId(Integer.parseInt(tournamentId));
                lobby.setRadiant_team_name(edtRadiantTeamName.getText().toString());
                lobby.setDire_team_name(edtDireTeamName.getText().toString());
                if(lobbyId != null && lobbyId.trim().length() > 0){
                    lobby.setId(Integer.parseInt(edtLId.getText().toString()));
                    updateLobby(lobby);
                } else {
                    addLobby(lobby);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLobby(Integer.parseInt(lobbyId));

                Intent intent = new Intent(LobbyActivity.this, MainLobbyActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addLobby(Lobby u){
        retrofit2.Call<Lobby> call = lobbyServices.addLobby(u);
        call.enqueue(new Callback<Lobby>() {
            @Override
            public void onResponse(retrofit2.Call<Lobby> call, Response<Lobby> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LobbyActivity.this, "Lobby created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Lobby> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateLobby(Lobby u){
        Call<Lobby> call = lobbyServices.updateLobby(u);
        call.enqueue(new Callback<Lobby>() {
            @Override
            public void onResponse(Call<Lobby> call, Response<Lobby> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LobbyActivity.this, "Lobby updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Lobby> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteLobby(int id){
        Call<Lobby> call =lobbyServices.deleteLobby(id);
        call.enqueue(new Callback<Lobby>() {
            @Override
            public void onResponse(Call<Lobby> call, Response<Lobby> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LobbyActivity.this, "Lobby deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Lobby> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
