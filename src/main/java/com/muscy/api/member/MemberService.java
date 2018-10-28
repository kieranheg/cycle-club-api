package com.muscy.api.member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<MemberDao> retrieveAllMembers();
    
    MemberDao createMember(final MemberDao newMember);
    
    Optional<MemberDao> getMember(final String lastName);
    
    Optional<MemberDao> getMember(final Long id);
    
    MemberDao updateMember(final MemberDao updatedMember);
}
