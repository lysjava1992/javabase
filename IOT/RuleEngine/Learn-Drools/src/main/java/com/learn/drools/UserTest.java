package com.learn.drools;

import com.learn.drools.model.User;
import org.kie.api.KieServices;
import org.kie.api.internal.utils.KieService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class UserTest {


    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();

        // 会话对象,用于和规则引擎交互
        KieSession kieSession = container.newKieSession();

        User user = new User(18,"张三");
        //将数据提供给规则引擎
        //规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(user);
        //激活规则引擎,如果匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();


    }
}
