package cornx.meetly.events;

import android.util.Log;

import com.squareup.otto.Bus;

import java.util.List;

import cornx.meetly.event.Event;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Aleksander Ciepiela
 */
public class EventsProviderImpl implements EventsProvider, Callback<List<Event>> {

    private static final String TAG = "EventProviderImpl";

    private Bus bus;
    private EventsService eventsService;

    public EventsProviderImpl(Bus bus, EventsService eventsService) {
        this.bus = bus;
        this.eventsService = eventsService;
    }

    @Override
    public void loadEvents(long teamID) {
        eventsService.listEventForTeam(teamID, this);
    }

    @Override
    public void loadAllEventsForUser() {
        eventsService.listAllEventsForUser(this);
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
