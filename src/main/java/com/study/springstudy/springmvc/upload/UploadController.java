package com.study.springstudy.springmvc.upload;

import com.study.springstudy.springmvc.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
public class UploadController {

    // 업로드 루트 경로
    private String rootPath = "D:/spring-prj/upload";

    @GetMapping("/upload/form")
    public String uploadForm() {
        return "upload/upload-form";
    }

    // MultipartFile -> 파일 받기
    @PostMapping("/upload/file")
    public String uploadFile(MultipartFile thumbnail) {
        log.info("file-name: {}", thumbnail.getOriginalFilename());
        log.info("file-size: {}MB", thumbnail.getSize() / 1024.0 / 1024.0);
        log.info("file-type: {}", thumbnail.getContentType());

        // 첨부파일 서버에 저장하기
        // 1. 루트 디렉토리를 생성
        File root = new File(rootPath);
        if (!root.exists()) root.mkdirs();

        FileUtil.uploadFile(rootPath, thumbnail);

        // 2. 첨부파일의 경로를 만들어서 파일 객체로 포장
//        File uploadFile = new File(rootPath, thumbnail.getOriginalFilename());

        // 3. MultipartFile 객체로 저장명령
//        try {
//            thumbnail.transferTo(uploadFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "redirect:/upload/form";
    }

}
