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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.ooec_mobile.Adapters.PatientAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.Diseases;
import com.example.lucky.ooec_mobile.Models.HealthInfo;
import com.example.lucky.ooec_mobile.Models.Patient;
import com.example.lucky.ooec_mobile.Services.DiseaseServices;
import com.example.lucky.ooec_mobile.Services.HealthInfoServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthInfoActivity extends AppCompatActivity {

    HealthInfoServices healthInfoServices;
    DiseaseServices diseaseServices;
    TokenManager tokenManager;
    EditText edtHId;
    EditText edtHPatientId;
    Spinner edtDiseaseCode;
    EditText edtHeartRate;
    EditText edtBloodPressure;
    EditText edtTemperature;
    EditText edtWeight;
    Button btnSave;
    Button btnDel;
    Button btnHealthInfo;
    TextView txtHId;
    TextView txtHPatientId;
    List<Diseases> list = new ArrayList<Diseases>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        setTitle("Health Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtHId = (TextView) findViewById(R.id.txtHId);
        txtHPatientId = (TextView) findViewById(R.id.txtHPatientId);
        edtHId = (EditText) findViewById(R.id.edtHId);
        edtDiseaseCode = (Spinner) findViewById(R.id.edtDiseaseCode);
        edtHeartRate =(EditText) findViewById(R.id.edtHeartRate);
        edtBloodPressure = (EditText) findViewById(R.id.edtBloodPressure);
        edtTemperature= (EditText) findViewById(R.id.edtTemperature);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        edtHPatientId = (EditText) findViewById(R.id.edtHPatientId);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnHealthInfo = (Button) findViewById(R.id.btnHealthInfo);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(HealthInfoActivity.this, LoginActivity.class));
            finish();
        }
        healthInfoServices = RetrofitClient.createServiceWithAuth(HealthInfoServices.class,tokenManager);
        diseaseServices = RetrofitClient.createServiceWithAuth(DiseaseServices.class,tokenManager);

        Bundle extras = getIntent().getExtras();
        final String healthInfoId = extras.getString("healthInfoId");
        final int patientId = extras.getInt("patientId");
        String diseaseCode =extras.getString("diseaseCode");
        final float heartRate = extras.getFloat("heartRate");
        float bloodPressure = extras.getFloat("bloodPressure");
        float temperature = extras.getFloat("temperature");
        float weight = extras.getFloat("weight");
        getDiseases();
        edtHId.setText(healthInfoId);
        edtHPatientId.setText(String.valueOf(patientId));

        edtHeartRate.setText(String.valueOf(heartRate));
        edtBloodPressure.setText(String.valueOf(bloodPressure));
        edtTemperature.setText(String.valueOf(temperature));
        edtWeight.setText(String.valueOf(weight));

        if(healthInfoId!= null && healthInfoId.trim().length() > 0 ){
            edtHId.setFocusable(false);
        } else {
            txtHId.setVisibility(View.INVISIBLE);
            edtHId.setVisibility(View.INVISIBLE);
            txtHPatientId.setVisibility(View.INVISIBLE);
            edtHPatientId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HealthInfo healthInfo = new HealthInfo();
                healthInfo.setDiseaseCode(edtDiseaseCode.getSelectedItem().toString());
                healthInfo.setHeartRate(Float.parseFloat(edtHeartRate.getText().toString()));
                healthInfo.setBloodPressure(Float.parseFloat(edtBloodPressure.getText().toString()));
                healthInfo.setTemperature(Float.parseFloat(edtTemperature.getText().toString()));
                healthInfo.setPatientId(patientId);
                healthInfo.setWeight(Float.parseFloat(edtWeight.getText().toString()));
                if(healthInfoId != null && healthInfoId.trim().length() > 0){
                    healthInfo.setId(Integer.parseInt(edtHId.getText().toString()));
                    updateHealthInfo(healthInfo);
                } else {
                    addHealthInfo(healthInfo);
                }
                Intent intent = new Intent(HealthInfoActivity.this, MainLobbyActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHealthInfo(Integer.parseInt(healthInfoId));

                Intent intent = new Intent(HealthInfoActivity.this, MainLobbyActivity.class);
                startActivity(intent);
            }
        });

        btnHealthInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthInfoActivity.this, CheckHealthInfoActivity.class);
                intent.putExtra("patientId",String.valueOf(patientId));
                intent.putExtra("healthInfoId", String.valueOf(healthInfoId));
                startActivity(intent);
            }
        });

    }
    public void getDiseases(){
        Call<List<Diseases>> call = diseaseServices.getDiseases();
        call.enqueue(new Callback<List<Diseases>>() {
            @Override
            public void onResponse(Call<List<Diseases>> call, Response<List<Diseases>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    List<String> listDisease = new ArrayList<String>();
                    for(int i=0; i<list.size();i++) {
                        listDisease.add(list.get(i).DiseaseCode);
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(HealthInfoActivity.this, android.R.layout.simple_spinner_item, listDisease);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    edtDiseaseCode.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Diseases>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void addHealthInfo(HealthInfo u){
        retrofit2.Call<HealthInfo> call = healthInfoServices.addHealthInfo(u);
        call.enqueue(new Callback<HealthInfo>() {
            @Override
            public void onResponse(retrofit2.Call<HealthInfo> call, Response<HealthInfo> response) {
                if(response.isSuccessful()){
                    Toast.makeText(HealthInfoActivity.this, "Health Info created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HealthInfo> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateHealthInfo(HealthInfo u){
        Call<HealthInfo> call = healthInfoServices.updateHealthInfo(u);
        call.enqueue(new Callback<HealthInfo>() {
            @Override
            public void onResponse(Call<HealthInfo> call, Response<HealthInfo> response) {
                if(response.isSuccessful()){
                    Toast.makeText(HealthInfoActivity.this, "Health Info updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HealthInfo> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteHealthInfo(int id){
        Call<HealthInfo> call =healthInfoServices.deleteHealthInfo(id);
        call.enqueue(new Callback<HealthInfo>() {
            @Override
            public void onResponse(Call<HealthInfo> call, Response<HealthInfo> response) {
                if(response.isSuccessful()){
                    Toast.makeText(HealthInfoActivity.this, "Health Info deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HealthInfo> call, Throwable t) {
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
