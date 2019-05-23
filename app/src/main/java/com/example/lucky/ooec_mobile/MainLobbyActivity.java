package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lucky.ooec_mobile.Adapters.HealthInfoAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.HealthInfo;
import com.example.lucky.ooec_mobile.Services.HealthInfoServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLobbyActivity extends AppCompatActivity {
    Button btnAddHealthInfo;
    Button btnGetHealthInfoList;
    ListView listView;
    HealthInfoServices healthInfoServices;
    TokenManager tokenManager;
    List<HealthInfo> list = new ArrayList<HealthInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lobby);

        setTitle("Health Info");

        btnAddHealthInfo = (Button) findViewById(R.id.btnAddHealthInfo);
        btnGetHealthInfoList = (Button) findViewById(R.id.btnGetHealthInfoList);
        listView = (ListView) findViewById(R.id.listView);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(MainLobbyActivity.this, LoginActivity.class));
            finish();
        }
        Bundle extras = getIntent().getExtras();
        final String patientId = extras.getString("patientId");
        healthInfoServices = RetrofitClient.createServiceWithAuth(HealthInfoServices.class,tokenManager);

        btnGetHealthInfoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHealthInfoList(Integer.parseInt(patientId));
            }
        });

        btnAddHealthInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLobbyActivity.this, HealthInfoActivity.class);
                intent.putExtra("diseaseCode", "");
                intent.putExtra("heartRate", "");
                intent.putExtra("bloodPressure", "");
                intent.putExtra("temperature", "");
                intent.putExtra("patientId", String.valueOf(patientId));
                intent.putExtra("weight", "");
                intent.putExtra("time", "");
                startActivity(intent);
            }
        });
    }

    public void getHealthInfoList(int patientId){
        Call<List<HealthInfo>> call = healthInfoServices.getHealthInfo(patientId);
        call.enqueue(new Callback<List<HealthInfo>>() {
            @Override
            public void onResponse(Call<List<HealthInfo>> call, Response<List<HealthInfo>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new HealthInfoAdapter(MainLobbyActivity.this, R.layout.list_health, list));
                }
            }

            @Override
            public void onFailure(Call<List<HealthInfo>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
