package servlet.domain.board;


import servlet.config.ConnectionMaker;

import servlet.domain.board.dto.BoardReqDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//DAO = DATA ACCESS OBJECT= 순수하게 DB에 접근해서 raw data 를 처리하는 용도의 클래스
public class BoardDao {

    private ConnectionMaker connectionMaker;


    /**
     * 이 코드 자체는 수정할 필요가 없어요.
     * @param connectionMaker
     */

    public BoardDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public int save(BoardReqDto dto) throws SQLException, ClassNotFoundException {

        PreparedStatement pstmt = null;
        int result;

        //[2]데이타베이스 연결
        Connection conn = connectionMaker.makeConnect();

        String sql = "INSERT INTO board(title, content) VALUES(?,?)";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, dto.getTitle());
        pstmt.setString(2, dto.getContent());

        result = pstmt.executeUpdate();
        connectionMaker.close(conn, pstmt);

        return result;
    }

    public List<Board> findAll() throws SQLException, ClassNotFoundException {

        ArrayList<Board> boards = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int result;

        Connection conn = connectionMaker.makeConnect();
        String sql = "SELECT * from board";

        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {

            Board board = Board.builder()
                    .id(rs.getInt("id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .build();

            boards.add(board);
        }

        connectionMaker.close(conn, pstmt, rs);

        return boards;
    }


    /**
     * 아 이번에는 JDBC 알아볼려고 하는데,
     * 그 전에 API 개념부터 살짝 살펴볼게요.
     *
     * 메서드를 JAVA로 변경 -> .class -> .jar
     *
     * 어떤 메서드가 다른 메서드에 디펜더시(의존) 되어있으면 이것만 jar 파일로 import하면 안돼여.
     *  디펜더시가 연속적으로 존재할 때, 쓰지도 않을 디펜더시 메서드가 잔뜩 있는 jar 파일을 억지로 import해야 돼요.
     *
     *  그럴 경우에는 라이브러리가 아니라, api 사용을 생각해볼 수 있어요.
     *  쓰고 싶은 외부의 메서드를 내 쪽에 호출시킴. 요런 걸 API 통신
     *
     *  호출하려면 사용자가 get/post, end point, body, mime 형식을 지정해주면 돼요.
     *  이걸 간편한 시킨 게 sdk , jdbc
     *
     *  DB: 갑 - 인터페이스를 만드는 역할.
     *  jdbc,
     *  Preparestatement (버퍼 연결)
     *  - exectureQuery
     * Resultset
     * - sdf
     * Connection
     * - sdf
     *
     *  CPU - 메모리(주기억장치) - 보조기억장치
     *  메모리는 휘발성이어서, 전류의 흐름으로 데이터를 기억하는데, 서버 재가동하면 백지
     *
     *  보조기억장치는 비휘발성. DBMS가 하드디스크의 특정영역을 잡아가지고,
     *  데이터베이스나 파일 쿼리를 날려서 데이터를 얻어오는 것을 IO 작업
     *
     *
     */


}
