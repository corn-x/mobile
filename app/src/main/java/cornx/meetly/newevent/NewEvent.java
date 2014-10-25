package cornx.meetly.newevent;

/**
 * Created by Dawid on 25/10/2014.
 */
public class NewEvent {
    private String name;
    private long length;
    private String description;
    private long team_id;
    private String where;

    public NewEvent(String name, long length, String description, long team_id, String where) {
        this.name = name;
        this.length = length;
        this.description = description;
        this.team_id = team_id;
        this.where = where;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(long team_id) {
        this.team_id = team_id;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
