package com.threadx.metrics.server.service;

import com.threadx.metrics.server.entity.InstanceItem;
import com.threadx.metrics.server.entity.ServerItem;

/**
 * *************************************************<br/>
 * 实例配置的业务操作<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/5/8 16:14
 */
public interface InstanceItemService {

    /**
     * 查询 根据服务名和实例名称
     *
     * @param serverName   服务名称
     * @param instanceName 实例名称
     * @return 实例信息
     */
    InstanceItem findByInstanceNameAndServerName(String serverName, String instanceName);


    /**
     * 查询 根据服务名和实例名称 不存在就创建
     *
     * @param serverName   服务名称
     * @param instanceName 实例名称
     * @return 实例信息
     */
    InstanceItem findByInstanceNameAndServerNameOrCreate(String serverName, String instanceName);

    /**
     * 从缓存中查询 缓存没有查数据库  数据库没有就新增
     *
     * @param serverName   服务名称
     * @param instanceName 实例名称
     * @return 实例信息
     */
    Long findByInstanceNameAndServerNameOrCreateOnCache(String serverName, String instanceName);
}