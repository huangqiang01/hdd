$(document).ready(function() {
	var minid = getParameter('minid');
	// 查询集合内容
	// 获取图合集类型
	var param = {
		funNo: "20004",
		minid: minid
	};
	Qajax(param, function(data){
		if (data.error_no === "0"){
			var result = data.results[0];
			// 描述
			$('.miaoshu').html('<i></i>' + result.arrayname);
			// 查看人数
			$('.guankan').html(result.checknum);
			// 点赞人数
			$('.dianzan').html(result.likenum);
			// 获取图片
			getImg(minid);
			if (isCheck(minid)) {
				// 查看人数
				addZanOrCheck('1', '1', minid);
			}
			// 绑定点击事件
			bindClick(minid);
		} else {
			alert(data.error_info);
		}
	});
});

// 获取图片详情
function getImg(minid) {
	// 获取图
	var param = {
		funNo: "20005",
		minid: minid
	};
	Qajax(param, function(data){
		if (data.error_no === "0"){
			var result = data.results;
			// 显示数据
			var str = getDataStr(result);
			$('.show-img').html(str);
		} else {
			alert(data.error_info);
		}
	});
}

function getDataStr(result) {
	var str = '';
	result.map(function(item, index) {
		var uri = item.uri.replace('/', '\/');
		var title = item.imgtitle;
		var content = item.imgcontent;
		var paddingBottom = Number(item.imgheight)/Number(item.imgwidth) * 100;
		var style = 'padding-bottom: ' + paddingBottom + '%; background: url(' + uri + ') center center / 100% 100% no-repeat';
		str += '<li><div style="' + style + '"></div>';
		if (title) {
			str += '<span>' + title + '</span>';
		}
		if (content) {
			str += '<p>' + content + '</p>';
		}
		str += '</li>';
	});
	return str;
}

function bindClick(minid) {
	// 点赞人数
	$('.dianzan').on('click', function() {
		if (isClickLike(minid)) {
			addZanOrCheck('1', '2', minid);
		}
	});
}

function addZanOrCheck(num, mark, id) {
	var param = {
			funNo: '20006',
			minid: id
	};
	if (mark === '1') {
		param['checknum'] = num;
		param['likenum'] = '';
	} else {
		param['checknum'] = '';
		param['likenum'] = num;
	}
	Qajax(param, function(data){
		if (data.error_no === "0"){
			if (mark === '1') {
				$('.guankan').html(Number($('.guankan').html()) + 1);
			} else {
				$('.dianzan').html(Number($('.dianzan').html()) + 1);
			}
		}
	});
}

// 判断是否查看过
function isCheck(id) {
	var _checkimgdetails_ = window.localStorage.getItem('_checkimgdetails_');
	if (_checkimgdetails_) {
		var arr = _checkimgdetails_.split('|');
		if (arr.indexOf(id) > -1) {
			return false;
		}
		_checkimgdetails_ += '|' + id;
	} else {
		_checkimgdetails_ = id;
	}
	window.localStorage.setItem('_checkimgdetails_', _checkimgdetails_);
	return true;
}

//判断是否点赞
function isClickLike(id) {
	var _likeimgdetails_ = window.localStorage.getItem('_likeimgdetails_');
	if (_likeimgdetails_) {
		var arr = _likeimgdetails_.split('|');
		if (arr.indexOf(id) > -1) {
			return false;
		}
		_likeimgdetails_ += '|' + id;
	} else {
		_likeimgdetails_ = id;
	}
	window.localStorage.setItem('_likeimgdetails_', _likeimgdetails_);
	return true;
}



