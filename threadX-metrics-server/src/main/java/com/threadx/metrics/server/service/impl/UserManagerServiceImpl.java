package com.threadx.metrics.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.common.code.CurrencyRequestEnum;
import com.threadx.metrics.server.common.exceptions.GeneralException;
import com.threadx.metrics.server.constant.RedisCacheKey;
import com.threadx.metrics.server.constant.UserConstant;
import com.threadx.metrics.server.dto.UserInfoDto;
import com.threadx.metrics.server.entity.User;
import com.threadx.metrics.server.mapper.UserMapper;
import com.threadx.metrics.server.service.UserManagerService;
import com.threadx.metrics.server.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * *************************************************<br/>
 * 用户管理服务<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/6/3 13:59
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserManagerServiceImpl extends ServiceImpl<UserMapper, User> implements UserManagerService {

    private final UserService userService;
    private final StringRedisTemplate redisTemplate;

    public UserManagerServiceImpl(UserService userService, StringRedisTemplate redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveUser(UserInfoDto userInfoDto) {
        if (userInfoDto == null) {
            throw new GeneralException(CurrencyRequestEnum.PARAMETER_MISSING);
        }
        User user = new User();
        user.init();
        user.setUserName(userInfoDto.getUserName());
        user.setNickName(userInfoDto.getNickName());
        user.setPassword(BCrypt.hashpw(userInfoDto.getPassword()));
        user.setEmail(userInfoDto.getEmail());
        user.setState(UserConstant.ENABLE);
        baseMapper.insert(user);
    }

    @Override
    @SuppressWarnings("all")
    public void updateUser(UserInfoDto userInfoDto) {
        if (userInfoDto == null) {
            throw new GeneralException(CurrencyRequestEnum.PARAMETER_MISSING);
        }
        String userName = userInfoDto.getUserName();
        String password = userInfoDto.getPassword();
        String email = userInfoDto.getEmail();
        String nickName = userInfoDto.getNickName();
        User user = userService.findByUserName(userName);

        if (user == null) {
            throw new GeneralException("用户不存在！");
        }

        //是否强制下线
        boolean isUpdate = false;

        if(StrUtil.isNotBlank(userName) && !userName.equals(user.getUserName())){
            user.setUserName(userName);
            isUpdate = true;
        }
        if(StrUtil.isNotBlank(password) && !BCrypt.checkpw(password, user.getPassword())) {
            user.setPassword(BCrypt.hashpw(password));
            isUpdate = true;
        }
        if(StrUtil.isNotBlank(email) && !email.equals(user.getEmail())) {
            user.setEmail(email);
            isUpdate = true;
        }
        if(StrUtil.isNotBlank(nickName) && !nickName.equals(user.getNickName())) {
            user.setNickName(nickName);
            isUpdate = true;
        }
        baseMapper.updateById(user);

        if (isUpdate) {
            Set<String> keys = redisTemplate.keys(String.format(RedisCacheKey.USER_CACHE, user.getId()) + "*");
            if(CollUtil.isNotEmpty(keys)) {
                keys.forEach(redisTemplate::delete);
            }
        }
    }
}
