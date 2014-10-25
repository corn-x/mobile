package cornx.meetly.event;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author Aleksander Ciepiela
 */
public interface EventService {


    @GET("/meetings/{id}")
    void listEvent(@Path("id") long eventId, Callback<Event> eventCallback);


}
