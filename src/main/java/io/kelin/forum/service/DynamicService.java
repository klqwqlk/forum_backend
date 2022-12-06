package io.kelin.forum.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kelin.forum.entity.Concernedpeople;
import io.kelin.forum.entity.Dynamic;
import com.baomidou.mybatisplus.extension.service.IService;
import io.kelin.forum.mapper.DynamicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
public interface DynamicService extends IService<Dynamic> {


    public List<Dynamic> getAllDynamics();

    public List<Dynamic> getConcernDynamics(List<Integer> concerns);
}
