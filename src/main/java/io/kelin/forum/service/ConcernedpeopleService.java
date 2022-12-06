package io.kelin.forum.service;

import io.kelin.forum.entity.Concernedpeople;
import com.baomidou.mybatisplus.extension.service.IService;
import io.kelin.forum.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
public interface ConcernedpeopleService extends IService<Concernedpeople> {

    public List<Integer> getConcerndPeopleIds(User user);

}
