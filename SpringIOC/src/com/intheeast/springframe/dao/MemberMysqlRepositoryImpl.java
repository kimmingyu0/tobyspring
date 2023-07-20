package com.intheeast.springframe.dao;

import com.intheeast.springframe.dto.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MemberMysqlRepositoryImpl implements MemberRepository {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


//    public Connection connectionMaker() throws ClassNotFoundException, SQLException {
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?characterEncoding=UTF-8",
//                "root",
//                "1234");
//
//        return c;
//    }

//    public int doubleCheck (Long id, Member member) throws SQLException, ClassNotFoundException {
////        int value = 0;
////
////        Connection c = connectionMaker();
////        PreparedStatement ps = c.prepareStatement("select count(id) from member where id = ? ");
////        ps.setString(1, member.getId());
////        ResultSet rs = ps.executeQuery();
////
////        if(rs.next()) {
////           value = Integer.parseInt(rs.getString("count(id)"));
////        }
////
////        return value;
//        int value = 0;
//        try{
//            jdbcTemplate.queryForObject("select count(id) from member where id = ?", Integer.class, member.getId());
//            value = 1;
//        } catch (EmptyResultDataAccessException e){
//        }
//        return value;
//    }
    public Optional<Integer> doubleCheck(Long id, Member member) {
        String sql = "select count(id) from member where id = ?";
        try {
            Integer item = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> Integer.parseInt(rs.getString("count(id)")), member.getId());
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

//    private RowMapper<Integer> itemRowMapper() {
//        return ((rs, rowNum) -> {
//           int result = Integer.parseInt(rs.getString("count(id)"));
//           return result;
//        });
//    }

    @Override
    public Optional<Member> find(Long id) throws SQLException, ClassNotFoundException {
//        Connection c = connectionMaker();
//        PreparedStatement ps = c.prepareStatement("select * from member where idx = ? ");
//        ps.setString(1, String.valueOf(id));
//        ResultSet rs = ps.executeQuery();
//        if(rs.next()) {
//            Member member = new Member();
//            member.setId(rs.getString("id"));
//            member.setName(rs.getString("name"));
//            return member;
//        }
//        rs.close();
//        ps.close();
//        c.close();
//        return null;
        String sql = "select id, name from member where idx = ?";

        try {
            Member item = this.jdbcTemplate.queryForObject(sql, (rs, rowNum)->{
                Member member = new Member();
                member.setId(rs.getString("id"));
                member.setName(rs.getString("name"));
                return member;
            }, id);
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

//    private RowMapper<Member> memberRowMapper() {
//        return ((rs, rowNum) -> {
//            Member member = new Member();
//            member.setId(rs.getString("id"));
//            member.setName(rs.getString("name"));
//            return member;
//        });
//    }


    @Override
    public void join(Long idx, Member member) throws SQLException, ClassNotFoundException {
//            Connection c = connectionMaker();
//
//            PreparedStatement ps = c.prepareStatement("insert into member(idx, id, name) values(?,?,?)");
//            ps.setString(1, String.valueOf(idx));
//            ps.setString(2, String.valueOf(member.getId()));
//            ps.setString(3, String.valueOf(member.getName()));
//            ps.executeUpdate();
//            ps.close();
//            c.close();
        this.jdbcTemplate.update("insert into member(idx, id, name) values (?,?,?)", ps -> {
            ps.setString(1, String.valueOf(idx));
            ps.setString(2, member.getId());
            ps.setString(3, member.getName());
        } );
    }

    @Override
    public void replace(Long idx, Member member) throws SQLException, ClassNotFoundException {
//        Connection c = connectionMaker();
//        PreparedStatement ps = c.prepareStatement("update member set id = ? , name = ? where idx= ?");
//        ps.setString(1, member.getId());
//        ps.setString(2, member.getName());
//        ps.setString(3, String.valueOf(idx));
//
//        ps.executeUpdate();
//        ps.close();
//        c.close();
        this.jdbcTemplate.update("update member set id = ? , name = ? where idx= ?", ps -> {
            ps.setString(1, member.getId());
            ps.setString(2, member.getName());
            ps.setString(3, String.valueOf(idx));
        } );
    }

    @Override
    public void remove(Long idx) throws SQLException, ClassNotFoundException {
//        Connection c = connectionMaker();
//        PreparedStatement ps = c.prepareStatement("delete from member where idx = ?");
//        ps.setString(1, String.valueOf(idx));
//
//        ps.executeUpdate();
//        ps.close();
//        c.close();
        this.jdbcTemplate.update("delete from member where idx = ?", ps -> {
            ps.setString(1, String.valueOf(idx));
        } );
    }

    @Override
    public void deleteAll(){
        this.jdbcTemplate.update("delete from member");
    }
}
