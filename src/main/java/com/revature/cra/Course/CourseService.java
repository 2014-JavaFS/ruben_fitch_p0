package com.revature.cra.Course;

import com.revature.cra.util.Interfaces.Serviceable;
import com.revature.cra.util.exceptions.InvalidInputException;

import java.util.List;
import java.util.function.Predicate;

public class CourseService implements Serviceable<Course> {
    private Predicate<String> isNotEmpty = str -> str != null && !str.isBlank();
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    /**
     * TODO: Describe and test
     * @return
     */
    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    /**
     * TODO: Describe and test
     * @param newCourse
     * @return
     * @throws InvalidInputException
     */
    @Override
    public Course create(Course newCourse) throws InvalidInputException {
        return courseRepository.create(newCourse);
    }

    /**
     * TODO: Describe and test
     * @param number
     * @return
     */
    @Override
    public Course findById(int number) {
        return courseRepository.findById(number);
    }
}
