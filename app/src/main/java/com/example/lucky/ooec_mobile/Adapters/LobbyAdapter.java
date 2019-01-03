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
import com.example.lucky.ooec_mobile.R;


import java.util.List;

public class LobbyAdapter extends ArrayAdapter<Lobby> {
    private Context context;
    private List<Lobby> lobbiesList;

    public LobbyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Lobby> objects) {
        super(context, resource, objects);
        this.context = context;
        this.lobbiesList = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_lobby, parent, false);

        TextView txtLobbyId = (TextView) rowView.findViewById(R.id.txtLobbyId);
        TextView txtScoreWinner = (TextView) rowView.findViewById(R.id.txtScoreWinner);
        TextView txtScoreLoser = (TextView) rowView.findViewById(R.id.txtScoreLoser);
        TextView txtWinner = (TextView) rowView.findViewById(R.id.txtWinner);
        TextView txtLDateStart = (TextView) rowView.findViewById(R.id.txtLDateStart);
        TextView txtTournamentId = (TextView) rowView.findViewById(R.id.txtTournamentId);
        TextView txtRadiantTeamName = (TextView) rowView.findViewById(R.id.txtRadiantTeamName);
        TextView txtDireTeamName = (TextView) rowView.findViewById(R.id.txtDireTeamName);

        txtLobbyId.setText(String.format("#ID: %d", lobbiesList.get(pos).getId()));
        txtLDateStart.setText(String.format(context.getString(R.string.dddatestart), lobbiesList.get(pos).getDateStart()));
        txtScoreWinner.setText(String.format(context.getString(R.string.sscorewinner), lobbiesList.get(pos).getScoreWinner()));
        txtScoreLoser.setText(String.format(context.getString(R.string.sscoreloser), lobbiesList.get(pos).getScoreLoser()));
        txtWinner.setText(String.format(context.getString(R.string.wwiner), lobbiesList.get(pos).getWinner()));
        txtTournamentId.setText(String.format(context.getString(R.string.ttournamentId), lobbiesList.get(pos).getTournamentId()));
        txtRadiantTeamName.setText(String.format(context.getString(R.string.rradiantteamaname), lobbiesList.get(pos).getRadiant_team_name()));
        txtDireTeamName.setText(String.format(context.getString(R.string.ddireteamname), lobbiesList.get(pos).getDire_team_name()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,LobbyActivity.class);
                intent.putExtra("lobbyId", String.valueOf(lobbiesList.get(pos).getId()));
                intent.putExtra("dateStart", lobbiesList.get(pos).getDateStart());
                intent.putExtra("scoreWinner", lobbiesList.get(pos).getScoreWinner());
                intent.putExtra("scoreLoser", lobbiesList.get(pos).getScoreLoser());
                intent.putExtra("winner", lobbiesList.get(pos).getWinner());
                intent.putExtra("tournamentId", lobbiesList.get(pos).getTournamentId());
                intent.putExtra("radiant_team_name", lobbiesList.get(pos).getRadiant_team_name());
                intent.putExtra("dire_team_name", lobbiesList.get(pos).getDire_team_name());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
