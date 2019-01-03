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
import com.example.lucky.ooec_mobile.Models.Tournament;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.example.lucky.ooec_mobile.Services.TournamentServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TournamentActivity extends AppCompatActivity {
    TournamentServices tournamentServices;
    TokenManager tokenManager;
    EditText edtUId;
    EditText edtTournamentname;
    EditText edtIdentityId;
    EditText edtPlace;
    EditText edtType;
    EditText edtPrizePool;
    EditText edtDateStart;
    EditText edtDateEnd;
    EditText edtDescription;
    EditText edtGame;
    Button btnSave;
    Button btnDel;
    Button btnLobby;
    Button btnTeam;
    TextView txtUId;
    TextView txtUIdentityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        setTitle(R.string.TTTournaments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        txtUIdentityId = (TextView) findViewById(R.id.txtUIdentityId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtTournamentname = (EditText) findViewById(R.id.edtTournamentname);
        edtIdentityId=(EditText) findViewById(R.id.edtIdentityId);
        edtPlace = (EditText) findViewById(R.id.edtPlace);
        edtType = (EditText) findViewById(R.id.edtType);
        edtPrizePool = (EditText) findViewById(R.id.edtPrizePool);
        edtDateStart = (EditText) findViewById(R.id.edtDateStart);
        edtDateEnd = (EditText) findViewById(R.id.edtDateEnd);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        edtGame = (EditText) findViewById(R.id.edtGame);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnLobby=(Button) findViewById(R.id.btnLobby);
        btnTeam=(Button) findViewById(R.id.btnTeam);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(TournamentActivity.this, LoginActivity.class));
            finish();
        }
        tournamentServices = RetrofitClient.createServiceWithAuth(TournamentServices.class,tokenManager);


        Bundle extras = getIntent().getExtras();
        final String tournamentId = extras.getString("tournamentId");
        String tournamentName = extras.getString("tournamentName");
        String identityId=extras.getString("identityId");
        String place = extras.getString("place");
        String type = extras.getString("type");
        long prizePool = extras.getLong("prizePool");
        String dateStart = extras.getString("dateStart");
        String dateEnd = extras.getString("dateEnd");
        String description = extras.getString("description");
        String game = extras.getString("game");

        edtUId.setText(tournamentId);
        edtTournamentname.setText(tournamentName);
        edtIdentityId.setText(identityId);
        edtPlace.setText(place);
        edtType.setText(type);
        edtPrizePool.setText(String.valueOf(prizePool));
        edtDateStart.setText(dateStart);
        edtDateEnd.setText(dateEnd);
        edtDescription.setText(description);
        edtGame.setText(game);

        if(tournamentId != null && tournamentId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            txtUIdentityId.setVisibility(View.INVISIBLE);
            edtIdentityId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tournament u = new Tournament();
                u.setIdentityId(edtIdentityId.getText().toString());
                u.setTournamentName(edtTournamentname.getText().toString());
                u.setPlace(edtPlace.getText().toString());
                u.setType(edtType.getText().toString());
                u.setPrizePool(Long.parseLong(edtPrizePool.getText().toString()));
                u.setDateStart(edtDateStart.getText().toString());
                u.setDateEnd(edtDateEnd.getText().toString());
                u.setDescription(edtDescription.getText().toString());
                u.setGame(edtGame.getText().toString());
                if(tournamentId != null && tournamentId.trim().length() > 0){
                    //update tournament
                    u.setId(Integer.parseInt(edtUId.getText().toString()));
                    updateTournament(u);
                } else {
                    //add tournament
                    addTournament(u);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTournament(Integer.parseInt(tournamentId));

                Intent intent = new Intent(TournamentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TournamentActivity.this, MainLobbyActivity.class);
                intent.putExtra("tournamentId",String.valueOf(tournamentId));
                startActivity(intent);
            }
        });

        btnTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TournamentActivity.this, MainTeamActivity.class);
                intent.putExtra("tournamentId",String.valueOf(tournamentId));
                startActivity(intent);
            }
        });

    }

    public void addTournament(Tournament u){
        retrofit2.Call<Tournament> call = tournamentServices.addTournament(u);
        call.enqueue(new Callback<Tournament>() {
            @Override
            public void onResponse(retrofit2.Call<Tournament> call, Response<Tournament> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TournamentActivity.this, "Tournament created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Tournament> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateTournament(Tournament u){
        Call<Tournament> call = tournamentServices.updateTournament(u);
        call.enqueue(new Callback<Tournament>() {
            @Override
            public void onResponse(Call<Tournament> call, Response<Tournament> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TournamentActivity.this, "Tournament updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Tournament> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteTournament(int id){
        Call<Tournament> call =tournamentServices.deleteTournament(id);
        call.enqueue(new Callback<Tournament>() {
            @Override
            public void onResponse(Call<Tournament> call, Response<Tournament> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TournamentActivity.this, "Tournament deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Tournament> call, Throwable t) {
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
