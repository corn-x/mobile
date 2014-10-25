package cornx.meetly.newevent;

import cornx.meetly.event.Event;

/**
 * Created by Dawid on 25/10/2014.
 */
public class NewEvent extends Event {
    private Long length;

    public NewEvent(String name, String description, long id, Long length) {
        super(name, description, id); //id - TeamID
        this.length = length;
    }
}
