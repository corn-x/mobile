package cornx.meetly.newevent;

import com.google.gson.JsonElement;

import java.util.Map;

import cornx.meetly.event.Event;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * @author Aleksander Ciepiela
 */
public interface NewEventService {

    @POST("/meetings")
    void postEvent(@Body Map<String, Event> map, Callback<JsonElement> callback);


}
