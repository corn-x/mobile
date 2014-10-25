package cornx.meetly.app;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

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
        injects = {MeetlyApplication.class, TeamsFragment.class, TeamProviderDummy.class}
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
}
