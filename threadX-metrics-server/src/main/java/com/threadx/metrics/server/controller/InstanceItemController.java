package com.threadx.metrics.server.controller;

import com.threadx.metrics.server.common.annotations.GlobalResultPackage;
import com.threadx.metrics.server.conditions.InstanceItemFindConditions;
import com.threadx.metrics.server.entity.InstanceItem;
import com.threadx.metrics.server.service.InstanceItemService;
import com.threadx.metrics.server.vo.InstanceItemVo;
import com.threadx.metrics.server.vo.ThreadxPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实例接口
 *
 * @author huangfukexing
 * @date 2023/5/30 15:23
 */
@RestController
@GlobalResultPackage
@Api(tags = "实例信息")
@RequestMapping("/instanceItem")
public class InstanceItemController {

    private final InstanceItemService instanceItemService;

    public InstanceItemController(InstanceItemService instanceItemService) {
        this.instanceItemService = instanceItemService;
    }

    @ApiOperation(value = "分页查询实例数据")
    @PostMapping("findByPage")
    public ThreadxPage<InstanceItemVo> findByPage(@RequestBody InstanceItemFindConditions conditions){
        return instanceItemService.findByPage(conditions);
    }
}
