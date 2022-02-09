package servlet.config;

import java.sql.*;

public class MConnectionMaker implements ConnectionMaker{

    String driverName="com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul";
    String id = "test"; // MySQL ID
    String pwd ="1234";     // MYSQL Password
    Connection conn = null;

    @Override
    public Connection makeConnect() throws ClassNotFoundException, SQLException {
                    //[1] JDBC 드라이버 로드
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url, id, pwd);
        System.out.println("DB연결성공!!");
        return conn;
    }

    @Override
    public void close(Connection conn, PreparedStatement pstmt) {
        //[3]데이타베이스 연결 해제
        try {
            conn.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        //[3]데이타베이스 연결 해제
        try {
            conn.close();
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}
