package com.threadx.metrics.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.entity.Menu;
import com.threadx.metrics.server.mapper.MenuMapper;
import com.threadx.metrics.server.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单实现类
 *
 * @author huangfukexing
 * @date 2023/6/1 14:36
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
