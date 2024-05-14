package com.study.springstudy.webservlet.chap02.v1.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.entity.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowController implements ControllerV1 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 적절한 저장소에서 회원정보들을 가져옴
        List<Member> memberList = repo.findAll();

        // 2. 해당 회원정보를 JSP파일에 전송하기 위한 세팅을 함
        request.setAttribute("memberList", memberList);

        // 3. 적절한 JSP를 찾아 화면 렌더링
        String viewName = "/WEB-INF/views/v1/m-list.jsp";

        RequestDispatcher dp = request.getRequestDispatcher(viewName);
        dp.forward(request, response);
    }
}
