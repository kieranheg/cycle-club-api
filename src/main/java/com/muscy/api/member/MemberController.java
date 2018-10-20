package com.muscy.api.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
public class MemberController {
    
    @Autowired
    MemberService memberService;
    
    @GetMapping("/member")
    public ResponseEntity member() {
        log.info(">> in GET members");
        return new ResponseEntity<Object>(memberService.findAllMembers(), HttpStatus.OK);
    }
    
    @PutMapping("/member")
    public ResponseEntity createNewMember(@RequestBody MemberDao newMember) {
        log.info(">> in PUT member");
        memberService.createMember(newMember);
        return new ResponseEntity(OK);
    }
}
// TODO error logging framework
// TODO Add tests