package servlet.utills;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Script {


    //thread safe
    private static StringBuffer sb = new StringBuffer();

    public static void responseData(HttpServletResponse resp, String jsonData) throws IOException {
        //나중에 CommonRespDto<T> jsonData
        PrintWriter out;
        resp.setContentType("application/json; charset=utf-8");

        try {
            out =resp.getWriter();
            out.println(jsonData);
            out.flush(); //버퍼 비우기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getBody(HttpServletRequest request) throws IOException {

        sb.setLength(0);
        String body = null;
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    sb.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (Exception ex) {
            //throw ex;
            ex.printStackTrace();

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    //throw ex;
                    ex.printStackTrace();
                }
            }
        }

        body = sb.toString();
        System.out.println("body=>" + body);
        return body;

    }



}
