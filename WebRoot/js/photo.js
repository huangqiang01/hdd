var winWidth = window.outerWidth;

var imgWidthArr = [[0, 1], [0.38, 0.62], [0.4, 0.6], [0.45, 0.55], [0.5, 0.5], [0.55, 0.45], [0.6, 0.4], [0.62, 0.38], [1, 0]];
var imgHeightArr = [240, 212, 194, 174, 140, 174, 180, 212, 200];

$(document).ready(function() {
	// 获取图合集类型
	var param = {
		funNo: "20004"
	};
	Qajax(param, function(data){
		if (data.error_no === "0"){
			var result = data.results;
			console.log(result);
			// 显示数据
			var str = getDataStr(result);
			$('ul').html(str);
			
			// 点击图片
			clickImg();
		} else {
			alert(data.error_info);
		}
	});
});

// 获取数据字符串
function getDataStr(photoArr) {
	var str = '',
		_width = 0,
		_widthHeight = null,
		arrLength = photoArr.length;
	for(var i = 0; i < arrLength; i++) {
		console.log(photoArr[i]);
		var item = photoArr[i],
			width = item.imgwidth,
			height = item.imgheight,
			size = +item.arraynum,
			uri = item.imguri,
			minid = item.id,
			spanStr = '';
		if (!isNaN(size) && size > 1) {
			spanStr = '<span>' + size + '</span>';
		}
		if (!_widthHeight) {
			_widthHeight = getWH();
			var imgRandom = _widthHeight[0];
			
			if (typeof imgRandom !== 'object') {
				str += '<li data-minid=' + minid + ' style="width: ' + imgRandom + 'px;height: ' + _widthHeight[1] + 'px; background: url(' + uri + ') center center / cover no-repeat;">' + spanStr + '</li>'
				_widthHeight = null;
			} else {
				str += '<li data-minid=' + minid + ' style="width: ' + imgRandom[0] + 'px;height: ' + _widthHeight[1] + 'px; background: url(' + uri + ') center center / cover no-repeat;">' + spanStr + '</li>'
			}
			continue;
		}
		if (_widthHeight) {
			str += '<li data-minid=' + minid + ' style="width: ' + (imgRandom[1] - 1) + 'px;height: ' + _widthHeight[1] + 'px;margin-left:1px; background: url(' + uri + ') center center / cover no-repeat;">' + spanStr + '</li>';
			_widthHeight = null;
		}
	}
	return str;
}

function getWH() {
	var rom = Math.floor(Math.random() * 7);
	var _height = imgHeightArr[rom];
	if (rom === 0 || rom === 6) {
		return [winWidth, _height];
	}
	var _ss = imgWidthArr[rom].map(function(item) {
		return item * winWidth;
	})
	return [_ss, _height]
	
}


function clickImg() {
	$('ul>li').on('click', function(e) {
		e.stopPropagation();
		window.location.href = './details.html?minid=' + $(this).attr('data-minid');
	});
}
