package servlet.domain;


import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//DAO = DATA ACCESS OBJECT= 순수하게 DB에 접근해서 raw data 를 처리하는 용도의 클래스
public class BoardDao {

    private static ConcurrentHashMap<String, Board> store = new ConcurrentHashMap<>();

    public void save(Board board){

        store.put(board.getTitle(), board);
    }

    public List<Board> findAll() {

        ArrayList<Board> boards = new ArrayList<>();

        for (String i:store.keySet()) {
            boards.add(store.get(i));
        }

        return boards;
    }


}
