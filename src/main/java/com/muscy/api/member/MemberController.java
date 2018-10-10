package com.muscy.api.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
public class MemberController {
    
    @Autowired
    MemberService memberService;
    
    @GetMapping("/member")
    public ResponseEntity member() {
        log.info("IN /member");
        
        List<MemberDao> memberDaos = memberService.findAllMembers();
        for (MemberDao memberDao : memberDaos) {
            log.info(memberDao.toString());
        }
        
        return new ResponseEntity(OK);
    }
}
