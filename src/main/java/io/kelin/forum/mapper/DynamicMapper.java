package io.kelin.forum.mapper;

import io.kelin.forum.entity.Dynamic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
public interface DynamicMapper extends BaseMapper<Dynamic> {
    int updateStarCount(@Param("dynamicId") Integer dynamicId, @Param("count") int count);

    int updateStorageCount(@Param("dynamicId") Integer dynamicId, @Param("count") int count);
}
