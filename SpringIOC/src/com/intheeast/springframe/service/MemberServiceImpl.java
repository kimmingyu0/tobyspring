package com.intheeast.springframe.service;

import com.intheeast.springframe.dao.MemberRepository;
import com.intheeast.springframe.dto.Member;
import com.intheeast.springframe.proxy.LoggingHandler;

import java.lang.reflect.Proxy;
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
        LoggingHandler txHandler = new LoggingHandler();
        txHandler.setTarget(this.memberRepository);
        txHandler.setPattern("findById");
        MemberRepository txMemberRepository = (MemberRepository) Proxy.newProxyInstance(
                getClass().getClassLoader(), new Class[]{MemberRepository.class}, txHandler
        );
        Optional<Member> memberFind = txMemberRepository.findById(member);
        if (memberFind.isEmpty()) {
            return null;
        } else {
            return memberFind.get();
        }
    }

    public void join(Long idx, Member member) {
        Optional<Integer> memberSearch = memberRepository.doubleCheck(idx, member);

        LoggingHandler txHandler = new LoggingHandler();
        txHandler.setTarget(this.memberRepository);
        txHandler.setPattern("join");
        MemberRepository txMemberRepository = (MemberRepository) Proxy.newProxyInstance(
                getClass().getClassLoader(), new Class[]{MemberRepository.class}, txHandler
        );

        if (memberSearch.get() == 0) {
            txMemberRepository.join(idx, member);
            System.out.println("회원 등록 되었습니다, 인덱스 번호 : " + idx);
        } else {
            System.out.println("중복된 회원입니다, 인덱스 번호 : " + idx);
        }
    }

    public void modify(Long idx, Member member) {
        LoggingHandler txHandler = new LoggingHandler();
        txHandler.setTarget(this.memberRepository);
        txHandler.setPattern("replace");
        MemberRepository txMemberRepository = (MemberRepository) Proxy.newProxyInstance(
                getClass().getClassLoader(), new Class[]{MemberRepository.class}, txHandler
        );
        txMemberRepository.replace(idx, member);
    }

    public void delete(Long idx) {
        LoggingHandler txHandler = new LoggingHandler();
        txHandler.setTarget(this.memberRepository);
        txHandler.setPattern("remove");
        MemberRepository txMemberRepository = (MemberRepository) Proxy.newProxyInstance(
                getClass().getClassLoader(), new Class[]{MemberRepository.class}, txHandler
        );
        txMemberRepository.remove(idx);
    }

    public int repositorySize() {
        if (memberRepository.repositorySize().isPresent()) {
            return memberRepository.repositorySize().get();
        } else {
            return 0;
        }
    }

}
