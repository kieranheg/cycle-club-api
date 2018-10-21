package com.muscy.api.member;

import com.muscy.api.member.exception.NonExistingMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberControllerAdvice {
    
    @ExceptionHandler(NonExistingMemberException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void NonExistingMember() {
    }
}