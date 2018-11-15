package com.muscy.api.unit.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muscy.api.member.MemberController;
import com.muscy.api.member.MemberDao;
import com.muscy.api.member.MemberExceptionHandler;
import com.muscy.api.member.MemberService;
import com.muscy.api.member.exception.NonExistingMemberException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static com.muscy.api.helpers.MemberTestUtilities.buildMemberDao;
import static com.muscy.api.helpers.MemberTestUtilities.buildMultiMembers;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberControllerMvc_Standalone_UT {
    private MockMvc mockMvc;  // standalone tests
    @Mock
    private MemberService memberService;
    @InjectMocks
    private MemberController memberController;
    
    // This object will be magically initialized by the initFields method below.
    private JacksonTester<MemberDao> jsonMemberDao;
    
    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mockMvc = MockMvcBuilders.standaloneSetup(memberController)
                .setControllerAdvice(new MemberExceptionHandler())
//                .addFilters(new SuperHeroFilter())
                .build();
    }
    
    @Test
    public void canCreateNewMember() throws Exception {
        MemberDao testMemberDao = buildMemberDao(1L, "Rob", "RobotMan", 25);
        // when
        MockHttpServletResponse response = mockMvc.perform(
                post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMemberDao.write(testMemberDao).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
    
    @Test
    public void canRetrieveAllMembers() throws Exception {
        final List<MemberDao> memberDaoList = buildMultiMembers();
        // given
        given(memberService.retrieveAllMembers())
                .willReturn(memberDaoList);
        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/members")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).toString().equals((memberDaoList.toString()));
    }
    
    @Test
    public void canRetrieveByLastNameWhenExists() throws Exception {
        MemberDao testMemberDao = buildMemberDao(1L, "Rob", "RobotMan", 25);
        // given
        given(memberService.getMember("RobotMan"))
                .willReturn(Optional.of(testMemberDao));
        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/member?lastname=RobotMan")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonMemberDao.write(testMemberDao).getJson());
    }
    
    @Test
    public void canRetrieveByLastNameWhenDoesNotExist() throws Exception {
        // given
        given(memberService.getMember("RobotMan"))
                .willThrow(new NonExistingMemberException());
        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/member?lastname=RobotMan")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }
    
    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        MemberDao testMemberDao = buildMemberDao(2L, "Rob", "RobotMan", 25);
        // given
        given(memberService.getMember(2L))
                .willReturn(Optional.of(testMemberDao));
        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/member/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonMemberDao.write(testMemberDao).getJson());
    }
    
    @Test
    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
        // given
        given(memberService.getMember(2L))
                .willThrow(new NonExistingMemberException());
        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/member/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }
    
    @Test
    public void canUpdateMemberWhenExists() throws Exception {
        MemberDao testMemberDao = buildMemberDao(2L, "Robbiex", "RobotMan", 25);
        // given
        given(memberService.updateMember(testMemberDao))
                .willReturn(testMemberDao);
        // when
        MockHttpServletResponse response = mockMvc.perform(
                put("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMemberDao.write(testMemberDao).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
