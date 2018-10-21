package com.muscy.api.member;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
        // when
        MockHttpServletResponse response = mockMvc.perform(
                put("/member/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMemberDao.write(new MemberDao(1L, "Rob", "Mannon", 50)).getJson()))
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
                get("/member/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).toString().equals((memberDaoList.toString()));
    }
    
    private List<MemberDao> buildMultiMembers() {
        return asList(
                buildMemberDao(1L, "fName2", "lName2", 31),
                buildMemberDao(2L, "fName2", "lName2", 32));
    }
    
    private MemberDao buildMemberDao(final Long id, final String firstName, final String lastName, final int age) {
        return MemberDao.builder().id(id).firstName(firstName).lastName(lastName).age(age).build();
    }
}