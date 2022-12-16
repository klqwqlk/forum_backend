package io.kelin.forum.controller;


import io.kelin.forum.service.DynamicService;
import io.kelin.forum.service.DynamicstorageService;
import io.kelin.forum.util.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/forum/dynamicstorage")
public class DynamicstorageController {

    @Autowired
    DynamicstorageService dynamicstorageService;

    @Autowired
    DynamicService dynamicService;

    @PostMapping("/storageDynamic")
    public CommonResult starDynamic(@RequestBody Map<String,Object> paramMap){

        String userId =  paramMap.get("userId").toString();
        String dynamicId =  paramMap.get("dynamicId").toString();
        Boolean storageFlag = (Boolean) paramMap.get("storageFlag");

//        System.out.println(paramMap.get("userId"));
//        System.out.println(dynamicId);
//        System.out.println(starFlag);

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(dynamicId) || storageFlag == null){
            return CommonResult.fail().setMessage("storageDynamic userId or dynamicId or storageFlag can not be empty!");
        }
        boolean flag = dynamicstorageService.storageDynamic(userId, dynamicId,storageFlag);

        //赞或取消赞，修改dynamic count
        int row = dynamicService.updateStorageCount(Integer.parseInt(dynamicId), flag ? 1 : -1);

        //System.out.println("row: "+ row);
        if(row == 0){
            //error
        }


        return CommonResult.ok().put("storageFlag", flag);
    }
}

