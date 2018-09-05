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
			var arraycontent = result.arraycontent;
			if (arraycontent) {
				// 描述
				$('.miaoshu11').html(arraycontent);
			}
			// 分享图标地址
			var shareUrl = result.shareurl;
			if (shareUrl) {
				// 处理分享
				shareCon(result.arrayname, arraycontent, shareUrl);
			}
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
			
			$('.bottom').show();
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

// 分享内容
function shareCon(title, content, imgurl) {
	$.ajax({
		url: "/app/signatureServlet?param=signature",
		type: "post",
		dataType: "json",
		success: function(data){
			title = stringHtml(title);
			title = stringHtml(content);
			// 页面功能授权
			wxToFunction(data, title, content, imgurl);
		}
	});
}

//页面功能授权
function wxToFunction(data, title, content, imgurl){
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: data.appId, // 必填，公众号的唯一标识
	    timestamp: data.timestamp, // 必填，生成签名的时间戳
	    nonceStr: data.noncestr, // 必填，生成签名的随机串
	    signature: data.signature,// 必填，签名，见附录1
	    jsApiList: ["onMenuShareTimeline", "onMenuShareAppMessage", "onMenuShareQQ"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	
	var _uri = location.protocol + '//' + location.host;
	wx.ready(function(){
		wx.onMenuShareTimeline({
		    title: title, // 分享标题
		    link: window.location.href, // 分享链接
		    imgUrl: _uri + imgurl, // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
//		    	alert("成功");
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
//		    	alert("取消");
		    }
		});
		wx.onMenuShareAppMessage({
		    title: title, // 分享标题
		    desc: content, // 分享描述
		    link: window.location.href, // 分享链接
		    imgUrl: _uri + imgurl, // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
//		       alert("成功");
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
//		      alert("取消");
		    }
		});
		
		wx.onMenuShareQQ({
		    title: title, // 分享标题
		    desc: content, // 分享描述
		    link: window.location.href, // 分享链接
		    imgUrl: _uri + imgurl, // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		       // 用户取消分享后执行的回调函数
		    }
		});
	});
	
	wx.error(function(res){
		// alert(res);
	});
}

function stringHtml(str) {
	return str.replace(/&lt;/g, "<")
			.replace(/&gt;/g, ">")
			.replace(/&nbsp;/g, " ")
			.replace(/&quot;/g, "\"");
}



