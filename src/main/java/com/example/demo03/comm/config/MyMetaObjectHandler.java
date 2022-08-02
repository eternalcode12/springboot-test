package com.example.demo03.comm.config;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/26 11:42
 * @describe  自动填充类
 * @since 1.8
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER= LoggerFactory.getLogger(MyMetaObjectHandler.class);

    //insert操作时要填充的字段
    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ...");
        //根据属性名字设置要填充的值
        this.setFieldValByName("createdAt",new Date(),metaObject);
        this.setFieldValByName("updatedAt",new Date(),metaObject);
        this.setFieldValByName("isAdmin", 1, metaObject);
        this.setFieldValByName("uId", IdUtil.randomUUID(), metaObject);
        this.setFieldValByName("assessNum", 0L, metaObject);
        this.setFieldValByName("status", 0, metaObject);
        this.setFieldValByName("menuStatus", 1, metaObject);
    }

    //update操作时要填充的字段
    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ...");
        this.setFieldValByName("updatedAt",new Date(),metaObject);
    }
}