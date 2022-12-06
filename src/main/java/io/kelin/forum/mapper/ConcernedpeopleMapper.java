package io.kelin.forum.mapper;

import io.kelin.forum.entity.Concernedpeople;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kelin.forum.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
public interface ConcernedpeopleMapper extends BaseMapper<Concernedpeople> {
     List<Integer> getConcernedPeopleIds( @Param("user") User user);
}
