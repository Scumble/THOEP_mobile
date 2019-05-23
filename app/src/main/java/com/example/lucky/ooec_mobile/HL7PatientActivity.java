package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.ProTeamMatches;
import com.example.lucky.ooec_mobile.Services.HealthInfoServices;
import com.example.lucky.ooec_mobile.Services.ProTeamsServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HL7PatientActivity extends AppCompatActivity {
    TokenManager tokenManager;
    HealthInfoServices healthInfoServices;
    TextView txtHL7Info;
    List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_team_matches);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(HL7PatientActivity.this, LoginActivity.class));
            finish();
        }
        healthInfoServices = RetrofitClient.createServiceWithAuth(HealthInfoServices.class,tokenManager);
        Bundle extras = getIntent().getExtras();
        final String patientId = extras.getString("patientId");

        txtHL7Info = (TextView) findViewById(R.id.txtHL7Info);
        encode(Integer.parseInt(patientId));
    }

    public void encode(int patientId){
        Call<String> call = healthInfoServices.HL7EncodePatient(patientId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    txtHL7Info.setText(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
