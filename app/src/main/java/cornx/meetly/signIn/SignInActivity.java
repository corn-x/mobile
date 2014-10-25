package cornx.meetly.signIn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import cornx.meetly.MainActivity;
import cornx.meetly.R;
import cornx.meetly.app.AppModule;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignInActivity extends Activity implements Callback<JsonElement> {

    private TextView email;
    private TextView passw;
    private EditText emailinput;
    private EditText passwinput;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = (TextView) findViewById(R.id.activity_signin_mailtext);
        passw = (TextView) findViewById(R.id.activity_signin_passwtext);
        emailinput = (EditText) findViewById(R.id.activity_signin_inputmail);
        passwinput = (EditText) findViewById(R.id.activity_signin_inputpassw);
        submitButton = (Button) findViewById(R.id.activity_signin_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// sign in!
            }
        });

        Map<String, User> map = new HashMap<String, User>();


        SignInService signInService
                = new RestAdapter.Builder().setEndpoint(AppModule.SERVER).build()
                .create(SignInService.class);


        User user = new User("a@a.pl", "dupadupa");
        map.put("user", user);
        signInService.logIn(map, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_in, menu);
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
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String auth = jsonObject.get("authentication_token").getAsString();
        AppModule.auth = auth;
        Log.d("auth", auth);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("login", error.getMessage());
    }
}
