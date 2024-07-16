package com.revature.cra.Member;

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

public class MemberRepository implements Crudable<Member> {

    /**
     * TODO: Description and Test
     * @param updatedMember
     * @return
     */
    @Override
    public boolean update(Member updatedMember) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "UPDATE members SET first_name = ?, last_name = ?, member_type = ?, password = ? WHERE member_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, updatedMember.getFirstName());
            preparedStatement.setString(2, updatedMember.getLastName());
            preparedStatement.setObject(3, updatedMember.getType());
            preparedStatement.setString(4, updatedMember.getPassword());
            preparedStatement.setInt(5, updatedMember.getMemberId());

            int checkUpdate = preparedStatement.executeUpdate();
            if (checkUpdate == 0){
                throw new RuntimeException("Member record was not updated.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * TODO: Description and Test
     * @param removedMember
     * @return
     */
    @Override
    public boolean delete(Member removedMember) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "DELETE FROM members WHERE member_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, removedMember.getMemberId());
            boolean checkDelete = preparedStatement.executeUpdate() == 1;
            if (checkDelete){
                System.out.println("Member record was deleted.");
            } else {
                System.out.println("Member record was not deleted.");
            }
            return checkDelete;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * TODO: Implement
     * @return
     */
    @Override
    public List<Member> findAll() {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Member> members = new ArrayList<>();
            String sql = "SELECT * FROM members";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                members.add(generateMemberFromResultSet(rs));
            }

            return members;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * TODO: Describe and Test
     * @param newMember
     * @return
     * @throws InvalidInputException
     */
    @Override
    public Member create(Member newMember) throws InvalidInputException {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "INSERT INTO members(member_id, first_name, last_name, type, password) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, newMember.getMemberId());
            preparedStatement.setString(2, newMember.getFirstName());
            preparedStatement.setString(3, newMember.getLastName());
            preparedStatement.setObject(4, newMember.getType());
            preparedStatement.setString(5, newMember.getPassword());

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0){
                throw new RuntimeException("Member was not inserted into the database.");
            }
            return newMember;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * TODO: Describe and Test
     * @param number
     * @return
     */
    @Override
    public Member findById(int number) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "SELECT * FROM members WHERE member_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, number);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                throw new DataNotFoundException("No member with that id " + number + " exists in our database.");
            }
            Member foundMember = generateMemberFromResultSet(resultSet);
            return foundMember;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * TODO: Description
     * @param id
     * @param password
     * @return
     */
    public Member findByIdAndPassword(int id, String password){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from members where member_id = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                throw new DataNotFoundException("No member with that information found");
            }

            Member member = new Member();

            member.setMemberId(resultSet.getInt("member_id"));
            member.setFirstName(resultSet.getString("first_name"));
            member.setLastName(resultSet.getString("last_name"));
            member.setType(resultSet.getString("member_type"));

            return member;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * TODO: Description and Test
     * @param rs
     * @return
     * @throws SQLException
     */
    private Member generateMemberFromResultSet(ResultSet rs) throws SQLException {
        Member member = new Member();

        member.setMemberId(rs.getInt("member_id"));
        member.setFirstName(rs.getString("first_name"));
        member.setLastName(rs.getString("last_name"));
        member.setType(rs.getString("member_type"));
        member.setPassword(rs.getString("password"));

        return member;
    }
}
