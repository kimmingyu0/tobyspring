package com.intheeast.springframe.factory;

import com.intheeast.springframe.dao.*;
import com.intheeast.springframe.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class MemberFactory {


    @Bean
    public DataSource dataSource() {

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/userdb?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        return dataSource;
    }

    @Bean
    public MemberServiceImpl memberService () throws SQLException, ClassNotFoundException {
        MemberServiceImpl member = new MemberServiceImpl();
        member.setMemberRepository(memberSqlRepo());
        return member;
    }

    @Bean
    public MemberRepository memberRepo () throws SQLException, ClassNotFoundException {
        MemberRepositoryImpl memberRepository = new MemberRepositoryImpl();

        return memberRepository;
    }

    @Bean
    public MemberRepository memberSqlRepo () throws SQLException, ClassNotFoundException {
        MemberMysqlRepositoryImpl memberRepository = new MemberMysqlRepositoryImpl();
        memberRepository.setDataSource(dataSource());
        return memberRepository;
    }


}
