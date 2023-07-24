package com.threadx.metrics.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.conditions.RolePageConditions;
import com.threadx.metrics.server.entity.Role;
import com.threadx.metrics.server.mapper.RoleMapper;
import com.threadx.metrics.server.service.RoleService;
import com.threadx.metrics.server.vo.RoleVo;
import com.threadx.metrics.server.vo.ThreadxPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色服务实现
 *
 * @author huangfukexing
 * @date 2023/7/24 14:01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public ThreadxPage<RoleVo> findAllByPage(RolePageConditions rolePageConditions) {
        Integer pageSize = rolePageConditions.getPageSize();
        Integer currentPage = rolePageConditions.getCurrentPage();
        String roleName = rolePageConditions.getRoleName();
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(roleName), "role_name", roleName);
        Page<Role> page = new Page<>(currentPage, pageSize);
        baseMapper.selectPage(page, queryWrapper);
        //数据转换
        List<RoleVo> roleVoList = page.getRecords().stream().map(record -> {
            RoleVo roleVo = new RoleVo();
            roleVo.setRoleId(record.getId());
            roleVo.setRoleName(record.getRoleName());
            roleVo.setRoleDesc(record.getRoleDesc());
            roleVo.setCreateDate(DateUtil.format(new Date(record.getCreateTime()), "yyyy-MM-dd HH:mm:ss"));
            roleVo.setUpdateDate(DateUtil.format(new Date(record.getUpdateTime()), "yyyy-MM-dd HH:mm:ss"));
            return roleVo;
        }).collect(Collectors.toList());

        ThreadxPage<RoleVo> voThreadPage = new ThreadxPage<>();
        voThreadPage.setTotal(page.getTotal());
        voThreadPage.setData(roleVoList);
        return voThreadPage;
    }
}
