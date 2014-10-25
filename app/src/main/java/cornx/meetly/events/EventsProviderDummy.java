package cornx.meetly.events;

import java.util.ArrayList;
import java.util.List;

import cornx.meetly.event.Event;

/**
 * Created by Mateusz on 2014-10-25.
 */
public class EventsProviderDummy implements EventsProvider{

    @Override
    public List<Event> getEvents() {
        List<Event> list = new ArrayList<>();
        list.add(new Event("First event", "desc 1", 1));
        list.add(new Event("Second event", "desc 2", 2));
        list.add(new Event("Third event", "desc 3", 3));
        return list;
    }

    @Override
    public Event getEvent(long eventId) {
        return new Event("Lol event wow no ID wow", "wow", 10);
        /// this line is so dumb omg sry
    }
}
