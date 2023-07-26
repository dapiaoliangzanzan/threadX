package com.threadx.metrics.server.service;

import com.threadx.metrics.server.conditions.RolePageConditions;
import com.threadx.metrics.server.entity.Role;
import com.threadx.metrics.server.vo.RoleAuthorityVo;
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

    /**
     * 获取角色的具体权限
     *
     * @param roleId 角色的id
     * @return 角色的具体权限信息
     */
    RoleAuthorityVo findRoleAuthority(Long roleId);

    /**
     * 保存角色信息
     *
     * @param roleAuthorityVo 角色权限
     */
    void saveOrUpdate(RoleAuthorityVo roleAuthorityVo);

    /**
     * 保存角色信息
     *
     * @param roleAuthorityVo 角色信息
     */
    void saveRole(RoleAuthorityVo roleAuthorityVo);

    /**
     * 修改角色
     *
     * @param roleAuthorityVo 角色权限
     */
    void updateRole(RoleAuthorityVo roleAuthorityVo);

    /**
     * 仅仅删除角色  不删除角色关联的数据  以及操作角色关联的用户相关信息
     * @param id 角色的id
     */
    void simpleDeleteById(Long id);
}
