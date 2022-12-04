package io.kelin.forum.service.impl;

import io.kelin.forum.entity.Product;
import io.kelin.forum.mapper.ProductMapper;
import io.kelin.forum.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
