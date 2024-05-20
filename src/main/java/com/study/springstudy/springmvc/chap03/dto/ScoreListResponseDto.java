package com.study.springstudy.springmvc.chap03.dto;

import com.study.springstudy.springmvc.chap03.entity.Score;
import lombok.Getter;

@Getter
public class ScoreListResponseDto {

    private long stuNum; // 학번
    private String maskingName; // 학생 이름 (첫글자 빼고 모두 *처리)
    private double average;
    private String grade;

    public ScoreListResponseDto(Score s) {
        this.stuNum = s.getStuNum();
        this.maskingName = makeMaskingName(s.getStuName());
        this.average = s.getAverage();
        this.grade = s.getGrade().toString();
    }

    private String makeMaskingName(String stuName) {
        // 첫번째 글자 뽑아오기
        char firstLetter = stuName.charAt(0);

        String maskedName = "" + firstLetter;
        for (int i = 0; i < stuName.length() - 1; i++) {
            maskedName += "*";
        }

        return maskedName;
    }
}
