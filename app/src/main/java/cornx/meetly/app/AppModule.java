package cornx.meetly.app;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import cornx.meetly.event.EventFragment;
import cornx.meetly.event.EventProviderDummy;
import cornx.meetly.events.EventsFragment;
import cornx.meetly.events.EventsProvider;
import cornx.meetly.events.EventsProviderDummy;
import cornx.meetly.team.MemberProvider;
import cornx.meetly.team.MemberProviderDummy;
import cornx.meetly.team.TeamFragment;
import cornx.meetly.team.TeamProvider;
import cornx.meetly.team.TeamProviderDummy;
import cornx.meetly.team.TeamsFragment;
import dagger.Module;
import dagger.Provides;

/**
 * @author Aleksander Ciepiela
 */

@Module(
        library = true,
        injects = {MeetlyApplication.class, TeamsFragment.class, TeamProviderDummy.class,
                TeamFragment.class, EventsFragment.class, EventProviderDummy.class}
)


public class AppModule {

    private MeetlyApplication meetlyApplication;

    public AppModule(MeetlyApplication meetlyApplication) {
        this.meetlyApplication = meetlyApplication;
    }

    @Provides
    public Context provideApplicationContext() {
        return meetlyApplication;
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    public TeamProvider provideTeamProvider(Bus bus) {
        return new TeamProviderDummy(bus);
    }

    @Provides
    @Singleton
    public EventsProvider provideEventsProvider(Bus bus) {
        return new EventsProviderDummy(bus);
    }

    @Provides
    @Singleton
    public MemberProvider provideMemberProvider(Bus bus) {
        return new MemberProviderDummy(bus);
    }
}
