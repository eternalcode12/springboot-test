package com.example.demo03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo03.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/26 14:22
 * @describe user mapper interface
 * @since 1.8
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
