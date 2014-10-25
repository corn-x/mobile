package cornx.meetly.event;

/**
 * Created by Dawid on 25/10/2014.
 */
public class EventProviderDummy implements EventProvider {

    @Override
    public Event getEvent(long id) {
        return new Event("Example event", "42", id);
    }
}
