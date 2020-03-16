package com.handbook.java.datastructure.stack;

public class StackTest {
    public static void main(String[] args) {

        testSymbol("({}<>)");

    }

    /**
     * 字符串合法性检查
     * @param str
     */
    public static  void testSymbol(String str){
        char[] strs=str.toCharArray();
        CharStack as=new CharStack(20);
        for (char ar:strs) {
            if(ar=='{'||ar=='('||ar=='<'){
               as.instack(ar);
            }
            if(ar=='}'||ar==')'||ar=='>'){
                char ar2=as.outstack();
                System.out.println(ar2+"   "+ar);
                if(ar2=='{'&&ar!='}'){
                    System.out.print(ar+"  字符串不合法");
                    return;
                }
                if(ar2=='('&&ar!=')'){
                    System.out.print(ar+"  字符串不合法");
                    return;
                }
                if(ar2=='<'&&ar!='>'){
                    System.out.print(ar+"  字符串不合法");
                    return;
                }
            }
        }
        if (as.getCount()!=0){
            System.out.print("字符串不合法");
            return;
        }
        System.out.print("合法的字符串");
    }
    public void test1(){
        ArrayStack as=new ArrayStack(15);
        System.out.println("入栈顺序：");
        for (int i=1;i<20;i++){
            if(!as.instack(i+"")) {
                System.out.println("\n当前入栈失败: " + i);
                break;
            }
            System.out.print(i+" ");
        }
        System.out.println("\n出栈顺序：");
        while (true){
            String str=as.outstack();
            if(str==null)
                break;
            System.out.print(str+" ");
        }
    }
}
