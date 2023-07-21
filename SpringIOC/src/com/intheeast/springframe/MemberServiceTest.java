package com.intheeast.springframe;

import com.intheeast.springframe.dao.MemberRepository;
import com.intheeast.springframe.dao.MemberRepositoryImpl;
import com.intheeast.springframe.dto.Member;
import com.intheeast.springframe.factory.MemberFactory;
import com.intheeast.springframe.proxy.LoggingHandler;

import com.intheeast.springframe.service.MemberService;
import com.intheeast.springframe.service.MemberServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Proxy;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MemberFactory.class})
public class MemberServiceTest {
    @Autowired
    MemberServiceImpl memberService;

    Member mem1 = new Member("김민규", "김민규");
    Member mem2 = new Member("김민규1", "김민규1");
    Member mem3 = new Member("김민규2", "김민규2");


    @BeforeEach
    public void setMemberRepo() {
        memberService.getMemberRepository().deleteAll();

        MemberRepository memberRepo = memberService.getMemberRepository();
        memberRepo.join(1L, mem1);
        memberRepo.join(2L, mem2);
        memberRepo.join(3L, mem3);
    }

    @AfterEach
    public void sizeCheck(){
        System.out.println("RepositorySize : " + memberService.repositorySize());
        System.out.println("------------------------------------------------------");
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void insert() {
        Member tmp = new Member("김민규3", "김민규");

        memberService.join(1L,mem3);
        memberService.join(4L, tmp);

        assertEquals(tmp.getId(), memberService.find(tmp).getId());
    }

    @Test
    @DisplayName("회원 조회")
    public void find() {
        //1번 유저 아이디 비교
        assertEquals(mem1.getId(), memberService.find(mem1).getId());
    }

    @Test
    @DisplayName("회원 수정")
    public void modify() {
        Member tmp = new Member("tmpId", "tmpName");
        //1번 유저 수정
        memberService.modify(1L, tmp);
    }

    @Test
    @DisplayName("회원 삭제")
    public void delete() {
        //1번 유저를 삭제
        memberService.delete(1L);
        assertNull(memberService.find(mem1));
    }
}
