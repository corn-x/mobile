package cornx.meetly.app;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import cornx.meetly.addteam.AddTeamActivity;
import cornx.meetly.addteam.AddTeamService;
import cornx.meetly.event.EventFragment;
import cornx.meetly.event.EventProviderImpl;
import cornx.meetly.event.EventService;
import cornx.meetly.events.EventsFragment;
import cornx.meetly.events.EventsProvider;
import cornx.meetly.team.MemberProvider;
import cornx.meetly.team.MemberProviderImpl;
import cornx.meetly.team.MemberService;
import cornx.meetly.team.TeamFragment;
import cornx.meetly.team.TeamProvider;
import cornx.meetly.team.TeamProviderDummy;
import cornx.meetly.team.TeamProviderImpl;
import cornx.meetly.team.TeamService;
import cornx.meetly.team.TeamsFragment;
import dagger.Module;
import dagger.Provides;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * @author Aleksander Ciepiela
 */

@Module(
        library = true,
        injects = {MeetlyApplication.class, TeamsFragment.class, TeamProviderDummy.class,
                TeamFragment.class, TeamProviderImpl.class, MemberProviderImpl.class,
                EventFragment.class, EventsFragment.class, AddTeamActivity.class}
)


public class AppModule {

    public static final String SERVER = "http://mints.ukasiu.pl:3000/api/v1";
    public static String auth;
    private MeetlyApplication meetlyApplication;
    private RestAdapter restAdapter;

    public AppModule(MeetlyApplication meetlyApplication) {
        this.meetlyApplication = meetlyApplication;
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("authentication_token", auth);
            }
        };
        restAdapter = new RestAdapter.Builder().setEndpoint(SERVER)
                .setRequestInterceptor(requestInterceptor).build();
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
    public MemberProvider provideMemberProvider(MemberService memberService, Bus bus) {
        return new MemberProviderImpl(memberService, bus);
    }

    @Provides
    @Singleton
    public TeamService provideTeamService() {
        return restAdapter.create(TeamService.class);
    }

    @Provides
    @Singleton
    public MemberService provideMemberService() {
        return restAdapter.create(MemberService.class);
    }

    @Provides
    @Singleton
    public EventService provideEventService() {
        return restAdapter.create(EventService.class);
    }

    @Provides
    @Singleton
    public EventsProvider provideEventsProvider(EventService eventService, Bus bus) {
        return new EventProviderImpl(bus, eventService);
    }

    @Provides
    @Singleton
    public AddTeamService provideAddTeamService() {
        return restAdapter.create(AddTeamService.class);
    }
}
