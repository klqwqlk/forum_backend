package io.kelin.forum.service;

import io.kelin.forum.entity.Dynamicstorage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
public interface DynamicstorageService extends IService<Dynamicstorage> {
    boolean getDynamicStorageByUser(String userId,String dynamicId);

    Integer getDynamicStorageCountById(String dynamicId);

    boolean storageDynamic(String userId ,String dynamicId, Boolean starFlag);
}
