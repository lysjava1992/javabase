package learn.netflix.eureka.consumer.feign.controller;

import learn.netflix.eureka.consumer.feign.service.ServiceApi;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

@RestController
public class ServiceController {


    @Autowired
    ServiceApi serviceApi;


    @GetMapping("/info")
    public Map info(){
        // 发起服务调用
        return serviceApi.info();
    }
    @GetMapping("/info2")
    public Map info2(){
        // 发起服务调用
        return serviceApi.info2();
    }
    @GetMapping("/file")
    public String file(){

        File file = new File("F:\\Desktop\\TEXT.txt");
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.TEXT_PLAIN_VALUE, true, file.getName());

        try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multi = new CommonsMultipartFile(fileItem);
      return   serviceApi.handleFileUpload(multi);
    }



}
