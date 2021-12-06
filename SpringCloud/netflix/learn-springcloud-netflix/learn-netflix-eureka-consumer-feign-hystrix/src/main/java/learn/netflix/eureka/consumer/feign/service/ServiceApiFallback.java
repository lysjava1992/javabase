package learn.netflix.eureka.consumer.feign.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceApiFallback  implements ServiceApi{
 static Map<String,String>map=new HashMap<>();
  static {
      map.put("status","服务不在线");
  }
    @Override
    public Map info() {
        return map;
    }

    @Override
    public Map info2() {
        return map;
    }

    @Override
    public String handleFileUpload(MultipartFile file) {
        String result="Try again later";
        return result;
    }
}
