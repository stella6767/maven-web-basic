package servlet.test;


import org.testng.annotations.Test;
import servlet.config.MConnectionMaker;
import servlet.domain.board.Board;
import servlet.domain.board.BoardDao;
import servlet.domain.board.dto.BoardReqDto;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestClass {



    @Test
    public void classScanTest(){

        List<Class> classes = componentScan();
        System.out.println(classes);
    }



    public List<Class> componentScan(){

        List<Class> controllerList = new ArrayList<>();

        String packageName = "servlet.web";
        String packageNameSlash = "./" + packageName.replace(".","/");

        System.out.println("packageNameSlash" + packageNameSlash);

        URL directoryUrl = Thread.currentThread().getContextClassLoader().getResource(packageNameSlash);
        System.out.println("directoryUrl=>" + directoryUrl);

        if (directoryUrl == null){
            System.err.println("url resource를 얻지 못했습니다..");
        }

        String directoryString = directoryUrl.getFile();

        if (directoryString == null){
            System.err.println("url resource를 얻지 못했습니다..");
        }

        File dierctoryFile = new File(directoryString);


        if (dierctoryFile.exists()){

            String[] files = dierctoryFile.list();

            for (String file : files) {

                if (file.endsWith(".class")){
                    System.out.println("class file check=>  " + file);
                    file = file.replace(".class", "");

                    try {
                        Class<?> temp = Class.forName(packageName + "." + file);
                        System.out.println(temp);
                        controllerList.add(temp);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }

            }

        }

        return controllerList;
    }



    @Test
    public void findAllTest() throws SQLException, ClassNotFoundException {
        BoardDao boardDao = new BoardDao(new MConnectionMaker());

        List<Board> boards = boardDao.findAll();

        for (Board board : boards) {
            System.out.println(board);
        }
    }


    @Test
    public void saveTest() throws SQLException, ClassNotFoundException {

        BoardDao boardDao = new BoardDao(new MConnectionMaker());

        int result = boardDao.save(new BoardReqDto("tesdfs", "sdfsdffss"));
        System.out.println(result);

    }


    @Test
    public void jdbcTest() {

        String driverName="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/servlet?serverTimezone=Asia/Seoul";
        String id = "servlet"; // MySQL ID
        String pwd ="Kang@1234";     // MYSQL Password

        try{
            //[1] JDBC 드라이버 로드
            Class.forName(driverName);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        System.out.println("mysql jdbc Driver registered!!");

        //[2]데이타베이스 연결
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,id,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DB연결성공!!");

        //[3]데이타베이스 연결 해제
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
