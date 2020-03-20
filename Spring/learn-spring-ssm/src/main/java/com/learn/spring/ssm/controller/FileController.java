package com.learn.spring.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-20 13:50
 **/
@RequestMapping("img")
@Controller
public class FileController {

    @GetMapping("")
    public String img(){
        return "img";
    }

    @PostMapping("upload")
    public String upload(MultipartFile file) throws Exception {
        if(file!=null){
            //原始图片名称
            String originalFilename = file.getOriginalFilename();
            //如果没有图片名称，则上传不成功
            if(originalFilename != null && originalFilename.length()>0)
            {

                String picPath = "E:/img/";

                LocalDateTime localDateTime=LocalDateTime.now();
                int year=localDateTime.getYear();
                int month=localDateTime.getMonthValue();
                int day=localDateTime.getDayOfMonth();
                picPath= picPath+year+"/"+month+"/"+day+"/";

           //     File newFile = new File(picPath+originalFilename);
                File newFile = new File(picPath);
                if(!newFile.exists()&&!newFile.isDirectory()) {
                    newFile.mkdirs();
                }
                file.transferTo(newFile);
            }
        }
        return "img";
    }
}
