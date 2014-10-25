package cornx.meetly.event;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author Aleksander Ciepiela
 */
public interface EventService {

    @GET("/teams/{id}/meetings")
    void listEventForTeam(@Path("id") long teamId, Callback<List<Event>> eventCallback);

    @GET("/meetings/my")
    void listAllEventsForUser(Callback<List<Event>> eventCallback);

    @GET("meetings/{id}")
    void listEvent(@Path("id") long eventId, Callback<List<Event>> eventCallback);

}
