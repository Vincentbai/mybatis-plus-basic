package au.com.vincentbai.mybatisplusbasic;

import au.com.vincentbai.mybatisplusbasic.mapper.UserMapper;
import au.com.vincentbai.mybatisplusbasic.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;

import javax.management.QueryEval;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){

        // 查询用户名中包含a，年龄在20 和 30 之间，邮箱信息不能为null的用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();

        userQueryWrapper.like("user_name", "a").between("age", 20, 30).isNotNull("email");

        List<User> userList = userMapper.selectList(userQueryWrapper);
        userList.forEach(System.out::println);


    }

    @Test
    public void testSorting(){

        // 需求：查询用户信息，按照年龄的降序排序，如果年龄相同，则按照id的升序排序

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.orderByDesc("age").orderByAsc("uid");

        List<User> userList = userMapper.selectList(userQueryWrapper);

        userList.forEach(System.out::println);

    }

    @Test
    public void testDelete(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.isNull("email");

        int delete = userMapper.delete(userQueryWrapper);

        System.out.println(delete);

    }

    @Test
    public void testUpdate(){

        // 需求：将（年龄大于20并且用户名中包含 a）或者邮箱为null的用户信息进行修改
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.gt("age", 20).like("user_name", "a").or().isNull("email");

        User user = new User();

        user.setName("HelloWorld!");
        user.setEmail("test@gmail.com");

        int update = userMapper.update(user, userQueryWrapper);

        System.out.println(update);

    }

    @Test
    public void testLambda(){

        // 需求：将用户名中包含 a 并且（年龄大于20并且或者邮箱为null）的用户信息进行修改
        // 和上个需求优先级不一样
        // lambda中的条件优先执行

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.like("user_name", "a").and(i->i.gt("age", 20).or().isNull("email"));

        User user = new User();

        user.setName("Jessie");
        user.setEmail("lambda@gmail.com");

        int update = userMapper.update(user, userQueryWrapper);

        System.out.println(update);

    }

    @Test
    public void testQueryWrapperSelect(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.select("user_name", "age");

//        List<User> userList = userMapper.selectList(userQueryWrapper);
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);

        maps.forEach(System.out::println);

    }

    @Test
    public void testInner(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();

        userQueryWrapper.inSql("uid", "select uid from t_user where uid < 4");

        List<User> userList = userMapper.selectList(userQueryWrapper);

        userList.forEach(System.out::println);
    }

    @Test
    public void testUpdateWrapperWithData(){

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();

        userUpdateWrapper.like("user_name", "a").and(i->i.gt("age", 20).or().isNull("email"));

        userUpdateWrapper.set("user_name", "updateWrapperData").set("email", "updataWrapper@gmail.com");

        int update = userMapper.update(null, userUpdateWrapper);

        System.out.println(update);


    }

    @Test
    public void testWrapperWithCondition(){

        String username = "";
        Integer minAge = 20;
        Integer maxAge = 22;

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        // 繁琐的写法
        if(StringUtils.isNotBlank(username)){

            System.out.println("1");

            userQueryWrapper.like("user_name", username);
        }

        if( minAge != null){
            System.out.println("2");
            userQueryWrapper.gt("age", minAge);
        }


        if (maxAge != null ) {
            System.out.println("3");
            userQueryWrapper.lt("age", maxAge);
        }

        // 简洁的写法
//        userQueryWrapper.like(StringUtils.isNotBlank(username), "user_name", username).gt(minAge!=null, "age", minAge).lt(maxAge!=null, "age", maxAge);


        List<User> userList = userMapper.selectList(userQueryWrapper);

        userList.forEach(System.out::println);

    }

    @Test
    public void testLambdaQueryWrapper(){

        String username = "";
        Integer minAge = 20;
        Integer maxAge = 22;

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();

        userLambdaQueryWrapper.like(StringUtils.isNotBlank(username), User::getName, username).gt(minAge!=null, User::getAge, minAge).lt(maxAge!=null, User::getAge, maxAge);

        List<User> userList = userMapper.selectList(userLambdaQueryWrapper);

        userList.forEach(System.out::println);

    }

    @Test
    public void testLambdaUpdateWrapper(){

        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        userLambdaUpdateWrapper.like(User::getName, "a").and(i-> i.gt(User::getAge, 20).or().isNull(User::getEmail));

        userLambdaUpdateWrapper.set(User::getName, "testLambdaUpdateWrapper").set(User::getEmail, "testLambdaUpdateWrapper@gmail.com");

        int update = userMapper.update(null, userLambdaUpdateWrapper);

        System.out.println(update);

    }


}
