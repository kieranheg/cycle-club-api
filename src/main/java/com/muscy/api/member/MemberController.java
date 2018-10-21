package com.muscy.api.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value="/member")
public class MemberController {
    
    private final MemberService memberService;
    
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity member() {
        log.info(">> in GET members");
        return new ResponseEntity<Object>(memberService.findAllMembers(), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewMember(@RequestBody MemberDao newMember) {
        log.info(">> in PUT member");
        memberService.createMember(newMember);
    }
}
// TODO error logging framework
// TODO Add tests -see https://thepracticaldeveloper.com/2017/07/31/guide-spring-boot-controller-tests/
// TODO get member/{id}
// TODO Add auditable
// TODO Add weather service