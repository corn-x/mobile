package cornx.meetly.events;

/**
 * Created by Mateusz on 2014-10-25.
 */
public interface EventsProvider {
    public void loadEvents(long teamID);

    public void loadEvent(long eventId);

    public void loadAllEventsForUser();
}
