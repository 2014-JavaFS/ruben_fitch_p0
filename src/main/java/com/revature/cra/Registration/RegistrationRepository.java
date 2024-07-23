package com.revature.cra.Registration;

import com.revature.cra.Course.Course;
import com.revature.cra.Member.Member;
import com.revature.cra.util.ConnectionFactory;
import com.revature.cra.util.exceptions.InvalidInputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationRepository {

    /**
     * Queries a SQL database for all registrations by a member.
     * @param memberId
     * @return
     */
    public List<Registration> findAllRegistrationsByMemberId(int memberId){
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Registration> registrations = new ArrayList<>();
            String sql =  "select * from registrations r \n" +
                    "join courses c on c.course_id = r.course_id\n" +
                    "where r.member_id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, memberId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = generateCourseFromResultSet(resultSet);

                Registration registration = new Registration();
                registration.setRegistrationId(resultSet.getInt("registration_id"));
                registration.setCourse(course);

                registrations.add(registration);
            }

            return registrations;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Queries a SQL database for a list of all registrations
     * @return A list of all member IDs that created a registration, the registration related to them, and the course id the registration was created for
     */
    public List<Registration> findAll() {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Registration> registrations = new ArrayList<>();
            String sql = "select * from registrations r join members m on m.member_id = r.member_id join courses c on c.course_id = r.course_id";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                Course course = generateCourseFromResultSet(resultSet);

                Member member = new Member();

                member.setMemberId(resultSet.getInt("member_id"));
                member.setFirstName(resultSet.getString("first_name"));
                member.setLastName(resultSet.getString("last_name"));
                member.setType(resultSet.getString("member_type"));
                member.setPassword(resultSet.getString("password"));

                Registration registration = new Registration();
                registration.setRegistrationId(resultSet.getInt("registration_id"));
                registration.setMember(member);
                registration.setCourse(course);

                registrations.add(registration);
            }
            return registrations;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Course generateCourseFromResultSet(ResultSet rs) throws SQLException{
        Course course = new Course();

        course.setCourseId(rs.getInt("course_id"));
        course.setSubject(rs.getString("subject"));
        course.setCourseNumber(rs.getShort("course_number"));
        course.setCourseName(rs.getString("course_name"));
        course.setProfessor(rs.getInt("professor"));
        course.setDescription(String.valueOf(rs.getBlob("description")));
        course.setCapacity(rs.getShort("capacity"));
        course.setNumRegistered(rs.getShort("num_registered"));

        return course;
    }

    /**
     * Inserts a provided Registration object into a SQL database
     * @param newRegistration
     * @return
     * @throws InvalidInputException
     */
    public Registration create(Registration newRegistration) throws InvalidInputException {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "insert into registrations(course_id, member_id) values (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, newRegistration.getCourse().getCourseId());
            preparedStatement.setInt(2, newRegistration.getMember().getMemberId());

            int checkInsert = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(checkInsert == 0 || !resultSet.next()){
                throw new InvalidInputException("Something went wrong when entering " + newRegistration + " into the database");
            }

            newRegistration.setRegistrationId(resultSet.getInt(1));
            return newRegistration;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
