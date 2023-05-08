package com.threadx.metrics.server.service;

import com.threadx.metrics.server.entity.ServerItem;

/**
 * *************************************************<br/>
 * 服务配置的业务操作<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/5/8 16:14
 */
public interface ServerItemService {

    /**
     * 查询服务 根据服务名
     *
     * @param serverName 服务名称
     * @return 服务配置
     */
    ServerItem findByName(String serverName);

    /**
     * 查询服务 根据服务名 不存在就新建一个
     *
     * @param serverName 服务名称
     * @return 查询到服务信息
     */
    ServerItem findByNameOrCreate(String serverName);
}
