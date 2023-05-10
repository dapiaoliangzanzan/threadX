package com.threadx.metrics.server.controller;

import com.threadx.metrics.server.common.annotations.GlobalResultPackage;
import com.threadx.metrics.server.conditions.ServerItemFindConditions;
import com.threadx.metrics.server.entity.ServerItem;
import com.threadx.metrics.server.service.ServerItemService;
import com.threadx.metrics.server.vo.ThreadxPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务接口
 *
 * @author huangfukexing
 * @date 2023/5/10 14:49
 */
@RestController
@GlobalResultPackage
@Api(tags = "服务信息")
@RequestMapping("/serverItem")
public class ServerItemController {

    private final ServerItemService serverItemService;

    public ServerItemController(ServerItemService serverItemService) {
        this.serverItemService = serverItemService;
    }

    /**
     * 分页查询服务数据
     *
     * @param serverItemFindConditions 查询条件
     * @return 分页查询服务信息
     */
    @ApiOperation(value = "分页查询服务数据")
    @PostMapping("findByPage")
    public ThreadxPage<ServerItem> findByPage(@RequestBody ServerItemFindConditions serverItemFindConditions) {
        return serverItemService.findServerItemByPage(serverItemFindConditions);
    }
}
