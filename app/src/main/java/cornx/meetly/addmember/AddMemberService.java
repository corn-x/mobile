package cornx.meetly.addmember;

import com.google.gson.JsonElement;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Mateusz on 2014-10-25.
 */
public interface AddMemberService {
    @POST("/teams/{id}/members/add")
    void addMember(@Path("id") long teamId, @Body Map<String, String[]> map, Callback<JsonElement> callback);
}
