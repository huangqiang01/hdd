CREATE TABLE `minphoto` (
  `id` int(4) NOT NULL auto_increment,
  `typeid` char(40) NOT NULL COMMENT '照片类别id',
  `albumid` varchar(4) default NULL COMMENT '相册id',
  `imguri` varchar(80) default NULL COMMENT '省略图路径',
  `imgwidth` varchar(8) default NULL COMMENT '省略图宽度',
  `imgheight` varchar(8) default NULL COMMENT '省略图高度',
  `arrayname` text COMMENT '集合名称(介绍)',
  `arraynum` char(4) default NULL COMMENT '集合大小(照片数量)',
  `createtime` varchar(35) NOT NULL COMMENT '上传时间',
  `updatetime` varchar(35) NOT NULL COMMENT '更新时间',
  `checknum` int(6) default '0' COMMENT '查看人数',
  `likenum` int(6) default '0' COMMENT '点赞人数',
  `isshow` char(2) default '1' COMMENT '是否显示',
  `reserve` varchar(80) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`,`createtime`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='省略图--图片集合';


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
  `isshow` char(2) default NULL COMMENT '是否显示',
  `reserve` varchar(80) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='照片集合明细表';

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





