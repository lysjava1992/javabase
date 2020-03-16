package com.handbook.java.designmode.proxy.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CustomProxy
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/12 16:19
 * @Version 1.0
 **/
public class CustomProxy {
    public static Object newProxyInstance(CustomClassLoader loader,
                                          Class<?>[] interfaces,
                                          CustomInvocationHandler h) {
        try {
            // 1.动态生成 .java文件
            String src=  generateSrc(interfaces);
            // 2. .java 输出磁盘
            String filePath=CustomProxy.class.getResource("").getPath();
            File f=new File(filePath+"$CustomProxy0.java");
            FileWriter fw= new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();
            // 3.  .java编译 .class
            JavaCompiler compiler= ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager=compiler.getStandardFileManager(null,null,null);
            Iterable iterable=manager.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task=compiler.getTask(null,manager,null,null,null,iterable);
            task.call();
            manager.close();
            // 4.  .class加载到JVM
            Class proxyClass=loader.findClass("$CustomProxy0");
            Constructor c=proxyClass.getConstructor(CustomInvocationHandler.class);
            // 5.  返回代理对象
        return c.newInstance(h);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;
    }
    private final static String ln="\r\n";
    private static String generateSrc(Class<?>[] interfaces) {
          StringBuilder sb=new StringBuilder();
        sb.append("package com.handbook.java.designmode.proxy.custom;" +ln);
        sb.append("import com.handbook.java.designmode.proxy.Person;"+ln);
        sb.append("import java.lang.reflect.Method;"+ln);
        sb.append("public class $CustomProxy0 implements "+interfaces[0].getName()+"{"+ln);
        sb.append("CustomInvocationHandler h;"+ln);
        sb.append("public $CustomProxy0(CustomInvocationHandler h) {"+ln);
                   sb.append("this.h=h;");
        sb.append("}"+ln);

             for(Method m:interfaces[0].getMethods()){
                   sb.append("public "+m.getReturnType().getName()+" "+m.getName()+"() { " +ln);
                   sb.append("try { "+ln);
                     sb.append("Method m ="+interfaces[0].getName()+".class.getMethod(\""+m.getName()+"\",new Class[]{});"+ln);
                     sb.append("this.h.invoke(this,m,null);"+ln);
                     sb.append("}catch (Throwable e){"+ln);
                    sb.append("e.printStackTrace();"+ln);
                    sb.append("}");
              sb.append("}");
        }
          sb.append("}"+ln);

        return sb.toString();
    }
}














