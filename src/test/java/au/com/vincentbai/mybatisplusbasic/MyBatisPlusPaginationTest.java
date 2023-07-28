package au.com.vincentbai.mybatisplusbasic;

import au.com.vincentbai.mybatisplusbasic.mapper.UserMapper;
import au.com.vincentbai.mybatisplusbasic.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPaginationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage(){

        Page<User> page = new Page<>(2, 3);

        Page<User> userPage = userMapper.selectPage(page, null);

        System.out.println(userPage);

        System.out.println(userPage.getRecords()); // 返回一个user对象的list
        System.out.println(userPage.getPages()); // 返回总页数
        System.out.println(userPage.getTotal()); // 返回总记录数
        System.out.println(userPage.hasNext()); // 有没有下一页
        System.out.println(userPage.hasPrevious()); // 有没有上一页

    }

    @Test
    public void testCustomeQueryWithPage(){

        Page<User> userPage = new Page<>(2, 3);

        Page<User> page = userMapper.selectPageVo(userPage, 18);

        System.out.println(page);


    }



}
