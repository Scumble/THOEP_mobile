package com.example.lucky.ooec_mobile.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucky.ooec_mobile.Models.Player;
import com.example.lucky.ooec_mobile.Models.ProTeamPlayers;
import com.example.lucky.ooec_mobile.Models.ProTeams;
import com.example.lucky.ooec_mobile.PlayerActivity;
import com.example.lucky.ooec_mobile.ProTeamPlayersActivity;
import com.example.lucky.ooec_mobile.ProTeamsActivity;
import com.example.lucky.ooec_mobile.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ProTeamsAdapter extends ArrayAdapter<ProTeams> {
    private Context context;
    private List<ProTeams> proTeamsList;

    public ProTeamsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ProTeams> objects) {
        super(context, resource, objects);
        this.context = context;
        this.proTeamsList = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_proteams, parent, false);

        TextView txtTeam_Id = (TextView) rowView.findViewById(R.id.txtTeam_Id);
        TextView txtRating = (TextView) rowView.findViewById(R.id.txtRating);
        TextView txtWins = (TextView) rowView.findViewById(R.id.txtWins);
        TextView txtLosses = (TextView) rowView.findViewById(R.id.txtLosses);
        TextView txtLastMatchTime= (TextView) rowView.findViewById(R.id.txtLastMatchTime);
        TextView txtName= (TextView) rowView.findViewById(R.id.txtName);
        TextView txtTag= (TextView) rowView.findViewById(R.id.txtTag);
        ImageView txtLogo= (ImageView) rowView.findViewById(R.id.txtLogo);
        Date date = new Date(proTeamsList.get(pos).getLast_Match_Time()*1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        String java_date = jdf.format(date);
        txtTeam_Id.setText(String.format("#ID: %d", proTeamsList.get(pos).getTeam_Id()));
        txtRating.setText(String.format(context.getString(R.string.rating), proTeamsList.get(pos).getRating()));
        txtWins.setText(String.format(context.getString(R.string.wwwins), proTeamsList.get(pos).getWins()));
        txtLosses.setText(String.format(context.getString(R.string.llosses), proTeamsList.get(pos).getLosses()));
        txtLastMatchTime.setText(String.format(context.getString(R.string.llastmatchtime), java_date));
        txtName.setText(String.format(context.getString(R.string.nnname), proTeamsList.get(pos).getName()));
        txtTag.setText(String.format(context.getString(R.string.tttag), proTeamsList.get(pos).getTag()));
     //   txtLogo.setText(String.format("Logo: %s", proTeamsList.get(pos).getLogo_url()));
        Picasso.with(context).load(proTeamsList.get(pos).getLogo_url()).into(txtLogo);

       rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ProTeamPlayersActivity.class);
                intent.putExtra("teamId", String.valueOf(proTeamsList.get(pos).getTeam_Id()));
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
