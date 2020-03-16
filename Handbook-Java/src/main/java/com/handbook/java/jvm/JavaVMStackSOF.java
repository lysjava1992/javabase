package com.handbook.java.jvm;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *   栈溢出
 *   配置： -Xss128k
 *         栈容量
 * @ClassName JavaVMStackSOF
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/2 18:35
 * @Version 1.0
 **/
public class JavaVMStackSOF {
    private int stackLength=1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom=new JavaVMStackSOF();
        try {
            oom.stackLeak();
        }catch (Throwable e){
           // Throwable 包括exception（异常）和error（错误）。
            System.out.println("stack length： 【"+oom.stackLength+"】");
            throw e;

        }
   /**
     * stack length： 【982】
     * Exception in thread "main" java.lang.StackOverflowError
     */
    }
}
