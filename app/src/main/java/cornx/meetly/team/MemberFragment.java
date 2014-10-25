package cornx.meetly.team;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cornx.meetly.R;

/**
 * Created by Mateusz on 2014-10-25.
 */
public class MemberFragment extends Fragment{
    private ListView listView;
    private TextView textView;
    private MemberListAdapter memberListAdapter;
    private MemberProvider memberProvider;

    public MemberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memberListAdapter = new MemberListAdapter(getActivity());
        memberProvider = new MemberProviderDummy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        memberListAdapter.setMembers(memberProvider.getMembers());
/// setview
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_details, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_members_details_memberslist);
        textView = (TextView) view.findViewById(R.id.fragment_members_details_description);
        listView.setAdapter(memberListAdapter);
        return view;
    }


}
