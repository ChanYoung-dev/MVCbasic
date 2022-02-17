package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {
    //void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    //MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    ModelView process(Map<String, String> paraMap);
}
