package learn.alibaba.discovery.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{
    /**
     * 若要对service层进行监控与限流操作
     * 则需要使用 @SentinelResource 显示表明
     * @return
     */
    @SentinelResource(value = "hello",blockHandler = "exceptionHandler",fallback = "fallbackHandler")
    @Override
    public String hello() {
        return "Hello!!!";
    }

    /**
     *   fallback：失败调用，若本接口出现未知异常，则调用fallback指定的接口。
     *
     *   blockHandler：sentinel定义的失败调用或限制调用，若本次访问被限流或服务降级，则调用blockHandler指定的接口。
     * @return
     */
    @SentinelResource(value = "hello2",blockHandler = "exceptionHandler",fallback = "fallbackHandler")
    @Override
    public String hello2() {
        throw new RuntimeException("自定义异常---");
      //  return "Hello2222!!!";
    }

    public String exceptionHandler( BlockException e){
        System.out.println("降级");
        return "";
    }
    public String fallbackHandler(){
        System.out.println("熔断日志");
       return "熔断日志";
    }
}
