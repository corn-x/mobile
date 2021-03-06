package cornx.meetly.signIn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                submitSignIn();
            }
        });

        //TODO
        emailinput.setText("a@a.pl");
        passwinput.setText("dupadupa");

    }

    private void submitSignIn() {
        if (email.getText().length() == 0) {
            showPopup("E-mail cannot be empty.");
            return;
        }
        if (passw.getText().length() == 0) {
            showPopup("Password cannot be empty.");
            return;
        }
        Map<String, User> map = new HashMap<String, User>();


        SignInService signInService
                = new RestAdapter.Builder().setEndpoint(AppModule.SERVER).build()
                .create(SignInService.class);


        User user = new User(emailinput.getText().toString(), passwinput.getText().toString());
        map.put("user", user);
        signInService.logIn(map, this);
    }

    private void showPopup(String why) {
        Toast.makeText(this, why, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_in, menu);
        return true;
    }


    @Override
    public void success(JsonElement jsonElement, Response response) {
        try {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String auth = jsonObject.get("authentication_token").getAsString();
            submitButton.setEnabled(false);
            AppModule.auth = auth;
            Log.d("auth", auth);
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        } catch (NullPointerException e) {
            showPopup("Invalid e-mail or password. Please try again.");
        }
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("login", error.getMessage());
    }
}
