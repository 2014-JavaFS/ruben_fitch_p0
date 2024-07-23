package com.revature.cra.Registration;

import com.revature.cra.Course.Course;
import com.revature.cra.Member.Member;

public class Registration {
    private int registrationId;
    private Course course;
    private Member member;

    public Registration() {}

    public Registration(int registrationId, Course course, Member member){
        this.registrationId = registrationId;
        this.course = course;
        this.member = member;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public String toString(){
        return "Registration {" +
                "registrationId=" + registrationId +
                ", course=" + course +
                ", member=" + member +
                "}";
    }
}

