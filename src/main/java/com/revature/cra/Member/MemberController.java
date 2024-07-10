package com.revature.cra.Member;

import com.revature.cra.util.exceptions.InvalidInputException;

import java.util.Scanner;

public class MemberController {
    private final Scanner scanner;
    private final MemberService memberService;
    private int count = 0;

    public MemberController(Scanner scanner, MemberService memberService) {
        this.scanner = scanner;
        this.memberService = memberService;
    }

    public void register() throws InvalidInputException {
        System.out.println("Please enter your first name: ");
        String firstName = scanner.next();

        System.out.println("Please enter your last name: ");
        String lastName = scanner.next();

        Member.MemberType memberType = Member.MemberType.valueOf("STUDENT");

        System.out.println("Please enter your password: ");
        String password = scanner.next();

        Member member = new Member(count, firstName, lastName, memberType, password);
        memberService.create(member);
        count++;
    }
    //TODO
    public void update(){

    }
}
