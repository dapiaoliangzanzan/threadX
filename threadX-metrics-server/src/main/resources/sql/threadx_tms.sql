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
-- Table structure for active_log
-- ----------------------------
DROP TABLE IF EXISTS `active_log`;
CREATE TABLE `active_log`  (
                               `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `active_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动key标记',
                               `active_log` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '行为日志',
                               `start_time` bigint(64) NOT NULL COMMENT '活动时间',
                               `end_time` bigint(64) NOT NULL COMMENT '结束时间',
                               `user_id` bigint(64) NOT NULL COMMENT '活跃用户',
                               `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浏览器信息',
                               `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系统信息',
                               `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '访问的ip地址',
                               `operation_time` bigint(64) NOT NULL COMMENT '操作耗时',
                               `result_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '0 成功  1 失败',
                               `error_message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '错误信息',
                               `update_time` bigint(64) NOT NULL COMMENT '修改时间',
                               `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of active_log
-- ----------------------------

-- ----------------------------
-- Table structure for instance_item
-- ----------------------------
DROP TABLE IF EXISTS `instance_item`;
CREATE TABLE `instance_item`  (
                                  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `instance_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '实例名称',
                                  `server_id` bigint(32) NOT NULL COMMENT '所属服务的名称',
                                  `active_time` bigint(64) NOT NULL COMMENT '活跃时间',
                                  `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                                  `update_time` bigint(64) NULL DEFAULT NULL COMMENT '修改时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '实例名称' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of instance_item
-- ----------------------------
BEGIN;
INSERT INTO `instance_item` VALUES (1, 'test-server-instance01', 1, 1685434363343, 1685434363343, NULL);
INSERT INTO `instance_item` VALUES (2, 'test-server-instance02', 1, 1685434363343, 1685434363343, NULL);
INSERT INTO `instance_item` VALUES (3, 'test-server-instance03', 1, 1685434363343, 1685434363343, NULL);
INSERT INTO `instance_item` VALUES (4, 'test-server-instance04', 1, 1685434363343, 1685434363343, NULL);
INSERT INTO `instance_item` VALUES (5, 'test-server-instance05', 1, 1685434363343, 1685434363343, NULL);
INSERT INTO `instance_item` VALUES (6, 'test-server-instance06', 1, 1685434363343, 1685434363343, NULL);
INSERT INTO `instance_item` VALUES (7, 'test-server-instance07', 1, 1685434363343, 1685434363343, NULL);
INSERT INTO `instance_item` VALUES (8, 'test-server-instance08', 1, 1685434363343, 1685434363343, NULL);
INSERT INTO `instance_item` VALUES (9, 'test-server-instance09', 1, 1685434363343, 1685434363343, NULL);
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
                         `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
                         `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单对应的',
                         `menu_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对应的图标',
                         `menu_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单介绍',
                         `sort` int(12) NOT NULL DEFAULT 0 COMMENT '排序字段',
                         `state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '菜单状态   0  正常   禁用',
                         `update_time` bigint(64) NOT NULL COMMENT '修改时间',
                         `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, '工作台', '/worktable', 'icon-gongzuotai', '工作台信息', 0, '0', 1685605885371, 1685605885371);
INSERT INTO `menu` VALUES (2, '项目', NULL, 'icon-icon-project', '项目信息', 10, '0', 1685605885371, 1685605885371);
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
                               `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
                               `permission_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限key',
                               `permission_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单介绍',
                               `update_time` bigint(64) NOT NULL COMMENT '修改时间',
                               `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, '添加用户', 'add:user', '管理员添加用户的权限信息', 1685770093193, 1685770093193);
INSERT INTO `permission` VALUES (2, '修改用户信息', 'update:user', '管理员修改用户信息的权限', 1685775482209, 1685775482209);
COMMIT;

-- ----------------------------
-- Table structure for server_item
-- ----------------------------
DROP TABLE IF EXISTS `server_item`;
CREATE TABLE `server_item`  (
                                `id` bigint(32) NOT NULL AUTO_INCREMENT,
                                `server_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务名称',
                                `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                                `update_time` bigint(64) NULL DEFAULT NULL COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '记录当前监控的服务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of server_item
-- ----------------------------
BEGIN;
INSERT INTO `server_item` VALUES (1, 'test-server', 1685434363335, NULL);
COMMIT;

-- ----------------------------
-- Table structure for thread_pool_data
-- ----------------------------
DROP TABLE IF EXISTS `thread_pool_data`;
CREATE TABLE `thread_pool_data`  (
                                     `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `server_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名称',
                                     `instance_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例名称',
                                     `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
                                     `thread_pool_group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '线程池组的名称',
                                     `thread_pool_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '线程池的名称',
                                     `core_pool_size` int(32) NOT NULL COMMENT '线程池的核心数量',
                                     `maximum_pool_size` int(32) NOT NULL COMMENT '最大可执行任务的线程数',
                                     `active_count` int(32) NOT NULL COMMENT '当前活跃的线程数',
                                     `this_thread_count` int(32) NOT NULL COMMENT '当前线程池的线程数量  包含没有执行任务的线程还没有来得及被销毁的非核心线程',
                                     `largest_pool_size` int(32) NOT NULL COMMENT '曾将达到的最大的线程数 历史信息',
                                     `rejected_count` bigint(64) NOT NULL COMMENT '拒绝执行的次数',
                                     `task_count` bigint(64) NOT NULL COMMENT '堆积的、执行中的、已经完成的任务的总和',
                                     `completed_task_count` bigint(64) NOT NULL COMMENT '已经完成的数量',
                                     `queue_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '队列类型',
                                     `rejected_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拒绝策略',
                                     `keep_alive_time` bigint(64) NULL DEFAULT NULL COMMENT '毫秒 线程空闲',
                                     `instance_id` bigint(32) NOT NULL COMMENT '所属实例的id',
                                     `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                                     `update_time` bigint(64) NULL DEFAULT NULL COMMENT '修改时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '线程池的监控数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of thread_pool_data
-- ----------------------------
BEGIN;
INSERT INTO `thread_pool_data` VALUES (1, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 2, 2, 2, 0, 8, 0, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434362972, NULL);
INSERT INTO `thread_pool_data` VALUES (2, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 3, 3, 3, 0, 15, 2, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434365902, NULL);
INSERT INTO `thread_pool_data` VALUES (3, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 1, 22, 2, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434368902, NULL);
INSERT INTO `thread_pool_data` VALUES (4, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 3, 27, 8, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434371897, NULL);
INSERT INTO `thread_pool_data` VALUES (5, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 5, 32, 13, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434374894, NULL);
INSERT INTO `thread_pool_data` VALUES (6, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 5, 40, 20, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434377896, NULL);
INSERT INTO `thread_pool_data` VALUES (7, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 8, 44, 25, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434380897, NULL);
INSERT INTO `thread_pool_data` VALUES (8, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 8, 52, 32, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434383897, NULL);
INSERT INTO `thread_pool_data` VALUES (9, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 10, 57, 38, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434386896, NULL);
INSERT INTO `thread_pool_data` VALUES (10, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 12, 63, 43, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434389896, NULL);
INSERT INTO `thread_pool_data` VALUES (11, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 12, 70, 50, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434392892, NULL);
INSERT INTO `thread_pool_data` VALUES (12, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 15, 74, 55, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434395902, NULL);
INSERT INTO `thread_pool_data` VALUES (13, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 15, 82, 62, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434398894, NULL);
INSERT INTO `thread_pool_data` VALUES (14, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 17, 84, 68, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434401892, NULL);
INSERT INTO `thread_pool_data` VALUES (15, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 10, 10, 10, 17, 84, 73, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434404896, NULL);
INSERT INTO `thread_pool_data` VALUES (16, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 4, 4, 10, 17, 84, 80, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434407902, NULL);
INSERT INTO `thread_pool_data` VALUES (17, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 2, 10, 0, 2, 10, 17, 84, 84, 'java.util.concurrent.ArrayBlockingQueue', 'java.util.concurrent.ThreadPoolExecutor$AbortPolicy', 0, 1, 1685434410898, NULL);
COMMIT;

-- ----------------------------
-- Table structure for thread_task_data
-- ----------------------------
DROP TABLE IF EXISTS `thread_task_data`;
CREATE TABLE `thread_task_data`  (
                                     `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `server_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名称',
                                     `instance_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例名称',
                                     `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
                                     `thread_pool_group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '线程池组的名称',
                                     `thread_pool_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '线程池的名称',
                                     `thread_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '被那一个线程执行的',
                                     `submit_time` bigint(64) NULL DEFAULT NULL COMMENT '该时间为任务被提交的时间，只要该任务被加载进线程池，这个时间就会被初始化',
                                     `start_time` bigint(64) NULL DEFAULT NULL COMMENT '任务开始运行的时间，注意，这里的开始时间是任务真正开始运行的时间，不是提交的时间，因为他可能被堆积在队列中',
                                     `end_time` bigint(64) NULL DEFAULT NULL COMMENT '任务的结束时间，无奈他是正常结束，或者是异常，这个时间都会存在，当然，被拒绝的任务不在此列！',
                                     `runIng_consuming_time` bigint(64) NULL DEFAULT NULL COMMENT '任务的执行耗时',
                                     `wait_time` bigint(64) NULL DEFAULT NULL COMMENT '任务等待时间',
                                     `consuming_time` bigint(64) NULL DEFAULT NULL COMMENT '任务总耗时',
                                     `refuse` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '0  false  1 true   当任务被拒绝策略执行的时候，该值为true,否则为false!',
                                     `success` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '0  false  1 true  任务是否被执行成功，如果中途异常、被拒绝，该值都会被设置为false, 否则为true',
                                     `throwable` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '任务的异常信息，当没有异常的时候，这个值为空！',
                                     `instance_id` bigint(32) NOT NULL COMMENT '所属实例的id',
                                     `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                                     `update_time` bigint(64) NULL DEFAULT NULL COMMENT '修改时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '线程池内任务执行信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of thread_task_data
-- ----------------------------
BEGIN;
INSERT INTO `thread_task_data` VALUES (1, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434359933, 1685434359934, 1685434364940, 5006, 1, 5007, '0', '1', NULL, 1, 1685434364954, NULL);
INSERT INTO `thread_task_data` VALUES (2, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434360339, 1685434360343, 1685434365349, 5006, 4, 5010, '0', '1', NULL, 1, 1685434365355, NULL);
INSERT INTO `thread_task_data` VALUES (3, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434368834, 1685434368834, 1685434368835, 1, 0, 1, '1', '0', NULL, 1, 1685434368845, NULL);
INSERT INTO `thread_task_data` VALUES (4, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434369244, 1685434369244, 1685434369244, 0, 0, 0, '1', '0', NULL, 1, 1685434369248, NULL);
INSERT INTO `thread_task_data` VALUES (5, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434369648, 1685434369648, 1685434369648, 0, 0, 0, '1', '0', NULL, 1, 1685434369649, NULL);
INSERT INTO `thread_task_data` VALUES (6, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434364798, 1685434364799, 1685434369801, 5002, 1, 5003, '0', '1', NULL, 1, 1685434369807, NULL);
INSERT INTO `thread_task_data` VALUES (7, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434360748, 1685434364942, 1685434369945, 5003, 4194, 9197, '0', '1', NULL, 1, 1685434369947, NULL);
INSERT INTO `thread_task_data` VALUES (8, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434361150, 1685434365350, 1685434370352, 5002, 4200, 9202, '0', '1', NULL, 1, 1685434370358, NULL);
INSERT INTO `thread_task_data` VALUES (9, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434366010, 1685434366013, 1685434371015, 5002, 3, 5005, '0', '1', NULL, 1, 1685434371026, NULL);
INSERT INTO `thread_task_data` VALUES (10, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434366415, 1685434366416, 1685434371421, 5005, 1, 5006, '0', '1', NULL, 1, 1685434371428, NULL);
INSERT INTO `thread_task_data` VALUES (11, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434366817, 1685434366817, 1685434371823, 5006, 0, 5006, '0', '1', NULL, 1, 1685434371831, NULL);
INSERT INTO `thread_task_data` VALUES (12, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434367218, 1685434367218, 1685434372220, 5002, 0, 5002, '0', '1', NULL, 1, 1685434372225, NULL);
INSERT INTO `thread_task_data` VALUES (13, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434367624, 1685434367624, 1685434372629, 5005, 0, 5005, '0', '1', NULL, 1, 1685434372638, NULL);
INSERT INTO `thread_task_data` VALUES (14, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434368028, 1685434368030, 1685434373032, 5002, 2, 5004, '0', '1', NULL, 1, 1685434373037, NULL);
INSERT INTO `thread_task_data` VALUES (15, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434368431, 1685434368432, 1685434373436, 5004, 1, 5005, '0', '1', NULL, 1, 1685434373441, NULL);
INSERT INTO `thread_task_data` VALUES (16, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434374090, 1685434374090, 1685434374091, 1, 0, 1, '1', '0', NULL, 1, 1685434374113, NULL);
INSERT INTO `thread_task_data` VALUES (17, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434374497, 1685434374497, 1685434374497, 0, 0, 0, '1', '0', NULL, 1, 1685434374499, NULL);
INSERT INTO `thread_task_data` VALUES (18, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434361556, 1685434369802, 1685434374807, 5005, 8246, 13251, '0', '1', NULL, 1, 1685434374809, NULL);
INSERT INTO `thread_task_data` VALUES (19, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434361961, 1685434369946, 1685434374952, 5006, 7985, 12991, '0', '1', NULL, 1, 1685434374955, NULL);
INSERT INTO `thread_task_data` VALUES (20, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434362365, 1685434370353, 1685434375354, 5001, 7988, 12989, '0', '1', NULL, 1, 1685434375358, NULL);
INSERT INTO `thread_task_data` VALUES (21, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434362771, 1685434371016, 1685434376021, 5005, 8245, 13250, '0', '1', NULL, 1, 1685434376028, NULL);
INSERT INTO `thread_task_data` VALUES (22, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434363176, 1685434371425, 1685434376430, 5005, 8249, 13254, '0', '1', NULL, 1, 1685434376440, NULL);
INSERT INTO `thread_task_data` VALUES (23, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434363581, 1685434371824, 1685434376829, 5005, 8243, 13248, '0', '1', NULL, 1, 1685434376840, NULL);
INSERT INTO `thread_task_data` VALUES (24, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434363986, 1685434372221, 1685434377223, 5002, 8235, 13237, '0', '1', NULL, 1, 1685434377230, NULL);
INSERT INTO `thread_task_data` VALUES (25, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434364392, 1685434372630, 1685434377632, 5002, 8238, 13240, '0', '1', NULL, 1, 1685434377638, NULL);
INSERT INTO `thread_task_data` VALUES (26, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434365203, 1685434373033, 1685434378034, 5001, 7830, 12831, '0', '1', NULL, 1, 1685434378037, NULL);
INSERT INTO `thread_task_data` VALUES (27, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434365604, 1685434373436, 1685434378437, 5001, 7832, 12833, '0', '1', NULL, 1, 1685434378442, NULL);
INSERT INTO `thread_task_data` VALUES (28, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434378936, 1685434378936, 1685434378936, 0, 0, 0, '1', '0', NULL, 1, 1685434378942, NULL);
INSERT INTO `thread_task_data` VALUES (29, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434379342, 1685434379342, 1685434379343, 1, 0, 1, '1', '0', NULL, 1, 1685434379347, NULL);
INSERT INTO `thread_task_data` VALUES (30, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434379746, 1685434379746, 1685434379746, 0, 0, 0, '1', '0', NULL, 1, 1685434379751, NULL);
INSERT INTO `thread_task_data` VALUES (31, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434370051, 1685434374807, 1685434379812, 5005, 4756, 9761, '0', '1', NULL, 1, 1685434379816, NULL);
INSERT INTO `thread_task_data` VALUES (32, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434370456, 1685434374952, 1685434379962, 5010, 4496, 9506, '0', '1', NULL, 1, 1685434379971, NULL);
INSERT INTO `thread_task_data` VALUES (33, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434370859, 1685434375354, 1685434380360, 5006, 4495, 9501, '0', '1', NULL, 1, 1685434380365, NULL);
INSERT INTO `thread_task_data` VALUES (34, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434371261, 1685434376021, 1685434381026, 5005, 4760, 9765, '0', '1', NULL, 1, 1685434381037, NULL);
INSERT INTO `thread_task_data` VALUES (35, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434371666, 1685434376431, 1685434381434, 5003, 4765, 9768, '0', '1', NULL, 1, 1685434381440, NULL);
INSERT INTO `thread_task_data` VALUES (36, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434372070, 1685434376829, 1685434381834, 5005, 4759, 9764, '0', '1', NULL, 1, 1685434381838, NULL);
INSERT INTO `thread_task_data` VALUES (37, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434372476, 1685434377223, 1685434382225, 5002, 4747, 9749, '0', '1', NULL, 1, 1685434382229, NULL);
INSERT INTO `thread_task_data` VALUES (38, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434372881, 1685434377633, 1685434382636, 5003, 4752, 9755, '0', '1', NULL, 1, 1685434382641, NULL);
INSERT INTO `thread_task_data` VALUES (39, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434373283, 1685434378034, 1685434383037, 5003, 4751, 9754, '0', '1', NULL, 1, 1685434383039, NULL);
INSERT INTO `thread_task_data` VALUES (40, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434373689, 1685434378438, 1685434383441, 5003, 4749, 9752, '0', '1', NULL, 1, 1685434383443, NULL);
INSERT INTO `thread_task_data` VALUES (41, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434384191, 1685434384191, 1685434384191, 0, 0, 0, '1', '0', NULL, 1, 1685434384196, NULL);
INSERT INTO `thread_task_data` VALUES (42, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434384593, 1685434384593, 1685434384593, 0, 0, 0, '1', '0', NULL, 1, 1685434384599, NULL);
INSERT INTO `thread_task_data` VALUES (43, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434374901, 1685434379813, 1685434384815, 5002, 4912, 9914, '0', '1', NULL, 1, 1685434384819, NULL);
INSERT INTO `thread_task_data` VALUES (44, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434375305, 1685434379963, 1685434384967, 5004, 4658, 9662, '0', '1', NULL, 1, 1685434384977, NULL);
INSERT INTO `thread_task_data` VALUES (45, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434375710, 1685434380360, 1685434385370, 5010, 4650, 9660, '0', '1', NULL, 1, 1685434385374, NULL);
INSERT INTO `thread_task_data` VALUES (46, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434376113, 1685434381027, 1685434386032, 5005, 4914, 9919, '0', '1', NULL, 1, 1685434386037, NULL);
INSERT INTO `thread_task_data` VALUES (47, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434376515, 1685434381435, 1685434386436, 5001, 4920, 9921, '0', '1', NULL, 1, 1685434386439, NULL);
INSERT INTO `thread_task_data` VALUES (48, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434376920, 1685434381835, 1685434386838, 5003, 4915, 9918, '0', '1', NULL, 1, 1685434386841, NULL);
INSERT INTO `thread_task_data` VALUES (49, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434377322, 1685434382225, 1685434387227, 5002, 4903, 9905, '0', '1', NULL, 1, 1685434387233, NULL);
INSERT INTO `thread_task_data` VALUES (50, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434377725, 1685434382637, 1685434387637, 5000, 4912, 9912, '0', '1', NULL, 1, 1685434387643, NULL);
INSERT INTO `thread_task_data` VALUES (51, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434378125, 1685434383037, 1685434388043, 5006, 4912, 9918, '0', '1', NULL, 1, 1685434388049, NULL);
INSERT INTO `thread_task_data` VALUES (52, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434378531, 1685434383441, 1685434388446, 5005, 4910, 9915, '0', '1', NULL, 1, 1685434388455, NULL);
INSERT INTO `thread_task_data` VALUES (53, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434389027, 1685434389027, 1685434389027, 0, 0, 0, '1', '0', NULL, 1, 1685434389030, NULL);
INSERT INTO `thread_task_data` VALUES (54, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434389433, 1685434389433, 1685434389433, 0, 0, 0, '1', '0', NULL, 1, 1685434389434, NULL);
INSERT INTO `thread_task_data` VALUES (55, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434380152, 1685434384816, 1685434389820, 5004, 4664, 9668, '0', '1', NULL, 1, 1685434389825, NULL);
INSERT INTO `thread_task_data` VALUES (56, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434380558, 1685434384967, 1685434389971, 5004, 4409, 9413, '0', '1', NULL, 1, 1685434389973, NULL);
INSERT INTO `thread_task_data` VALUES (57, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434380962, 1685434385370, 1685434390375, 5005, 4408, 9413, '0', '1', NULL, 1, 1685434390380, NULL);
INSERT INTO `thread_task_data` VALUES (58, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434381365, 1685434386033, 1685434391035, 5002, 4668, 9670, '0', '1', NULL, 1, 1685434391037, NULL);
INSERT INTO `thread_task_data` VALUES (59, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434381771, 1685434386436, 1685434391440, 5004, 4665, 9669, '0', '1', NULL, 1, 1685434391442, NULL);
INSERT INTO `thread_task_data` VALUES (60, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434382172, 1685434386839, 1685434391842, 5003, 4667, 9670, '0', '1', NULL, 1, 1685434391844, NULL);
INSERT INTO `thread_task_data` VALUES (61, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434382577, 1685434387227, 1685434392232, 5005, 4650, 9655, '0', '1', NULL, 1, 1685434392233, NULL);
INSERT INTO `thread_task_data` VALUES (62, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434382980, 1685434387638, 1685434392642, 5004, 4658, 9662, '0', '1', NULL, 1, 1685434392644, NULL);
INSERT INTO `thread_task_data` VALUES (63, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434383381, 1685434388043, 1685434393049, 5006, 4662, 9668, '0', '1', NULL, 1, 1685434393050, NULL);
INSERT INTO `thread_task_data` VALUES (64, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434383786, 1685434388447, 1685434393449, 5002, 4661, 9663, '0', '1', NULL, 1, 1685434393451, NULL);
INSERT INTO `thread_task_data` VALUES (65, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434393875, 1685434393875, 1685434393875, 0, 0, 0, '1', '0', NULL, 1, 1685434393877, NULL);
INSERT INTO `thread_task_data` VALUES (66, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434394278, 1685434394278, 1685434394279, 1, 0, 1, '1', '0', NULL, 1, 1685434394283, NULL);
INSERT INTO `thread_task_data` VALUES (67, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434394685, 1685434394685, 1685434394685, 0, 0, 0, '1', '0', NULL, 1, 1685434394688, NULL);
INSERT INTO `thread_task_data` VALUES (68, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434384995, 1685434389821, 1685434394821, 5000, 4826, 9826, '0', '1', NULL, 1, 1685434394824, NULL);
INSERT INTO `thread_task_data` VALUES (69, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434385397, 1685434389971, 1685434394976, 5005, 4574, 9579, '0', '1', NULL, 1, 1685434394977, NULL);
INSERT INTO `thread_task_data` VALUES (70, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434385801, 1685434390378, 1685434395383, 5005, 4577, 9582, '0', '1', NULL, 1, 1685434395386, NULL);
INSERT INTO `thread_task_data` VALUES (71, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434386204, 1685434391035, 1685434396040, 5005, 4831, 9836, '0', '1', NULL, 1, 1685434396046, NULL);
INSERT INTO `thread_task_data` VALUES (72, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434386607, 1685434391441, 1685434396446, 5005, 4834, 9839, '0', '1', NULL, 1, 1685434396450, NULL);
INSERT INTO `thread_task_data` VALUES (73, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434387012, 1685434391843, 1685434396848, 5005, 4831, 9836, '0', '1', NULL, 1, 1685434396852, NULL);
INSERT INTO `thread_task_data` VALUES (74, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434387414, 1685434392232, 1685434397237, 5005, 4818, 9823, '0', '1', NULL, 1, 1685434397245, NULL);
INSERT INTO `thread_task_data` VALUES (75, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434387816, 1685434392643, 1685434397645, 5002, 4827, 9829, '0', '1', NULL, 1, 1685434397646, NULL);
INSERT INTO `thread_task_data` VALUES (76, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434388221, 1685434393049, 1685434398059, 5010, 4828, 9838, '0', '1', NULL, 1, 1685434398064, NULL);
INSERT INTO `thread_task_data` VALUES (77, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434388625, 1685434393449, 1685434398451, 5002, 4824, 9826, '0', '1', NULL, 1, 1685434398459, NULL);
INSERT INTO `thread_task_data` VALUES (78, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434399129, 1685434399129, 1685434399129, 0, 0, 0, '1', '0', NULL, 1, 1685434399131, NULL);
INSERT INTO `thread_task_data` VALUES (79, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', NULL, 1685434399535, 1685434399535, 1685434399535, 0, 0, 0, '1', '0', NULL, 1, 1685434399542, NULL);
INSERT INTO `thread_task_data` VALUES (80, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434389837, 1685434394821, 1685434399825, 5004, 4984, 9988, '0', '1', NULL, 1, 1685434399827, NULL);
INSERT INTO `thread_task_data` VALUES (81, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434390240, 1685434394977, 1685434399982, 5005, 4737, 9742, '0', '1', NULL, 1, 1685434399985, NULL);
INSERT INTO `thread_task_data` VALUES (82, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434390642, 1685434395384, 1685434400387, 5003, 4742, 9745, '0', '1', NULL, 1, 1685434400393, NULL);
INSERT INTO `thread_task_data` VALUES (83, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434391046, 1685434396041, 1685434401042, 5001, 4995, 9996, '0', '1', NULL, 1, 1685434401047, NULL);
INSERT INTO `thread_task_data` VALUES (84, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434391449, 1685434396446, 1685434401449, 5003, 4997, 10000, '0', '1', NULL, 1, 1685434401450, NULL);
INSERT INTO `thread_task_data` VALUES (85, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434391854, 1685434396848, 1685434401853, 5005, 4994, 9999, '0', '1', NULL, 1, 1685434401856, NULL);
INSERT INTO `thread_task_data` VALUES (86, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434392257, 1685434397244, 1685434402245, 5001, 4987, 9988, '0', '1', NULL, 1, 1685434402248, NULL);
INSERT INTO `thread_task_data` VALUES (87, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434392662, 1685434397645, 1685434402651, 5006, 4983, 9989, '0', '1', NULL, 1, 1685434402655, NULL);
INSERT INTO `thread_task_data` VALUES (88, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434393067, 1685434398060, 1685434403065, 5005, 4993, 9998, '0', '1', NULL, 1, 1685434403070, NULL);
INSERT INTO `thread_task_data` VALUES (89, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434393471, 1685434398452, 1685434403454, 5002, 4981, 9983, '0', '1', NULL, 1, 1685434403455, NULL);
INSERT INTO `thread_task_data` VALUES (90, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434395088, 1685434399825, 1685434404831, 5006, 4737, 9743, '0', '1', NULL, 1, 1685434404835, NULL);
INSERT INTO `thread_task_data` VALUES (91, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434395496, 1685434399982, 1685434404987, 5005, 4486, 9491, '0', '1', NULL, 1, 1685434404990, NULL);
INSERT INTO `thread_task_data` VALUES (92, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434395902, 1685434400388, 1685434405393, 5005, 4486, 9491, '0', '1', NULL, 1, 1685434405398, NULL);
INSERT INTO `thread_task_data` VALUES (93, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434396305, 1685434401043, 1685434406045, 5002, 4738, 9740, '0', '1', NULL, 1, 1685434406055, NULL);
INSERT INTO `thread_task_data` VALUES (94, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434396708, 1685434401449, 1685434406454, 5005, 4741, 9746, '0', '1', NULL, 1, 1685434406458, NULL);
INSERT INTO `thread_task_data` VALUES (95, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434397109, 1685434401854, 1685434406859, 5005, 4745, 9750, '0', '1', NULL, 1, 1685434406863, NULL);
INSERT INTO `thread_task_data` VALUES (96, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434397511, 1685434402246, 1685434407246, 5000, 4735, 9735, '0', '1', NULL, 1, 1685434407251, NULL);
INSERT INTO `thread_task_data` VALUES (97, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434397916, 1685434402651, 1685434407652, 5001, 4735, 9736, '0', '1', NULL, 1, 1685434407656, NULL);
INSERT INTO `thread_task_data` VALUES (98, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434398322, 1685434403066, 1685434408071, 5005, 4744, 9749, '0', '1', NULL, 1, 1685434408074, NULL);
INSERT INTO `thread_task_data` VALUES (99, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434398724, 1685434403454, 1685434408457, 5003, 4730, 9733, '0', '1', NULL, 1, 1685434408462, NULL);
INSERT INTO `thread_task_data` VALUES (100, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434399939, 1685434404831, 1685434409836, 5005, 4892, 9897, '0', '1', NULL, 1, 1685434409838, NULL);
INSERT INTO `thread_task_data` VALUES (101, 'test-server', 'test-server-instance01', '/127.0.0.1:51405', 'impl.huangfu.M2Print#main:23', 'impl.huangfu.M2Print#main:23-405215542', 'sfdfdsdfdsfsdf ', 1685434400341, 1685434404988, 1685434409992, 5004, 4647, 9651, '0', '1', NULL, 1, 1685434409997, NULL);
COMMIT;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
                         `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
                         `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
                         `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户邮箱',
                         `state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1' COMMENT '0  冻结  1 正常',
                         `create_time` bigint(64) NOT NULL COMMENT '创建时间',
                         `update_time` bigint(64) NULL DEFAULT NULL COMMENT '修改时间',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE INDEX `user_name_index`(`user_name`) USING BTREE COMMENT '用户用户名 唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'threadx的用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$BQrmrFvsElKyr/b4j8wkVuxsYAuIdaWzs8UbR2PsTmR9ewveAyKpG', '测试用户', 'huangfusuper@163.com', '1', 1685584117047, 1685584117047);
INSERT INTO `user` VALUES (2, 'zanzan', '$2a$10$7adq/2vocU3Bclhonq8mT.DSEZlVU/wTb2uVfeZwEJWHhCI7zo0Wa', '昝', 'zanzan@qq.com', '1', 1685770152606, 1685770152606);
COMMIT;
-- ----------------------------
-- Table structure for user_menu
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu`  (
                              `user_id` bigint(64) NOT NULL COMMENT '用户id',
                              `menu_id` bigint(64) NOT NULL COMMENT '菜单id',
                              PRIMARY KEY (`user_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_menu
-- ----------------------------
BEGIN;
INSERT INTO `user_menu` VALUES (1, 1);
INSERT INTO `user_menu` VALUES (1, 2);
COMMIT;
-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission`  (
                                    `user_id` bigint(20) NOT NULL COMMENT '用户id',
                                    `permission_id` bigint(20) NOT NULL COMMENT '权限id',
                                    PRIMARY KEY (`user_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_permission
-- ----------------------------
BEGIN;
INSERT INTO `user_permission` VALUES (1, 1);
INSERT INTO `user_permission` VALUES (1, 2);
COMMIT;
SET FOREIGN_KEY_CHECKS = 1;



