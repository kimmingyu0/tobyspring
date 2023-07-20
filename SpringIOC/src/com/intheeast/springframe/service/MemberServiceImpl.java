package com.intheeast.springframe.service;

import com.intheeast.springframe.dao.MemberRepository;
import com.intheeast.springframe.dto.Member;

import java.sql.SQLException;
import java.util.Optional;

public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;

    public MemberRepository getMemberRepository (){
        return memberRepository;
    }

    public void setMemberRepository (MemberRepository memberRepo) {
            this.memberRepository = memberRepo;
    }

    public Member find (Long idx) throws SQLException, ClassNotFoundException {
        Optional<Member> memberFind = memberRepository.find(idx);
        if(memberFind.isEmpty()){
            return null;
        } else {
            return memberFind.get();
        }
    }

    public void join (Long idx, Member member) throws SQLException, ClassNotFoundException {
        Optional<Integer> memberSearch = memberRepository.doubleCheck(idx, member);

        if(memberSearch.get() == 0) {
            memberRepository.join(idx, member);
            System.out.println("회원 등록 되었습니다, 인덱스 번호 : " + idx);
        } else {
            System.out.println("중복된 회원입니다, 인덱스 번호 : " + idx);
        }
    }
    public void modify (Long idx, Member member) throws SQLException, ClassNotFoundException {
        memberRepository.replace(idx, member);
    }

    public void delete (Long idx) throws SQLException, ClassNotFoundException {
        memberRepository.remove(idx);
    }


}
