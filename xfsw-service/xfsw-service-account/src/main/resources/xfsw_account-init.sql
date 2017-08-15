/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : xfsw_account

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-15 22:48:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for categoryauthority
-- ----------------------------
DROP TABLE IF EXISTS `categoryauthority`;
CREATE TABLE `categoryauthority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hashId` int(11) DEFAULT NULL COMMENT '哈希ID',
  `name` varchar(20) NOT NULL COMMENT '权限名称',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '上级ID',
  `url` varchar(100) DEFAULT NULL COMMENT '权限链接路径',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `orderIndex` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ico` varchar(20) DEFAULT NULL COMMENT '菜单图标',
  `lastUpdater` varchar(40) DEFAULT NULL,
  `lastUpdateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hashId` (`hashId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of categoryauthority
-- ----------------------------
INSERT INTO `categoryauthority` VALUES ('1', null, '系统管理', '0', '', '', '1', 'laptop', 'admin', '2017-05-14 21:08:11');
INSERT INTO `categoryauthority` VALUES ('2', '1353971725', '系统权限管理', '1', '/manager/account/category/authority/index.shtml', '', '1', '', 'admin', '2017-05-14 21:08:15');
INSERT INTO `categoryauthority` VALUES ('3', '350334729', '系统角色管理', '1', '/manager/account/role/index.shtml', '', '2', '', 'admin', '2017-05-14 21:08:00');
INSERT INTO `categoryauthority` VALUES ('4', '800112598', '系统用户管理', '1', '/manager/account/user/index.shtml', '', '3', '', null, null);
INSERT INTO `categoryauthority` VALUES ('5', '790353534', '公共权限管理', '1', '/manager/account/common/authority/insertAuthority.shtml', '', '4', '', null, null);

-- ----------------------------
-- Table structure for categoryauthority_copy
-- ----------------------------
DROP TABLE IF EXISTS `categoryauthority_copy`;
CREATE TABLE `categoryauthority_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hashId` int(11) DEFAULT NULL COMMENT '哈希ID',
  `name` varchar(20) NOT NULL COMMENT '权限名称',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '上级ID',
  `url` varchar(100) DEFAULT NULL COMMENT '权限链接路径',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `orderIndex` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ico` varchar(20) DEFAULT NULL COMMENT '菜单图标',
  `lastUpdater` varchar(40) DEFAULT NULL,
  `lastUpdateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hashId` (`hashId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of categoryauthority_copy
-- ----------------------------
INSERT INTO `categoryauthority_copy` VALUES ('1', null, '系统管理', '0', '', '', '1', 'laptop', 'admin', '2017-05-14 21:08:11');
INSERT INTO `categoryauthority_copy` VALUES ('2', '1353971725', '系统权限管理', '1', '/manager/account/category/authority/index.shtml', '', '1', '', 'admin', '2017-05-14 21:08:15');
INSERT INTO `categoryauthority_copy` VALUES ('3', '350334729', '系统角色管理', '1', '/manager/account/role/index.shtml', '', '2', '', 'admin', '2017-05-14 21:08:00');
INSERT INTO `categoryauthority_copy` VALUES ('4', '800112598', '系统用户管理', '1', '/manager/account/user/index.shtml', '', '3', '', null, null);
INSERT INTO `categoryauthority_copy` VALUES ('5', '790353534', '公共权限管理', '1', '/manager/account/common/authority/insertAuthority.shtml', '', '4', '', null, null);
INSERT INTO `categoryauthority_copy` VALUES ('6', null, '课程管理', '0', '', '教师课程管理功能', '1', 'book', 'admin', '2017-05-15 18:45:56');
INSERT INTO `categoryauthority_copy` VALUES ('7', '1453275528', '课程模版', '6', '/manager/acadamic/course/templet/index.shtml', '', '1', '', 'admin', '2017-05-15 18:45:52');

-- ----------------------------
-- Table structure for commonauthority
-- ----------------------------
DROP TABLE IF EXISTS `commonauthority`;
CREATE TABLE `commonauthority` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `url` varchar(300) NOT NULL COMMENT '权限路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commonauthority
-- ----------------------------
INSERT INTO `commonauthority` VALUES ('74222338', '首页', '/index.shtml');
INSERT INTO `commonauthority` VALUES ('187717343', '系统品类属性数据列表', '/public/data/category/getPropertyInfo.shtml');
INSERT INTO `commonauthority` VALUES ('232747584', 'Materia人员数据列表', '/system/user/onlinerNameList.shtml');
INSERT INTO `commonauthority` VALUES ('302831291', '系统货币数据列表', '/system/coin/coinList.shtml');
INSERT INTO `commonauthority` VALUES ('319634232', '系统品类数据接口', '/public/data/category/getCategoryInfo.shtml');
INSERT INTO `commonauthority` VALUES ('396119327', '修改个人密码', '/system/personal/updatePwd.shtml');
INSERT INTO `commonauthority` VALUES ('473419769', '品类数据（已排序）', '/data/category/allListInGroup.shtml');
INSERT INTO `commonauthority` VALUES ('664979706', '获取左侧菜单', '/system/authority/getUserAuthorityList.shtml');
INSERT INTO `commonauthority` VALUES ('1138642947', '系统消息数据列表', '/system/user/info/list.shtml');
INSERT INTO `commonauthority` VALUES ('1154859319', '检测买手店名称是否已被注册', '/buyer/shop/exsitName.shtml');
INSERT INTO `commonauthority` VALUES ('1210111114', '系统品类数据列表', '/public/data/category/list.shtml');
INSERT INTO `commonauthority` VALUES ('1252461780', '错误提示页面', '/error.shtml');
INSERT INTO `commonauthority` VALUES ('1385947220', '检测买手店账号是否已被注册', '/buyer/shop/checkRegistePhone.shtml');
INSERT INTO `commonauthority` VALUES ('1397630782', '买手店数据列表', '/buyer/shop/listAll.shtml');
INSERT INTO `commonauthority` VALUES ('1412446789', '获取品类属性数据', '/platform/product/getPropertyInfoByCategoryId.shtml');
INSERT INTO `commonauthority` VALUES ('1575607534', '消息标记已读', '/system/user/info/updateStatus.shtml');
INSERT INTO `commonauthority` VALUES ('1625836425', '个人信息权限', '/system/personal/index.shtml');
INSERT INTO `commonauthority` VALUES ('1657532216', '更新个人资料', '/system/personal/updateSystemuser.shtml');
INSERT INTO `commonauthority` VALUES ('1709686809', '无权访问', '/noAuth.shtml');
INSERT INTO `commonauthority` VALUES ('1840002871', '平台品牌数据列表', '/brand/info/list.shtml');

-- ----------------------------
-- Table structure for linkauthority
-- ----------------------------
DROP TABLE IF EXISTS `linkauthority`;
CREATE TABLE `linkauthority` (
  `id` int(11) NOT NULL COMMENT '哈希ID',
  `name` varchar(30) NOT NULL COMMENT '功能权限名称',
  `authorityId` int(11) NOT NULL COMMENT '菜单权限ID',
  `url` varchar(300) NOT NULL COMMENT '权限链接路径',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '最后操作人',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='链接权限表';

-- ----------------------------
-- Records of linkauthority
-- ----------------------------
INSERT INTO `linkauthority` VALUES ('23456991', '删除功能权限数据', '2', '/manager/account/category/authority/deleteLinkAuthority.shtml', 'admin', '2017-05-14 00:00:00');
INSERT INTO `linkauthority` VALUES ('83574335', '更新菜单权限数据', '2', '/manager/account/category/authority/updateCategoryAuthority.shtml', 'admin', '2017-05-14 21:06:00');
INSERT INTO `linkauthority` VALUES ('88688268', '添加角色数据信息', '3', '/manager/account/role/addRole.shtml', 'admin', '2017-05-15 18:56:58');
INSERT INTO `linkauthority` VALUES ('185149435', '获取角色权限SQL配置数据', '3', '/manager/account/role/authority/sql/getRoleAuthoritySqlById.shtml', 'admin', '2017-05-15 12:58:55');
INSERT INTO `linkauthority` VALUES ('237193705', '更新角色数据信息', '3', '/manager/account/role/editRole.shtml', 'admin', '2017-05-15 00:58:37');
INSERT INTO `linkauthority` VALUES ('271569888', '进入角色权限SQL配置页面', '3', '/manager/account/role/authority/sql/index.shtml', 'admin', '2017-05-15 10:15:19');
INSERT INTO `linkauthority` VALUES ('354450803', '添加菜单权限数据', '2', '/manager/account/category/authority/insertAuthority.shtml', 'admin', '2017-05-14 00:00:00');
INSERT INTO `linkauthority` VALUES ('484206187', '进入角色编辑页面', '3', '/manager/account/role/initEdit.shtml', 'admin', '2017-05-14 22:09:32');
INSERT INTO `linkauthority` VALUES ('594354886', '获取功能权限数据', '2', '/manager/account/category/authority/initEditLinkAuthority.shtml', 'admin', '2017-05-14 00:00:00');
INSERT INTO `linkauthority` VALUES ('632158582', '获取菜单权限数据', '2', '/manager/account/category/authority/initEditCategoryAuthority.shtml', 'admin', '2017-05-14 20:57:46');
INSERT INTO `linkauthority` VALUES ('662487556', '获取角色权限SQL配置数据', '3', '/manager/account/role/authority/sql/list.shtml', 'admin', '2017-05-15 11:17:45');
INSERT INTO `linkauthority` VALUES ('737364230', '菜单权限数据查询', '2', '/manager/data/list.shtml?aRandomCode=AccountCategoryAuthority', 'admin', '2017-05-14 02:16:42');
INSERT INTO `linkauthority` VALUES ('800993014', '删除角色数据信息', '3', '/manager/account/role/deleteRole.shtml', 'admin', '2017-05-15 22:36:23');
INSERT INTO `linkauthority` VALUES ('905764150', '配置角色权限SQL数据', '3', '/manager/account/role/authority/sql/updateRoleAuthoritySql.shtml', 'admin', '2017-05-15 15:20:31');
INSERT INTO `linkauthority` VALUES ('907507678', '获取角色数据（分页）', '3', '/manager/data/pageInfo.shtml?aRandomCode=AccountRole', 'admin', '2017-05-14 21:34:55');
INSERT INTO `linkauthority` VALUES ('1159544726', '进入功能权限配置页面', '2', '/manager/account/category/authority/initConfigLinkAuthority.shtml', 'admin', '2017-05-14 00:00:00');
INSERT INTO `linkauthority` VALUES ('1185770255', '更新功能权限数据', '2', '/manager/account/category/authority/updateLinkAuthority.shtml', 'admin', '2017-05-14 19:04:19');
INSERT INTO `linkauthority` VALUES ('1335180289', '刷新权限缓存数据', '2', '/manager/account/category/authority/refreshAuthorityCache.shtml', 'admin', '2017-05-14 21:12:53');
INSERT INTO `linkauthority` VALUES ('1774437206', '功能权限数据查询', '2', '/manager/data/list.shtml?aRandomCode=AccountLinkAuthority', 'admin', '2017-05-14 00:00:00');
INSERT INTO `linkauthority` VALUES ('1797044113', '删除菜单权限数据', '2', '/manager/account/category/authority/deleteAuthority.shtml', 'admin', '2017-05-14 20:42:26');
INSERT INTO `linkauthority` VALUES ('1801666536', '获取角色拥有的权限ID数据', '3', '/manager/account/role/authority/selectAuthorityListByRoleId.shtml', 'admin', '2017-05-14 22:18:59');
INSERT INTO `linkauthority` VALUES ('1839758972', '根据一级权限获取权限数据', '3', '/manager/account/role/authority/selectRoleAuthorityByPid.shtml', 'admin', '2017-05-14 23:05:21');
INSERT INTO `linkauthority` VALUES ('1888802574', '进入角色添加页面', '3', '/manager/account/role/initAdd.shtml', 'admin', '2017-05-15 01:08:54');
INSERT INTO `linkauthority` VALUES ('1908265921', '新增功能权限数据', '2', '/manager/account/category/authority/insertLinkAuthority.shtml', 'admin', '2017-05-14 00:00:00');

-- ----------------------------
-- Table structure for linkauthority_copy
-- ----------------------------
DROP TABLE IF EXISTS `linkauthority_copy`;
CREATE TABLE `linkauthority_copy` (
  `id` int(11) NOT NULL COMMENT '哈希ID',
  `name` varchar(30) NOT NULL COMMENT '功能权限名称',
  `authorityId` int(11) NOT NULL COMMENT '菜单权限ID',
  `url` varchar(300) NOT NULL COMMENT '权限链接路径',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '最后操作人',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='链接权限表';

-- ----------------------------
-- Records of linkauthority_copy
-- ----------------------------
INSERT INTO `linkauthority_copy` VALUES ('177622', '1', '2', '1', 'admin', '2017-05-14 18:24:14');
INSERT INTO `linkauthority_copy` VALUES ('177623', '2', '2', '2', 'admin', '2017-05-14 18:53:56');
INSERT INTO `linkauthority_copy` VALUES ('5861575', '11', '2', '11', 'admin', '2017-05-14 19:05:14');
INSERT INTO `linkauthority_copy` VALUES ('5861609', '22', '2', '22', 'admin', '2017-05-14 19:05:16');
INSERT INTO `linkauthority_copy` VALUES ('5861643', '33', '2', '33', 'admin', '2017-05-14 19:05:17');
INSERT INTO `linkauthority_copy` VALUES ('2088289545', '2222', '2', '1111', 'admin', '2017-05-14 19:07:34');

-- ----------------------------
-- Table structure for messagecode
-- ----------------------------
DROP TABLE IF EXISTS `messagecode`;
CREATE TABLE `messagecode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mediumType` int(2) NOT NULL,
  `mediumTypeRemark` varchar(20) NOT NULL,
  `code` varchar(4) NOT NULL,
  `ip` varchar(20) NOT NULL,
  `account` varchar(30) NOT NULL,
  `sendTime` datetime NOT NULL,
  `isValid` int(2) NOT NULL DEFAULT '0',
  `businessType` int(2) NOT NULL,
  `businessTypeRemark` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messagecode
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dataSql` varchar(300) DEFAULT NULL COMMENT '数据的SQL语句或者条件',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '最后操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', '系统管理员', null, 'admin', '2017-05-15 22:36:34');

-- ----------------------------
-- Table structure for roleauthoritysql
-- ----------------------------
DROP TABLE IF EXISTS `roleauthoritysql`;
CREATE TABLE `roleauthoritysql` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `authorityHashId` int(11) NOT NULL COMMENT '权限hashId',
  `countSql` varchar(200) DEFAULT NULL COMMENT '查询数量SQL',
  `dataSql` varchar(500) NOT NULL COMMENT '角色权限所对应的SQL语句',
  `dataSubfixSql` varchar(200) DEFAULT NULL COMMENT '数据SQL结尾（排序或者其他）',
  `dataPool` varchar(20) NOT NULL COMMENT '数据库代码（对应调用的service服务）',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '上次操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '上次操作时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleId` (`roleId`,`authorityHashId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleauthoritysql
-- ----------------------------
INSERT INTO `roleauthoritysql` VALUES ('1', '1', '737364230', null, 'SELECT * FROM CategoryAuthority ORDER BY pid,orderIndex ASC', null, 'account', 'admin', '2017-05-14 00:00:00');
INSERT INTO `roleauthoritysql` VALUES ('2', '1', '1774437206', null, 'SELECT * FROM LinkAuthority WHERE ', null, 'account', 'admin', '2017-05-14 00:00:00');
INSERT INTO `roleauthoritysql` VALUES ('3', '1', '907507678', 'SELECT COUNT(r.id) FROM Role r', 'SELECT r.* FROM Role r', '', 'account', 'admin', '2017-05-15 16:10:52');

-- ----------------------------
-- Table structure for roleauthoritysqlparam
-- ----------------------------
DROP TABLE IF EXISTS `roleauthoritysqlparam`;
CREATE TABLE `roleauthoritysqlparam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleAuthoritySqlId` int(11) NOT NULL COMMENT '对应的SQL表ID',
  `paramsName` varchar(100) NOT NULL COMMENT '参数名称',
  `paramsSql` varchar(300) DEFAULT NULL COMMENT '参数对应的SQL语句',
  `countParamSql` varchar(300) DEFAULT NULL COMMENT '数量查询SQL配置语句',
  `orderIndex` int(11) NOT NULL DEFAULT '0' COMMENT '参数顺序',
  `sign` int(2) NOT NULL DEFAULT '1' COMMENT '参数类型（0：内部参数，1：外部参数）',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '最后操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleauthoritysqlparam
-- ----------------------------
INSERT INTO `roleauthoritysqlparam` VALUES ('1', '2', 'authorityId', 'authorityId = #{authorityId}', null, '1', '1', 'admin', '2017-05-14 00:00:00');

-- ----------------------------
-- Table structure for roleauthoritysqlparam_copy
-- ----------------------------
DROP TABLE IF EXISTS `roleauthoritysqlparam_copy`;
CREATE TABLE `roleauthoritysqlparam_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleAuthoritySqlId` int(11) NOT NULL COMMENT '对应的SQL表ID',
  `paramsName` varchar(100) NOT NULL COMMENT '参数名称',
  `paramsSql` varchar(100) DEFAULT NULL COMMENT '参数对应的SQL语句',
  `countParamSql` varchar(100) DEFAULT NULL COMMENT '数量查询SQL配置语句',
  `orderIndex` int(11) NOT NULL DEFAULT '0' COMMENT '参数顺序',
  `sign` int(2) NOT NULL DEFAULT '1' COMMENT '参数类型（0：内部参数，1：外部参数）',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '最后操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleauthoritysqlparam_copy
-- ----------------------------

-- ----------------------------
-- Table structure for roleauthoritysql_copy
-- ----------------------------
DROP TABLE IF EXISTS `roleauthoritysql_copy`;
CREATE TABLE `roleauthoritysql_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `authorityHashId` int(11) NOT NULL COMMENT '权限hashId',
  `countSql` varchar(200) DEFAULT NULL COMMENT '查询数量SQL',
  `dataSql` varchar(500) NOT NULL COMMENT '角色权限所对应的SQL语句',
  `dataSubfixSql` varchar(200) DEFAULT NULL COMMENT '数据SQL结尾（排序或者其他）',
  `dataPool` varchar(20) NOT NULL COMMENT '数据库代码（对应调用的service服务）',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '上次操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '上次操作时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleId` (`roleId`,`authorityHashId`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleauthoritysql_copy
-- ----------------------------

-- ----------------------------
-- Table structure for rolecategoryauthority
-- ----------------------------
DROP TABLE IF EXISTS `rolecategoryauthority`;
CREATE TABLE `rolecategoryauthority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `authorityId` int(11) NOT NULL COMMENT '权限ID',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '最后操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of rolecategoryauthority
-- ----------------------------
INSERT INTO `rolecategoryauthority` VALUES ('1', '1', '1', 'admin', '2017-05-14 02:17:58');
INSERT INTO `rolecategoryauthority` VALUES ('2', '1', '2', 'admin', '2017-05-14 02:17:58');
INSERT INTO `rolecategoryauthority` VALUES ('3', '1', '3', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolecategoryauthority` VALUES ('6', '1', '4', null, null);
INSERT INTO `rolecategoryauthority` VALUES ('8', '1', '5', null, null);

-- ----------------------------
-- Table structure for rolecategoryauthority_copy
-- ----------------------------
DROP TABLE IF EXISTS `rolecategoryauthority_copy`;
CREATE TABLE `rolecategoryauthority_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `authorityId` int(11) NOT NULL COMMENT '权限ID',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '最后操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of rolecategoryauthority_copy
-- ----------------------------
INSERT INTO `rolecategoryauthority_copy` VALUES ('4', '1', '4', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolecategoryauthority_copy` VALUES ('5', '1', '5', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolecategoryauthority_copy` VALUES ('7', '1', '5', null, null);
INSERT INTO `rolecategoryauthority_copy` VALUES ('9', '1', '6', 'admin', '2017-05-15 18:45:56');
INSERT INTO `rolecategoryauthority_copy` VALUES ('10', '1', '7', 'admin', '2017-05-15 18:45:52');
INSERT INTO `rolecategoryauthority_copy` VALUES ('11', '2', '1', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolecategoryauthority_copy` VALUES ('12', '2', '5', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolecategoryauthority_copy` VALUES ('13', '2', '2', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolecategoryauthority_copy` VALUES ('14', '2', '3', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolecategoryauthority_copy` VALUES ('15', '3', '1', 'admin', '2017-05-15 22:44:53');
INSERT INTO `rolecategoryauthority_copy` VALUES ('16', '3', '4', 'admin', '2017-05-15 22:44:53');
INSERT INTO `rolecategoryauthority_copy` VALUES ('17', '3', '5', 'admin', '2017-05-15 22:44:53');

-- ----------------------------
-- Table structure for rolelinkauthority
-- ----------------------------
DROP TABLE IF EXISTS `rolelinkauthority`;
CREATE TABLE `rolelinkauthority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `authorityId` int(11) NOT NULL COMMENT '链接权限ID',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '最后操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='角色链接权限表';

-- ----------------------------
-- Records of rolelinkauthority
-- ----------------------------
INSERT INTO `rolelinkauthority` VALUES ('1', '1', '737364230', 'admin', '2017-05-14 02:18:17');
INSERT INTO `rolelinkauthority` VALUES ('2', '1', '1185770255', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('3', '1', '1159544726', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('4', '1', '594354886', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('5', '1', '1908265921', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('6', '1', '1774437206', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('7', '1', '23456991', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('8', '1', '354450803', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('9', '1', '1797044113', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('10', '1', '632158582', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('11', '1', '83574335', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('12', '1', '1335180289', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('13', '1', '907507678', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('14', '1', '484206187', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('15', '1', '1801666536', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('16', '1', '1839758972', 'admin', '2017-05-14 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('17', '1', '237193705', 'admin', '2017-05-15 00:00:00');
INSERT INTO `rolelinkauthority` VALUES ('18', '1', '1888802574', null, null);
INSERT INTO `rolelinkauthority` VALUES ('19', '1', '271569888', null, null);
INSERT INTO `rolelinkauthority` VALUES ('20', '1', '662487556', null, null);
INSERT INTO `rolelinkauthority` VALUES ('21', '1', '185149435', null, null);
INSERT INTO `rolelinkauthority` VALUES ('22', '1', '905764150', null, null);
INSERT INTO `rolelinkauthority` VALUES ('23', '1', '88688268', 'admin', '2017-05-15 19:08:39');
INSERT INTO `rolelinkauthority` VALUES ('24', '1', '800993014', 'admin', '2017-05-15 22:36:34');

-- ----------------------------
-- Table structure for rolelinkauthority_copy
-- ----------------------------
DROP TABLE IF EXISTS `rolelinkauthority_copy`;
CREATE TABLE `rolelinkauthority_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `authorityId` int(11) NOT NULL COMMENT '链接权限ID',
  `lastUpdater` varchar(255) DEFAULT NULL COMMENT '最后操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2526 DEFAULT CHARSET=utf8 COMMENT='角色链接权限表';

-- ----------------------------
-- Records of rolelinkauthority_copy
-- ----------------------------
INSERT INTO `rolelinkauthority_copy` VALUES ('24', '2', '1774437206', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('25', '2', '23456991', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('26', '2', '1185770255', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('27', '2', '737364230', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('28', '2', '632158582', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('29', '2', '1908265921', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('30', '2', '1797044113', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('31', '2', '83574335', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('32', '2', '1335180289', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('33', '2', '1159544726', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('34', '2', '594354886', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('35', '2', '354450803', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('36', '2', '1888802574', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('37', '2', '237193705', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('38', '2', '1801666536', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('39', '2', '88688268', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('40', '2', '907507678', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('41', '2', '484206187', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('42', '2', '271569888', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('43', '2', '1839758972', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('44', '2', '185149435', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('45', '2', '905764150', 'admin', '2017-05-15 22:39:28');
INSERT INTO `rolelinkauthority_copy` VALUES ('46', '2', '662487556', 'admin', '2017-05-15 22:39:28');

-- ----------------------------
-- Table structure for role_copy
-- ----------------------------
DROP TABLE IF EXISTS `role_copy`;
CREATE TABLE `role_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dataSql` varchar(300) DEFAULT NULL COMMENT '数据的SQL语句或者条件',
  `lastUpdater` varchar(40) DEFAULT NULL COMMENT '最后操作者',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role_copy
-- ----------------------------
INSERT INTO `role_copy` VALUES ('2', '老师', '老师', null, 'admin', '2017-05-15 22:39:26');
INSERT INTO `role_copy` VALUES ('3', '老师', '老师', null, 'admin', '2017-05-15 22:44:53');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(40) NOT NULL COMMENT '账号',
  `pwd` varchar(120) NOT NULL COMMENT '密码',
  `nickName` varchar(20) NOT NULL COMMENT '昵称',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '状态（-1：禁用，1：启用）',
  `head` varchar(200) NOT NULL DEFAULT 'http://acadamic-resource.oss-cn-qingdao.aliyuncs.com/resources/b_120.png' COMMENT '头像链接',
  `registeTime` datetime NOT NULL COMMENT '注册时间',
  `name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户账号库';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '超级管理员', '1', 'http://acadamic-resource.oss-cn-qingdao.aliyuncs.com/resources/b_120.png', '2017-05-10 17:47:01', null, null, '1');

-- ----------------------------
-- Table structure for userloginrecord
-- ----------------------------
DROP TABLE IF EXISTS `userloginrecord`;
CREATE TABLE `userloginrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `ip` varchar(20) NOT NULL COMMENT 'IP地址',
  `loginTime` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='用户登录记录';

-- ----------------------------
-- Records of userloginrecord
-- ----------------------------
INSERT INTO `userloginrecord` VALUES ('1', '1', '127.0.0.1', '2017-05-10 17:47:46');
INSERT INTO `userloginrecord` VALUES ('2', '1', '127.0.0.1', '2017-05-10 17:48:16');
INSERT INTO `userloginrecord` VALUES ('3', '1', '127.0.0.1', '2017-05-10 23:37:53');
INSERT INTO `userloginrecord` VALUES ('4', '1', '127.0.0.1', '2017-05-10 23:38:17');
INSERT INTO `userloginrecord` VALUES ('5', '1', '127.0.0.1', '2017-05-11 00:19:16');
INSERT INTO `userloginrecord` VALUES ('6', '1', '127.0.0.1', '2017-05-11 11:13:26');
INSERT INTO `userloginrecord` VALUES ('7', '1', '127.0.0.1', '2017-05-12 11:53:52');
INSERT INTO `userloginrecord` VALUES ('8', '1', '127.0.0.1', '2017-05-12 11:54:00');
INSERT INTO `userloginrecord` VALUES ('9', '1', '127.0.0.1', '2017-05-12 11:55:40');
INSERT INTO `userloginrecord` VALUES ('10', '1', '127.0.0.1', '2017-05-12 11:56:29');
INSERT INTO `userloginrecord` VALUES ('11', '1', '127.0.0.1', '2017-05-12 12:09:15');
INSERT INTO `userloginrecord` VALUES ('12', '1', '127.0.0.1', '2017-05-12 12:32:18');
INSERT INTO `userloginrecord` VALUES ('13', '1', '127.0.0.1', '2017-05-12 13:10:00');
INSERT INTO `userloginrecord` VALUES ('14', '1', '127.0.0.1', '2017-05-12 13:10:17');
INSERT INTO `userloginrecord` VALUES ('15', '1', '127.0.0.1', '2017-05-12 13:11:58');
INSERT INTO `userloginrecord` VALUES ('16', '1', '127.0.0.1', '2017-05-12 13:12:50');
INSERT INTO `userloginrecord` VALUES ('17', '1', '127.0.0.1', '2017-05-12 13:17:44');
INSERT INTO `userloginrecord` VALUES ('18', '1', '127.0.0.1', '2017-05-12 13:19:08');
INSERT INTO `userloginrecord` VALUES ('19', '1', '127.0.0.1', '2017-05-12 13:42:04');
INSERT INTO `userloginrecord` VALUES ('20', '1', '127.0.0.1', '2017-05-12 14:25:30');
INSERT INTO `userloginrecord` VALUES ('21', '1', '127.0.0.1', '2017-05-12 14:25:50');
INSERT INTO `userloginrecord` VALUES ('22', '1', '127.0.0.1', '2017-05-12 14:26:39');
INSERT INTO `userloginrecord` VALUES ('23', '1', '127.0.0.1', '2017-05-12 14:27:09');
INSERT INTO `userloginrecord` VALUES ('24', '1', '127.0.0.1', '2017-05-12 14:30:42');
INSERT INTO `userloginrecord` VALUES ('25', '1', '127.0.0.1', '2017-05-12 14:32:28');
INSERT INTO `userloginrecord` VALUES ('26', '1', '127.0.0.1', '2017-05-12 14:33:15');
INSERT INTO `userloginrecord` VALUES ('27', '1', '127.0.0.1', '2017-05-12 14:54:08');
INSERT INTO `userloginrecord` VALUES ('28', '1', '127.0.0.1', '2017-05-12 16:49:12');
INSERT INTO `userloginrecord` VALUES ('29', '1', '127.0.0.1', '2017-05-14 01:11:06');
INSERT INTO `userloginrecord` VALUES ('30', '1', '127.0.0.1', '2017-05-14 02:49:51');
INSERT INTO `userloginrecord` VALUES ('31', '1', '127.0.0.1', '2017-05-14 03:08:06');
INSERT INTO `userloginrecord` VALUES ('32', '1', '127.0.0.1', '2017-05-14 10:42:45');
INSERT INTO `userloginrecord` VALUES ('33', '1', '127.0.0.1', '2017-05-14 15:49:55');
INSERT INTO `userloginrecord` VALUES ('34', '1', '127.0.0.1', '2017-05-14 16:03:18');
INSERT INTO `userloginrecord` VALUES ('35', '1', '127.0.0.1', '2017-05-14 17:20:58');
INSERT INTO `userloginrecord` VALUES ('36', '1', '127.0.0.1', '2017-05-14 17:40:59');
INSERT INTO `userloginrecord` VALUES ('37', '1', '127.0.0.1', '2017-05-14 18:22:01');
INSERT INTO `userloginrecord` VALUES ('38', '1', '127.0.0.1', '2017-05-14 19:05:35');
INSERT INTO `userloginrecord` VALUES ('39', '1', '127.0.0.1', '2017-05-14 19:25:19');
INSERT INTO `userloginrecord` VALUES ('40', '1', '127.0.0.1', '2017-05-14 19:30:49');
INSERT INTO `userloginrecord` VALUES ('41', '1', '127.0.0.1', '2017-05-14 19:51:34');
INSERT INTO `userloginrecord` VALUES ('42', '1', '127.0.0.1', '2017-05-14 20:40:49');
INSERT INTO `userloginrecord` VALUES ('43', '1', '127.0.0.1', '2017-05-14 20:59:06');
INSERT INTO `userloginrecord` VALUES ('44', '1', '127.0.0.1', '2017-05-14 21:07:48');
INSERT INTO `userloginrecord` VALUES ('45', '1', '127.0.0.1', '2017-05-14 21:13:11');
INSERT INTO `userloginrecord` VALUES ('46', '1', '127.0.0.1', '2017-05-14 21:58:30');
INSERT INTO `userloginrecord` VALUES ('47', '1', '127.0.0.1', '2017-05-14 22:09:54');
INSERT INTO `userloginrecord` VALUES ('48', '1', '127.0.0.1', '2017-05-14 22:19:35');
INSERT INTO `userloginrecord` VALUES ('49', '1', '127.0.0.1', '2017-05-14 23:05:44');
INSERT INTO `userloginrecord` VALUES ('50', '1', '127.0.0.1', '2017-05-15 00:59:15');
INSERT INTO `userloginrecord` VALUES ('51', '1', '127.0.0.1', '2017-05-15 10:02:56');
INSERT INTO `userloginrecord` VALUES ('52', '1', '127.0.0.1', '2017-05-15 17:51:37');
INSERT INTO `userloginrecord` VALUES ('53', '1', '127.0.0.1', '2017-05-15 22:35:52');
