package com.threadx.metrics.server.controller;

import com.threadx.metrics.server.common.annotations.GlobalResultPackage;
import com.threadx.metrics.server.common.annotations.Login;
import com.threadx.metrics.server.common.annotations.UserPermission;
import com.threadx.metrics.server.conditions.RolePageConditions;
import com.threadx.metrics.server.enums.PermissionValue;
import com.threadx.metrics.server.service.RoleService;
import com.threadx.metrics.server.vo.RoleAuthorityVo;
import com.threadx.metrics.server.vo.RoleVo;
import com.threadx.metrics.server.vo.ThreadxPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 角色控制器
 *
 * @author huangfukexing
 * @date 2023/7/24 13:56
 */
@RestController
@GlobalResultPackage
@Api(tags = "角色信息")
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @Login
    @PostMapping("findAllByPage")
    @UserPermission(PermissionValue.FIND_ALL_ROLE_LIST)
    @ApiOperation(value = "分页查询所有的角色信息")
    public ThreadxPage<RoleVo> findAllByPage(@RequestBody RolePageConditions rolePageConditions) {
        return roleService.findAllByPage(rolePageConditions);
    }

    @Login
    @GetMapping("findRoleAuthority")
    @UserPermission(PermissionValue.FIND_ROLE_AUTHORITY)
    @ApiOperation(value = "查询一个角色的全部权限信息")
    public RoleAuthorityVo findRoleAuthority(@RequestParam("roleId") Long roleId) {
        return roleService.findRoleAuthority(roleId);
    }

    @Login
    @PostMapping("save")
    @UserPermission(PermissionValue.SAVE_ROLE_AUTHORITY)
    @ApiOperation(value = "保存角色信息")
    public void save(@RequestBody RoleAuthorityVo roleAuthorityVo){
        roleService.saveOrUpdate(roleAuthorityVo);
    }
}
