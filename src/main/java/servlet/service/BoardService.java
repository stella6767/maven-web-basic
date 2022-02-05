package servlet.service;

import servlet.domain.Board;
import servlet.domain.BoardDao;

import java.util.ArrayList;
import java.util.List;

public class BoardService {

    private BoardDao boardDao;

    public BoardService() {
        boardDao = new BoardDao();
    }

    public void save(Board board){

        boardDao.save(board);
    }

    public List<Board> findAll() {

        return boardDao.findAll();
    }
}
