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
import com.example.lucky.ooec_mobile.Models.Team;
import com.example.lucky.ooec_mobile.R;
import com.example.lucky.ooec_mobile.TeamActivity;

import java.util.List;

public class TeamAdapter extends ArrayAdapter<Team> {
    private Context context;
    private List<Team> teamsList;

    public TeamAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Team> objects) {
        super(context, resource, objects);
        this.context = context;
        this.teamsList = objects;
    }
}
