package com.handbook.io;


import com.handbook.io.entity.Person;

import java.io.*;

/**
 * 文件操作  针对不同数据源 字符流和字节流都有相似的操作
 * @description:
 * 字符流  以char为单位进行的读写 因此一下在操作文本特别是中文时优先使用
 *
 *     Reader    所有【字符输入流】的抽象父类
 *                 InputStreamReader     字符输入流/转换流                    【extends Reader】
 *                                                         FileReader  专门读取文件     【extends InputStreamReader】
 *                 BufferedReader        字符缓冲流（按行读取）              【extends Reader】
 *                 StringReader       数据源为String                         【extends Reader】
 *                CharArrayReader     数据源为字符数组                       【extends Reader】
 *                 PipedReader       管道线程通信                            【extends Reader】
 *                 FilterReader      过滤流                                  【extends Reader】
 *                                                       PushbackReader     流不在是单向          【extends FilterReader 】
 *     Writer    所有【字符输出流】的抽象父类
 *
 *               OutputStreamWriter     字符输出流/转换流                    【extends Writer】
 *                                                        FileWriter   专门写入文件      【extends OutputStreamWriter】
 *                BufferedWriter        有换行功能                           【extends Writer】
 *                PrintWriter           字符打印流print()prinln（）          【extends Writer】
 *                 StringWriter        数据源为String                        【extends Reader】
 *                CharArrayWriter     数据源为字符数组                       【extends Writer】
 *                 PipedWriter       管道线程通信                            【extends Writer】
 *                 FilterWriter      过滤流                                  【extends Reader】
 *
 * 字节流
 *
 *    InputStream  所有【字节输入流】的抽象父类
 *                 FileInputStream      文件输入流（字节）             【extends InputStream】
 *                 FilterInputStream    过滤流装饰，包装其他流         【extends InputStream】
 *                                                     DataInputStream   读取Java的基本类型（与机器无关）   【extends FilterInputStream】
 *                                                 BufferedInputStream                                      【extends FilterInputStream】
 *                 ObjectInputStream     java对象                       【extends InputStream】
 *                 ByteArrayInputStream   字节组                        【extends InputStream】
 *                  PipedInputStream       线程通信管道                 【extends InputStream】
 *                   SequenceInputStream   流合并                       【extends InputStream】
 *    OutputStream  所有【字节输入流】的抽象父类
 *                 FileOutputStream      文件输出流（字节）             【extends OutputStream】
 *                 FilterOutputStream    过滤流装饰，包装其他流         【extends OutputStream】
 *                                                     DataOutputStream   写入Java的基本类型（与机器无关）  【extends  FilterOutputStream】
 *                                                 BufferedOurputStream                                     【extends  FilterOutputStream】
 *                                                    PrintStream          可换行，打印                      【extends  FilterOutputStream】
 *                 ObjectOurputStream     java对象                      【extends OutputStream】
 *                 ByteArrayInputStream   字节组                        【extends InputStream】
 *                 PipedOurputStream       线程通信管道                 【extends OutputStream】
 * 对于文件操作 中文文档还是使用BufferedReader更合适 使用字节流在byte[]不能一次读完时会造成错误
 *   对于其他图片 视频不能使用字符传输
 *
 *     字节——》字符读入     InputStreamReader isr=new InputStreamReader(new FileInputStream(path));
 *     字符 ——》字节输出       OutputStreamWriter  bw = new OutputStreamWriter(new FileOutputStream(path));
 * @author: Mr.Luan
 * @create: 2019-10-23 09:05
 **/
public class StreamFile {
    public static void main(String[] args) {

        String path="F:/desktop/dir/io2.txt";
        String bigPath = "F:/desktop/软件设计师教程 第5版.pdf";
        //字符流读写操作
       //  readCharByFileReader(path);
       //  readCharByFileReaderAndArray(path);
       //  readCharByBufferedReader(path);
       //  writeByFileWriter(path);
       //  writeByBufferedWriter(path);
        //字节流操作
        //  readByteByFileInputStream(path);
          //   readByteByBufferedInputStream(path);
        //  writeByteByFilOutPutStrem(path);
           // writeByteByBufferedInputStream(path);
        //     byteByFilterInputStream(path);
       //  pushbackReaderTest(path);
            ObjectReadAndWrite(path);
    }

    private static void ObjectReadAndWrite(String path) {
        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(path));
            Person  person=new Person(15,180,67.8,"Tom","山东济南");
            oos.writeObject(person);
            oos.flush();
            oos.close();
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(path));
           Person person1= (Person) ois.readObject();
           System.out.println(person1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * PushbackReader 使流不在是单向操作
     *  在操作流时加入数据
     *  不会改变源数据
     * @param path
     */
    private static void pushbackReaderTest(String path) {

        try {
            StringReader sr=new StringReader("1234567891");
            PushbackReader pis=new PushbackReader(sr,100);
            StringBuilder result=new StringBuilder();
            char[] arr=new char[5];
            pis.read(arr);
            result.append(arr);
            pis.unread(new char[]{'a','b','c'});
            pis.read(arr);
            result.append(arr);
          pis.close();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void writeByteByBufferedInputStream(String path) {
        try {
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(path));
            bos.write("缓冲流 读  相比FileInputStream有了缓冲区".getBytes());
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓冲流 读  相比FileInputStream有了缓冲区
     * @param path
     */
    private static void readByteByBufferedInputStream(String path) {
        try {
            BufferedInputStream bis=new BufferedInputStream(new FileInputStream(path));
            byte[] b=new byte[10];
            int length = 0 ;
            while( (length = bis.read( b ) )!= -1 ){
                   System.out.print(new String(b,0,length));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * DataOutputStream
     * DataInputStream
     * 可以直接在文件中写入java 基本类型
     * 注意 读取的顺序要和写入的顺序一致
     * @param path
     */
    private static void byteByFilterInputStream(String path) {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
            DataInputStream dis=new DataInputStream(new FileInputStream(path));
            dos.writeInt(1266);
            dos.writeUTF("name");
            dos.writeUTF("ppppp");
            dos.writeBoolean(true);
            dos.close();
            System.out.println(dis.readInt());
            System.out.println(dis.readUTF());
            System.out.println(dis.readUTF());
            System.out.println(dis.readBoolean());
            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeByteByFilOutPutStrem(String path) {
        try {
             //new FileOutputStream(path,true) 追加写
            FileOutputStream fos=new FileOutputStream(path);
            fos.write("FileOutputStream".getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 字节输入流 读取文件
     * @param path
     */
    private static void readByteByFileInputStream(String path) {
        try {
            FileInputStream fis=new FileInputStream(path);
            //字节数组  是二进制数据 所以此处一次性读完在转成String
            byte[] bytes=new byte[1024];
            fis.read(bytes);
            System.out.print(new String(bytes,0,bytes.length));
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符流写操作
     * @param path
     */
    private static void writeByBufferedWriter(String path) {
        BufferedWriter bw=null;
        try {
            //覆盖写  bw=new BufferedWriter(new FileWriter(path));
            //追加写
            bw=new BufferedWriter(new FileWriter(path,true));
            for (int i=0;i<10;i++){
                bw.write("第"+i+"行");
                //换行
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *字符流写操作
     * @param path
     */
    private static void writeByFileWriter(String path) {
        try {
            //不存在会创建 会覆盖写
            FileWriter fw=new FileWriter(path);
            fw.write("开始写");
            fw.flush();
            fw.close();

            //追加写
            FileWriter fw2=new FileWriter(path,true);
            fw2.write("追加写");
            fw2.flush();
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * BufferedReader  缓冲流 高级流
     *  自动根据换行符
     *  一行一行读出
     * @param path
     */
    public static void readCharByBufferedReader(String path){
                   BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(path));
            String lin="";
            while ((lin=br.readLine())!=null){
                System.out.println(lin);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以字符为单位读取 返回 int强转为char
     * @param path
     */
    public static void readCharByFileReader(String path) {
        FileReader in = null;
        try {
            long start = System.currentTimeMillis();
            in = new FileReader(path);
            int chr;
            while ((chr = in.read()) != -1) {
                System.out.print((char) chr);
            }
            long end = System.currentTimeMillis();
            System.out.println();
            System.out.println("char 耗时: " + ((end - start) / 1000) + "S");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *数组读取 速度快
     * 需要自己控制指针
     * @param path
     */
    public static void readCharByFileReaderAndArray(String path) {
        FileReader in = null;
        try {
            long start = System.currentTimeMillis();
            in = new FileReader(path);

           char[] arr=new char[1024];
           int len=0;
            while ((len=in.read(arr))!=-1) {
                System.out.print(new String(arr,0,len));
            }
            long end = System.currentTimeMillis();
            System.out.println();
            System.out.println("char[] 耗时: " + ((end - start) / 1000) + "S");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
