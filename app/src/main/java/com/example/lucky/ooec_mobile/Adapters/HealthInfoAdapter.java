package com.example.lucky.ooec_mobile.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucky.ooec_mobile.HealthInfoActivity;
import com.example.lucky.ooec_mobile.Models.HealthInfo;
import com.example.lucky.ooec_mobile.R;


import java.util.List;

public class HealthInfoAdapter extends ArrayAdapter<HealthInfo> {
    private Context context;
    private List<HealthInfo> healthInfoList;

    public HealthInfoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<HealthInfo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.healthInfoList = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_health, parent, false);

        TextView txtHealthInfoId = (TextView) rowView.findViewById(R.id.txtHId);
        TextView txtHPatientId = (TextView) rowView.findViewById(R.id.txtHPatientId);
        TextView txtDiseaseCode = (TextView) rowView.findViewById(R.id.txtDiseaseCode);
        TextView txtHeartRate = (TextView) rowView.findViewById(R.id.txtHeartRate);
        TextView txtBloodPressure = (TextView) rowView.findViewById(R.id.txtBloodPressure);
        TextView txtTemperature = (TextView) rowView.findViewById(R.id.txtTemperature);
        TextView txtWeight = (TextView) rowView.findViewById(R.id.txtWeight);
        TextView txtTime = (TextView) rowView.findViewById(R.id.txtHTime);

        txtHealthInfoId.setText(String.format(context.getString(R.string.id), healthInfoList.get(pos).getId()));
        txtHPatientId.setText(String.format(context.getString(R.string.patientid), healthInfoList.get(pos).getPatientId()));
        txtDiseaseCode.setText(String.format(context.getString(R.string.diseaseCode), healthInfoList.get(pos).getDiseaseCode()));
        txtHeartRate.setText(String.format(context.getString(R.string.heartRate), healthInfoList.get(pos).getHeartRate()));
        txtBloodPressure.setText(String.format(context.getString(R.string.bloodPressure), healthInfoList.get(pos).getBloodPressure()));
        txtTemperature.setText(String.format(context.getString(R.string.temperature), healthInfoList.get(pos).getTemperature()));
        txtWeight.setText(String.format(String.format(context.getString(R.string.weight), healthInfoList.get(pos).getWeight())));
        txtTime.setText(String.format(context.getString(R.string.time), healthInfoList.get(pos).getTime()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HealthInfoActivity.class);
                intent.putExtra("healthInfoId", String.valueOf(healthInfoList.get(pos).getId()));
                intent.putExtra("patientId", healthInfoList.get(pos).getPatientId());
                intent.putExtra("diseaseCode", healthInfoList.get(pos).getDiseaseCode());
                intent.putExtra("heartRate", healthInfoList.get(pos).getHeartRate());
                intent.putExtra("bloodPressure", healthInfoList.get(pos).getBloodPressure());
                intent.putExtra("temperature", healthInfoList.get(pos).getTemperature());
                intent.putExtra("weight", healthInfoList.get(pos).getWeight());
                intent.putExtra("time", healthInfoList.get(pos).getTime());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
