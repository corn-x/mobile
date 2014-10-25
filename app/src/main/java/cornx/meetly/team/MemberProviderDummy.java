package cornx.meetly.team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 2014-10-25.
 */
public class MemberProviderDummy implements MemberProvider {

    @Override
    public List<Member> getMembers() {
        List<Member> t = new ArrayList<>();
        t.add(new Member("asd@op.pl", "Grzesiek", "Kowal"));
        t.add(new Member("as@op.pl", "Owczarek", "Mateusz"));
        t.add(new Member("fsd@op.pl", "Kowalski", "Jan"));
        return t;
    }
}

