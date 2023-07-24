package com.threadx.metrics.server.service;

import com.threadx.metrics.server.conditions.RolePageConditions;
import com.threadx.metrics.server.entity.Role;
import com.threadx.metrics.server.vo.RoleVo;
import com.threadx.metrics.server.vo.ThreadxPage;

/**
 * 角色服务
 *
 * @author huangfukexing
 * @date 2023/7/24 14:01
 */
public interface RoleService {

    /**
     * 查询所有的角色分页
     *
     * @param rolePageConditions 角色查询条件
     * @return 分页数据
     */
    ThreadxPage<RoleVo> findAllByPage(RolePageConditions rolePageConditions);
}
