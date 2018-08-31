CREATE TABLE `minphoto` (
  `id` int(4) NOT NULL auto_increment,
  `typeid` char(40) NOT NULL COMMENT '��Ƭ���id',
  `albumid` varchar(4) default NULL COMMENT '���id',
  `imguri` varchar(80) default NULL COMMENT 'ʡ��ͼ·��',
  `imgwidth` varchar(8) default NULL COMMENT 'ʡ��ͼ���',
  `imgheight` varchar(8) default NULL COMMENT 'ʡ��ͼ�߶�',
  `arrayname` text COMMENT '��������(����)',
  `arraynum` char(4) default NULL COMMENT '���ϴ�С(��Ƭ����)',
  `createtime` varchar(35) NOT NULL COMMENT '�ϴ�ʱ��',
  `updatetime` varchar(35) NOT NULL COMMENT '����ʱ��',
  `checknum` int(6) default '0' COMMENT '�鿴����',
  `likenum` int(6) default '0' COMMENT '��������',
  `isshow` char(2) default '1' COMMENT '�Ƿ���ʾ',
  `reserve` varchar(80) default NULL COMMENT '��ע',
  PRIMARY KEY  (`id`,`createtime`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='ʡ��ͼ--ͼƬ����';


CREATE TABLE `myphoto` (
  `id` int(4) NOT NULL auto_increment,
  `typeid` varchar(40) NOT NULL COMMENT '���id',
  `minid` char(4) NOT NULL COMMENT 'ʡ��ͼid',
  `albumid` char(4) default NULL COMMENT '���id',
  `uri` varchar(80) default NULL COMMENT '��Ƭ·��',
  `imgwidth` char(6) default NULL COMMENT 'ͼƬ���',
  `imgheight` char(6) default NULL COMMENT 'ͼƬ�߶�',
  `imgtitle` varchar(200) default NULL COMMENT 'ͼƬ����',
  `imgcontent` text COMMENT 'ͼƬ����',
  `isshow` char(2) default NULL COMMENT '�Ƿ���ʾ',
  `reserve` varchar(80) default NULL COMMENT '��ע',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='��Ƭ������ϸ��';

CREATE TABLE `photoalbum` (
  `id` int(11) NOT NULL auto_increment,
  `albumname` varchar(40) default NULL COMMENT '�������',
  `introduce` varchar(200) default NULL COMMENT '������',
  `isshow` varchar(4) default '1',
  `reserve` varchar(80) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

insert into photoalbum values(0,'�찲��','','1','');
insert into photoalbum values(0,'����','','1','');

CREATE TABLE `phototype` (
  `id` int(2) NOT NULL auto_increment,
  `typename` varchar(40) default NULL COMMENT '��������',
  `introduce` text COMMENT '˵��',
  `isshow` char(2) default '1' COMMENT '�Ƿ���ʾ',
  `reserve` varchar(80) default NULL COMMENT '��ע',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='��Ƭ����';

insert into phototype values(0,'����','','1','');
insert into phototype values(0,'����','','1','');





