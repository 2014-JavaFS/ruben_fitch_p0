package com.revature.cra.Registration;

import com.revature.cra.Course.CourseService;
import com.revature.cra.Member.MemberService;
import com.revature.cra.util.Interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class RegistrationController implements Controller {
    private final RegistrationService registrationService;
    private final MemberService memberService;
    private final CourseService courseService;

    public RegistrationController(RegistrationService registrationService, MemberService memberService, CourseService courseService) {
        this.registrationService = registrationService;
        this.memberService = memberService;
        this.courseService = courseService;
    }

    /**
     * registerPaths accepts a Javalin object and is called by the front controller to create the
     * endpoints for the registration HTTP requests.
     *
     * @param app Javalin object.
     */
    @Override
    public void registerPaths(Javalin app) {
        app.post("/registrations", this::postRegisterCourse);
        app.get("/registrations", this::findAllRegistrations);
        app.get("/registrations/my", this::getMemberRegistrations);
        app.delete("/registrations/{registration_id}", this::deleteRegistration);
    }

    /**
     * TODO: Implement
     * @param ctx
     */
    private void deleteRegistration(Context ctx) {

    }

    /**
     *
     * @param ctx
     */
    private void postRegisterCourse(Context ctx) {

    }

    /**
     * findAllRegistrations accepts a Context object before parsing the header to get the memberType attribute to
     * verify that the memberType is set to PROFESSOR. If it is null or not PROFESSOR a 403 Forbidden HTTP status is returned,
     * along with a status message
     * If memberType is PROFESSOR it responds with a call to registrationService.findAll via ctx.json.
     *
     * @param ctx Jackson Context object.
     */
    private void findAllRegistrations(Context ctx) {
        String memberType = ctx.header("memberType");
        if (memberType == null || !memberType.equals("PROFESSOR")){
           ctx.status(403);
           ctx.result("You do not have sufficient permission to perform this action");
           return;
        }
        ctx.json(registrationService.findAll());
    }

    /**
     * getMemberRegistrations accepts a Context object as a parameter and verifies that the member is logged in.
     * If a member is logged in it will then call registrationService.findAllRegistrationsByMemberId to return the courses
     * with that specific memberId via a json response.
     * @param ctx Jackson Context object.
     */
    private void getMemberRegistrations(Context ctx) {
        int memberId = loggedInCheck(ctx);
        if(memberId == -1) return;

        //TODO: implement the registrationService.findAllRegistrationsByMemberId method
        //ctx.json(registrationService.findAllRegistrationsByMemberId(memberId));
    }

    private int loggedInCheck(Context ctx) {
        String headerMemberId = ctx.header("memberId");
        if(headerMemberId == null) {
            ctx.status(400);
            ctx.result("You are not logged in.");
            return -1;
        }
        return Integer.parseInt(headerMemberId);
    }
}
