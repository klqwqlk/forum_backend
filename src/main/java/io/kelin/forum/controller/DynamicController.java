package io.kelin.forum.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.kelin.forum.entity.Concernedpeople;
import io.kelin.forum.entity.Dynamic;
import io.kelin.forum.entity.User;
import io.kelin.forum.service.*;
import io.kelin.forum.util.CommonResult;
import io.kelin.forum.util.JwtUtil;
import io.kelin.forum.vo.DynamicVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
@RestController
@RequestMapping("/forum/dynamic")
public class DynamicController {

    @Autowired
    DynamicService dynamicService;

    @Autowired
    ConcernedpeopleService concernedpeopleService;

    @Autowired
    DynamicstarService dynamicstarService;

    @Autowired
    DynamicstorageService dynamicstorageService;

    @Autowired
    UserService userService;

    @PostMapping("/getAll")
    public CommonResult getAll(@RequestBody Map<String,Object> params){

        if(StringUtils.isEmpty(params.get("page").toString()) || StringUtils.isEmpty(params.get("pageSize").toString())){
            return CommonResult.fail().setMessage("can not find page or pageSize! ");
        }

        int page = Integer.parseInt(params.get("page").toString());
        int pageSize = Integer.parseInt(params.get("pageSize").toString());



        //查动态
        Page<Dynamic> allDynamics = dynamicService.getAllDynamics(page,pageSize);
        // 获得request对象,response对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String authToken = request.getHeader("AuthToken");
        List<DynamicVo> dynamicVoList = new ArrayList<>();
        for(Dynamic dynamic:allDynamics.getRecords()){
            DynamicVo dynamicVo = new DynamicVo();
            BeanUtils.copyProperties(dynamic, dynamicVo);
            //查发表人
            User userById = userService.getUserById(Integer.parseInt(dynamicVo.getUserId()));
            dynamicVo.setUserName(userById.getUserName());
            dynamicVoList.add(dynamicVo);
        }

        Page<DynamicVo> resultPage = new Page<>();
        resultPage.setCurrent(allDynamics.getCurrent());
        resultPage.setSize(allDynamics.getSize());
        resultPage.setTotal(allDynamics.getTotal());
        resultPage.setPages(allDynamics.getPages());
        resultPage.setRecords(dynamicVoList);

        //如果登录了，查询是否关注，是否收藏;
        if(!StringUtils.isEmpty(authToken) && JwtUtil.verifyJwt(authToken)!= null){
            //查询用户是否关注了该动态
            String userId = request.getHeader("userId");
            for(DynamicVo dynamicVo:dynamicVoList){
                boolean dynamicStarByUser = dynamicstarService.getDynamicStarByUser(userId, dynamicVo.getDynamicId().toString());
                dynamicVo.setStarFlag(dynamicStarByUser);
                boolean dynamicStorageByUser = dynamicstorageService.getDynamicStorageByUser(userId, dynamicVo.getDynamicId().toString());
                dynamicVo.setStorageFlag(dynamicStorageByUser);
            }

        }
        return CommonResult.ok().put("data", resultPage);
    }

    @GetMapping("/getDynamicsByPage")
    public Page<Dynamic> getDynamicByPage(@RequestParam Integer pageNo, @RequestParam Integer count){
        QueryWrapper<Dynamic> wrapper = new QueryWrapper<>();
        Page<Dynamic> page = dynamicService.page(new Page<>(pageNo, count), wrapper);
        return page;
    }

    @GetMapping("getConcernedDynamics")
    public Page<Dynamic> getConcernedDynamics(@RequestParam Integer pageNo, @RequestParam Integer count){

        User user = new User();
        user.setId(1);

        List<Integer> concerndPeopleIds = concernedpeopleService.getConcerndPeopleIds(user);

        concerndPeopleIds.add(1);
        concerndPeopleIds.add(2);


        if(concerndPeopleIds == null || concerndPeopleIds.size() == 0){
            return new Page<Dynamic>();
        }

        List<Dynamic> concernDynamics = dynamicService.getConcernDynamics(concerndPeopleIds);

        Page<Dynamic> page = new Page<>();
        concernDynamics.add(new Dynamic().setContent("hahaha"));
        page.setRecords(concernDynamics);
        page.setCurrent(pageNo);
        page.setSize(count);
        return page;
    }
}

