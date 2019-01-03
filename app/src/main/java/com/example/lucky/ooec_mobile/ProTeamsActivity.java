package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lucky.ooec_mobile.Adapters.PlayerAdapter;
import com.example.lucky.ooec_mobile.Adapters.ProTeamsAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Adapters.TournamentAdapter;
import com.example.lucky.ooec_mobile.Models.ProTeams;
import com.example.lucky.ooec_mobile.Models.Tournament;
import com.example.lucky.ooec_mobile.Services.ProTeamsServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.example.lucky.ooec_mobile.Services.TournamentServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProTeamsActivity extends AppCompatActivity {
    ProTeamsServices proTeamsServices;
    TokenManager tokenManager;
    ListView listView;
    List<ProTeams> list = new ArrayList<ProTeams>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Pro Teams");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_teams);

        listView = (ListView) findViewById(R.id.listView);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(ProTeamsActivity.this, LoginActivity.class));
            finish();
        }
        proTeamsServices = RetrofitClient.createServiceWithAuth(ProTeamsServices.class,tokenManager);
        getProTeams();
    }
    public void getProTeams(){
        Call<List<ProTeams>> call = proTeamsServices.getTeams();
        call.enqueue(new Callback<List<ProTeams>>() {
            @Override
            public void onResponse(Call<List<ProTeams>> call, Response<List<ProTeams>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ProTeamsAdapter(ProTeamsActivity.this, R.layout.list_proteams, list));
                }
            }

            @Override
            public void onFailure(Call<List<ProTeams>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
