package com.revature.cra.util.auth;

import com.revature.cra.Member.Member;
import com.revature.cra.Member.MemberService;

import javax.security.sasl.AuthenticationException;

/**
 * Authentication Service to check our member database for matching information based on users input. Separated for
 * security concerns. REQUIRES MemberService to be injected.
 */
public class AuthService {
    private final MemberService memberService;

    public AuthService(MemberService memberService){
        this.memberService = memberService;
    }

    public Member login(int memberId, String password) throws AuthenticationException {
        Member member = memberService.findByMemberIdAndPassword(memberId, password);
        if (member == null) throw new AuthenticationException("Invalid member credentials, please try again.");
        return member;
    }
}
