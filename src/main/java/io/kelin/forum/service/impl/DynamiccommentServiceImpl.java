package io.kelin.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.kelin.forum.entity.Dynamic;
import io.kelin.forum.entity.Dynamiccomment;
import io.kelin.forum.entity.User;
import io.kelin.forum.mapper.DynamicMapper;
import io.kelin.forum.mapper.DynamiccommentMapper;
import io.kelin.forum.mapper.UserMapper;
import io.kelin.forum.service.DynamiccommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.kelin.forum.vo.DynamicCommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
@Service
public class DynamiccommentServiceImpl extends ServiceImpl<DynamiccommentMapper, Dynamiccomment> implements DynamiccommentService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    DynamicMapper dynamicMapper;

    @Override
    public Page<DynamicCommentVo> getCommentByDynamicId(String dynamicId, Integer page, Integer pageSize, Integer childPage, Integer childPageSize) {
        Page<Dynamiccomment> comment_1_page = new Page();
        comment_1_page.setCurrent(page);
        comment_1_page.setSize(pageSize);

        QueryWrapper<Dynamiccomment> wrapper_1 = new QueryWrapper<>();
        wrapper_1.eq("dynamicId", dynamicId);
        wrapper_1.eq("parentCommentId",-1);
        wrapper_1.orderByAsc("commentTime");

        Page<Dynamiccomment> dynamiccommentPage_1 = baseMapper.selectPage(comment_1_page, wrapper_1);

        Page<DynamicCommentVo> dynamicCommentVoPage = new Page<>();

        if(dynamiccommentPage_1.getTotal() == 0){
            return dynamicCommentVoPage;
        }

        dynamicCommentVoPage.setSize(dynamiccommentPage_1.getSize());
        dynamicCommentVoPage.setCurrent(dynamiccommentPage_1.getCurrent());
        dynamicCommentVoPage.setTotal(dynamiccommentPage_1.getTotal());
        dynamicCommentVoPage.setPages(dynamiccommentPage_1.getPages());

        Dynamic dynamic = dynamicMapper.selectById(dynamicId);
        User dynamicUser = userMapper.selectOne(new QueryWrapper<User>().eq("userId", dynamic.getUserId()));

        Map<String,String> names = new HashMap<>();

        List<DynamicCommentVo> commentVoLists = new ArrayList<>();
        for(Dynamiccomment comment_1 : dynamiccommentPage_1.getRecords()){
            DynamicCommentVo commentVo = new DynamicCommentVo();
            BeanUtils.copyProperties(comment_1, commentVo);
            commentVo.setFromUserId(comment_1.getUserId());
            String fromname = names.get(comment_1.getUserId());
            if(fromname == null){
                //查用户姓名
                User fromuser = userMapper.selectOne(new QueryWrapper<User>().eq("userId", comment_1.getUserId()));
                fromname = fromuser.getUserName();
                names.put(fromuser.getUserId(),fromname);
            }
            commentVo.setFromUserName(fromname);
            //author of the dynamic
            commentVo.setToUserId(dynamicUser.getUserId());
            commentVo.setToUserName(dynamicUser.getUserName());
            commentVoLists.add(commentVo);
        }
        dynamicCommentVoPage.setRecords(commentVoLists);


        //查找二级
        for(DynamicCommentVo commentVo1 : commentVoLists){

            Page<Dynamiccomment> comment_2_page = new Page();
            comment_2_page.setCurrent(childPage);
            comment_2_page.setSize(childPageSize);

            QueryWrapper<Dynamiccomment> wrapper_2 = new QueryWrapper<>();
            wrapper_2.eq("dynamicId", dynamicId);
            wrapper_2.eq("parentCommentId",commentVo1.getCommentId());
            wrapper_2.orderByAsc("commentTime");

            //二级评论
            Page<Dynamiccomment> dynamiccommentPage_2 = baseMapper.selectPage(comment_2_page, wrapper_2);
            if(dynamiccommentPage_2.getRecords() == null || dynamiccommentPage_2.getRecords().size() == 0){
                continue;
            }

            List<DynamicCommentVo> commentVo2_list = new ArrayList<>();

            for(Dynamiccomment comment_2 : dynamiccommentPage_2.getRecords()){
                DynamicCommentVo vo = new DynamicCommentVo();
                BeanUtils.copyProperties(comment_2, vo);
                vo.setChildComments(null);
                vo.setFromUserId(comment_2.getUserId());
                //查用户姓名
                String fromname = names.get(comment_2.getUserId());
                if(fromname == null){
                    User fromuser = userMapper.selectOne(new QueryWrapper<User>().eq("userId", comment_2.getUserId()));
                    fromname = fromuser.getUserName();
                    names.put(fromuser.getUserId(),fromname);
                }
                vo.setFromUserName(fromname);


                //查找被评论的人
//                if(comment_2.getParentCommentId() == -1){
//
//                    vo.setToUserId(commentVo1.getFromUserId());
//
//                }else {
//
//                    QueryWrapper<Dynamiccomment> wrapper_3 = new QueryWrapper<>();
//                    wrapper_3.eq("dynamicId", dynamicId);
//                    wrapper_3.eq("commentId",comment_2.getToCommentId());
//
//                    Dynamiccomment dynamiccomment = baseMapper.selectOne(wrapper_3);
//                    vo.setToUserId(dynamiccomment.getUserId());
//                }

                vo.setToUserId(comment_2.getToUserId());
                //查用户姓名
                String toname = names.get(vo.getToUserId());
                if(toname == null){
                    User touser = userMapper.selectOne(new QueryWrapper<User>().eq("userId", vo.getToUserId()));
                    toname = touser.getUserName();
                    names.put(touser.getUserId(),toname);
                }
                vo.setToUserName(toname);



                commentVo2_list.add(vo);

            }
            if(commentVo2_list != null && commentVo2_list.size() > 0){
                commentVo1.setChildComments(commentVo2_list);
            }

        }

        return dynamicCommentVoPage;

    }

    @Override
    public int comment(Dynamiccomment dynamiccomment) {
        int insert = baseMapper.insert(dynamiccomment);
        return insert;
    }
}
