package longah;

import java.util.ArrayList;
import java.util.List;

public class GroupList {
    private List<Member> members;

    public GroupList() {
        this.members = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public List<Member> getMembers() {
        return members;
    }
}



