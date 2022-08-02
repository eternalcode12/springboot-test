package com.example.demo03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo03.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/2 22:21
 * @describe 文章 mapper
 * @since 1.8
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
