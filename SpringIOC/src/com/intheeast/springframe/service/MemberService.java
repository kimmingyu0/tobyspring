package com.intheeast.springframe.service;

import com.intheeast.springframe.dto.Member;

import java.sql.SQLException;

public interface MemberService {

    Member find(Member member) throws SQLException, ClassNotFoundException;

    void join(Long idx, Member member) throws SQLException, ClassNotFoundException;

    void modify (Long idx, Member member) throws SQLException, ClassNotFoundException;


}
