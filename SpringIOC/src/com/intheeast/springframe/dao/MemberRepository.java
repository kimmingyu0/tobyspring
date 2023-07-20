package com.intheeast.springframe.dao;

import com.intheeast.springframe.dto.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Integer> doubleCheck(Long idx, Member member);

    Optional<Member> findById(Member member);

    void join(Long idx, Member member);

    void replace(Long idx, Member member);

    void remove(Long idx);

    void deleteAll();

    Optional<Integer> repositorySize();
}
