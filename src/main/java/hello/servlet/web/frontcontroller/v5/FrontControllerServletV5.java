package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns= "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    //private Map<String, ControllerV4> controllerMap = new HashMap<>();
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapter();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        //핸들러 찾기
        // handler -> MemberFormControllerV3
        Object handler = getHandler(request);
        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return;
        }


        // handler의 어댑터 찾기 -> ControllerV3HandlerAdapter
        MyHandlerAdapter adapter = getHandlelrAdapter(handler);
        // ControllerV3HandlerAdapter안에서의 handle을 통해 handler의 로직 실행 및 jsp 주소반환
        ModelView mv = adapter.handle(request, response, handler);


        String viewName = mv.getViewName(); //논리이름 new-form
        MyView view = viewResolver(viewName); //WEB-INF/views/new-form.jsp

        // 6. render(model) 호출
        // jsp파일에 요청파라미터, HTTP정보 전달 후 호출
        view.render(mv.getModel(), request, response);
    }


    private void initHandlerMappingMap() {
    /*
    controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
    controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
    controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
     */
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }
    private void initHandlerAdapter() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }


    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        //MemberFormControllerV3 호출
        Object handler = handlerMappingMap.get(requestURI);
        return handler;
    }


    private MyHandlerAdapter getHandlelrAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을수 없습니다" + handler);   
    }


    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
