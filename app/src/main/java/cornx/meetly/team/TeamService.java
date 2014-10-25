package cornx.meetly.team;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author Aleksander Ciepiela
 */
public interface TeamService {

    @GET("/teams/my")
    void listTeams(Callback<List<Team>> teamListCallback);
}
