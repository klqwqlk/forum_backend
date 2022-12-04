package io.kelin.forum.controller;


import io.kelin.forum.entity.Dynamic;
import io.kelin.forum.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/getAll")
    public List<Dynamic> getAll(){
        List<Dynamic> allDynamics = dynamicService.getAllDynamics();
        return allDynamics;
    }
}

