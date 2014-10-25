package cornx.meetly.event;

import android.util.Log;

import com.squareup.otto.Bus;

import java.util.List;

import cornx.meetly.events.EventsLoadEvent;
import cornx.meetly.events.EventsProvider;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Aleksander Ciepiela
 */
public class EventProviderImpl implements EventsProvider, Callback<List<Event>> {

    private static final String TAG = "EventProviderImpl";

    private Bus bus;
    private EventService eventService;

    public EventProviderImpl(Bus bus, EventService eventService) {
        this.bus = bus;
        this.eventService = eventService;
    }

    @Override
    public void loadEvents(long teamID) {
        eventService.listEventForTeam(teamID, this);
    }

    @Override
    public void loadEvent(long eventId) {
        eventService.listEvent(eventId, this);
    }

    @Override
    public void loadAllEventsForUser() {
        eventService.listAllEventsForUser(this);
    }

    @Override
    public void success(List<Event> events, Response response) {
        bus.post(new EventsLoadEvent(events));
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d(TAG, error.toString());
    }
}
