package com.example.lucky.ooec_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lucky.ooec_mobile.Adapters.PatientAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.Patient;
import com.example.lucky.ooec_mobile.Services.PatientServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddPatient;
    Button btnGetPatientList;
    Button btnLogout;
    ListView listView;

    PatientServices patientServices;
    TokenManager tokenManager;
    List<Patient> list = new ArrayList<Patient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("THOEP");

        btnAddPatient = (Button) findViewById(R.id.btnAddPatient);
        btnGetPatientList = (Button) findViewById(R.id.btnGetPatientList);

        btnLogout=(Button) findViewById(R.id.btnLogout);
        listView = (ListView) findViewById(R.id.listView);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        patientServices = RetrofitClient.createServiceWithAuth(PatientServices.class,tokenManager);

        btnGetPatientList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPatientList();
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PatientActivity.class);
                intent.putExtra("firstName", "");
                intent.putExtra("lastName", "");
                intent.putExtra("userId", "");
                intent.putExtra("gender", "");
                intent.putExtra("age", "");
                intent.putExtra("address", "");
                intent.putExtra("birthDate", "");
                startActivity(intent);
            }
        });
    }

    public void getPatientList(){
        Call<List<Patient>> call = patientServices.getPatients();
        call.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new PatientAdapter(MainActivity.this, R.layout.list_patient, list));
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
