package com.threadx.metrics.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.entity.UserMenu;
import com.threadx.metrics.server.mapper.UserMenuMapper;
import com.threadx.metrics.server.service.UserMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务实现
 *
 * @author huangfukexing
 * @date 2023/6/1 14:54
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenu> implements UserMenuService {
}
