package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
1. 파라미터 전송 기능
http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();

        System.out.println("전체파라미터조회 시작");
        parameterNames.asIterator().forEachRemaining(paraName-> System.out.println(paraName + "=" + request.getParameter(paraName)));
        //paraName = 키값(username) , request.getParameter(paraName)= 키에 해당하는 값(helllo)
        System.out.println("전체파라미터조회 끝");
        System.out.println();

        System.out.println("단일파라미터조회");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("username = " + username);
        System.out.println();

        System.out.println("이름이 같은 복수 파라미터");
        String[] usernames = request.getParameterValues("username");
        for(String name : usernames){
            System.out.println("name = " + name);
        }

        response.getWriter().write("ok"); // 웹페이지에 ok 쓰기
    }
}
