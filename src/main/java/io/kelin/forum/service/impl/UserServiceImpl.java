package io.kelin.forum.service.impl;

import io.kelin.forum.entity.User;
import io.kelin.forum.mapper.UserMapper;
import io.kelin.forum.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
