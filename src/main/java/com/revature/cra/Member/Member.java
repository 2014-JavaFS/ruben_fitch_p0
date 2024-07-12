package com.revature.cra.Member;

public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private MemberType type;
    private String password;

    public enum MemberType {
        STUDENT, FACULTY
    }

    // No arg constructor used jackson
    public Member(){
    }

    public Member(int memberId, String firstName, String lastName, MemberType type, String password) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.password = password;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type=" + type +
                ", password='" + password + '\'' +
                '}';
    }
}
