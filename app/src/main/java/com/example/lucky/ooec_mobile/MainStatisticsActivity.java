package com.example.lucky.ooec_mobile;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.lucky.ooec_mobile.Adapters.PatientAdapter;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.HealthInfoStatistics;
import com.example.lucky.ooec_mobile.Models.Patient;
import com.example.lucky.ooec_mobile.Services.HealthInfoStatisticsServices;
import com.example.lucky.ooec_mobile.Services.PatientServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainStatisticsActivity extends AppCompatActivity {

    HealthInfoStatisticsServices healthInfoStatisticsServices;
    HealthInfoStatistics list;
    TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_team);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(MainStatisticsActivity.this, LoginActivity.class));
            finish();
        }
        healthInfoStatisticsServices = RetrofitClient.createServiceWithAuth(HealthInfoStatisticsServices.class,tokenManager);

        Bundle extras = getIntent().getExtras();
        final String patientId = extras.getString("patientId");
        getHealthInfoList(Integer.parseInt(patientId));

    }

    public void getHealthInfoList(int patientId){
        Call<HealthInfoStatistics> call = healthInfoStatisticsServices.getHealthInfoStatistics(patientId);
        call.enqueue(new Callback<HealthInfoStatistics>() {
            @Override
            public void onResponse(Call<HealthInfoStatistics> call, Response<HealthInfoStatistics> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    BarChart barChart = (BarChart) findViewById(R.id.barchart);
                    ArrayList<BarEntry> entries = new ArrayList<>();
                    entries.add(new BarEntry(list.getAverageHeartRate(), 0));
                    entries.add(new BarEntry(list.getAverageBloodPressure(), 1));
                    entries.add(new BarEntry(list.getAverageTemperature(), 2));
                    entries.add(new BarEntry(list.getAverageWeight(), 3));


                    BarDataSet bardataset = new BarDataSet(entries, "Cells");

                    ArrayList<String> labels = new ArrayList<String>();
                    labels.add("Average Heart Rate");
                    labels.add("Average Blood Pressure");
                    labels.add("Average Temperature");
                    labels.add("Average Weight");

                    BarData data = new BarData(labels, bardataset);
                    barChart.setData(data);

                    bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

                    barChart.animateY(5000);
                }
            }

            @Override
            public void onFailure(Call<HealthInfoStatistics> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
