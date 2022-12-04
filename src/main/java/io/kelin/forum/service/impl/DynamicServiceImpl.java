package io.kelin.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public List<Dynamic> getAllDynamics() {
        List<Dynamic> dynamics = dynamicMapper.selectList(new QueryWrapper<>());
        return dynamics;
    }
}
