package com.threadx.metrics.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.entity.UserMenu;
import com.threadx.metrics.server.mapper.UserMenuMapper;
import com.threadx.metrics.server.service.UserMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现
 *
 * @author huangfukexing
 * @date 2023/6/1 14:54
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenu> implements UserMenuService {

    @Override
    public List<Long> findAllByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        QueryWrapper<UserMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserMenu> userMenus = baseMapper.selectList(queryWrapper);
        return userMenus.stream().map(UserMenu::getMenuId).collect(Collectors.toList());
    }
}
