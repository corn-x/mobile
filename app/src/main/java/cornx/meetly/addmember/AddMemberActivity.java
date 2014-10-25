package cornx.meetly.addmember;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import cornx.meetly.R;
import cornx.meetly.app.MeetlyApplication;
import cornx.meetly.team.TeamActivity;
import dagger.ObjectGraph;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Mateusz on 2014-10-25.
 */
public class AddMemberActivity extends Activity implements View.OnClickListener, Callback<JsonElement> {

    private TextView member_email;
    private Button submit;
    private ProgressBar progressBar;
    private long id;

    @Inject
    AddMemberService addMemberService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectGraph objectGraph = ((MeetlyApplication) getApplication()).getObjectGraph();
        objectGraph.inject(this);
        setContentView(R.layout.activity_add_member);
        submit = (Button) findViewById(R.id.member_add_submit);
        member_email = (TextView) findViewById(R.id.member_add_name);
        id = getIntent().getLongExtra(TeamActivity.TEAM_ID, -1);
        progressBar = (ProgressBar) findViewById(R.id.member_add_progress);
        progressBar.setVisibility(View.INVISIBLE);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (member_email.getText().length() == 0) {
            showPopup("E-mail cannot be empty.");
            return;
        }

        Map<String, String[]> map = new HashMap<>();
        String[] tab = {member_email.getText().toString()};
        map.put("user_emails", tab);
        submit.setEnabled(false);
        progressBar.setProgress(50);
        progressBar.setVisibility(View.VISIBLE);
        addMemberService.addMember(id, map, this);
    }

    private void showPopup(String why) {
        Toast.makeText(this, why, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_member, menu);
        return true;
    }


    @Override
    public void success(JsonElement jsonElement, Response response) {
        showPopup("Member added successfully.");
        this.finish();
    }

    @Override
    public void failure(RetrofitError error) {
        showPopup(error.getMessage());
        submit.setEnabled(true);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.INVISIBLE);
    }


}
