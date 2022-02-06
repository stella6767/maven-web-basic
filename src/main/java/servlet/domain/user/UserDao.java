package servlet.domain.user;


import servlet.config.ConnectionMaker;
import servlet.domain.board.Board;
import servlet.domain.board.dto.BoardReqDto;
import servlet.domain.user.dto.LoginDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO = DATA ACCESS OBJECT= 순수하게 DB에 접근해서 raw data 를 처리하는 용도의 클래스
public class UserDao {

    private ConnectionMaker connectionMaker;


    /**
     * 이 코드 자체는 수정할 필요가 없어요.
     * @param connectionMaker
     */

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public int save(LoginDto dto) throws SQLException, ClassNotFoundException {

        PreparedStatement pstmt = null;
        int result;

        //[2]데이타베이스 연결
        Connection conn = connectionMaker.makeConnect();

        String sql = "INSERT INTO user(name, password) VALUES(?,?)";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, dto.getName());
        pstmt.setString(2, dto.getPassword());

        result = pstmt.executeUpdate();
        connectionMaker.close(conn, pstmt);

        return result;
    }


    public User findByName(String name) throws SQLException, ClassNotFoundException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int result;

        Connection conn = connectionMaker.makeConnect();
        String sql = "SELECT * from user where name = ? ";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);

        rs = pstmt.executeQuery();

        while (rs.next()) {

            User user = User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .password(rs.getString("password"))
                    .build();

            return user;
        }

        connectionMaker.close(conn, pstmt, rs);

        return null;
    }


    public User findByUserNameAndPassword(LoginDto dto) throws SQLException, ClassNotFoundException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int result;

        Connection conn = connectionMaker.makeConnect();
        String sql = "SELECT * from user where name = ? and password = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, dto.getName());
        pstmt.setString(2, dto.getPassword());

        rs = pstmt.executeQuery();

        while (rs.next()) {

            User user = User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .password(rs.getString("password"))
                    .build();

            return user;
        }

        connectionMaker.close(conn, pstmt, rs);

        return null;
    }




}
