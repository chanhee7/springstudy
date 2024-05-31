package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.repository.BoardRepository;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

//    private final BoardRepository boardRepository;
    private final BoardService service;

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(@ModelAttribute("s") Search page, Model model) {

        // 1. 데이터베이스로 부터 게시글 목록 조회
//        List<Board> boardList = boardRepository.findAll();
        // 서비스에게 조회 요청 위임
        List<BoardListResponseDto> bList = service.findAll(page);
        // 페이지 정보를 생성하여 JSP 에게 전송
        PageMaker maker = new PageMaker(page, service.getCount(page));

        // 3. JSP 파일에 해당 목록데이터를 보냄
        model.addAttribute("bList", bList);
        model.addAttribute("maker", maker);
//        model.addAttribute("s", page);

        return "board/list";
    }


    // 2. 글 쓰기 양식 화면 열기 요청 (/board/write : GET)
    @GetMapping("/write")
    public String open() {
        return "board/write";
    }


    // 3. 게시글 등록 요청 (/board/write : POST)
    // -> 목록조회 요청 리다이렉션
    @PostMapping("/write")
    public String save(BoardWriteRequestDto dto, HttpSession session) {

        // 1. 브라우저가 전달한 게시글 내용 읽기

        // 2. 해당 게시글을 데이터베이스에 저장하기 위해  엔터티 클래스로 변환
//        Board b = dto.toEntity();

        // 3. 데이터베이스 저장 명령
        service.save(dto, session);

        return "redirect:/board/list";
    }


    // 4. 게시글 삭제 요청 (/board/delete : GET)
    // -> 목록조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(int bno) {

        service.delete(bno);
        return "redirect:/board/list";
    }


    // 5. 게시글 상세 조회 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(int bno, Model model, HttpServletRequest request) {

        // 1. 상세조회하고 싶은 글번호를 읽기
        System.out.println("bno = " + bno);

        // 2. 데이터베이스로부터 해당 글번호 데이터 조회하기
//        Board board = boardRepository.findOne(bno);
//        if (board != null) boardRepository.viewCount(bno);
        BoardDetailResponseDto dto = service.findOne(bno);

        // 3. JSP 파일에 조회한 데이터 보내기
        model.addAttribute("b", dto);

        // 4. 요청 헤더를 파싱하여 이전 페이지의 주소를 얻어냄
        String ref = request.getHeader("Referer");
        model.addAttribute("ref", ref);

        return "board/detail";
    }

}
