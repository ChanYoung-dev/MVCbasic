package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/springmvc/v3/members") // 공통된 목록
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping("/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newform(){
        return "new-form";
    }


    //@RequestMapping("/save", method = RequestMethod.POST)
    @GetMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {

        //String username = request.getParameter("username");
        //int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);

        //ModelAndView mv = new ModelAndView("save-result");
        //mv.addObject("member", member);
        model.addAttribute("member", member);
        return "save-result";
    }

    //@RequestMapping( method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        //ModelAndView mv = new ModelAndView("members");
        //mv.addObject("members", members);
        model.addAttribute("members", members);

        return "members";
    }
}
