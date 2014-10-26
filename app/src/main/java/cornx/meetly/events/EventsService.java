package cornx.meetly.events;

import java.util.List;

import cornx.meetly.event.Event;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author Aleksander Ciepiela
 */
public interface EventsService {

    @GET("/teams/{id}/meetings")
    void listEventForTeam(@Path("id") long teamId, Callback<List<Event>> eventCallback);

    @GET("/meetings/my")
    void listAllEventsForUser(Callback<List<Event>> eventCallback);
}
