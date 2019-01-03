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

import com.example.lucky.ooec_mobile.Models.Tournament;
import com.example.lucky.ooec_mobile.R;
import com.example.lucky.ooec_mobile.TournamentActivity;

import java.util.List;

public class TournamentAdapter extends ArrayAdapter<Tournament> {

    private Context context;
    private List<Tournament> tournamentsList;

    public TournamentAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Tournament> objects) {
        super(context, resource, objects);
        this.context = context;
        this.tournamentsList = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_tournament, parent, false);

        TextView txtTournamentId = (TextView) rowView.findViewById(R.id.txtTournamentId);
        TextView txtIdentityId = (TextView) rowView.findViewById(R.id.txtIdentityId);
        TextView txtTournamentname = (TextView) rowView.findViewById(R.id.txtTournamentname);
        TextView txtPlace = (TextView) rowView.findViewById(R.id.txtPlace);
        TextView txtType = (TextView) rowView.findViewById(R.id.txtType);
        TextView txtPrizePool = (TextView) rowView.findViewById(R.id.txtPrizePool);
        TextView txtDateStart = (TextView) rowView.findViewById(R.id.txtDateStart);
        TextView txtDateEnd = (TextView) rowView.findViewById(R.id.txtDateEnd);
        TextView txtDescription = (TextView) rowView.findViewById(R.id.txtDescription);
        TextView txtGame = (TextView) rowView.findViewById(R.id.txtGame);

        txtTournamentId.setText(String.format("#ID: %d", tournamentsList.get(pos).getId()));
        txtIdentityId.setText(String.format(context.getString(R.string.Identity_id), tournamentsList.get(pos).getIdentityId()));
        txtTournamentname.setText(String.format(context.getString(R.string.tournamentname), tournamentsList.get(pos).getTournamentName()));
        txtPlace.setText(String.format(context.getString(R.string.pplace), tournamentsList.get(pos).getPlace()));
        txtType.setText(String.format(context.getString(R.string.ttype), tournamentsList.get(pos).getType()));
        txtPrizePool.setText(String.format(context.getString(R.string.prizePPool), tournamentsList.get(pos).getPrizePool()));
        txtDateStart.setText(String.format(context.getString(R.string.ddatestart), tournamentsList.get(pos).getDateStart()));
        txtDateEnd.setText(String.format(context.getString(R.string.ddateend), tournamentsList.get(pos).getDateEnd()));
        txtDescription.setText(String.format(context.getString(R.string.ddescription), tournamentsList.get(pos).getDescription()));
        txtGame.setText(String.format(context.getString(R.string.ggame), tournamentsList.get(pos).getGame()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TournamentActivity.class);
                intent.putExtra("tournamentId", String.valueOf(tournamentsList.get(pos).getId()));
                intent.putExtra("identityId", tournamentsList.get(pos).getIdentityId());
                intent.putExtra("tournamentName", tournamentsList.get(pos).getTournamentName());
                intent.putExtra("place", tournamentsList.get(pos).getPlace());
                intent.putExtra("type", tournamentsList.get(pos).getType());
                intent.putExtra("prizePool", tournamentsList.get(pos).getPrizePool());
                intent.putExtra("dateStart", tournamentsList.get(pos).getDateStart());
                intent.putExtra("dateEnd", tournamentsList.get(pos).getDateEnd());
                intent.putExtra("description", tournamentsList.get(pos).getDescription());
                intent.putExtra("game", tournamentsList.get(pos).getGame());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}