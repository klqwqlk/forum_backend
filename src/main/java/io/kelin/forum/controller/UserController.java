package io.kelin.forum.controller;


import io.kelin.forum.entity.User;
import io.kelin.forum.service.UserService;
import io.kelin.forum.util.CommonResult;
import io.kelin.forum.util.EncryptUtil;
import io.kelin.forum.util.JwtUtil;
import io.kelin.forum.vo.UserVo;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
@RestController
@RequestMapping("/forum/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public CommonResult Login(@RequestBody User user){

        if(StringUtils.isEmpty(user.getUserId()) || StringUtils.isEmpty(user.getPassword())){
            System.out.println(user);
            return null;
        }

        String salt = "1234567890";

        //查询数据库
        User userById = userService.getUserById(user.getId());
        try {
            if(userById == null || !EncryptUtil.authenticate(user.getPassword(), userById.getPassword(),salt)){
                return CommonResult.fail().setMessage("用户名或密码错误！");
//                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        String token = JwtUtil.generateToken(userById.getUserId(), userById.getUserName());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userById, userVo);
        userVo.setToken(token);

//        return userVo;
        return CommonResult.ok().put("data", userVo);
    }
}

