package cornx.meetly.team;

/**
 * Created by Dawid on 25/10/2014.
 */
public interface TeamProvider {
    public void loadTeams();

    public Team getTeam(long teamId);
}
