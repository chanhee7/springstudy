package com.study.springstudy.springmvc.chap04.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;
//    private final BoardRepository boardRepository;

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(Model model) {

        // 1. 데이터베이스로 부터 게시글 목록 조회
//        List<Board> boardList = boardRepository.findAll();
//
//        // 2. 클라이언트에 데이터를 보내기전에 렌더링에 필요한
//        //    데이터만 추출하기
////        boardList.stream()
////                .map(board -> new BoardListResponseDto())
////                .collect(Collectors.toList());
//
//        List<BoardListResponseDto> bList = new ArrayList<>();
//
//        for (Board b : boardList) {
//            BoardListResponseDto dto = new BoardListResponseDto(b);
//            bList.add(dto);
//        }
//        boardRepository.findAll();

        // 서비스에게 조회 요청 위임
        List<BoardListResponseDto> all = service.findAll();

        // 3. JSP 파일에 해당 목록데이터를 보냄
        model.addAttribute("bList", all);

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
    public String save(BoardWriteRequestDto dto) {

        // 1. 브라우저가 전달한 게시글 내용 읽기

        // 2. 해당 게시글을 데이터베이스에 저장하기 위해  엔터티 클래스로 변환
//        Board b = dto.toEntity();

        // 3. 데이터베이스 저장 명령
//        service.save(b);

        service.save(dto);
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
    public String detail(int bno, Model model) {

        // 1. 상세조회하고 싶은 글번호를 읽기
        System.out.println("bno = " + bno);

        // 2. 데이터베이스로부터 해당 글번호 데이터 조회하기
//        Board board = boardRepository.findOne(bno);
//        if (board != null) boardRepository.viewCount(bno);
        BoardDetailResponseDto dto = service.findOne(bno);

        // 3. JSP 파일에 조회한 데이터 보내기
        model.addAttribute("b", dto);

        return "board/detail";
    }

}
