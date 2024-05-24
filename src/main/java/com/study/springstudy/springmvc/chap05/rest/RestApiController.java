package com.study.springstudy.springmvc.chap05.rest;

import com.study.springstudy.database.chap01.Person;
import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/rest")
//@Controller
//@ResponseBody
@RestController // -> @Controller, @ResponseBody 다 들어있음
@RequiredArgsConstructor
public class RestApiController {

    private final BoardService boardService;

    @GetMapping("/hello")
    // @ResponseBody // 서버가 클라이언트에게 순수한 데이터를 전달할 때 사용
    public String hello() {
        return "안녕안녕 메롱메롱";
    }

    @GetMapping("/hobby")
    // @ResponseBody
    public List<String> hobby() {
        List<String> hobbies = List.of("태권도", "장기", "댄스");
        return hobbies;
    }

    // value = , produces = "application/json" 는 기본값 (생략가능)
    @GetMapping(value = "/person", produces = "application/json")
    // @ResponseBody
    public Person person() {
        Person p = new Person(100, "핑구", 10);
        return p;
    }

    @GetMapping("/board")
    public Map<String, Object> board() {

        List<BoardListResponseDto> list = boardService.findAll(new Search());

        HashMap<String, Object> map = new HashMap<>();
        map.put("page", new PageMaker(new Page(), boardService.getCount(new Search())));
        map.put("articles", list);

        return map;
    }

    /*
         RestController 에서 리턴타입을 ResponseEntity 를 쓰는 이유

         - 클라이언트에게 응답할 때는 메시지 바디안에 들어 있는 데이터도 중요하지만
         - 상태코드와 헤더정보를 포함해야 한다
         - 근데 일반 리턴타입은 상태코드와 헤더정보를 전송하기 어렵다
     */

    @GetMapping("/people")
    public ResponseEntity<?> people() {
        Person p1 = new Person(111, "딸기", 30);
        Person p2 = new Person(112, "망고", 30);
        Person p3 = new Person(113, "사과", 30);

        List<Person> people = List.of(p1, p2, p3);

        HttpHeaders headers = new HttpHeaders();
        headers.add("my-pet", "cat");
        headers.add("money", "100");

        return ResponseEntity
                .status(200) // 서버 상태: 200(문제없음), 400(클라이언트 잘못),
                            // 403(접근불가), 404(url 틀림), 500(서버문제)
                .headers(headers) // Response Headers 정보 추가 전송
                .body(people); // 전송할 people 데이터
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg
            ) {
                if (cm == null || kg == null) {
                    return ResponseEntity
                            .badRequest() // .status(400)
                            .body("키와 몸무게를 전달하세요!");
                }

                double m = cm / 100;
                double bmi = kg / (m * m);

                return ResponseEntity
                        .ok() // .status(200)
                        .body(bmi);
    }
}
