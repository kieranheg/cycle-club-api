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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
                        .content(jsonMemberDao.write(new MemberDao(1L, "Rob", "Mannon", 50)).getJson())
        ).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
}