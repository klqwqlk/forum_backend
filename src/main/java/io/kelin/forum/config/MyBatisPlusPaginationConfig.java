package io.kelin.forum.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlus 分页插件配置
 *
 * @Author k
 * @Date 2022/12/14
 **/
@Configuration
public class MyBatisPlusPaginationConfig {



    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
