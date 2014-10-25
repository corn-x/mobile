package cornx.meetly.team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cornx.meetly.R;

/**
 * Created by Dawid on 25/10/2014.
 */
public class TeamListAdapter extends BaseAdapter {
    private List<Team> teams;
    private Context context;
    private LayoutInflater layoutInflater;

    public TeamListAdapter(Context context) {
        this.context = context.getApplicationContext();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setTeams(List<Team> t) {
        teams = t;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return teams == null ? 0 : teams.size();
    }

    @Override
    public Object getItem(int position) {
        return teams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return teams.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.row_fragment_teams, parent, false);
        }
        Team team = teams.get(position);
        TextView textView = (TextView) view.findViewById(R.id.row_fragment_teams_teamname);
        textView.setText(team.getName());
        return view;
    }
}
