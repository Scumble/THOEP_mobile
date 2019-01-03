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
import com.example.lucky.ooec_mobile.Models.Team;
import com.example.lucky.ooec_mobile.Services.LobbyServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.example.lucky.ooec_mobile.Services.TeamServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamActivity extends AppCompatActivity {

    TeamServices teamServices;
    TokenManager tokenManager;
    EditText edtTId;
    EditText edtTeamName;
    EditText edtTag;
    EditText edtNumberOfPlayers;
    Button btnSave;
    Button btnDel;
    Button btnPlayer;
    TextView txtTId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        setTitle(R.string.TTeam);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTId = (TextView) findViewById(R.id.txtTId);
        edtTId = (EditText) findViewById(R.id.edtTId);
        edtTeamName = (EditText) findViewById(R.id.edtTeamName);
        edtTag=(EditText) findViewById(R.id.edtTag);
        edtNumberOfPlayers = (EditText) findViewById(R.id.edtNumberOfPlayers);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnPlayer = (Button) findViewById(R.id.btnPlayer);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(TeamActivity.this, LoginActivity.class));
            finish();
        }
        teamServices = RetrofitClient.createServiceWithAuth(TeamServices.class,tokenManager);


        Bundle extras = getIntent().getExtras();
        final String teamId = extras.getString("teamId");
        String teamName = extras.getString("teamName");
        String tag=extras.getString("tag");
        int numberOfPlayers = extras.getInt("numberOfPlayers");

        edtTId.setText(teamId);
        edtTeamName.setText(teamName);
        edtTag.setText(tag);
        edtNumberOfPlayers.setText(String.valueOf(numberOfPlayers));

        if(teamId != null && teamId.trim().length() > 0 ){
            edtTId.setFocusable(false);
        } else {
            txtTId.setVisibility(View.INVISIBLE);
            edtTId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Team team=new Team();
                team.setTeamName(edtTeamName.getText().toString());
                team.setTag(edtTag.getText().toString());
                team.setNumberOfPlayers(Integer.parseInt(edtNumberOfPlayers.getText().toString()));
                if(teamId != null && teamId.trim().length() > 0){
                    team.setId(Integer.parseInt(edtTId.getText().toString()));
                    updateTeam(team);
                } else {
                    addTeam(team);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTeam(Integer.parseInt(teamId));
                Intent intent = new Intent(TeamActivity.this, MainTeamActivity.class);
                startActivity(intent);
            }
        });
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamActivity.this, MainPlayerActivity.class);
                intent.putExtra("teamId",String.valueOf(teamId));
                startActivity(intent);
            }
        });

    }

    public void addTeam(Team u){
        retrofit2.Call<Team> call = teamServices.addTeam(u);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(retrofit2.Call<Team> call, Response<Team> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TeamActivity.this, "Team created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateTeam(Team u){
        Call<Team> call = teamServices.updateTeam(u);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TeamActivity.this, "Team updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteTeam(int id){
        Call<Team> call =teamServices.deleteTeam(id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TeamActivity.this, "Lobby deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
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
