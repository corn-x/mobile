package cornx.meetly.addteam;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

import cornx.meetly.R;
import cornx.meetly.app.AppModule;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTeamActivity extends Activity implements View.OnClickListener, Callback<JsonElement> {

    private TextView team_name;
    private Button submit;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        setTitle("Add team");
        team_name = (TextView) findViewById(R.id.team_add_name);
        submit = (Button) findViewById(R.id.team_add_submit);
        progressBar = (ProgressBar) findViewById(R.id.team_add_progress);
        progressBar.setVisibility(View.INVISIBLE);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (team_name.getText().length() == 0) {
            showPopup("Name cannot be empty.");
            return;
        }
        AddTeamService addTeamService
                = new RestAdapter.Builder().setEndpoint(AppModule.SERVER).build()
                .create(AddTeamService.class);
        Map<String, String> map = new HashMap<>();
        map.put("name", team_name.getText().toString());
        submit.setEnabled(false);
        progressBar.setProgress(50);
        progressBar.setVisibility(View.VISIBLE);
        addTeamService.addTeam(map, this);
    }

    private void showPopup(String why) {
        Toast.makeText(this, why, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_team, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(JsonElement jsonElement, Response response) {
        showPopup("Team added successfully.");
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
