package com.revature.cra.Member;

import com.revature.cra.util.Interfaces.Serviceable;
import com.revature.cra.util.exceptions.InvalidInputException;
import com.revature.cra.util.exceptions.DataNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MemberService implements Serviceable<Member> {
    private List<Member> memberList = new ArrayList<>();
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll(){
        return new ArrayList<>();
    }

    @Override
    public Member create(Member newMember) throws InvalidInputException {
        memberList.add(newMember);
        System.out.println(newMember);
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


    /**
     * Searches database for information where the memberId and password must be equal to
     * a row within our database
     *
     * @param memberId - int
     * @param password - String
     * @return - Member object, null if not found
     */
    public Member findByMemberIdAndPassword(int memberId, String password){
        for (Member member: memberList){
            if (member.getMemberId() == memberId && member.getPassword().equals(password)){
                return member;
            }
        }
        return null;
    }

    /**
     * Find the member with updatedMember's ID and replace it
     * Otherwise, throw a DataNotFoundException
     *
     * @param updatedMember - an existing member with updated information
     * @throws DataNotFoundException - member not found within database
     */
    public void update(Member updatedMember) throws DataNotFoundException {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemberId() == updatedMember.getMemberId()){
                memberList.set(i, updatedMember);
                return;
            }
        }
        throw new DataNotFoundException("Member with ID not within Database");
    }
}
