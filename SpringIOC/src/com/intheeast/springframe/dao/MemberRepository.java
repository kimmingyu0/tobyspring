package com.intheeast.springframe.dao;

import com.intheeast.springframe.dto.Member;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public interface MemberRepository {
    Optional<Integer> doubleCheck (Long idx, Member member) throws ClassNotFoundException, SQLException;
    Optional<Member> find(Long idx) throws SQLException, ClassNotFoundException;
    void join(Long idx, Member member) throws SQLException, ClassNotFoundException;
    void replace(Long idx, Member member) throws SQLException, ClassNotFoundException;
    void remove(Long idx) throws SQLException, ClassNotFoundException;
    void deleteAll ();
}
