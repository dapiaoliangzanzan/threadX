package com.threadx.metrics.server.service;

import com.threadx.metrics.server.entity.Menu;

import java.util.List;

/**
 * 菜单服务类
 *
 * @author huangfukexing
 * @date 2023/6/1 14:36
 */
public interface MenuService {

    /**
     * 查询菜单信息 根据用户的id查询符合条件的菜单
     *
     * @return 返回符合调价的菜单
     */
    List<Menu> findThisUserMenu();

}
