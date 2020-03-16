import com.hand.written.orm.demo.dao.MemberDao;
import com.hand.written.orm.demo.model.Member;
import com.hand.written.orm.framework.QueryRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

/**
 *
 * @ClassName AppTest
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/11 17:16
 * @Version 1.0
 **/
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {
    @Autowired
    MemberDao memberDao;
    @Test
    @Ignore
    public void testSave() throws Exception {
       for (int i=0;i<10;i++){
           Member member=new Member("张三"+i,"北京"+i,20+i);
           System.out.println(memberDao.insert(member));
       }
    }
    @Test
    @Ignore
    public void  testUpdate()throws Exception{
        Member member=new Member("李四","上海",25);
        member.setId(1L);
        System.out.println(memberDao.update(member));
    }
    @Test
    @Ignore
    public void testSelect() throws Exception {
        Member member=memberDao.selectOne(1L);
        System.out.println(member);
    }
    @Test
    @Ignore
    public void testSelect2() throws Exception {
        QueryRule queryRule=QueryRule.getInstance();
        queryRule.andLike("name","Tom");
        queryRule.addAscOrder("age");
        List<Member> list=memberDao.selectByQueryRule(queryRule);
        list.stream().forEach(System.out::println);
    }
    @Test
    public void testSelect3()throws Exception{
          QueryRule queryRule=QueryRule.getInstance();
          queryRule.andBetween("age",15,25);
          queryRule.addAscOrder("id");
          queryRule.addAscOrder("age");
          List<Member> list= memberDao.selectByQueryRule(queryRule);
          list.stream().forEach(System.out::println);
    }
}
