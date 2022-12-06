package io.kelin.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kelin.forum.entity.Concernedpeople;
import io.kelin.forum.entity.User;
import io.kelin.forum.mapper.ConcernedpeopleMapper;
import io.kelin.forum.service.ConcernedpeopleService;
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
public class ConcernedpeopleServiceImpl extends ServiceImpl<ConcernedpeopleMapper, Concernedpeople> implements ConcernedpeopleService {

    @Autowired
    ConcernedpeopleMapper concernedpeopleMapper;

    @Override
    public List<Integer> getConcerndPeopleIds(User user) {
        List<Integer> concernedPeopleIds = concernedpeopleMapper.getConcernedPeopleIds(user);
        return concernedPeopleIds;
    }
}
