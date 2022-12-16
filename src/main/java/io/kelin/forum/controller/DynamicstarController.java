package io.kelin.forum.controller;


import io.kelin.forum.service.DynamicService;
import io.kelin.forum.service.DynamicstarService;
import io.kelin.forum.util.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
@RestController
@RequestMapping("/forum/dynamicstar")
public class DynamicstarController {

    @Autowired
    DynamicstarService dynamicstarService;
    @Autowired
    DynamicService dynamicService;

    @PostMapping("/getDynamicStarCount")
    public CommonResult getDynamicStarCount(@RequestParam String dynamicId){
        if(StringUtils.isEmpty(dynamicId)){
            return CommonResult.fail().setMessage("dynamicId can not be empty!");
        }
        Integer starCount = dynamicstarService.getDynamicStarCountById(dynamicId);
        return CommonResult.ok().put("starCount", starCount);
    }

    @PostMapping("/getDynamicStarByUser")
    public  CommonResult getDynamicStarByUser(@RequestParam String userId, @RequestParam String dynamicId){
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(dynamicId)){
            return CommonResult.fail().setMessage("userId or dynamicId can not be empty!");
        }
        boolean starFlag = dynamicstarService.getDynamicStarByUser(userId, dynamicId);
        return CommonResult.ok().put("starFlag", starFlag);
    }

    @PostMapping("/starDynamic")
    public  CommonResult starDynamic(@RequestBody Map<String,Object> paramMap){

        String userId =  paramMap.get("userId").toString();
        String dynamicId =  paramMap.get("dynamicId").toString();
        Boolean starFlag = (Boolean) paramMap.get("starFlag");

//        System.out.println(paramMap.get("userId"));
//        System.out.println(dynamicId);
//        System.out.println(starFlag);

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(dynamicId) || starFlag == null){
            return CommonResult.fail().setMessage("starDynamic userId or dynamicId can not be empty!");
        }
        boolean flag = dynamicstarService.starDynamic(userId, dynamicId,starFlag);

        //赞或取消赞，修改dynamic count
        int row = dynamicService.updateStarCount(Integer.parseInt(dynamicId), flag ? 1 : -1);

        //System.out.println("row: "+ row);
        if(row == 0){
            //error
        }


        return CommonResult.ok().put("starFlag", flag);
    }



}

