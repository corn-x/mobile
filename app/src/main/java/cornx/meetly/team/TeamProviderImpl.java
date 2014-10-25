package cornx.meetly.team;

import android.util.Log;

import com.squareup.otto.Bus;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Aleksander Ciepiela
 */
public class TeamProviderImpl implements TeamProvider, Callback<List<Team>> {

    private TeamService teamService;
    private Bus bus;


    public TeamProviderImpl(TeamService teamService, Bus bus) {
        this.teamService = teamService;
        this.bus = bus;
    }

    @Override
    public void loadTeams() {
        teamService.listTeams(this);
    }

    @Override
    public Team getTeam(long teamId) {
        return null;
    }

    @Override
    public void success(List<Team> teams, Response response) {
        bus.post(new TeamsLoadEvent(teams));
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("failure", error.getMessage());
    }
}
