package com.revature.cra.Member;

import com.revature.cra.util.exceptions.InvalidInputException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.Scanner;

public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public void registerPaths(Javalin app){
        app.post("/members",this::register);
    }

    public void register(Context ctx) throws InvalidInputException {
        // Request
        Member member = ctx.bodyAsClass(Member.class);
        Member.MemberType memberType = Member.MemberType.valueOf("STUDENT");
        member.setType(memberType);
        memberService.create(member);
        // Responses
        ctx.status(HttpStatus.CREATED);
        ctx.result("Successfully registered!");
    }
    //TODO
    public void update(){

    }
}
