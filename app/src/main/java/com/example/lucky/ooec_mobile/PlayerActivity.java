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
import com.example.lucky.ooec_mobile.Models.Player;
import com.example.lucky.ooec_mobile.Services.PlayerServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerActivity extends AppCompatActivity {
    PlayerServices playerServices;
    TokenManager tokenManager;
    EditText edtPId;
    EditText edtName;
    EditText edtPosition;
    EditText edtAge;
    EditText edtPTeamId;
    Button btnSave;
    Button btnDel;
    TextView txtPId;
    TextView txtPTeamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        setTitle(R.string.PPPlayers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtPId = (TextView) findViewById(R.id.txtPId);
        txtPTeamId = (TextView) findViewById(R.id.txtPTeamId);
        edtPId = (EditText) findViewById(R.id.edtPId);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPosition=(EditText) findViewById(R.id.edtPosition);
        edtAge = (EditText) findViewById(R.id.edtAge);
        edtPTeamId = (EditText) findViewById(R.id.edtPTeamId);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(PlayerActivity.this, LoginActivity.class));
            finish();
        }
        playerServices = RetrofitClient.createServiceWithAuth(PlayerServices.class,tokenManager);


        Bundle extras = getIntent().getExtras();
        final String playerId = extras.getString("playerId");
        String name = extras.getString("name");
        String position=extras.getString("position");
        int age = extras.getInt("age");
        final String teamId=extras.getString("teamId");

        edtPId.setText(playerId);
        edtName.setText(name);
        edtPosition.setText(position);
        edtAge.setText(String.valueOf(age));
     //   edtTeamId.setText(String.valueOf(teamId));

        if(playerId != null && playerId.trim().length() > 0 ){
            edtPId.setFocusable(false);
        } else {
            txtPId.setVisibility(View.INVISIBLE);
            edtPId.setVisibility(View.INVISIBLE);
            txtPTeamId.setVisibility(View.INVISIBLE);
            edtPTeamId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player=new Player();
                player.setName(edtName.getText().toString());
                player.setPosition(edtPosition.getText().toString());
                player.setAge(Integer.parseInt(edtAge.getText().toString()));
                player.setTeamId(Integer.parseInt(teamId));
                if(playerId != null && playerId.trim().length() > 0){
                    player.setId(Integer.parseInt(edtPId.getText().toString()));
                    updatePlayer(player);
                } else {
                    addPlayer(player);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePlayer(Integer.parseInt(playerId));
                Intent intent = new Intent(PlayerActivity.this, MainPlayerActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addPlayer(Player u){
        retrofit2.Call<Player> call = playerServices.addPlayer(u);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(retrofit2.Call<Player> call, Response<Player> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PlayerActivity.this, "Player created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updatePlayer(Player u){
        Call<Player> call = playerServices.updatePlayer(u);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PlayerActivity.this, "Player updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deletePlayer(int id){
        Call<Player> call =playerServices.deletePlayer(id);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PlayerActivity.this, "Player deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
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
