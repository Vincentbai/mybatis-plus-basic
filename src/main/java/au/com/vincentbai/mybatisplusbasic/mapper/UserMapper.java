package au.com.vincentbai.mybatisplusbasic.mapper;

import au.com.vincentbai.mybatisplusbasic.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

// mapper接口继承了mybatis plus的BaseMapper来实现简单CRUD的功能
@Repository // 将 接口 或者 类 标识成持久层组件
public interface UserMapper extends BaseMapper<User> {

    Map<String, Object> selectMapById(Long id);

    // 通过年龄查询用户信息并分页
    Page<User> selectPageVo(@Param("page") Page<User> page,@Param("age") Integer age);

}
