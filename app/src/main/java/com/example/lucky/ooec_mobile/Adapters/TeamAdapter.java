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

import com.example.lucky.ooec_mobile.LobbyActivity;
import com.example.lucky.ooec_mobile.Models.Lobby;
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

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_team, parent, false);

        TextView txtTeamId = (TextView) rowView.findViewById(R.id.txtTeamId);
        TextView txtTeamName = (TextView) rowView.findViewById(R.id.txtTeamName);
        TextView txtTag = (TextView) rowView.findViewById(R.id.txtTag);
        TextView txtNumberOfPlayers = (TextView) rowView.findViewById(R.id.txtNumberOfPlayers);

        txtTeamId.setText(String.format("#ID: %d", teamsList.get(pos).getId()));
        txtTeamName.setText(String.format(context.getString(R.string.tteamname), teamsList.get(pos).getTeamName()));
        txtTag.setText(String.format(context.getString(R.string.ttag), teamsList.get(pos).getTag()));
        txtNumberOfPlayers.setText(String.format(context.getString(R.string.nnumberofplayers), teamsList.get(pos).getNumberOfPlayers()));
        
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TeamActivity.class);
                intent.putExtra("teamId", String.valueOf(teamsList.get(pos).getId()));
                intent.putExtra("teamName", teamsList.get(pos).getTeamName());
                intent.putExtra("tag", teamsList.get(pos).getTag());
                intent.putExtra("numberOfPlayers", teamsList.get(pos).getNumberOfPlayers());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
