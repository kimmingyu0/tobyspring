package com.intheeast.springframe.dao;

import com.intheeast.springframe.dto.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {

    private Map<Long, Member> memoryMemberRepositoy = new HashMap<>();

    @Override
    public Optional<Integer> doubleCheck(Long idx, Member member) {
        Optional<Integer> value = Optional.of(0);
        if (memoryMemberRepositoy.containsKey(idx)) {
            value = Optional.of(1);
        }
        return value;
    }

    @Override
    public Optional<Member> findById(Member member) {
        for (Map.Entry<Long, Member> entry : memoryMemberRepositoy.entrySet()) {
            Member memberValue = entry.getValue();
            if (memberValue.getId() == member.getId()) {
                return Optional.of(memberValue);
            }
        }
        return Optional.empty();
    }

    @Override
    public void join(Long idx, Member member) {
        memoryMemberRepositoy.put(idx, member);
    }

    @Override
    public void replace(Long idx, Member member) {
        memoryMemberRepositoy.replace(idx, member);
    }

    @Override
    public void remove(Long idx) {
        memoryMemberRepositoy.remove(idx);
    }

    @Override
    public void deleteAll() {
        memoryMemberRepositoy.clear();
    }

    @Override
    public Optional<Integer> repositorySize() {
        return Optional.of(memoryMemberRepositoy.size());
    }
}
