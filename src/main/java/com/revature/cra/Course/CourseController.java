package com.revature.cra.Course;

import com.revature.cra.util.Interfaces.Controller;
import com.revature.cra.util.exceptions.DataNotFoundException;
import com.revature.cra.util.exceptions.InvalidInputException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;
import java.util.function.Predicate;

public class CourseController implements Controller {
    private final CourseService courseService;

    private Predicate<String> isNotEmpty = str -> str != null && !str.isBlank();

    // Constructors - Dependency Injection - any dependent objects are provided at initialization
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    // One of the principles of REST is uniform interface, simply means that the PATH in your URL makes sense given the context
    // of what that path is handling
    @Override
    public void registerPaths(Javalin app) {
        app.get("/courses", this::getAllCourses);
        app.post("/courses", this::postNewCourses);
        app.get("/courses/{courseNumber}", this::getCourseById); // Path Parameter
        app.put("courses", this::putUpdateCourse);
    }

    public void getAllCourses(Context ctx){
        List<Course> courses = courseService.findAll();
        ctx.json(courses);
    }

    private void postNewCourses(Context ctx) throws InvalidInputException {
        String memberType = ctx.header("memberType");
        System.out.println(memberType);
        if (memberType == null || !memberType.equals("PROFESSOR")){
            ctx.status(403);
            ctx.result("You do not have sufficient permission to perform this action, as you are not logged in as an Admin");
            return;
        }

        Course course = ctx.bodyAsClass(Course.class);
        ctx.json(courseService.create(course));
        ctx.status(HttpStatus.CREATED);
    }

    private void getCourseById(Context ctx) {
        int courseId = Integer.parseInt(ctx.pathParam("courseId"));

        try {
            Course foundCourse = courseService.findById(courseId);
            ctx.json(foundCourse);
        } catch (DataNotFoundException e){
            e.printStackTrace();
            ctx.status(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e){
            e.printStackTrace();
            ctx.status(500);
        }
    }

    private void putUpdateCourse(Context ctx) {
        Course updatedCourse = ctx.bodyAsClass(Course.class);

        try {
            if (courseService.update(updatedCourse)){
                ctx.status(HttpStatus.ACCEPTED);
            } else {
                ctx.status(HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e){
            e.printStackTrace();
        }
    }







}
