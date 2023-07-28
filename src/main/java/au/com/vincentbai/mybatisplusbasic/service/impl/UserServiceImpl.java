package au.com.vincentbai.mybatisplusbasic.service.impl;

import au.com.vincentbai.mybatisplusbasic.mapper.UserMapper;
import au.com.vincentbai.mybatisplusbasic.pojo.User;
import au.com.vincentbai.mybatisplusbasic.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}
