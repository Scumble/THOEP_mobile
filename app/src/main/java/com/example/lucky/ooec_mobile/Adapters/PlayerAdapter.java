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

import com.example.lucky.ooec_mobile.Models.Player;
import com.example.lucky.ooec_mobile.Models.Team;
import com.example.lucky.ooec_mobile.PlayerActivity;
import com.example.lucky.ooec_mobile.R;
import com.example.lucky.ooec_mobile.TeamActivity;

import java.util.List;

public class PlayerAdapter extends ArrayAdapter<Player> {
    private Context context;
    private List<Player> playersList;

    public PlayerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Player> objects) {
        super(context, resource, objects);
        this.context = context;
        this.playersList = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_player, parent, false);

        TextView txtPlayerId = (TextView) rowView.findViewById(R.id.txtPlayerId);
        TextView txtName = (TextView) rowView.findViewById(R.id.txtName);
        TextView txtPosition = (TextView) rowView.findViewById(R.id.txtPosition);
        TextView txtAge = (TextView) rowView.findViewById(R.id.txtAge);
        TextView txtTeamId = (TextView) rowView.findViewById(R.id.txtTeamId);

        txtPlayerId.setText(String.format("#ID: %d", playersList.get(pos).getId()));
        txtName.setText(String.format(context.getString(R.string.pplayername), playersList.get(pos).getName()));
        txtPosition.setText(String.format(context.getString(R.string.pposition), playersList.get(pos).getPosition()));
        txtAge.setText(String.format(context.getString(R.string.aage), playersList.get(pos).getAge()));
        txtTeamId.setText(String.format(context.getString(R.string.tteamid), playersList.get(pos).getTeamId()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PlayerActivity.class);
                intent.putExtra("playerId", String.valueOf(playersList.get(pos).getId()));
                intent.putExtra("name", playersList.get(pos).getName());
                intent.putExtra("position", playersList.get(pos).getPosition());
                intent.putExtra("age", playersList.get(pos).getAge());
                intent.putExtra("teamId", playersList.get(pos).getTeamId());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
