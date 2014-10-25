package cornx.meetly.addteam;

import com.google.gson.JsonElement;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * @author Dawid Dworak
 */
public interface AddTeamService {

    @POST("/teams")
    void addTeam(@Body Map<String, String> map, Callback<JsonElement> callback);

}