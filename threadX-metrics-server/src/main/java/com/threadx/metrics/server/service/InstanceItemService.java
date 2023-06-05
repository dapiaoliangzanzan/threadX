package com.threadx.metrics.server.service;

import com.threadx.metrics.server.conditions.InstanceItemDataConditions;
import com.threadx.metrics.server.conditions.InstanceItemFindConditions;
import com.threadx.metrics.server.entity.InstanceItem;
import com.threadx.metrics.server.vo.InstanceItemDataVo;
import com.threadx.metrics.server.vo.InstanceItemVo;
import com.threadx.metrics.server.vo.ThreadxPage;

import java.util.List;

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
     * 分页查询 根据条件进行分页查询
     *
     * @param conditions 查询条件
     * @return 分页后的数据
     */
    ThreadxPage<InstanceItemVo> findByPage(InstanceItemFindConditions conditions);


    /**
     * 查询最常使用的top10
     *
     * @return 当前用户常用的top10
     */
    List<InstanceItemVo> commonlyUsedTop10();


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

    /**
     * 实例详情查询
     *
     * @param instanceItemDataConditions 查询条件
     * @return 实例的详情信息
     */
    InstanceItemDataVo instanceDetails(InstanceItemDataConditions instanceItemDataConditions);
}
