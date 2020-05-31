package com.blog.pro.service.impl;

import com.blog.pro.entity.Blog;
import com.blog.pro.mapper.BlogMapper;
import com.blog.pro.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
