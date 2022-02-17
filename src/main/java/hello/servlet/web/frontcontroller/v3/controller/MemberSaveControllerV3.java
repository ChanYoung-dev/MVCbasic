package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paraMap) {
        /*
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
         */
        String username = paraMap.get("username");
        int age = Integer.parseInt(paraMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //new MyView("/WEB-INF/views/save-result.jsp");
        ModelView mv = new ModelView("save-result");
        //request.setAttribute("member", member);
        mv.getModel().put("member", member);
        return mv;
    }
}
