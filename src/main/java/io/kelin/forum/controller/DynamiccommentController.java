package io.kelin.forum.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.kelin.forum.entity.Dynamic;
import io.kelin.forum.entity.Dynamiccomment;
import io.kelin.forum.service.DynamiccommentService;
import io.kelin.forum.util.CommonResult;
import io.kelin.forum.util.JwtUtil;
import io.kelin.forum.vo.DynamicCommentVo;
import io.kelin.forum.vo.DynamicVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/forum/dynamiccomment")
public class DynamiccommentController {

    @Autowired
    DynamiccommentService dynamiccommentService;

    @PostMapping("/getComment")
    public CommonResult getComment(@RequestBody Map<String,Object> params){
        if(StringUtils.isEmpty(params.get("page").toString()) || StringUtils.isEmpty(params.get("pageSize").toString())
        || StringUtils.isEmpty(params.get("childPage").toString())||StringUtils.isEmpty(params.get("childPageSize").toString())){
            return CommonResult.fail().setMessage("can not find page or pageSize or childPage or childPageSize ! ");
        }

        String dynamicId =params.get("dynamicId").toString();
        int page = Integer.parseInt(params.get("page").toString());
        int pageSize = Integer.parseInt(params.get("pageSize").toString());
        int childPage = Integer.parseInt(params.get("childPage").toString());
        int childPageSize = Integer.parseInt(params.get("childPageSize").toString());

        Page<DynamicCommentVo> commentByDynamicId = dynamiccommentService.getCommentByDynamicId(dynamicId, page, pageSize, childPage, childPageSize);

        return CommonResult.ok().put("data", commentByDynamicId);

    }

    @PostMapping("/comment")
    public CommonResult comment(@RequestBody Map<String,Object> params){

        // 获得request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String authToken = request.getHeader("AuthToken");

        //如果没有登录，不可以评论
        if(StringUtils.isEmpty(authToken) || JwtUtil.verifyJwt(authToken) == null){
            return CommonResult.fail().setMessage("未登录，不可评论! ");
        }
        String fromUserId = request.getHeader("userId");

        if(StringUtils.isEmpty(params.get("toUserId").toString()) || StringUtils.isEmpty(params.get("content").toString())
                || StringUtils.isEmpty(params.get("parentCommentId").toString())
                || StringUtils.isEmpty(params.get("dynamicId").toString())){
            return CommonResult.fail().setMessage("/forum/dynamiccomment/comment can not find params ! ");
        }


        String toUserId = params.get("toUserId").toString();
        String parentCommentId = params.get("parentCommentId").toString();
        String content = params.get("content").toString();
        String dynamicId = params.get("dynamicId").toString();

        Dynamiccomment dynamiccomment = new Dynamiccomment();
        dynamiccomment.setCommentTime(new Date());
        dynamiccomment.setDynamicId(dynamicId);
        dynamiccomment.setUserId(fromUserId);
        dynamiccomment.setToUserId(toUserId);
        dynamiccomment.setParentCommentId(Integer.parseInt(parentCommentId));
        dynamiccomment.setContent(content);

        int insert = dynamiccommentService.comment(dynamiccomment);

        if(insert > 0){
            return CommonResult.ok().setMessage("评论成功！");
        }else{
            return CommonResult.fail().setMessage("评论失败！");
        }
    }
}

