package cornx.meetly.team;

import java.util.List;

/**
 * @author Aleksander Ciepiela
 */
public class MembersLoadEvent {

    private List<Member> memberList;

    public MembersLoadEvent(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }
}
