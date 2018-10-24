package com.muscy.api.unit.member;

import com.muscy.api.helpers.MemberTestUtilities;
import com.muscy.api.member.MemberDao;
import com.muscy.api.member.MemberService;
import com.muscy.api.member.exception.NonExistingMemberException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberController_SpringBootTest_UT {
    @MockBean
    private MemberService memberService;
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void canCreateANewSuperHero() {
        // when
        ResponseEntity<MemberDao> memberDaoResponse = restTemplate.postForEntity("/member/",
                new MemberDao(1L, "Rob", "Mannon", 23), MemberDao.class);
        // then
        assertThat(memberDaoResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
    
    @Test
    public void canRetrieveByIdWhenExists() {
        MemberDao testMemberDao = MemberTestUtilities.buildMemberDao(2L, "Rob", "RobotMan", 25);
        // given
        given(memberService.getMember(2L))
                .willReturn(Optional.of(testMemberDao));
        // when
        ResponseEntity<MemberDao> memberDaoResponse = restTemplate.
                getForEntity("/member/2", MemberDao.class);
        // then
        assertThat(memberDaoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(memberDaoResponse.getBody().equals(testMemberDao));
    }
    
    @Test
    public void canRetrieveByIdWhenDoesNotExist() {
        // given
        given(memberService.getMember(2L))
                .willThrow(new NonExistingMemberException());
        
        // when
        ResponseEntity<MemberDao> memberDaoResponse = restTemplate.getForEntity("/member/2", MemberDao.class);
        
        // then
        assertThat(memberDaoResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(memberDaoResponse.getBody()).isNull();
    }
    
    @Test
    public void canRetrieveByNameWhenExists() {
        MemberDao testMemberDao = MemberTestUtilities.buildMemberDao(1L, "Rob", "RobotMan", 25);
        // given
        given(memberService.getMember("RobotMan"))
                .willReturn(Optional.of(testMemberDao));
        // when
        ResponseEntity<MemberDao> memberDaoResponse = restTemplate
                .getForEntity("/member/?lastname=RobotMan", MemberDao.class);
        // then
        assertThat(memberDaoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(memberDaoResponse.getBody().equals(testMemberDao));
    }
    
    @Test
    public void canRetrieveByNameWhenDoesNotExist() {
        // given
        given(memberService.getMember("RobotMan"))
                .willReturn(Optional.empty());;
        // when
        ResponseEntity<MemberDao> memberDaoResponse = restTemplate
                .getForEntity("/member/?lastname=RobotMan", MemberDao.class);
        // then
        assertThat(memberDaoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(memberDaoResponse.getBody()).isNull();
    }
    
}
