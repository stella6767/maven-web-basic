package servlet.config;

import servlet.domain.board.BoardDao;
import servlet.domain.user.UserDao;


public class DaoFactory {

    public BoardDao boardDao(){
        return new BoardDao(connectionMaker());
    }

    public UserDao userDao(){
        return new UserDao(connectionMaker());
    }

    public ConnectionMaker connectionMaker(){
        return new MConnectionMaker();
    }


}
