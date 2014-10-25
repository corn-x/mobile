package cornx.meetly.event;

/**
 * Created by Dawid on 25/10/2014.
 */
public class Event {
    private String name, description;
    private long id;

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
}
