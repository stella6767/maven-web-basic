package servlet.service;

import com.google.gson.Gson;
import servlet.config.DaoFactory;
import servlet.domain.board.Board;
import servlet.domain.board.BoardDao;
import servlet.domain.board.dto.BoardReqDto;
import servlet.utills.Script;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BoardService {

    private BoardDao boardDao;

    public BoardService() {
        boardDao = new DaoFactory().boardDao();
    }

    public void save(String title, String content) throws SQLException, ClassNotFoundException {

        BoardReqDto dto = BoardReqDto.builder()
                .title(title)
                .content(content)
                .build();


        boardDao.save(dto);
    }


    public String ajaxSaveAndFindAll(HttpServletRequest req) throws SQLException, ClassNotFoundException, IOException {
        Gson gson = new Gson();
        String jsonBody = Script.getBody(req);
        System.out.println("jsonBody=>" + jsonBody);
        BoardReqDto dto = gson.fromJson(jsonBody, BoardReqDto.class);
        System.out.println("자바 오브젝트로 역직렬화 =>" + dto);
        boardDao.save(dto);

        List<Board> boards = boardDao.findAll();
        String jsonBoards = gson.toJson(boards);

        return jsonBoards;
    }



    public List<Board> findAll() throws SQLException, ClassNotFoundException {

        return boardDao.findAll();
    }
}
