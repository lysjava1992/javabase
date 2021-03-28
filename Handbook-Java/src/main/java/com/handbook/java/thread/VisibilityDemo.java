package com.handbook.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *  //设置参数，打印出jit编译的内容（非.java到.class的编译），而是运行时.class到汇编的编译
 *    可通过可视化工具jitwatch进行编译
 *  -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly  -XX:+LogCompilation -XX:LogFile=jit.log
 *
 *  JVM 有两种模式 Server模式和 Client模式   java -version可看当前模式
 *  在client模式下，不会指令重排序
 *  在server模式下，会发生指令重排序
 *             但    -Djava.compiler=NONE  禁止指令重排序 此时会正常结束
 * @ClassName VisibilityDemo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/28 15:53
 * @Version 1.0
 **/
public class VisibilityDemo {
    private boolean flag=true;

    public static void main(String[] args) throws InterruptedException {
        VisibilityDemo visibilityDemo=new VisibilityDemo();
        new Thread(()->{
            int i=0;
            // 发生指令重排序 相当于 if(visibilityDemo.flag){while(true){i++}}
            while (visibilityDemo.flag){
                i++;
            }
            System.out.println(i);
        }).start();
        TimeUnit.SECONDS.sleep(2);
        // 并不会停止线程
        visibilityDemo.flag=false;
        System.out.println(visibilityDemo.flag);
    }
}
