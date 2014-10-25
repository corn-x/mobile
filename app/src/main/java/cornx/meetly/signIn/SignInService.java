package cornx.meetly.signIn;

import com.google.gson.JsonElement;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * @author Aleksander Ciepiela
 */
public interface SignInService {

    @POST("/users/sign_in")
    void logIn(@Body Map<String, User> map, Callback<JsonElement> callback);

}
