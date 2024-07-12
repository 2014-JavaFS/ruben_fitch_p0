/**
 * Project 0 - Course Registration Application
 * Author: Ruben Fitch
 * Due Date: 07/17/2024
 * Description: Allows users to manage their classes
 */
package com.revature.cra;

import com.revature.cra.Member.Member;
import com.revature.cra.Member.MemberController;
import com.revature.cra.Member.MemberService;
import io.javalin.Javalin;

// FrontController
public class CourseRegistrationRunner {
    public static void main(String[] args) {
        System.out.println("Course Registration Application Running....");
        Javalin app = Javalin.create();
        MemberService memberService = new MemberService();
        MemberController memberController = new MemberController(memberService);
        memberController.registerPaths(app);

        app.start(8080);
    }
}