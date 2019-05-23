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

import com.example.lucky.ooec_mobile.Models.Patient;
import com.example.lucky.ooec_mobile.PatientActivity;
import com.example.lucky.ooec_mobile.R;
import java.util.List;

public class PatientAdapter extends ArrayAdapter<Patient> {

    private Context context;
    private List<Patient> patientList;

    public PatientAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Patient> objects) {
        super(context, resource, objects);
        this.context = context;
        this.patientList = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_patient, parent, false);

        TextView txtPatientId = (TextView) rowView.findViewById(R.id.txtPatientId);
        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtFirstName = (TextView) rowView.findViewById(R.id.txtFirstName);
        TextView txtLastName = (TextView) rowView.findViewById(R.id.txtLastName);
        TextView txtGender = (TextView) rowView.findViewById(R.id.txtGender);
        TextView txtPatientAge = (TextView) rowView.findViewById(R.id.txtPatientAge);
        TextView txtAddress = (TextView) rowView.findViewById(R.id.txtAddress);
        TextView txtBirthDate = (TextView) rowView.findViewById(R.id.txtBirthDate);

        txtPatientId.setText(String.format(context.getString(R.string.patientid), patientList.get(pos).getId()));
        txtUserId.setText(String.format(context.getString(R.string.userId), patientList.get(pos).getUserId()));
        txtFirstName.setText(String.format(context.getString(R.string.firstName), patientList.get(pos).getFirstName()));
        txtLastName.setText(String.format(context.getString(R.string.lastName), patientList.get(pos).getLastName()));
        txtGender.setText(String.format(context.getString(R.string.Gender), patientList.get(pos).getGender()));
        txtPatientAge.setText(String.format(context.getString(R.string.Age), patientList.get(pos).getAge()));
        txtAddress.setText(String.format(context.getString(R.string.Address), patientList.get(pos).getAddress()));
        txtBirthDate.setText(String.format(context.getString(R.string.BirthDate), patientList.get(pos).getBirthDate()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PatientActivity.class);
                intent.putExtra("patientId", String.valueOf(patientList.get(pos).getId()));
                intent.putExtra("userId", patientList.get(pos).getUserId());
                intent.putExtra("firstName", patientList.get(pos).getFirstName());
                intent.putExtra("lastName", patientList.get(pos).getLastName());
                intent.putExtra("gender", patientList.get(pos).getGender());
                intent.putExtra("patientAge", patientList.get(pos).getAge());
                intent.putExtra("address", patientList.get(pos).getAddress());
                intent.putExtra("birthDate", patientList.get(pos).getBirthDate());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}