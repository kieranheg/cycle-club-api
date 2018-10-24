package com.muscy.api.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    @Override
    public List<MemberDao> retrieveAllMembers() {
        log.info("IN MemberRepositoryImpl retrieveAllMembers()");
        List<MemberDao> memberDaos = new ArrayList<>();
        for (MemberDao memberDao : memberRepository.findAll()) {
            memberDaos.add(memberDao);
            log.info("Found " + memberDao.toString());
        }
        return memberDaos;
    }
    
    @Override
    public MemberDao createMember(final MemberDao newMember) {
        log.info("IN MemberRepositoryImpl newMember()");
        return memberRepository.save(newMember);
    }
    
    @Override
    public Optional<MemberDao> getMember(final String lastName) {
        return memberRepository.findByLastName(lastName);
    }
    
    @Override
    public Optional<MemberDao> getMember(final Long id) {
        return memberRepository.findById(id);
    }
}

