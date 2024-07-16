package com.revature.cra.Course;

import com.revature.cra.util.ConnectionFactory;
import com.revature.cra.util.Interfaces.Crudable;
import com.revature.cra.util.exceptions.DataNotFoundException;
import com.revature.cra.util.exceptions.InvalidInputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO: Implement logger
// import static com.revature.cra.CourseRegistrationFrontController.logger;

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
     * @param newCourse
     * @return
     * @throws InvalidInputException
     */
    @Override
    public Course create(Course newCourse) throws InvalidInputException {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "insert into courses(course_id, subject, course_number, course_name, professor, description, capacity, num_registered) values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, newCourse.getCourseId());
            preparedStatement.setString(2, newCourse.getSubject());
            preparedStatement.setShort(3, newCourse.getCourseNumber());
            preparedStatement.setString(4, newCourse.getCourseName());
            preparedStatement.setString(5, newCourse.getProfessor());
            preparedStatement.setString(6, newCourse.getDescription());
            preparedStatement.setShort(7, newCourse.getCapacity());
            preparedStatement.setShort(8, newCourse.getNumRegistered());


            // TODO: implement logger
            //logger.info(preparedStatement.toString());
            int checkInsert = preparedStatement.executeUpdate();
            if(checkInsert == 0){
                throw new RuntimeException("Course was not inserted into the database.");
            }
            return newCourse;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * TODO: Description
     * @param number
     * @return
     */
    @Override
    public Course findById(int number) throws DataNotFoundException {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            //logger.info("Course number provided to the Repository be the service is {}", number);
            String sql = "select * from courses where course_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, number);

            //logger.info(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                //logger.warn("Information not found within the database given course id {}", number);
                throw new DataNotFoundException("No flight with that id " + number + " exists in our database.");
            }
            Course foundCourse = generateCourseFromResultSet(resultSet);
            //logger.info("Sending back course information {}", foundCourse);
            return foundCourse;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
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
