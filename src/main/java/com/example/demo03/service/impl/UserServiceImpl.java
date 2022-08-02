package com.example.demo03.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo03.comm.dto.user.UserLoginDto;
import com.example.demo03.comm.dto.user.UserRegisterDto;
import com.example.demo03.comm.exception.BaseException;
import com.example.demo03.comm.utils.ThreadUtils;
import com.example.demo03.comm.utils.TokenUtils;
import com.example.demo03.entity.User;
import com.example.demo03.mapper.UserMapper;
import com.example.demo03.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/1 14:00
 * @describe user service impl
 * @since 1.8
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void register(UserRegisterDto userRegisterDto) throws BaseException {
        // 判断用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userRegisterDto.getUsername());
        User one = this.getOne(queryWrapper);
        if (one != null) {
            throw new BaseException(500, "用户已存在");
        }

        // 注册用户
        User user = BeanUtil.copyProperties(userRegisterDto, User.class);
        try {
            this.save(user);
        } catch (Exception e) {
            throw new BaseException(500, "注册失败,请联系管理员");
        }
    }

    @Override
    public JSONObject login(UserLoginDto userLoginDto) throws BaseException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userLoginDto.getUsername()).or().eq(User::getEmail, userLoginDto.getUsername()).or().eq(User::getPhone, userLoginDto.getUsername());
        // 判断用户是否存在
        User one = this.getOne(queryWrapper);
        if (one == null) {
            throw new BaseException(500, "用户不存在");
        }
        // 判断密码是否正确
        if (!one.getPassword().equals(userLoginDto.getPassword())) {
            throw new BaseException(500, "用户密码错误");
        }
        // 生成token并返回字段信息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", TokenUtils.generateToken(one.getUsername(), one.getUId()));
        jsonObject.put("username", one.getUsername());
        jsonObject.put("nickname", one.getNickname());
        jsonObject.put("avatar", one.getAvatar());

        return jsonObject;
    }

    @Override
    public void deleteById(Long id) throws BaseException {
        // 获取当前操作用户信息
        HashMap<String, Object> lock = ThreadUtils.getLock();
        String uid = lock.get("uid").toString();
        // 判断当前用户是否有删除权限
        User one = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUId, uid));
        // 判断当前用户是否是管理员
        if (one.getIsAdmin() == 1) {
            throw new BaseException(500, "当前用户无管理员权限");
        }
        // 执行删除操作
        try {
            this.removeById(id);
        } catch (Exception e) {
            throw new BaseException(500, "删除失败,请联系管理员");
        }
    }

    @Override
    public void update(User user) throws BaseException {
        // 获取当前操作用户信息
        HashMap<String, Object> lock = ThreadUtils.getLock();
        String uid = lock.get("uid").toString();
        // 判断当前用户是否有更新权限
        User one = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUId, uid));
        // 判断当前用户是否是管理员
        if (one.getIsAdmin() == 1) {
            throw new BaseException(500, "当前用户无管理员权限");
        }
        // 执行更新操作
        try {
            this.updateById(user);
        } catch (Exception e) {
            throw new BaseException(500, "更新失败,请联系管理员");
        }
    }

    @Override
    public User getUserInfoById(Long id) throws BaseException {
        User one = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUId, id));
        if (one == null) {
            throw new BaseException(500, "用户不存在");
        }
        return one;
    }
}
