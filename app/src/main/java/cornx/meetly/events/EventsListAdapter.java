package cornx.meetly.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cornx.meetly.R;
import cornx.meetly.event.Event;

/**
 * Created by Mateusz on 2014-10-25.
 */
public class EventsListAdapter extends BaseAdapter {
    private List<Event> events;
    private Context context;
    private LayoutInflater layoutInflater;

    public EventsListAdapter(Context context) {
        this.context = context.getApplicationContext();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setEvents(List<Event> e) {
        events = e;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return events == null ? 0 : events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return events.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.row_fragment_events, parent, false);
        }
        Event event = events.get(position);
        TextView textView = (TextView) view.findViewById(R.id.row_fragment_events_eventname);
        textView.setText(event.getName());
        return view;
    }
}
