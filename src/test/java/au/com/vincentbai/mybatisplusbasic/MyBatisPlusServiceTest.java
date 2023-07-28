package au.com.vincentbai.mybatisplusbasic;

import au.com.vincentbai.mybatisplusbasic.pojo.User;
import au.com.vincentbai.mybatisplusbasic.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetCount(){

        long count = userService.count();
        System.out.println("Total count: " + count);

    }

    @Test
    public void testInsertMulti(){

        // 批量添加 单个sql语句，通过循环操作，实现了批量添加，并不是将所有要添加的数据都写在一个sql里

        List<User> list = new ArrayList<User>();

        for (int i = 0; i <= 10; i++) {
            User user = new User();
            user.setName("Vincent" + i);
            user.setAge(20 + i);
            list.add(user);
        }

        boolean b = userService.saveBatch(list);

        System.out.println(b);
    }

}
