package cornx.meetly.team;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author Aleksander Ciepiela
 */
public interface MemberService {


    @GET("/teams/{id}/members")
    void listMembers(@Path("id") long teamId, Callback<List<Member>> memberListCallback);
}
