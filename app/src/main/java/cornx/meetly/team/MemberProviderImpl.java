package cornx.meetly.team;

import android.util.Log;

import com.squareup.otto.Bus;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Aleksander Ciepiela
 */
public class MemberProviderImpl implements MemberProvider, Callback<List<Member>> {

    private MemberService memberService;
    private Bus bus;

    public MemberProviderImpl(MemberService memberService, Bus bus) {
        this.memberService = memberService;
        this.bus = bus;
    }

    @Override
    public void loadMembers(long teamId) {
        memberService.listMembers(teamId, this);
    }

    @Override
    public void success(List<Member> members, Response response) {
        bus.post(new MembersLoadEvent(members));
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("memberProviderImpl", error.getMessage());
    }
}
