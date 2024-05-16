package com.study.springstudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/coffee/*")
public class CoffeeController {

    /**
     * GET 요청만 받겠다
     * @request-uri : /coffee/order
     * @forwarding-jsp : /WEB-INF/views/mvc/coffee-form.jsp
     */
//    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @GetMapping("/order")
    public String order() {
        return "mvc/coffee-form";
    }

    // POST 요청만 받겠다
//    @RequestMapping(value = "/result", method = RequestMethod.POST)
    @PostMapping("/result")
    public String result(
            // @RequestParam 생략되어있음
            String menu, int price, Model model) {

        // 1. 주문 데이터 (menu, price) 읽어오기


        // 2. jsp에 보내서 렌더링
        model.addAttribute("mmm", menu);
        model.addAttribute("ppp", price);


        return "mvc/coffee-result";
    }
}
