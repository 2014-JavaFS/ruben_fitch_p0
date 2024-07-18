package com.revature.cra.util.auth;

import com.revature.cra.Member.Member;
import com.revature.cra.util.Interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import javax.security.sasl.AuthenticationException;

public class AuthController implements Controller {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @Override
    public void registerPaths(Javalin app) {
        app.post("/login", this::postlogin);
        app.post("/user-info", this::getRedirect);
    }

    private void postlogin(Context ctx) {
        int memberId = Integer.parseInt(ctx.queryParam("member_id"));
        String password = ctx.queryParam("password");

        try {
            Member member = authService.login(memberId, password);
            ctx.header("memberId", String.valueOf(member.getMemberId()));
            ctx.header("memberType", member.getType().name());
            ctx.status(200);
        } catch (AuthenticationException e) {
            ctx.status(HttpStatus.UNAUTHORIZED);
        }
    }

    private void getRedirect(Context ctx) {
        ctx.redirect("https://media1.tenor.com/m/j6hWHMxX3k8AAAAC/grumpy-cat-meme.gif");
    }
}
