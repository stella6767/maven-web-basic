package servlet.service;


import servlet.config.DaoFactory;
import servlet.domain.user.User;
import servlet.domain.user.UserDao;
import servlet.domain.user.dto.LoginDto;

import java.sql.SQLException;

public class UserService {

    private UserDao userDao;

    public UserService() {
        this.userDao = new DaoFactory().userDao();
    }


    //정산은 과정이



    public int join(LoginDto dto) throws SQLException, ClassNotFoundException {
        //회원가입

        if (userDao.findByName(dto.getName()) != null ){
            //나는 중복된 이름을 가지고 싶지 않다.
            return -1;
        }

        return userDao.save(dto);
    }


    public User login(LoginDto dto) throws SQLException, ClassNotFoundException {

        return userDao.findByUserNameAndPassword(dto);
    }


}