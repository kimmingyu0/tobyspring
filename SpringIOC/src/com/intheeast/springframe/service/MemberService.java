package com.intheeast.springframe.service;

import com.intheeast.springframe.dto.Member;

public interface MemberService {

    Member find(Member member);

    void join(Long idx, Member member);

    void modify(Long idx, Member member);

}
