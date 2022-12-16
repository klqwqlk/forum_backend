package io.kelin.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.kelin.forum.entity.Concernedpeople;
import io.kelin.forum.entity.Dynamic;
import io.kelin.forum.mapper.DynamicMapper;
import io.kelin.forum.service.DynamicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
@Service
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper, Dynamic> implements DynamicService {

    @Autowired
    DynamicMapper dynamicMapper ;

    @Override
    public Page<Dynamic> getAllDynamics(Integer page, Integer pageSize) {

        Page<Dynamic> p = new Page<>();
        p.setSize(pageSize);
        p.setCurrent(page);

        Page<Dynamic> dynamicPage = dynamicMapper.selectPage(p, null);


//        List<Dynamic> dynamics = dynamicMapper.selectList(new QueryWrapper<>());
        return dynamicPage;
    }

    @Override
    public List<Dynamic> getConcernDynamics(List<Integer> concerndPeopleIds) {
        QueryWrapper<Dynamic> dynamicQueryWrapper = new QueryWrapper<>();
        dynamicQueryWrapper.in("userId",concerndPeopleIds);
        List<Dynamic> concernedDynamics = dynamicMapper.selectList(dynamicQueryWrapper);
        return concernedDynamics;
    }

    @Override
    public int updateStarCount(Integer dynamicId, int count) {

       return dynamicMapper.updateStarCount(dynamicId, count);
    }

    @Override
    public int updateStorageCount(Integer dynamicId, int count) {
        return dynamicMapper.updateStorageCount(dynamicId, count);
    }
}
