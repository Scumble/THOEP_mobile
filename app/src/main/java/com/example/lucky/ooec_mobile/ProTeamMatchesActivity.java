package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.lucky.ooec_mobile.Adapters.ProTeamMatchesAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.ProTeamMatches;
import com.example.lucky.ooec_mobile.Services.ProTeamsServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProTeamMatchesActivity extends AppCompatActivity {

    ProTeamsServices proTeamsServices;
    TokenManager tokenManager;
    ListView listView;
    List<ProTeamMatches> list = new ArrayList<ProTeamMatches>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Pro Matches");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_team_matches);
        listView = (ListView) findViewById(R.id.listView);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        Bundle extras = getIntent().getExtras();
        final String proteamId = extras.getString("teamId");
        if(tokenManager.getToken() == null){
            startActivity(new Intent(ProTeamMatchesActivity.this, LoginActivity.class));
            finish();
        }
        proTeamsServices = RetrofitClient.createServiceWithAuth(ProTeamsServices.class,tokenManager);
        getProTeamMatches(Integer.parseInt(proteamId));
    }
    public void getProTeamMatches(int proteamId){
        Call<List<ProTeamMatches>> call = proTeamsServices.getTeamMatches(proteamId);
        call.enqueue(new Callback<List<ProTeamMatches>>() {
            @Override
            public void onResponse(Call<List<ProTeamMatches>> call, Response<List<ProTeamMatches>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ProTeamMatchesAdapter(ProTeamMatchesActivity.this, R.layout.list_promatches, list));
                }
            }

            @Override
            public void onFailure(Call<List<ProTeamMatches>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
