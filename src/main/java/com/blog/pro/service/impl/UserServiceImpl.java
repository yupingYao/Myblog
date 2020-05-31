package com.blog.pro.service.impl;

import com.blog.pro.entity.User;
import com.blog.pro.mapper.UserMapper;
import com.blog.pro.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yaoyp
 * @since 2020-05-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
