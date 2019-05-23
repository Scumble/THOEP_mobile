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

import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.Patient;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.example.lucky.ooec_mobile.Services.PatientServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientActivity extends AppCompatActivity {
    PatientServices patientServices;
    TokenManager tokenManager;
    EditText edtUId;
    EditText edtFirstName;
    EditText edtLastName;
    EditText edtUserId;
    Spinner edtGender;
    EditText edtAddress;
    EditText edtBirthDate;
    Button btnSave;
    Button btnDel;
    Button btnHealthInfo;
    Button btnMaps;
    Button btnStatistics;
    Button btnEncodePatient;
  //  Button btnTeam;
    TextView txtUId;
    TextView txtUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        setTitle("Patients");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<String> list = new ArrayList<String>();
        list.add("Male");
        list.add("Female");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        txtUId = (TextView) findViewById(R.id.txtUId);
        txtUserId = (TextView) findViewById(R.id.txtUserId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtUserId=(EditText) findViewById(R.id.edtUserId);
        edtGender = (Spinner) findViewById(R.id.edtGender);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtBirthDate = (EditText) findViewById(R.id.edtBirthDate);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnEncodePatient =(Button) findViewById(R.id.btnEncodePatient);
        btnHealthInfo=(Button) findViewById(R.id.btnHealthInfo);
        btnMaps = (Button) findViewById(R.id.btnMaps);
        btnStatistics =(Button) findViewById(R.id.btnStatistics);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken() == null){
            startActivity(new Intent(PatientActivity.this, LoginActivity.class));
            finish();
        }
        patientServices = RetrofitClient.createServiceWithAuth(PatientServices.class,tokenManager);


        Bundle extras = getIntent().getExtras();
        final String patientId = extras.getString("patientId");
        String firstName = extras.getString("firstName");
        String lastName = extras.getString("lastName");
        String userId=extras.getString("userId");
        String gender = extras.getString("gender");
        String address = extras.getString("address");
        String birthDate = extras.getString("birthDate");

        edtUId.setText(patientId);
        edtFirstName.setText(firstName);
        edtLastName.setText(lastName);
        edtUserId.setText(userId);
        edtGender.setAdapter(dataAdapter);
        edtAddress.setText(address);
        edtBirthDate.setText(birthDate);

        if(patientId != null && patientId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            txtUserId.setVisibility(View.INVISIBLE);
            edtUserId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patient u = new Patient();
                u.setUserId(edtUserId.getText().toString());
                u.setFirstName(edtFirstName.getText().toString());
                u.setLastName(edtLastName.getText().toString());
                u.setGender(edtGender.getSelectedItem().toString());
                u.setAddress(edtAddress.getText().toString());
                u.setBirthDate(edtBirthDate.getText().toString());
                if(patientId != null && patientId.trim().length() > 0){
                    u.setId(Integer.parseInt(edtUId.getText().toString()));
                    updatePatient(u);
                } else {
                    addPatient(u);
                }
                Intent intent = new Intent(PatientActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePatient(Integer.parseInt(patientId));

                Intent intent = new Intent(PatientActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

       btnHealthInfo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(PatientActivity.this, MainLobbyActivity.class);
               intent.putExtra("patientId",String.valueOf(patientId));
               startActivity(intent);
           }
       });

        btnEncodePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this, HL7PatientActivity.class);
                intent.putExtra("patientId",String.valueOf(patientId));
                startActivity(intent);
            }
        });


        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this, MainMapsActivity.class);
                intent.putExtra("patientId",String.valueOf(patientId));
                startActivity(intent);
            }
        });

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this, MainStatisticsActivity.class);
                intent.putExtra("patientId",String.valueOf(patientId));
                startActivity(intent);
            }
        });
     //   btnTeam.setOnClickListener(new View.OnClickListener() {
      //      @Override
       //     public void onClick(View v) {
       //         Intent intent = new Intent(TournamentActivity.this, MainTeamActivity.class);
       //         intent.putExtra("tournamentId",String.valueOf(tournamentId));
       //         startActivity(intent);
       //     }
       // });

    }

    public void addPatient(Patient u){
        retrofit2.Call<Patient> call = patientServices.addPatient(u);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(retrofit2.Call<Patient> call, Response<Patient> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PatientActivity.this, "Patient created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updatePatient(Patient u){
        Call<Patient> call = patientServices.updatePatient(u);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PatientActivity.this, "Patient updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deletePatient(int id){
        Call<Patient> call =patientServices.deletePatient(id);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PatientActivity.this, "Patient deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
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
