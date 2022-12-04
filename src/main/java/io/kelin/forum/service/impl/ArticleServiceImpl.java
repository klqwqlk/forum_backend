package io.kelin.forum.service.impl;

import io.kelin.forum.entity.Article;
import io.kelin.forum.mapper.ArticleMapper;
import io.kelin.forum.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
