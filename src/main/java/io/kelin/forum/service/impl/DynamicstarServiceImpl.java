package io.kelin.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kelin.forum.entity.Dynamic;
import io.kelin.forum.entity.Dynamicstar;
import io.kelin.forum.mapper.DynamicstarMapper;
import io.kelin.forum.service.DynamicstarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
@Service
public class DynamicstarServiceImpl extends ServiceImpl<DynamicstarMapper, Dynamicstar> implements DynamicstarService {

    @Override
    public boolean getDynamicStarByUser(String userId, String dynamicId) {



        QueryWrapper<Dynamicstar> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("dynamicId", dynamicId);
        wrapper.eq("isDeleted",0);
        Dynamicstar dynamicstar = baseMapper.selectOne(wrapper);
        if(dynamicstar == null){
            return false;
        }
        return true;
    }

    @Override
    public Integer getDynamicStarCountById(String dynamicId) {


        QueryWrapper<Dynamicstar> wrapper = new QueryWrapper<>();
        wrapper.eq("dynamicId", dynamicId);
        wrapper.eq("isDeleted", 0);
        Integer count = baseMapper.selectCount(wrapper);

        return count;
    }

    @Override
    public boolean starDynamic(String userId, String dynamicId, Boolean starFlag) {
        QueryWrapper<Dynamicstar> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("dynamicId",dynamicId);


        Dynamicstar exist= baseMapper.selectOne(wrapper);

        int isdel = starFlag == true?0:1;


        Dynamicstar dynamicstar = new Dynamicstar();
        dynamicstar.setUserId(userId);
        dynamicstar.setDynamicId(dynamicId);
        dynamicstar.setEditTime(new Date());
        dynamicstar.setIsDeleted(isdel);

        int row = -1;
        //no need to update
        if(isdel == exist.getIsDeleted()){
            return starFlag;
        }

        if(exist == null){
            //insert
            dynamicstar.setStarTime(dynamicstar.getEditTime());
            row = baseMapper.insert(dynamicstar);

        }else{

            //update
            dynamicstar.setIsDeleted((exist.getIsDeleted() + 1) % 2);
            row = baseMapper.update(dynamicstar, wrapper);
        }

//        insert into player_count(player_id,count,name) value(1,2,”张三”)
        if(row > 0){
            return dynamicstar.getIsDeleted() == 0;
        }else{
            return starFlag;
        }

    }
}
