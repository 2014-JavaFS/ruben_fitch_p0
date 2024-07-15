package com.revature.cra.Course;

import com.revature.cra.util.Interfaces.Serviceable;
import com.revature.cra.util.exceptions.InvalidInputException;

import java.util.List;
import java.util.function.Predicate;

public class CourseService implements Serviceable<Course> {
    private Predicate<String> isNotEmpty = str -> str != null && !str.isBlank();
    //private CourseRepository;

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public Course create(Course newObject) throws InvalidInputException {
        return null;
    }

    @Override
    public Course findById(int number) {
        return null;
    }
}
