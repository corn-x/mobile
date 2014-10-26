package cornx.meetly.event;

/**
 * Created by Dawid on 25/10/2014.
 */
public class Event {

    private long id;
    public static final String tid = "teamID";
    private String name;
    private String description;
    private String creator;
    private String where;
    private String start_time;
    private String end_time;
    private boolean scheduled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }


    public Event(String name, String description, long id) {
        this.description = description;
        this.name = name;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Start: " + start_time + "\nEnd: " + end_time + "\nDescription:\n" + description + "\nScheduled: " + (isScheduled() ? "YES" : "NO") + "\n";
    }
}
