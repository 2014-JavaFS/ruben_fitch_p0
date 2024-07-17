package com.revature.cra.util.Interfaces;

import io.javalin.Javalin;
//import io.javalin.http.Context;

public interface Controller {
    void registerPaths(Javalin app);
}
