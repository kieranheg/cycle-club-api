package com.muscy.api.member;

import java.util.List;

public interface MemberService {
    List<MemberDao> retrieveAllMembers();
    
    void createMember(MemberDao newMember);
}
