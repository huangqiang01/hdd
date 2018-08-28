
// ’’∆¨∑÷¿‡
create table phototype(
	id int(4) primary key auto_increment,
	typename varchar(40),
	introduce text,
	isShow varchar(4),
	reserve varchar(80)
);

//  °¬‘Õº
create table minphoto(
	id int(4) primary key auto_increment,
	arrayname varchar(40),
	arraynum varchar(6),
	isShow varchar(4),
	reserve varchar(80)
);

create table myphoto(
	id int(4) primary key auto_increment,
	classId int,
	imgName varchar(40),
	shotTypes varchar(40),
	imgInfo varchar(40),
	imgUrl varchar(100),
	imgWidth varchar(10),
	imgHeight varchar(10),
	imgDate varchar(20),
	imgTime varchar(20),
	introduce text,
	isShow varchar(4),
	reserve varchar(80)
);