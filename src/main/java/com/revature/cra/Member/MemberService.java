package com.revature.cra.Member;

import com.revature.cra.util.Interfaces.Serviceable;
import com.revature.cra.util.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class MemberService implements Serviceable<Member> {
    private List<Member> memberList = new ArrayList<>();

    @Override
    public Member[] findAll(){
        return new Member[0];
    }

    @Override
    public Member create(Member newMember) throws InvalidInputException {
        memberList.add(newMember);
        return newMember;
    }

    @Override
    public Member findById(int memberId) {
        for (Member member: memberList){
            if (member.getMemberId() == memberId){
                return member;
            }
        }
        return null;
    }
}
