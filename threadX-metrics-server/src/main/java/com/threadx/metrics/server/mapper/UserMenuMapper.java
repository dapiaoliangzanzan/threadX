package com.threadx.metrics.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.threadx.metrics.server.entity.UserMenu;
import org.springframework.stereotype.Repository;

/**
 * 用户菜单数据库操作
 *
 * @author huangfukexing
 * @date 2023/6/1 14:52
 */
@Repository
public interface UserMenuMapper extends BaseMapper<UserMenu> {
}
