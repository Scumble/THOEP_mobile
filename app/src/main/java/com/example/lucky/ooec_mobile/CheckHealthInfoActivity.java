package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.Diseases;
import com.example.lucky.ooec_mobile.Models.Player;
import com.example.lucky.ooec_mobile.Services.HealthInfoServices;
import com.example.lucky.ooec_mobile.Services.PlayerServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckHealthInfoActivity extends AppCompatActivity {
    TokenManager tokenManager;
    HealthInfoServices healthInfoServices;
    TextView txtHealthInfo;
    List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(CheckHealthInfoActivity.this, LoginActivity.class));
            finish();
        }
        healthInfoServices = RetrofitClient.createServiceWithAuth(HealthInfoServices.class,tokenManager);
        Bundle extras = getIntent().getExtras();
        final String patientId = extras.getString("patientId");
        final String healthInfoId = extras.getString("healthInfoId");

        txtHealthInfo = (TextView) findViewById(R.id.txtHealthInfo);
        checkhealthInfo(Integer.parseInt(healthInfoId),Integer.parseInt(patientId));
    }

    public void checkhealthInfo(int healthInfoId, int patientId){
        Call<List<String>> call = healthInfoServices.checkHealthInfo(healthInfoId,patientId);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    txtHealthInfo.setText(list.toString());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
