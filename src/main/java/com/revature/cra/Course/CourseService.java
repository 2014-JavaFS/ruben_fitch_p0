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

    /**
     * TODO: Implement
     * @param courseToUpdate
     * @return
     */
    public boolean update(Course courseToUpdate) throws InvalidInputException {
        validateCourse(courseToUpdate);
        return courseRepository.update(courseToUpdate);
    }

    private void validateCourse(Course course) throws InvalidInputException {
        if (course == null){
            throw new InvalidInputException("Course is null as it has not been instantiated in memory");
        }
        int courseTempId = course.getCourseId();

        // Test for valid courseId
        if(course.getCourseId() < 0){
            throw new InvalidInputException("Course ID cannot be negative");
        }

        if(course.getCapacity() < 0){
            throw new InvalidInputException("Course capacity cannot be less than 0");
        }
    }
}
