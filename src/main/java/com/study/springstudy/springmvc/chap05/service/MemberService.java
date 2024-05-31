package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.dto.request.LoginDto;
import com.study.springstudy.springmvc.chap05.dto.request.SignUpDto;
import com.study.springstudy.springmvc.chap05.entity.Member;
import com.study.springstudy.springmvc.chap05.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import static com.study.springstudy.springmvc.chap05.service.LoginResult.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원 가입 중간 처리
    public boolean join(SignUpDto dto) {
        // dto 를 엔터티로 변환
        Member member = dto.toEntity();

        // 비밀번호를 인코딩(암호화)
        String encodedPassword = encoder.encode(dto.getPassword());
        member.setPassword(encodedPassword);

//        member.setPassword(encoder.encode(dto.getPassword()));

        return memberMapper.save(member);
    }

    // 로그인 검증 처리
    public LoginResult authenticate(LoginDto dto, HttpSession session) {

        // 회원가입 여부 확인
        String account = dto.getAccount();
        Member foundMember = memberMapper.findOne(account);

        if (foundMember == null) {
            log.info("{} - 회원가입이 필요합니다.", account);
            return NO_ACC;
        }

        // 비밀번호 일치 검사
        String inputPassword = dto.getPassword(); // 클라이언트에 입력한 비밀번호
        String originPassword = foundMember.getPassword(); // 데이터베이스에 저장된 비밀번호

        // PasswordEncoder 에서는 암호화된 비밀번호를 내부적으로 비교해주는 기능을 제공
        if (!encoder.matches(inputPassword, originPassword)) {
            log.info("비밀번호가 일치하지 않습니다.");
            return NO_PW;
        }

        log.info("{}님 로그인 성공", foundMember.getName());

        // 세션의 수명 : 설정된 시간 OR 브라우저를 닫기 전까지
        int maxInactiveInterval = session.getMaxInactiveInterval();
//        session.setMaxInactiveInterval(60 * 60 * 24 * 90); // 세션의 수명 -> 하루 * 90 = 90일
        session.setMaxInactiveInterval(60 * 60); // 세션의 수명 1시간 설정
        log.debug("session time: {}", maxInactiveInterval);

        session.setAttribute("loginUserName", foundMember.getName());

        return SUCCESS;
    }

    // 아이디, 이메일 중복검사
    public boolean checkIdentifier(String type, String keyword) {
        return memberMapper.existsById(type, keyword);
    }
}
