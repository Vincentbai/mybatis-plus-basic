package au.com.vincentbai.mybatisplusbasic.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@TableName("t_user")
public class User {

    @TableId("uid")
    // 实现id自增： 1. 数据库中id设置自增 2. type设置为 IdType.AUTO
//    @TableId(value = "uid", type = IdType.AUTO) //
    private Long id;

    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    @TableLogic // 逻辑删除操作，设置了之后 BaseMapper中的删除操作都会变成修改操作，更新 is_deleted的值
    private Boolean is_deleted;

}
