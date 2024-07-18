/**
 * Project 0 - Course Registration Application
 * Author: Ruben Fitch
 * Due Date: 07/17/2024
 * Description: Allows users to manage their classes
 */
package com.revature.cra;

import com.revature.cra.Course.CourseController;
import com.revature.cra.Course.CourseRepository;
import com.revature.cra.Course.CourseService;
import com.revature.cra.Member.Member;
import com.revature.cra.Member.MemberController;
import com.revature.cra.Member.MemberRepository;
import com.revature.cra.Member.MemberService;
import com.revature.cra.util.auth.AuthController;
import com.revature.cra.util.auth.AuthService;
import io.javalin.Javalin;

// FrontController
public class CourseRegistrationRunner {
    public static void main(String[] args) {
        System.out.println("Course Registration Application Running....");
        Javalin app = Javalin.create();
        MemberRepository memberRepository = new MemberRepository();
        MemberService memberService = new MemberService(memberRepository);
        MemberController memberController = new MemberController(memberService);
        memberController.registerPaths(app);

        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository);
        CourseController courseController = new CourseController(courseService);
        courseController.registerPaths(app);

        AuthService authService = new AuthService(memberService);
        AuthController authController = new AuthController(authService);
        authController.registerPaths(app);

        app.start(8080);
    }
}