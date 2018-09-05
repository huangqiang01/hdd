
var imgArr = [],
	// 上传图片的长度
	uploadLength = 0,
	// 处理成功的图片
	doimgLength = 0,
	// 已上传的图片
	uploadend = 0,
	// 选取的图集标签
	selectSign = [],
	// 选取的图集相册
	selectAlbum = '',
	// 是否新建相册
	isCreatAlbum = false,
	// 新建相册定时器
	setCreat = null,
	// 缩略图信息
	firstminimg = {},
	// 发送请求
	myRequest = null,
	// 分享图标信息
	shareObj = {};

$(document).ready(function() {
	// 获取图合集类型
	queryPhototype();
	// 获取相册
	queryAlbum();
	
	// 选择分享头像
	selectShareImg();
	// 选择图片
	$('.file-input').on('change', function(e) {
		e.stopPropagation();
		var files = e.target.files,
			filesLength = files.length,
			isNotImg = 0,
			imgIndex = 0;
		if (filesLength > 100) {
			alert('上传图片数量过大');
			return false;
		}
		// 上传长度（全局）
		uploadLength = filesLength;
		for (var i = 0; i < filesLength; i++) {
			var item = files[i];
			if (item.type.split('/')[0] !== 'image') {
				alert('请上传正确的图片文件');
				continue;
			}
			var reader = new FileReader();
	      	reader.readAsDataURL(item);
	      	reader.onloadstart = function () {
	      		//用以在上传前加入一些事件或效果，如载入中...的动画效果
	      		this.index = imgIndex;
	      		this.id = getFileid();
	      		$('.img-list-box').append(createHtml(this.id, imgIndex));
	      		imgIndex++;
	      		if (imgIndex === filesLength) {
	      			// 绑定图片操作事件
	      			bindImgClick();
	      		}
	      	};
	      	reader.onloadend = function (e) {
		        var imaged = new Image(),
		        	imgObj = {},
		        	rethis = this;
		        imaged.src = rethis.result;
		        imaged.id = rethis.id;
		        // 利用canvas对图片进行压缩
		        imaged.onload = function () {
		        	var _this = this;
		          	// 设置图片高度
					var arr = setImgWH(_this.width, _this.height);
					if (!arr) {
						showImgInfo(null, _this.id);
						return false;
					}
					var w = arr[0];
		          	var h = arr[1];
					var canvas = document.createElement('canvas');
		          	var ctx = canvas.getContext('2d');
			        canvas.width = w;
			        canvas.height = h;
			        ctx.drawImage(_this, 0, 0, w, h);
			        var _src = canvas.toDataURL('image/jpeg');
			        // 显示页面参数
			        showImgInfo(_src, _this.id, w, h);
			        // 
			        imgObj = {
			        	// 
			        	img: _src,
			        	imgid: _this.id,
			        	imgw_h: w + '_' + h,
			        	title: '',
			        	content: '',
			        	numno: ''
			        };
			        if (rethis.index === 0) {
			        	firstminimg['minname'] = _this.id;
			        	firstminimg['minwidth'] = w;
			        	firstminimg['minheight'] = h;
			        }
			        // 存入处理完的集合（全局）
			        imgArr.push(imgObj);
		        };
	      	};
		}
	});
	
	// 提交
	$('.subimt-img').on('click', function(e) {
		// 初始化上传长度（全局）
		uploadend = 0;
		// 处理好后的集合长度（全局）
		doimgLength = imgArr.length;
		if (uploadLength === 0) {
			return false;
		}
		// 检测集合信息
		if (!checkArrInfo()) {
			return false;
		}
		if (uploadLength > doimgLength) {
			if (!confirm('上传照片为' + uploadLength + '张，处理成功' + doimgLength + '张，是否继续上传？')) {
				console.log('取消');
			}
		}
		$('.cover-box').show();
		// 上传
		uploadFiles(imgArr[uploadend]);
	});
	
	// 选取图集相册---点击相册
	$('.select-album').on('click', function(e) {
		e.stopPropagation();
		if (isCreatAlbum) {
			return false;
		}
		var $this = $(this);
		if ($this.hasClass('active')) {
			$this.removeClass('active');
		} else {
			$this.addClass('active');
		}
	});
	// 选取图集相册---隐藏相册
	$('body').on('click', function(e) {
		e.stopPropagation();
		$('.select-album').removeClass('active');
	});
	
	// 新建图集相册
	$('.img-album span').on('click', function(e) {
		isCreatAlbum = true;
		alert('暂时不支持此功能');
	//	$('.select-album input').val('').addClass('active').prop('readonly', false).focus();
	});
	
	// 取消请求
	$('.cancel-btn span').on('click', function(e) {
		if (myRequest == null) {
			return false;
		}
		myRequest.abort();
	});
});

// 获取标签
function queryPhototype() {
	var param = {
		funNo: "20003"
	};
	Qajax(param, function(data){
		if (data.error_no === "0"){
			var result = data.results,
				str = '';
			result.map(function(item) {
				str += '<em data-val=' + item.id + '>' + item.typename + '</em>';
			});
			$('.img-sign').html(str);
			// 选取图集标签
			$('.img-sign>em').on('click', function(e) {
				var $this = $(this),
					val = $this.attr('data-val');
				if ($this.hasClass('active')) {
					$this.removeClass('active');
					// 移除指定值
					removeArrVal(selectSign, val);
				} else {
					$this.addClass('active');
					// 添加值
					selectSign.push(val);
				}
			});
		} else {
			alert(data.error_info);
		}
	});
}

// 获取相册
function queryAlbum() {
	var param = {
		funNo: "20007"
	};
	Qajax(param, function(data){
		if (data.error_no === "0"){
			var result = data.results,
				str = '<li data-val="">未分类相册</li>';
			result.map(function(item) {
				str += '<li data-val=' + item.id + '>' + item.albumname + '</li>';
			});
			$('.select-album>ul').html(str);
			// 选取图集相册---选择相册
			$('.select-album li').on('click', function(e) {
				var $this = $(this);
				$('.select-album input').val($this.text());
				// 相册id（全局）
				selectAlbum = $this.attr('data-val');
			});
		} else {
			alert(data.error_info);
		}
	});
}

/**
 * 设置图片大小
 */
function setImgWH(imgwidth, imgheight) {
	var imgMod = 1200;
	if (imgwidth >= imgheight) {
		if (imgwidth < 600 || imgheight < 400) {
			return null;
		}
		if (imgwidth > imgMod) {
			return [imgMod, Number((imgMod * imgheight / imgwidth).toFixed(0))];
		}
		return [imgwidth, imgheight];
	}
	if (imgheight < 600 || imgwidth < 400) {
		return null;
	}
	if (imgheight > imgMod) {
		return [Number((imgMod * imgwidth / imgheight).toFixed(0)), imgMod];
	}
	return [imgwidth, imgheight];
}

function createHtml(id, i) {
	var arr = [];
	arr.push('<div class="img-info" id=' + id + '>');
	arr.push('<div class="img">');
	arr.push('<span data-id=' + id +'></span>');
	arr.push('</div>');
	arr.push('<div class="img-describe">');
	arr.push('<label for="first-img-' + id + '">');
	arr.push('<span>是否设置为首图</span>');
	arr.push('<input type="radio" ' + (imgArr.length === 0 && i === 0 ? 'checked' : '') + ' name="selectFirst" id="first-img-' + id + '" />');
	arr.push('</label>');
	arr.push('<span class="img-title">图片标题</span>');
	arr.push('<input class="img-title-input" type="text" />');
	arr.push('<span class="img-title">图片描述</span>');
	arr.push('<textarea class="img-content-input"></textarea>');
	arr.push('</div>');
	arr.push('</div>');
	return arr.join('');
}

// 设置图片
function showImgInfo(img, id, width, height) {
	var $imgInfo = $('#' + id).find('.img');
	if (!img) {
		$imgInfo.append('<em>图片长边像素必须大于600，且短边像素必须大于400</em>');
		return false;
	}
	$imgInfo.find('span').attr('data-mark', 'isimg');
	var arr = [],
		_w = width >= height ? '260' : (width * 260 / height).toFixed(0),
		_h = width >= height ? (height * 260 / width).toFixed(0) : '260';
	$imgInfo.css('background', 'url(' + img + ') center center / ' + _w + 'px ' + _h + 'px no-repeat');
	$('#first-img-' + id).attr('data-info', id + '_' + width + '_' + height);
}

// 生成图片名称
function getFileid() {
	var id = new Date().getTime();
	return id + '_' + (Math.random() * 1000).toFixed(0);
}

// 绑定点击事件
function bindImgClick() {
	// 选择首个缩略图
	$('.img-describe input[type=radio]').off('click').on('click', function() {
		var info = $(this).attr('data-info');
		if (!info) {
			return false;
		}
		var infoArr = info.split('_');
		firstminimg['minname'] = infoArr[0];
    	firstminimg['minwidth'] = infoArr[1];
    	firstminimg['minheight'] = infoArr[2];
	});
	
	// 删除图片
	$('.img>span').off('click').on('click', function() {
		var $this = $(this),
			id = $this.attr('data-id');
		if (!id) {
			return false;
		}
		// 处理相关数据
		doDelData(id, $this);
	});
	
}

// 处理删除图片后的数据，
function doDelData(id, $this) {
	if (firstminimg['minname'] === id) {
		alert('次图片被选中，不能删除');
		return false;
	}
	if ($this.attr('data-mark')) {
		// 剩余图片集合
		imgArr = removeArrVal(imgArr, id, 'imgid');
		// 处理成功的图片
		doimgLength--;
	}
	// 移除图片
	$('#' + id).remove();
	// 上传图片的长度
	uploadLength--;
}

function checkArrInfo() {
	// 图集标题
	var arrTitle = $('.arr-title').val();
	if (!arrTitle.trim()) {
		alert('图集标题不能为空');
		return false;
	}
	if (!firstminimg.minname) {
		alert('请选择图集缩略图');
		return false;
	}
	return true;
}

function uploadFiles(obj) {
	// 需要发送的数据
	var formData = new FormData();
	//使用ajax发送(全局)
	myRequest = new XMLHttpRequest();
	if (!obj) {
		formData.append('arrtitle', $('.arr-title').val());
		formData.append('arrcontent', $('.arr-describe').val().trim());
		formData.append('arrtips', selectSign.join('_'));
		formData.append('arralbum', selectAlbum);
		formData.append('firsturl', firstminimg.minname + '.jpg');
		formData.append('firstwidth', firstminimg.minwidth);
		formData.append('firstheight', firstminimg.minheight);
		
		var blob = dataURLtoBlob(getCutInfo()),
			id = new Date().getTime();
		formData.append('image', blob, 'share_' + id + '.jpg');
	} else {
		var blob = dataURLtoBlob(obj.img),
			id = obj.imgid;
		formData.append('image', blob, id + '.jpg');
		formData.append('imgw_h', obj.imgw_h);
		formData.append('title', $('#' + id + ' .img-title-input').val());
		formData.append('content', $('#' + id + ' .img-content-input').val());
		formData.append('numno', obj.numno);
		if (uploadend === 0) {
			formData.append('firstupload', '1');
		}
		// 进度条
		myRequest.upload.onprogress = updateProgress;
		myRequest.upload.onload = transferComplete;
		myRequest.upload.onerror = transferFailed;
		myRequest.upload.onabort = transferCanceled;
		$('.upload-total').text((uploadend + 1) + '/' + doimgLength);
	}
	formData.enctype = 'multipart/form-data';
	// 生产修改
	myRequest.open('POST', '/20001');
	myRequest.onreadystatechange = function() {
    	if (myRequest.readyState == 4 && myRequest.status == 200){
            var b = JSON.parse(myRequest.responseText);
            if (b.error_no === "0"){
            	if (!obj) {
//            		alert('完成');
            		window.location.reload();
            	} else {
            		uploadend++;
            		uploadFiles(imgArr[uploadend]);
            	}
            } else {
                alert(b.error_info);
                $('.cover-box').hide();
            }
        }
    };
    myRequest.send(formData);
}

// 将图片转成二进制
function dataURLtoBlob(dataurl) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type:mime});
}

//上传进度实现方法，上传过程中会频繁调用该方法
function updateProgress(evt) {
     // event.total是需要传输的总字节，event.loaded是已经传输的字节。如果event.lengthComputable不为真，则event.total等于0
     if (evt.lengthComputable) {
    	 var rate = Math.round(evt.loaded / evt.total * 100);
    	 $('.runner').css('width', (100 - rate) + '%');
    	 $('.upload-num-img').text(rate + '%');
     }
}

//上传成功响应
function transferComplete(evt) {
	
}
//上传失败
function transferFailed(evt) {
	alert('网络异常');
	$('.cover-box').hide();
}
  //取消上传
function transferCanceled(){
    console.log('请求已取消');
    $('.cover-box').hide();
}


// 移除指定的值
function removeArrVal(arr, val, key) {
	if (key) {
		var _arr = [];
		arr.map(function(item) {
			if (item[key] !== val) {
				_arr.push(item);
			}
		});
		return _arr;
	} else {
		var index = arr.indexOf(val);
		if (index > -1) {
			arr.splice(index, 1);
		}
		return arr;
	}
}


function selectShareImg() {
	$('.file-input-share').on('change', function(e) {
		e.stopPropagation();
		var files = e.target.files[0];
		var reader = new FileReader();
      	reader.readAsDataURL(files);
      	reader.onloadend = function (e) {
	        var imaged = new Image();
	        imaged.src = this.result;
	        console.log(this);
	        // 利用canvas对图片进行压缩
	        imaged.onload = function () {
	        	$('.img-container').html(this);
	        	shareObj.img = this;
	        	// cut-img
	        	cutImg();
	        };
      	};
	});
}

function cutImg() {
	$('.img-container > img').cropper({
		aspectRatio: 10 / 10,
		preview: '.img-preview',
		crop: function(data) {
			shareObj.x = data.x;
			shareObj.y = data.y;
			shareObj.w = data.width;
			shareObj.h = data.height;
			console.log(shareObj);
		}
	});
}

function getCutInfo() {
	var canvas = document.createElement('canvas');
  	var ctx = canvas.getContext('2d');
    canvas.width = shareObj.w;
    canvas.height = shareObj.h;
    ctx.drawImage(shareObj.img, 0, 0, shareObj.w, shareObj.h, shareObj.x, shareObj.y, 500, 500);
    return canvas.toDataURL('image/jpeg');
}




















