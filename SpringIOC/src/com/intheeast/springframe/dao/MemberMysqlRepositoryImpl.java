package com.intheeast.springframe.dao;

import com.intheeast.springframe.dto.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Optional;

public class MemberMysqlRepositoryImpl implements MemberRepository {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<Integer> doubleCheck(Long id, Member member) {
        String sql = "select count(id) from member where id = ?";
        try {
            Integer item = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> Integer.parseInt(rs.getString("count(id)")), member.getId());
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findById(Member member) {
        String sql = "select id, name from member where id = ?";

        try {
            Member item = this.jdbcTemplate.queryForObject(sql, (rs, rowNum)->{
                Member findMember = new Member();
                findMember.setId(rs.getString("id"));
                findMember.setName(rs.getString("name"));
                return findMember;
            }, member.getId());
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void join(Long idx, Member member){
        if(findById(member).isEmpty()){
            this.jdbcTemplate.update("insert into member(idx, id, name) values (?,?,?)", ps -> {
                ps.setString(1, String.valueOf(idx));
                ps.setString(2, member.getId());
                ps.setString(3, member.getName());
            } );
            System.out.println("회원 등록 되었습니다, 인덱스 번호 : " + idx);
        } else {
            System.out.println("중복된 회원입니다, 인덱스 번호 : " + idx);
        }
    }

    @Override
    public void replace(Long idx, Member member) {
        this.jdbcTemplate.update("update member set id = ? , name = ? where idx= ?", ps -> {
            ps.setString(1, member.getId());
            ps.setString(2, member.getName());
            ps.setString(3, String.valueOf(idx));
        } );
    }

    @Override
    public void remove(Long idx) {
        this.jdbcTemplate.update("delete from member where idx = ?", ps -> {
            ps.setString(1, String.valueOf(idx));
        } );
    }

    @Override
    public void deleteAll(){
        this.jdbcTemplate.update("delete from member");
    }
    @Override
    public Optional<Integer> repositorySize(){
        String sql = "select count(idx) as size from member";
        try {
            Integer item = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> Integer.parseInt(rs.getString("size")));
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
