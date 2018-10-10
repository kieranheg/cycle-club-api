package com.muscy.api.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    
    @Override
    public List<MemberDao> findAllMembers() {
        log.info("IN MemberRepositoryImpl findAllMembers()");
        List<MemberDao> memberDaos = new ArrayList<>();
        for (MemberDao memberDao : memberRepository.findAll()) {
            memberDaos.add(memberDao);
            log.info(memberDao.toString());
        }
        return memberDaos;
    }
}

