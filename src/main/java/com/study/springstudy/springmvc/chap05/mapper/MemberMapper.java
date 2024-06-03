package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap05.dto.request.AutoLoginDto;
import com.study.springstudy.springmvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    // 회원 가입
    boolean save(Member member);

    // 회원 정보 개별 조회
    Member findOne(String account);

    /**
     * 중복 확인 (아이디, 이메일)
     * @param type - 어떤 것을 중복검사할지 (ex: account OR email)
     * @param keyword - 중복검사할 실제값
     * @return - 중복이면 true, 아니면 false
     */
    // 값 두개 이상일때는 @Param 붙여주기
    boolean existsById(@Param("type") String type, @Param("keyword") String keyword);

    // 자동 로그인 쿠키값, 만료시간 업데이트
    void updateAutoLogin(AutoLoginDto dto);

    // 세션아이디로 회원정보 조회
    Member findMemberBySessionId(String sessionId);
}
