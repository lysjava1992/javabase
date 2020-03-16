import com.hand.written.orm.demo.model.Member;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.Table;
/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName JDBCtest
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/12 16:38
 * @Version 1.0
 **/

public class JDBCTest {
    public static void main(String[] args) {
       Member member=new Member();
       //member.setAge(25);
        member.setName("李四");
        select(member);
    }
    public static void select(Object object){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.1.6:3306/user?characterEncoding=UTF-8&rewriteBatchedStatements=true","root","root");
            Class clazz=object.getClass();

            Table table = (Table) clazz.getAnnotation(Table.class);
            String sql="SELECT * FROM "+table.name();
            StringBuilder sb=new StringBuilder(sql+" WHERE 1=1");
            Field[] fields=clazz.getDeclaredFields();
            for (Field f:fields){
                   f.setAccessible(true);
                   Object value=f.get(object);
                   if(value!=null){
                        if(f.getType()==String.class){
                            sb.append(" AND "+f.getName()+" = '"+value+"'");
                        }else {
                            sb.append(" AND "+f.getName()+" = "+value);
                        }
                   }
            }
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sb.toString());
            while (rs.next()){
                Object instance = clazz.newInstance();
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++){
                     String filedName=rs.getMetaData().getColumnName(i);
                    Field field=clazz.getDeclaredField(filedName);
                    field.setAccessible(true);
                    field.set(instance,rs.getObject(filedName));
                }
             System.out.println(instance);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
