package cornx.meetly.team;

import java.util.List;

/**
 * @author Aleksander Ciepiela
 */
public class TeamsLoadEvent {

    private List<Team> teamList;

    public TeamsLoadEvent(List<Team> teamList) {
        this.teamList = teamList;
    }

    public List<Team> getTeamList() {
        return teamList;
    }
}
