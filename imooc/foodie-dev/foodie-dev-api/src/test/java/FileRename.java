import java.io.File;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName FileNama
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/2/9 10:38
 * @Version 1.0
 **/
public class FileRename {
    public static void main(String[] args) {
        String path="F:\\Desktop\\课程\\慕课网\\直通车\\阶段一：单体电商项目架构，开发与上线（1~5周）\\第2周.分类，推荐，搜索，评价，购物车开发";

        rename(new File(path),path);
    }

    private static void rename(File file,String path) {
        if (file.isDirectory()){
           File[] files= file.listFiles();
            for (File temp : files) {
                rename(temp,path);
            }
        }else {
           String fileName= file.getName();
           fileName=  fileName.replace("_[更多精品资源www.cx1314.cn]","");
          file.renameTo(new File(path+"\\"+fileName));
        }
    }
}
