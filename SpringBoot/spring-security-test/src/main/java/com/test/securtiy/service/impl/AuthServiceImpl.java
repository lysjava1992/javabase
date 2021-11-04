package com.test.securtiy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.test.securtiy.component.sms.SmsCode;
import com.test.securtiy.component.sms.SmsCodeMemory;
import com.test.securtiy.model.CustomUser;
import com.test.securtiy.model.Result;
import com.test.securtiy.repository.UserRepository;
import com.test.securtiy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;
    SmsCodeMemory smsCodeMemory=SmsCodeMemory.getInstance();
    @Override
    public Result smsCode(String phone) {
        CustomUser user=userRepository.findByPhone(phone);
        if(user==null){
            return new Result(false,"号码不存在");
        }
        SmsCode smsCode= smsCodeMemory.put(phone);
        try {
          //  Client client = createClient("", "");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",smsCode.getCode());
//            SendSmsRequest sendSmsRequest = new SendSmsRequest();
//            sendSmsRequest.setTemplateCode("SMS_162737300");
//            sendSmsRequest.setSignName("TOP01");
//            sendSmsRequest.setTemplateParam(jsonObject.toJSONString());
//            sendSmsRequest.setPhoneNumbers(user.getPhone());
//            SendSmsResponse smsResponse= client.sendSms(sendSmsRequest);
        }catch (Exception e){
            return new Result(false,"短信发送失败");
        }
        return new Result(true,smsCode.getCode());
    }
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }


}
