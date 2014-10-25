package cornx.meetly.events;

/**
 * Created by Mateusz on 2014-10-25.
 */
public abstract class EventsProviderDummy implements EventsProvider {
/*    //TODO provide either all or teams events
    @Override
    public List<Event> getEvents(long teamID) {
        List<Event> list = new ArrayList<>();
        if (teamID == -1) { //if all
            list.add(new Event("First event", "desc 1", 1));
            list.add(new Event("Second event", "desc 2", 2));
            list.add(new Event("Third event", "desc 3", 3));
        } else {
            list.add(new Event("First event for this team", "desc1", 1));
            list.add(new Event("Second event for this team", "desc2", 2));
        }
        return list;
    }

    @Override
    public Event getEvent(long eventId) {
        return new Event("Lol event wow no ID wow", "wow", 10);
        /// this line is so dumb omg sry
    }*/
}
