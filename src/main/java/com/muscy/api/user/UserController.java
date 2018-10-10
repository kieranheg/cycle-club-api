package com.muscy.api.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
public class UserController {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @GetMapping("/user")
    public ResponseEntity users() {
        log.info("IN /user");
    
        for (MemberDao memberDao : memberRepository.findAll()) {
            log.info(memberDao.toString());
        }
        
        return new ResponseEntity(OK);
    }
}
