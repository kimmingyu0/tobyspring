package com.intheeast.springframe.service;

import com.intheeast.springframe.dao.MemberRepository;
import com.intheeast.springframe.dto.Member;

import java.util.Optional;

public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;

    public MemberRepository getMemberRepository (){
        return memberRepository;
    }

    public void setMemberRepository (MemberRepository memberRepo) {
            this.memberRepository = memberRepo;
    }

    public Member find(Member member) {
        Optional<Member> memberFind = memberRepository.findById(member);
        if (memberFind.isEmpty()) {
            return null;
        } else {
            return memberFind.get();
        }
    }

    public void join(Long idx, Member member) {
        memberRepository.join(idx, member);
    }

    public void modify(Long idx, Member member) {
        memberRepository.replace(idx, member);
    }

    public void delete(Long idx) {
        memberRepository.remove(idx);
    }

    public int repositorySize() {
        if (memberRepository.repositorySize().isPresent()) {
            return memberRepository.repositorySize().get();
        } else {
            return 0;
        }
    }

}
