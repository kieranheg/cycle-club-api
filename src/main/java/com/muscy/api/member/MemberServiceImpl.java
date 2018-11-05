package com.muscy.api.member;

import com.muscy.api.member.exception.NonExistingMemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @PersistenceContext
    private EntityManager entityManager;
    
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
    
    @Override
    public MemberDao updateMember(final MemberDao updatedMemberDetails) {
        log.info("IN MemberRepositoryImpl updateMember()");
        
        Optional<MemberDao> existingMember = memberRepository.findById(updatedMemberDetails.getId());
        if (existingMember.isPresent()) {
            MemberDao memberToSave = merge(existingMember.get(), updatedMemberDetails);
            return memberRepository.save(memberToSave);
        } else {
            throw new NonExistingMemberException();
        }
    }
    
    private MemberDao merge(final MemberDao existing, final MemberDao updated) {
        MemberDao memberToSave = existing;
        memberToSave.setFirstName(getMemberFieldValueToSave(existing.getFirstName(), updated.getFirstName()));
        memberToSave.setLastName(getMemberFieldValueToSave(existing.getLastName(), updated.getLastName()));
        memberToSave.setAge(getMemberFieldValueToSave(existing.getAge(), updated.getAge()));
        return memberToSave;
    }
    
    private String getMemberFieldValueToSave(final String existingField, final String updatedField) {
        return updatedField == null ? existingField : updatedField;
    }
    
    private int getMemberFieldValueToSave(final int existingField, final int updatedField) {
        return updatedField == 0 ? existingField : updatedField;
    }
}

