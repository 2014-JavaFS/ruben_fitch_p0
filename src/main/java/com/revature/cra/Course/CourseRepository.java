package com.revature.cra.Course;

import com.revature.cra.util.ConnectionFactory;
import com.revature.cra.util.Interfaces.Crudable;
import com.revature.cra.util.exceptions.InvalidInputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Course repository follows the Data Access Object (DAO) pattern
 */
public class CourseRepository implements Crudable<Course> {

    /**
     * Updates a course's information in the database.
     * @param updatedCourse the course to be updated
     * @return true if the update was successful, otherwise false
     */
    @Override
    public boolean update(Course updatedCourse) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "UPDATE courses SET subject = ?, course_number = ?, course_name = ?, professor = ?, description = ?, capacity = ?, num_registered = ? WHERE course_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, updatedCourse.getSubject());
            preparedStatement.setShort(2, updatedCourse.getCourseNumber());
            preparedStatement.setString(3, updatedCourse.getCourseName());
            preparedStatement.setString(4, updatedCourse.getProfessor());
            preparedStatement.setString(5, updatedCourse.getDescription());
            preparedStatement.setShort(6, updatedCourse.getCapacity());
            preparedStatement.setShort(7, updatedCourse.getNumRegistered());
            preparedStatement.setInt(8, updatedCourse.getCourseId());

            int checkUpdate = preparedStatement.executeUpdate();
            System.out.println("Updating information.....");
            if (checkUpdate == 0){
                throw new RuntimeException("Course record was not updated.");
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Depending on user input for which course to be deleted, delete the course from the database.
     * @param removedCourse
     * @return true if course was deleted, otherwise false.
     */
    @Override
    public boolean delete(Course removedCourse) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql  = "DELETE FROM courses WHERE course_id = ?";
            PreparedStatement preparedStatement =  conn.prepareStatement(sql);

            preparedStatement.setInt(1, removedCourse.getCourseId());
            boolean checkDelete = preparedStatement.executeUpdate() == 1;
            if (checkDelete){
                System.out.println("Course record was deleted.");
            } else {
                System.out.println("Course record was not deleted.");
            }
            return checkDelete;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * TODO: Description
     * @return
     */
    @Override
    public List<Course> findAll() {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Course> courses = new ArrayList<>();
            String sql = "select * from courses";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                courses.add(generateCourseFromResultSet(rs));
            }

            return courses;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * TODO: Description
     * @param newObject
     * @return
     * @throws InvalidInputException
     */
    @Override
    public Course create(Course newObject) throws InvalidInputException {
        return null;
    }

    /**
     * TODO: Description
     * @param number
     * @return
     */
    @Override
    public Course findById(int number) {
        return null;
    }

    /**
     * TODO: Description
     * @param rs
     * @return
     * @throws SQLException
     */
    private Course generateCourseFromResultSet(ResultSet rs) throws SQLException{
        Course course = new Course();

        course.setCourseId(rs.getInt("course_id"));
        course.setSubject(rs.getString("subject"));
        course.setCourseNumber(rs.getShort("course_number"));
        course.setCourseName(rs.getString("course_name"));
        course.setProfessor(rs.getString("professor"));
        course.setDescription(rs.getString("description"));
        course.setCapacity(rs.getShort("capacity"));
        course.setNumRegistered(rs.getShort("num_registered"));
        course.setCourseId(rs.getInt("course_id"));

        return course;
    }
}
