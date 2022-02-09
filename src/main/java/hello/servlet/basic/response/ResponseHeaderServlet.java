package hello.servlet.basic.response;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[start-line]
        response.setStatus(HttpServletResponse.SC_OK); // = response.setStatus(200)

        //response-header
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        /*
            response.setContentType("text/plain");
            response.setCharacterEncoding("utf-8");
         */
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("pragma", "no-cache");
        response.setHeader("my-header", "hello");

        cookie((response));
        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요");
    }
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }
    private void redirect(HttpServletResponse response) throws IOException {
        /*
            -----------HTTP------------
            Status Code 302
            Location: /basic/hello-form.html
            ---------------------------
        */

        /*
            response.setStatus(HttpServletResponse.SC_FOUND); //302
            response.setHeader("Location", "/basic/hello-form.html");
         */
        response.sendRedirect("/basic/hello-form.html");
    }
}
