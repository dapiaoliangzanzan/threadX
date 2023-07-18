package com.threadx.metrics.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.common.code.CurrencyRequestEnum;
import com.threadx.metrics.server.common.exceptions.GeneralException;
import com.threadx.metrics.server.conditions.UserPageConditions;
import com.threadx.metrics.server.constant.RedisCacheKey;
import com.threadx.metrics.server.constant.UserConstant;
import com.threadx.metrics.server.dto.UserInfoDto;
import com.threadx.metrics.server.entity.User;
import com.threadx.metrics.server.mapper.UserMapper;
import com.threadx.metrics.server.service.UserManagerService;
import com.threadx.metrics.server.service.UserService;
import com.threadx.metrics.server.vo.ThreadxPage;
import com.threadx.metrics.server.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * *************************************************<br/>
 * 用户管理服务<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/6/3 13:59
 */
@Slf4j
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

    @Override
    public ThreadxPage<UserVo> findAllUser(UserPageConditions userPageConditions) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String userName = userPageConditions.getUserName();
        String nickName = userPageConditions.getNickName();
        Integer pageNumber = userPageConditions.getPageNumber();
        Integer pageSize = userPageConditions.getPageSize();
        queryWrapper.like(StrUtil.isNotBlank(userName), "user_name", userName);
        queryWrapper.like(StrUtil.isNotBlank(nickName), "nick_name", nickName);

        Page<User> userPage = new Page<>(pageNumber, pageSize);
        baseMapper.selectPage(userPage, queryWrapper);
        //转换数据为需要的对象
        List<User> records = userPage.getRecords();
        List<UserVo> userVos = records.stream().map(record -> {
            UserVo userVo = new UserVo();
            userVo.setUserName(record.getUserName());
            userVo.setNickName(record.getNickName());
            userVo.setCreateTime(DateUtil.format(new Date(record.getCreateTime()), "yyyy-MM-dd HH:mm:ss"));
            userVo.setUpdateTime(DateUtil.format(new Date(record.getUpdateTime()), "yyyy-MM-dd HH:mm:ss"));
            userVo.setId(record.getId());
            userVo.setState(record.getState());
            return userVo;
        }).collect(Collectors.toList());

        ThreadxPage<UserVo> threadPage = new ThreadxPage<>();
        threadPage.setData(userVos);
        threadPage.setTotal(userPage.getTotal());
        return threadPage;
    }
}
