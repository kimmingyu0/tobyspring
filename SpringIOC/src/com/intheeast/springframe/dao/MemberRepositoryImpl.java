package com.intheeast.springframe.dao;

import com.intheeast.springframe.dto.Member;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {

    private Map<Long, Member> memoryMemberRepositoy = new HashMap<>();

    @Override
    public Optional<Integer> doubleCheck(Long idx, Member member) throws ClassNotFoundException, SQLException {
        Optional<Integer> value = Optional.of(0);
        if(memoryMemberRepositoy.containsKey(idx)){
            value = Optional.of(1);
        }
        return value;
    }

    @Override
    public Optional<Member> find(Long idx) throws SQLException, ClassNotFoundException {
        return Optional.ofNullable(memoryMemberRepositoy.get(idx));
    }
    @Override
    public void join(Long idx, Member member) throws SQLException, ClassNotFoundException {
        memoryMemberRepositoy.put(idx, member);
    }
    @Override
    public void replace(Long idx, Member member) throws SQLException, ClassNotFoundException {
        memoryMemberRepositoy.replace(idx, member);
    }
    @Override
    public void remove(Long idx) throws SQLException, ClassNotFoundException {
        memoryMemberRepositoy.remove(idx);
    }
    @Override
    public void deleteAll(){
        memoryMemberRepositoy.clear();
    }

}
