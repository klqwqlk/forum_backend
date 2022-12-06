package io.kelin.forum.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.kelin.forum.entity.Concernedpeople;
import io.kelin.forum.entity.Dynamic;
import io.kelin.forum.entity.User;
import io.kelin.forum.service.ConcernedpeopleService;
import io.kelin.forum.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/getAll")
    public List<Dynamic> getAll(){
        List<Dynamic> allDynamics = dynamicService.getAllDynamics();
        return allDynamics;
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

