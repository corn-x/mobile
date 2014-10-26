package cornx.meetly.event;

import android.util.Log;

import com.squareup.otto.Bus;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Aleksander Ciepiela
 */
public class EventProviderImpl implements EventProvider, Callback<Event> {

    private EventService eventService;
    private Bus bus;

    public EventProviderImpl(EventService eventService, Bus bus) {
        this.eventService = eventService;
        this.bus = bus;
    }

    @Override
    public void loadEvent(long eventId) {
        eventService.listEvent(eventId, this);
    }

    @Override
    public void success(Event event, Response response) {
        bus.post(event);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("eventProvider", error.toString());
    }
}
