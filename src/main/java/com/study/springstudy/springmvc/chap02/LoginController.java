package com.study.springstudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hw")
public class LoginController {
    /*
        1번요청: 로그인 폼 화면 열어주기
        - 요청 URL : /hw/s-login-form : GET
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-form.jsp
        - html form: 아이디랑 비번을 입력받으세요.
     */
    @GetMapping("/s-login-form")
    public String login() {
        return "mvc/s-form";
    }

    /*
        2번요청: 로그인 검증하기
        - 로그인 검증: 아이디를 grape111이라고 쓰고 비번을 ggg9999 라고 쓰면 성공
        - 요청 URL : /hw/s-login-check : POST
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-result.jsp
        - jsp에게 전달할 데이터: 로그인 성공여부, 아이디 없는경우, 비번 틀린경우
     */
    @PostMapping("/s-login-check")
    public String loginCheck(String id, String pw, Model model) {

        model.addAttribute("grape111", id);
        model.addAttribute("ggg9999", pw);

        if (id.equals("grape111") && pw.equals("ggg9999")) {
            // 로그인 성공창
            model.addAttribute("result", "success");
        } else if (!id.equals("grape1111")) {
            // 아이디 오류창
            model.addAttribute("result", "f-id");
        } else if (!pw.equals("ggg9999")) {
            // 비밀번호 오류창
            model.addAttribute("result", "f-pw");
        }

        return "mvc/s-result";
    }


    // 2번 요청 다른 방법
/*
    @PostMapping("/s-login-check")
    public String sLoginCheck(String id, String pw, Model model) {

        String message;
        if (id.equals("grape111")) {
            if (password.equals("ggg9999")) {
                // 로그인 성공
                message = "success";
            } else {
                // 비밀번호 틀림
                message = "f-pw";
            }
        } else {
            // 아이디 없음
            message = "f-id";
        }
        model.addAttribute("result", message);

        return "mvc/s-result";
    }
 */

}
