package cornx.meetly.events;

import java.util.List;

import cornx.meetly.event.Event;

/**
 * Created by Mateusz on 2014-10-25.
 */
public interface EventsProvider {
    public void loadEvents();

    public void loadEvent(long eventId);
}
