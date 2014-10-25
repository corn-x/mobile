package cornx.meetly.events;

import java.util.List;

import cornx.meetly.event.Event;

/**
 * Created by Mateusz on 2014-10-25.
 */
public class EventsLoadEvent {
    private List<Event> eventList;

    public EventsLoadEvent(List<Event> memberList) {
        this.eventList = memberList;
    }

    public List<Event> getEventList() {
        return eventList;
    }
}
