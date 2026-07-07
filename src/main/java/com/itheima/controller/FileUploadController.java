package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOssUtil;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    public Result<String> upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();

        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String url=AliOssUtil.uploadFile(filename,file.getInputStream());
        //file.transferTo(new File("C:\\Users\\张林\\Desktop\\files\\"+filename));
        return Result.success(url);
    }
}
