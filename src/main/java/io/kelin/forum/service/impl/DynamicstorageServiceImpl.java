package io.kelin.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kelin.forum.entity.Dynamicstar;
import io.kelin.forum.entity.Dynamicstorage;
import io.kelin.forum.mapper.DynamicstorageMapper;
import io.kelin.forum.service.DynamicstorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class DynamicstorageServiceImpl extends ServiceImpl<DynamicstorageMapper, Dynamicstorage> implements DynamicstorageService {

    @Override
    public boolean getDynamicStorageByUser(String userId, String dynamicId) {
        QueryWrapper<Dynamicstorage> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("dynamicId", dynamicId);
        wrapper.eq("isDeleted",0);
        Dynamicstorage dynamicstorage = baseMapper.selectOne(wrapper);
        if(dynamicstorage == null){
            return false;
        }
        return true;
    }

    @Override
    public Integer getDynamicStorageCountById(String dynamicId) {
        QueryWrapper<Dynamicstorage> wrapper = new QueryWrapper<>();
        wrapper.eq("dynamicId", dynamicId);
        wrapper.eq("isDeleted", 0);
        Integer count = baseMapper.selectCount(wrapper);

        return count;
    }

    @Override
    public boolean storageDynamic(String userId, String dynamicId, Boolean starFlag) {
        QueryWrapper<Dynamicstorage> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("dynamicId",dynamicId);


        Dynamicstorage exist= baseMapper.selectOne(wrapper);

        int isdel = starFlag == true?0:1;


        Dynamicstorage dynamicstorage = new Dynamicstorage();
        dynamicstorage.setUserId(userId);
        dynamicstorage.setDynamicId(dynamicId);
        dynamicstorage.setEditTime(new Date());
        dynamicstorage.setIsDeleted(isdel);

        int row = -1;
        //no need to update
        if(exist != null && isdel == exist.getIsDeleted()){
            return starFlag;
        }

        if(exist == null){
            //insert
            dynamicstorage.setStorageTime(dynamicstorage.getEditTime());
            row = baseMapper.insert(dynamicstorage);

        }else{

            //update
            dynamicstorage.setIsDeleted((exist.getIsDeleted() + 1) % 2);
            row = baseMapper.update(dynamicstorage, wrapper);
        }

//        insert into player_count(player_id,count,name) value(1,2,”张三”)
        if(row > 0){
            return dynamicstorage.getIsDeleted() == 0;
        }else{
            return starFlag;
        }
    }
}
