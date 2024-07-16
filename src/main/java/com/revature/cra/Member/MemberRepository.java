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
     * TODO: Implement
     * @param updatedMember
     * @return
     */
    @Override
    public boolean update(Member updatedMember) {
        return false;
    }

    /**
     * TODO: Implement
     * @param removedMember
     * @return
     */
    @Override
    public boolean delete(Member removedMember) {
        return false;
    }

    /**
     * TODO: Implement
     * @return
     */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>();
    }

    /**
     * TODO: Implement
     * @param newMember
     * @return
     * @throws InvalidInputException
     */
    @Override
    public Member create(Member newMember) throws InvalidInputException {
        return null;
    }

    /**
     * TODO: Implement
     * @param number
     * @return
     */
    @Override
    public Member findById(int number) {
        return null;
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
}
