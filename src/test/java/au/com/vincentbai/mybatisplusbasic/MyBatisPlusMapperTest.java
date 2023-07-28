package au.com.vincentbai.mybatisplusbasic;

import au.com.vincentbai.mybatisplusbasic.mapper.UserMapper;
import au.com.vincentbai.mybatisplusbasic.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusMapperTest {

    /**
     * spring ioc中只能托管类的bean，不能托管接口的bean
     * 虽然 UserMapper是接口，但是他会被动生成个类放到ioc容器中
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        // 通过条件构造器查询一个list集合，若没有条件，则可以设置为null
        List<User> list = userMapper.selectList(null);

        list.forEach(System.out::println);

    }

    @Test
    public void testAdd(){

        User user = new User();

        user.setAge(18);
        user.setName("Vincent");
        user.setEmail("vincentbai319@gmail.com");

        int result = userMapper.insert(user);

        System.out.println(result); // 返回影响了多少几行数据
        System.out.println(user.getId()); // mybatis insert 后自动返回组件

    }

    @Test
    public void testDelete(){

        // 1. 通过ID进行删除
//        int result = userMapper.deleteById(1683740225760051201L);
//
//        System.out.println(result);

        // 2. 通过 map 键值对的方式删除
        // 根据 Map 中设置的条件来删除数据

//        Map<String, Object> whereMap = new HashMap<String, Object>();
//        whereMap.put("name", "Tom");
//        whereMap.put("age", 28);
//
//        int result = userMapper.deleteByMap(whereMap);
//
//        System.out.println(result);

        // 3. 通过ID进行批量删除
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println(result);

    }

    @Test
    public void testUpdate(){

        User user = new User();
        user.setId(4L);
        user.setName("Vincent");
        user.setEmail("vincentbai319@gmail.com");

        int result = userMapper.updateById(user);
        System.out.println(result);

    }

    @Test
    public void testSelect(){

        // 1. select by id
//        User user = userMapper.selectById(1L);
//        System.out.println(user);
        
        // 2. select by id list
//        List<Long> idList = Arrays.asList(1L, 2L, 3L);
//        List<User> userList = userMapper.selectBatchIds(idList);
//
//        userList.forEach(System.out::println);

        // 3. select by map conditions
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        map.put("name", "Jone");
//        map.put("age", 18);
//
//        List<User> userList = userMapper.selectByMap(map);
//        userList.forEach(System.out::println);

        // 4. select by wrapper
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);

    }

    @Test
    public void testOwnMethods(){

        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);

    }

    @Test
    public void testCount(){

        // there is no check count function in BaseMapper

    }


}
