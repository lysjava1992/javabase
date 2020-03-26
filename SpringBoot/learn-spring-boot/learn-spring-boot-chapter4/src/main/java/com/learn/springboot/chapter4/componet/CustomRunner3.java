package com.learn.springboot.chapter4.componet;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @description:
 *  与CommandLineRunner相比 参数传播更丰富
 *  args.getNonOptionArgs();可以用来获取命令行中的无key参数（和CommandLineRunner一样）。
 *  args.getOptionNames();可以用来获取所有key/value形式的参数的key。
 *  args.getOptionValues(key));可以根据key获取key/value 形式的参数的value。
 *  args.getSourceArgs(); 则表示获取命令行中的所有参数。
 * @author: Mr.Luan
 * @create: 2020-03-26 14:51
 **/
@Component
@Order(98)
public class CustomRunner3 implements ApplicationRunner{
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("无key参数>>>"+nonOptionArgs);
        Set<String> optionNames = args.getOptionNames();
        for (String key : optionNames) {
            System.out.println("键值对参数>>>"+key + ":" + args.getOptionValues(key));
        }
        String[] sourceArgs = args.getSourceArgs();
        System.out.println("所有参数>>>"+ Arrays.toString(sourceArgs));
        System.out.println("====================【CustomRunner3】任务执行优先级98");
    }
}
