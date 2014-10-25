package cornx.meetly.team;

import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 2014-10-25.
 */
public class MemberProviderDummy implements MemberProvider {

    private Bus bus;

    public MemberProviderDummy(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void loadMembers() {
        List<Member> t = new ArrayList<>();
        t.add(new Member("asd@op.pl", "Grzesiek", "Kowal"));
        t.add(new Member("as@op.pl", "Owczarek", "Mateusz"));
        t.add(new Member("fsd@op.pl", "Kowalski", "Jan"));
        bus.post(new MembersLoadEvent(t));
    }
}

