package com.muscy.api.member;

import java.util.List;

public interface MemberService {
    List<MemberDao> findAllMembers();
    
    void createMember(MemberDao newMember);
}
