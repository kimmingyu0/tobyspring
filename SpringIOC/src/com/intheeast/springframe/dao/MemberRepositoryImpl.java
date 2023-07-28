package com.intheeast.springframe.dao;

import com.intheeast.springframe.dto.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
            if (Objects.equals(memberValue.getId(), member.getId())) {
                return Optional.of(memberValue);
            }
        }
        return Optional.empty();
    }

    @Override
    public void join(Long idx, Member member) {
        if(findById(member).isEmpty()){
            memoryMemberRepositoy.put(idx, member);
            System.out.println("회원 등록 되었습니다, 인덱스 번호 : " + idx);
        } else {
            System.out.println("중복된 회원입니다, 인덱스 번호 : " + idx);
        }
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
