package com.intheeast.springframe.factory;

import com.intheeast.springframe.dao.MemberMysqlRepositoryImpl;
import com.intheeast.springframe.dao.MemberRepository;
import com.intheeast.springframe.dao.MemberRepositoryImpl;
import com.intheeast.springframe.proxy.LoggingHandler;
import com.intheeast.springframe.service.MemberServiceImpl;
 import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;

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
    public MemberServiceImpl memberService () {
        MemberServiceImpl member = new MemberServiceImpl();
        member.setMemberRepository(proxyMemberRepository());
        return member;
    }

    @Bean
    public MemberRepository proxyMemberRepository(){
        LoggingHandler loggingHandler = new LoggingHandler();
        loggingHandler.setTarget(memberRepo());
        loggingHandler.setPattern(new String[]{"replace", "findById", "remove"});
        return (MemberRepository) Proxy.newProxyInstance(
                getClass().getClassLoader(), new Class[]{MemberRepository.class}, loggingHandler
        );
    }

    @Bean
    public MemberRepository memberRepo() {
        MemberRepositoryImpl memberRepository = new MemberRepositoryImpl();
        return memberRepository;
    }

    @Bean
    public MemberRepository memberSqlRepo() {
        MemberMysqlRepositoryImpl memberRepository = new MemberMysqlRepositoryImpl();
        memberRepository.setDataSource(dataSource());
        return memberRepository;
    }
}
