package com.example.lucky.ooec_mobile.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucky.ooec_mobile.Models.ProTeamPlayers;
import com.example.lucky.ooec_mobile.Models.ProTeams;
import com.example.lucky.ooec_mobile.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ProTeamPlayersAdapter  extends ArrayAdapter<ProTeamPlayers> {
    private Context context;
    private List<ProTeamPlayers> proTeamPlayersList;

    public ProTeamPlayersAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ProTeamPlayers> objects) {
        super(context, resource, objects);
        this.context = context;
        this.proTeamPlayersList = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_proplayers, parent, false);

        TextView txtAccount_Id = (TextView) rowView.findViewById(R.id.txtAccount_Id);
        TextView txtPlayersname = (TextView) rowView.findViewById(R.id.txtPlayersName);
        TextView txtGamesPlayed = (TextView) rowView.findViewById(R.id.txtGamesPlayed);
        TextView txtWins = (TextView) rowView.findViewById(R.id.txtWins);
        TextView txtIsCurrentTeamMember= (TextView) rowView.findViewById(R.id.txtIsCurrentTeamMember);

        txtAccount_Id.setText(String.format("#ID: %s", proTeamPlayersList.get(pos).getAccount_id()));
        txtPlayersname.setText(String.format(context.getString(R.string.ppplayername), proTeamPlayersList.get(pos).getName()));
        txtGamesPlayed.setText(String.format(context.getString(R.string.gamesplayed), proTeamPlayersList.get(pos).getGames_played()));
        txtWins.setText(String.format(context.getString(R.string.WWins), proTeamPlayersList.get(pos).getWins()));
        txtIsCurrentTeamMember.setText(String.format(context.getString(R.string.iscurrentteammember), proTeamPlayersList.get(pos).isIs_current_team_member()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(context,PlayerActivity.class);
                intent.putExtra("playerId", String.valueOf(playersList.get(pos).getId()));
                intent.putExtra("name", playersList.get(pos).getName());
                intent.putExtra("position", playersList.get(pos).getPosition());
                intent.putExtra("age", playersList.get(pos).getAge());
                intent.putExtra("teamId", playersList.get(pos).getTeamId());
                context.startActivity(intent);*/
            }
        });

        return rowView;
    }
}
