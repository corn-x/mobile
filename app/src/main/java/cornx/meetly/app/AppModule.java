package cornx.meetly.app;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import cornx.meetly.team.MemberProvider;
import cornx.meetly.team.MemberProviderDummy;
import cornx.meetly.team.TeamFragment;
import cornx.meetly.team.TeamProvider;
import cornx.meetly.team.TeamProviderDummy;
import cornx.meetly.team.TeamProviderImpl;
import cornx.meetly.team.TeamService;
import cornx.meetly.team.TeamsFragment;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * @author Aleksander Ciepiela
 */

@Module(
        library = true,
        injects = {MeetlyApplication.class, TeamsFragment.class, TeamProviderDummy.class,
                TeamFragment.class, TeamProviderImpl.class}
)


public class AppModule {

    private static final String SERVER = "http://";
    private MeetlyApplication meetlyApplication;
    private RestAdapter restAdapter;

    public AppModule(MeetlyApplication meetlyApplication) {
        this.meetlyApplication = meetlyApplication;
        restAdapter = new RestAdapter.Builder().setEndpoint(SERVER).build();
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
    public TeamProvider provideTeamProvider(TeamService teamService, Bus bus) {
        /*return new TeamProviderDummy(bus);*/
        return new TeamProviderImpl(teamService, bus);
    }

    @Provides
    @Singleton
    public MemberProvider provideMemberProvider(Bus bus) {
        return new MemberProviderDummy(bus);
    }

    @Provides
    @Singleton
    public TeamService provideTeamService() {
        return restAdapter.create(TeamService.class);
    }
}
