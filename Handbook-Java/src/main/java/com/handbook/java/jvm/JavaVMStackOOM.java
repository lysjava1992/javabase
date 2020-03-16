package com.handbook.java.jvm;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *    栈溢出
 *    配置
 *     -Xss5M
 * @ClassName javaVMStrackOOM
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/2 18:43
 * @Version 1.0
 **/
public class JavaVMStackOOM {
    private void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
           new Thread(()->dontStop()).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom=new JavaVMStackOOM();
        oom.stackLeakByThread();
        //系统死机
    }
}
