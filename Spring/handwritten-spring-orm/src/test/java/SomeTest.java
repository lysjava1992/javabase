import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SomeTest
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/9 10:38
 * @Version 1.0
 **/
public class SomeTest {
    public static void main(String[] args) {
        int[] arr=new int[10];
        int length=arr.length;
        for (int i=0;i<length;i++){
            arr[i]= (int) (Math.random()*100+1);
        }
        System.out.println(Arrays.toString(arr));
//        for (int i=0;i<length-1;i++){
//            for (int j=0;j<length-1-i;j++){
//                if(arr[j]>arr[j+1]){
//                    int temp=arr[j+1];
//                    arr[j+1]=arr[j];
//                    arr[j]=temp;
//                }
//            }
//        }


//          for (int i=0;i<length;i++){
//              int min=i;
//              for (int j=i;j<length;j++){
//                  if(arr[j]<arr[min]){
//                      min=j;
//                  }
//              }
//              if(min!=i){
//                  int temp=arr[i];
//                  arr[i]=arr[min];
//                  arr[min]=temp;
//              }
//          }

//        for (int i=1;i<length;i++){
//            for (int j=i;j>0;j--){
//             if(arr[j]<arr[j-1]){
//                 int temp=arr[j];
//                 arr[j]=arr[j-1];
//                 arr[j-1]=temp;
//             }
//            }
//        }
        for (int i=1;i<length;i++){
            int index=i;
            int temp=arr[i];
            for (int j=i-1;j>0;j--){
               if(arr[j]>arr[i]){
                  arr[j++]=arr[j];
                  index=j;
               }else {
                  break;
               }
            }

          arr[index]=temp;

        }
        System.out.println(Arrays.toString(arr));
    }
}
