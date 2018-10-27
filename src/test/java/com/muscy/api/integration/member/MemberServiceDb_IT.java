package com.muscy.api.integration.member;

import com.muscy.api.member.MemberDao;
import com.muscy.api.member.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.muscy.api.helpers.MemberTestUtilities.buildMemberDao;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceDb_IT {
    
    
    @Autowired
    MemberService memberService;
    
    @Rollback()
    @Test
    public void saveShouldPersistedData() throws InterruptedException {
        final String JAMES = "James";
        final String BOND = "Bond";
        final int AGE_26 = 26;
        
        MemberDao savedMember = memberService.createMember(buildMemberDao(JAMES, BOND, AGE_26));
        assertThat(savedMember).isNotNull();
        assertThat(savedMember.getFirstName()).isEqualToIgnoringCase(JAMES);
        assertThat(savedMember.getLastName()).isEqualToIgnoringCase(BOND);
        assertThat(savedMember.getAge()).isEqualTo(AGE_26);
        assertThat(savedMember.getCreatedDate().toInstant());
    }
    
    @Test
    public void retrieveShouldGetMemberWithId() {
        final String ERNST = "Ernst";
        final String BLOFELD = "Blofeld";
        final int AGE_40 = 40;
        
        MemberDao savedMember = memberService.createMember(buildMemberDao(ERNST, BLOFELD, AGE_40));
        assertThat(savedMember).isNotNull();
        assertThat(savedMember.getFirstName()).isEqualToIgnoringCase(ERNST);
        assertThat(savedMember.getLastName()).isEqualToIgnoringCase(BLOFELD);
        assertThat(savedMember.getAge()).isEqualTo(AGE_40);
        
        Optional<MemberDao> retrievedMember = memberService.getMember(savedMember.getId());
        assertThat(retrievedMember.isPresent()).isNotNull();
        assertThat(retrievedMember.get().getFirstName()).isEqualToIgnoringCase(ERNST);
        assertThat(retrievedMember.get().getLastName()).isEqualToIgnoringCase(BLOFELD);
        assertThat(retrievedMember.get().getAge()).isEqualTo(AGE_40);
        assertThat(savedMember.getCreatedDate().toInstant());
    }
    
    @Rollback()
    @Test
    public void retrieveShouldGetMemberWithLastName() {
        final String FRANCISCO = "Francisco";
        final String SCARAMANGA = "Scaramanga";
        final int AGE_55 = 55;
        
        MemberDao savedMember = memberService.createMember(buildMemberDao(FRANCISCO, SCARAMANGA, AGE_55));
        assertThat(savedMember).isNotNull();
        assertThat(savedMember.getFirstName()).isEqualToIgnoringCase(FRANCISCO);
        assertThat(savedMember.getLastName()).isEqualToIgnoringCase(SCARAMANGA);
        assertThat(savedMember.getAge()).isEqualTo(AGE_55);
        
        Optional<MemberDao> retrievedMember = memberService.getMember(SCARAMANGA);
        assertThat(retrievedMember.isPresent()).isNotNull();
        assertThat(retrievedMember.get().getFirstName()).isEqualToIgnoringCase(FRANCISCO);
        assertThat(retrievedMember.get().getLastName()).isEqualToIgnoringCase(SCARAMANGA);
        assertThat(retrievedMember.get().getAge()).isEqualTo(AGE_55);
        assertThat(savedMember.getCreatedDate().toInstant());  
    }
}

