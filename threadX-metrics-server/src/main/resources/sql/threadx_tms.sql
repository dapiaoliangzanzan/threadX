/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.206
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 192.168.3.206:3306
 Source Schema         : threadx_tms

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 08/05/2023 17:50:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- DROP DATABASE IF EXISTS threadx_tms;
CREATE DATABASE threadx_tms CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE threadx_tms;
-- ----------------------------
-- Table structure for instance_item
-- ----------------------------
DROP TABLE IF EXISTS `instance_item`;
CREATE TABLE `instance_item` (
                                 `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `instance_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '实例名称',
                                 `server_id` bigint(32) NOT NULL COMMENT '所属服务的名称',
                                 `active_time` bigint(64) NOT NULL COMMENT '活跃时间',
                                 `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='实例名称';

-- ----------------------------
-- Table structure for server_item
-- ----------------------------
DROP TABLE IF EXISTS `server_item`;
CREATE TABLE `server_item` (
                               `id` bigint(32) NOT NULL AUTO_INCREMENT,
                               `server_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务名称',
                               `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='记录当前监控的服务';

-- ----------------------------
-- Table structure for thread_pool_data
-- ----------------------------
DROP TABLE IF EXISTS `thread_pool_data`;
CREATE TABLE `thread_pool_data` (
                                    `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `server_key` varchar(255) NOT NULL COMMENT '服务名称',
                                    `instance_key` varchar(255) NOT NULL COMMENT '实例名称',
                                    `address` varchar(255) NOT NULL COMMENT '地址',
                                    `thread_pool_group_name` varchar(255) NOT NULL COMMENT '线程池组的名称',
                                    `thread_pool_name` varchar(255) NOT NULL COMMENT '线程池的名称',
                                    `core_pool_size` int(32) NOT NULL COMMENT '线程池的核心数量',
                                    `maximum_pool_size` int(32) NOT NULL COMMENT '最大可执行任务的线程数',
                                    `active_count` int(32) NOT NULL COMMENT '当前活跃的线程数',
                                    `this_thread_count` int(32) NOT NULL COMMENT '当前线程池的线程数量  包含没有执行任务的线程还没有来得及被销毁的非核心线程',
                                    `largest_pool_size` int(32) NOT NULL COMMENT '曾将达到的最大的线程数 历史信息',
                                    `rejected_count` bigint(64) NOT NULL COMMENT '拒绝执行的次数',
                                    `task_count` bigint(64) NOT NULL COMMENT '堆积的、执行中的、已经完成的任务的总和',
                                    `completed_task_count` bigint(64) NOT NULL COMMENT '已经完成的数量',
                                    `queue_type` varchar(255) DEFAULT NULL COMMENT '队列类型',
                                    `rejected_type` varchar(255) DEFAULT NULL COMMENT '拒绝策略',
                                    `keep_alive_time` bigint(64) DEFAULT NULL COMMENT '毫秒 线程空闲',
                                    `instance_id` bigint(32) NOT NULL COMMENT '所属实例的id',
                                    `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='线程池的监控数据';

-- ----------------------------
-- Table structure for thread_task_data
-- ----------------------------
DROP TABLE IF EXISTS `thread_task_data`;
CREATE TABLE `thread_task_data` (
                                    `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `server_key` varchar(255) NOT NULL COMMENT '服务名称',
                                    `instance_key` varchar(255) NOT NULL COMMENT '实例名称',
                                    `address` varchar(255) NOT NULL COMMENT '地址',
                                    `thread_pool_group_name` varchar(255) NOT NULL COMMENT '线程池组的名称',
                                    `thread_pool_name` varchar(255) NOT NULL COMMENT '线程池的名称',
                                    `thread_name` varchar(255) DEFAULT NULL COMMENT '被那一个线程执行的',
                                    `submit_time` bigint(64) DEFAULT NULL COMMENT '该时间为任务被提交的时间，只要该任务被加载进线程池，这个时间就会被初始化',
                                    `start_time` bigint(64) DEFAULT NULL COMMENT '任务开始运行的时间，注意，这里的开始时间是任务真正开始运行的时间，不是提交的时间，因为他可能被堆积在队列中',
                                    `end_time` bigint(64) DEFAULT NULL COMMENT '任务的结束时间，无奈他是正常结束，或者是异常，这个时间都会存在，当然，被拒绝的任务不在此列！',
                                    `runIng_consuming_time` bigint(64) DEFAULT NULL COMMENT '任务的执行耗时',
                                    `wait_time` bigint(64) DEFAULT NULL COMMENT '任务等待时间',
                                    `consuming_time` bigint(64) DEFAULT NULL COMMENT '任务总耗时',
                                    `refuse` char(1) DEFAULT NULL COMMENT '0  false  1 true   当任务被拒绝策略执行的时候，该值为true,否则为false!',
                                    `success` char(1) DEFAULT NULL COMMENT '0  false  1 true  任务是否被执行成功，如果中途异常、被拒绝，该值都会被设置为false, 否则为true',
                                    `throwable` longtext COMMENT '任务的异常信息，当没有异常的时候，这个值为空！',
                                    `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                                    `instance_id` bigint(32) NOT NULL COMMENT '所属实例的id',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='线程池内任务执行信息';

SET FOREIGN_KEY_CHECKS = 1;

