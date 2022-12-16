package io.kelin.forum.service;

import io.kelin.forum.entity.Dynamicstar;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
public interface DynamicstarService extends IService<Dynamicstar> {

    boolean getDynamicStarByUser(String userId,String dynamicId);

    Integer getDynamicStarCountById(String dynamicId);

    boolean starDynamic(String userId ,String dynamicId, Boolean starFlag);
}
