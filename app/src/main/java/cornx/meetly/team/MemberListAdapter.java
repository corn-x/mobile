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
 * Created by Mateusz on 2014-10-25.
 */


public class MemberListAdapter extends BaseAdapter {
    private List<Member> members;
    private Context context;
    private LayoutInflater layoutInflater;

    public MemberListAdapter(Context context) {
        this.context = context.getApplicationContext();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setMembers(List<Member> m) {
        members = m;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return members == null ? 0 : members.size();
    }

    @Override
    public Object getItem(int position) {
        return members.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.row_fragment_members, parent, false);
        }
        Member member = members.get(position);
        TextView textView = (TextView) view.findViewById(R.id.row_fragment_members_description);
        textView.setText(member.toString());
        return view;
    }
}
