package servlet.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConnectionMaker {

    public Connection makeConnect() throws ClassNotFoundException, SQLException;

    public void close(Connection conn, PreparedStatement pstmt);

    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs);

}
