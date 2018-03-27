/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost
 Source Database       : bank

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : utf-8

 Date: 03/15/2018 00:04:55 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `bank_node_info`
-- ----------------------------
DROP TABLE IF EXISTS `bank_node_info`;
CREATE TABLE `bank_node_info` (
  `id` bigint(20) NOT NULL,
  `node_code` varchar(200) DEFAULT NULL COMMENT '节点编码',
  `node_name` varchar(200) DEFAULT NULL COMMENT '节点名称',
  `ip_addr` varchar(200) DEFAULT NULL COMMENT 'ip地址',
  `call_addr` varchar(200) DEFAULT NULL COMMENT '调用地址',
  `yn` tinyint(1) DEFAULT '0' COMMENT '删除标识：1 已删除，0 未删除,(有效标识、日期、录入人)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_pin` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(50) DEFAULT NULL COMMENT '更新操作人',
  `ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点管理';

-- ----------------------------
--  Table structure for `bank_sys_call_rela`
-- ----------------------------
DROP TABLE IF EXISTS `bank_sys_call_rela`;
CREATE TABLE `bank_sys_call_rela` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_uuid` varchar(200) DEFAULT NULL COMMENT '路由uuid',
  `now_route_node` varchar(200) DEFAULT NULL COMMENT '当前路由节点',
  `next_route_node` varchar(200) DEFAULT NULL COMMENT '下一个路由名称',
  `protocol_uuid` varchar(200) DEFAULT NULL COMMENT '协议uuid',
  `yn` tinyint(1) DEFAULT '0' COMMENT '删除表示（0:未删除，1:已删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_pin` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_pin` varchar(255) DEFAULT NULL COMMENT '修改人',
  `ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统调用关系';

-- ----------------------------
--  Table structure for `sys_service`
-- ----------------------------
DROP TABLE IF EXISTS `sys_service`;
CREATE TABLE `sys_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(500) DEFAULT NULL COMMENT '服务名称',
  `node_code` varchar(200) DEFAULT NULL COMMENT '节点编码',
  `node_name` varchar(200) DEFAULT NULL COMMENT '节点名称',
  `service_addr` varchar(200) DEFAULT NULL COMMENT '服务地址',
  `method_name` varchar(200) DEFAULT NULL COMMENT '方法名称',
  `protocol_type` varchar(50) DEFAULT NULL COMMENT '协议类型',
  `yn` tinyint(1) DEFAULT '0' COMMENT '删除表示（0:未删除，1:已删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_pin` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_pin` varchar(255) CHARACTER SET utf32 DEFAULT NULL COMMENT '修改人',
  `ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统服务表';

-- ----------------------------
--  Table structure for `trade_detail`
-- ----------------------------
DROP TABLE IF EXISTS `trade_detail`;
CREATE TABLE `trade_detail` (
  `id` bigint(20) NOT NULL,
  `uuid` varchar(200) NOT NULL COMMENT 'uuid主键',
  `jkmc` varchar(255) DEFAULT NULL COMMENT '接口名称',
  `qqxt` varchar(255) DEFAULT NULL COMMENT '请求系统',
  `mbxt` varchar(255) DEFAULT NULL COMMENT '目标系统',
  `mbjk` varchar(255) DEFAULT NULL COMMENT '目标接口名称',
  `qqxml` blob COMMENT '请求xml',
  `fhxml` blob COMMENT '返回xml',
  `xyxml` blob COMMENT '相应xml',
  `ycxx` blob COMMENT '异常信息',
  `jdwz` varchar(1) DEFAULT NULL COMMENT '1:接入;2:调用;3:返回:4:相应',
  `zt` varchar(1) DEFAULT NULL COMMENT '成功失败（0:成功，1:失败）',
  `lrrq` date DEFAULT NULL COMMENT '录入日期',
  `jyhs` varchar(50) DEFAULT NULL COMMENT '交易耗时',
  `ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`,`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易流水表';

SET FOREIGN_KEY_CHECKS = 1;
