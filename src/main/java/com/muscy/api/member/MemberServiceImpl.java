package com.muscy.api.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    @Override
    public List<MemberDao> findAllMembers() {
        log.info("IN MemberRepositoryImpl findAllMembers()");
        List<MemberDao> memberDaos = new ArrayList<>();
        for (MemberDao memberDao : memberRepository.findAll()) {
            memberDaos.add(memberDao);
            log.info("Found " + memberDao.toString());
        }
        return memberDaos;
    }
    
    @Override
    public void createMember(final MemberDao newMember) {
        log.info("IN MemberRepositoryImpl newMember()");
        memberRepository.save(newMember);
    }
}

