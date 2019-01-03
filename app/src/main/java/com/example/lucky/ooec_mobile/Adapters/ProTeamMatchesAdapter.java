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

import com.example.lucky.ooec_mobile.Models.ProTeamMatches;
import com.example.lucky.ooec_mobile.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ProTeamMatchesAdapter extends ArrayAdapter<ProTeamMatches> {
    private Context context;
    private List<ProTeamMatches> proTeamMatchesList;

    public ProTeamMatchesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ProTeamMatches> objects) {
        super(context, resource, objects);
        this.context = context;
        this.proTeamMatchesList = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_promatches, parent, false);

        TextView txtMatch_id = (TextView) rowView.findViewById(R.id.txtMatch_Id);
        TextView txtRadiantWin = (TextView) rowView.findViewById(R.id.txtRadiantWin);
        TextView txtRadiant = (TextView) rowView.findViewById(R.id.txtRadiant);
        TextView txtDuration = (TextView) rowView.findViewById(R.id.txtDuration);
        TextView txtStartTime= (TextView) rowView.findViewById(R.id.txtStartTime);
        TextView txtLeagueId= (TextView) rowView.findViewById(R.id.txtLeagueId);
        TextView txtLeagueName= (TextView) rowView.findViewById(R.id.txtLeagueName);
        TextView txtCluster= (TextView) rowView.findViewById(R.id.txtCluster);
        TextView txtOpposingTeamId= (TextView) rowView.findViewById(R.id.txtOpposingTeamId);
        TextView txtOpposingTeamName= (TextView) rowView.findViewById(R.id.txtOpposingTeamName);
        ImageView txtOpposingTeamLogo= (ImageView) rowView.findViewById(R.id.txtOpposingTeamLogo);

        txtMatch_id.setText(String.format("#ID: %s", proTeamMatchesList.get(pos).getMatch_id()));
        txtRadiantWin.setText(String.format("Radiant Win: %s", proTeamMatchesList.get(pos).isRadiant_win()));
        txtRadiant.setText(String.format("Radiant: %s", proTeamMatchesList.get(pos).isRadiant()));
        Date dateStartTime = new Date(proTeamMatchesList.get(pos).getStart_time()*1000L);
        SimpleDateFormat jdfStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jdfStartTime.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        String java_dateStartTime = jdfStartTime.format(dateStartTime);
        txtStartTime.setText(String.format("Start time: %s",java_dateStartTime));
        Date date = new Date(proTeamMatchesList.get(pos).getDuration()*1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("HH:mm:ss");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        String java_date = jdf.format(date);
        txtDuration.setText(String.format("Duration: %s",java_date));
        txtLeagueId.setText(String.format("League Id: %s", proTeamMatchesList.get(pos).getLeagueid()));
        txtLeagueName.setText(String.format("League Name: %s", proTeamMatchesList.get(pos).getLeague_name()));
        txtCluster.setText(String.format("Cluster: %s", proTeamMatchesList.get(pos).getCluster()));
        txtOpposingTeamId.setText(String.format("Opposing team id: %s", proTeamMatchesList.get(pos).getOpposing_team_id()));
        txtOpposingTeamName.setText(String.format("Opposing team name: %s", proTeamMatchesList.get(pos).getOpposing_team_name()));
        Picasso.with(context).load(proTeamMatchesList.get(pos).getOpposing_team_logo()).into(txtOpposingTeamLogo);

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
