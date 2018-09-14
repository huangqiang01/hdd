CREATE TABLE `minphoto` (
  `id` int(4) NOT NULL auto_increment,
  `typeid` char(40) NOT NULL COMMENT '照片类别id',
  `albumid` varchar(4) default NULL COMMENT '相册id',
  `imguri` varchar(80) default NULL COMMENT '省略图路径',
  `imgwidth` varchar(8) default NULL COMMENT '省略图宽度',
  `imgheight` varchar(8) default NULL COMMENT '省略图高度',
  `arrayname` text COMMENT '集合名称(介绍)',
  `arraycontent` text COMMENT '图集说明',
  `arraynum` char(4) default NULL COMMENT '集合大小(照片数量)',
  `createtime` varchar(35) NOT NULL COMMENT '上传时间',
  `updatetime` varchar(35) NOT NULL COMMENT '更新时间',
  `checknum` int(6) default '0' COMMENT '查看人数',
  `likenum` int(6) default '0' COMMENT '点赞人数',
  `shareurl` varchar(80) default NULL COMMENT '分享图标路径',
  `isshow` char(2) default '1' COMMENT '是否显示',
  `reserve` varchar(80) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='省略图--图片集合';

CREATE TABLE `myphoto` (
  `id` int(4) NOT NULL auto_increment,
  `typeid` varchar(40) NOT NULL COMMENT '类别id',
  `minid` char(4) NOT NULL COMMENT '省略图id',
  `albumid` char(4) default NULL COMMENT '相册id',
  `uri` varchar(80) default NULL COMMENT '照片路径',
  `imgwidth` char(6) default NULL COMMENT '图片宽度',
  `imgheight` char(6) default NULL COMMENT '图片高度',
  `imgtitle` varchar(200) default NULL COMMENT '图片标题',
  `imgcontent` text COMMENT '图片介绍',
  `numno` char(3) default NULL COMMENT '图片集合里的排序',
  `isshow` char(2) default NULL COMMENT '是否显示',
  `reserve` varchar(80) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8 COMMENT='照片集合明细表';

CREATE TABLE `photoalbum` (
  `id` int(11) NOT NULL auto_increment,
  `albumname` varchar(40) default NULL COMMENT '相册名称',
  `introduce` varchar(200) default NULL COMMENT '相册介绍',
  `isshow` varchar(4) default '1',
  `reserve` varchar(80) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

insert into photoalbum values(0,'天安门','','1','');
insert into photoalbum values(0,'北京','','1','');

CREATE TABLE `phototype` (
  `id` int(2) NOT NULL auto_increment,
  `typename` varchar(40) default NULL COMMENT '分类名称',
  `introduce` text COMMENT '说明',
  `isshow` char(2) default '1' COMMENT '是否显示',
  `reserve` varchar(80) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='照片分类';

insert into phototype values(0,'人像','','1','');
insert into phototype values(0,'旅游','','1','');

CREATE TABLE `web_item` (
  `id` int(4) NOT NULL auto_increment,
  `itemname` varchar(40) default NULL COMMENT '类型名称',
  `introduce` text COMMENT '简介',
  `isshow` char(2) default NULL,
  `reserve` varchar(80) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into web_item values(0,'HTML','','1','');
insert into web_item values(0,'JS','','1','');
insert into web_item values(0,'CSS','','1','');

CREATE TABLE `web_list` (
  `id` int(4) NOT NULL auto_increment,
  `itemid` char(4) default NULL COMMENT '类型id',
  `title` varchar(300) default NULL COMMENT 'title',
  `articledesc` text,
  `createtime` varchar(35) default NULL,
  `writer` varchar(60) default NULL,
  `isshow` char(2) default NULL,
  `reserve` varchar(80) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `web_content` (
  `id` int(4) NOT NULL auto_increment,
  `itemid` char(4) default NULL,
  `listid` char(4) default NULL,
  `content` text,
  `isshow` char(2) default NULL,
  `reserve` varchar(80) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;







