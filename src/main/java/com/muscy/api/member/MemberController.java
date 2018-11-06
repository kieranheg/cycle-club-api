package com.muscy.api.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class MemberController {
    private final MemberService memberService;
    
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @RequestMapping(value="/members", method = RequestMethod.GET)
    public List<MemberDao> getAllMembers() {
        log.info(">> in getAllMembers");
        return memberService.retrieveAllMembers();
    }
    
    @GetMapping("/member/{id}")
    public Optional<MemberDao> getMemberWithId(@PathVariable Long id) {
        log.info(">> in getMemberWithId");
        return memberService.getMember(id);
    }
    
    @RequestMapping(value="/member", method = RequestMethod.GET)
    public Optional<MemberDao> getMemberWithLastName(@RequestParam("lastname") String lastName) {
        log.info(">> in getMemberWithLastName");
        return memberService.getMember(lastName);
    }
    
    @RequestMapping(value="/member", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewMember(@RequestBody MemberDao newMember) {
        log.info(">> in POST member");
        memberService.createMember(newMember);
    }
    
    @RequestMapping(value="/member", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateMember(@RequestBody MemberDao updatedMemberDetails) {
        log.info(">> in PUT member");
        memberService.updateMember(updatedMemberDetails);
    }
}
// TODO Add weather service