package com.muscy.api.member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<MemberDao> retrieveAllMembers();
    
    MemberDao createMember(MemberDao newMember);
    
    Optional<MemberDao> getMember(String lastName);
    
    Optional<MemberDao> getMember(Long id);
}
