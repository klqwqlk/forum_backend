package io.kelin.forum.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.kelin.forum.entity.Dynamiccomment;
import com.baomidou.mybatisplus.extension.service.IService;
import io.kelin.forum.vo.DynamicCommentVo;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
public interface DynamiccommentService extends IService<Dynamiccomment> {
    Page<DynamicCommentVo> getCommentByDynamicId(String dynamicId, Integer page, Integer pageSize, Integer childPage, Integer childPageSize);

    int comment(Dynamiccomment dynamiccomment);
}
