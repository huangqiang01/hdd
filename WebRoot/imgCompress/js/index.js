
var imgArr = [],
	// 上传图片的长度
	uploadLength = 0,
	// 处理成功的图片
	doimgLength = 0,
	// 已上传的图片
	uploadend = 0;
// 选择图片
$('.file-input').on('change', function(e) {
	e.stopPropagation();
	var files = e.target.files,
		filesLength = files.length,
		isNotImg = 0,
		arrId = [];
		console.log(files);
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
      		var id = getFileid();
      		$('.img-list-box').append(createHtml(id));
      		arrId.push(id);
      	};
      	reader.onloadend = function (e) {
	        var imaged = new Image(),
	        	imgObj = {};
	        imaged.src = this.result;
	        imaged.id = arrId.shift();
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
		        	isFirst: '',
		        	title: '',
		        	content: '',
		        	numno: ''
		        }
		        imgArr.push(imgObj);
//		        $('.img-list-box').append(addImgStr(_src, i, w, h));
		        // 上传
//				uploadFiles(_src);
	        };
      	};
	}
});

// 提交
$('.subimt-img').on('click', function(e) {
	doimgLength = imgArr.length;
	if (uploadLength === 0) {
		return false;
	}
	if (uploadLength > doimgLength) {
		if (!confirm('上传照片为' + uploadLength + '张，处理成功' + doimgLength + '张，是否继续上传？')) {
			console.log('取消');
		}
	}
	// 上传
	uploadFiles(imgArr[uploadend]);
});

/**
 * 设置图片大小
 */
function setImgWH(imgwidth, imgheight) {
	var imgMod = 1600;
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

function createHtml(id) {
	var arr = [];
	arr.push('<div class="img-info" id=' + id + '>');
	arr.push('<div class="img">');
	arr.push('<span></span>');
	arr.push('</div>');
	arr.push('<div class="img-describe">');
	arr.push('<label for="first-img-' + id + '">');
	arr.push('<span>是否设置为首图</span>');
	arr.push('<input type="checkbox" id="first-img-' + id + '" />');
	arr.push('</label>');
	arr.push('<span class="img-title">图片标题</span>');
	arr.push('<input type="text" />');
	arr.push('<span class="img-title">图片描述</span>');
	arr.push('<textarea></textarea>');
	arr.push('</div>');
	arr.push('</div>');
	return arr.join('');
}

// 设置图片
function showImgInfo(img, id, width, height) {
	var $imgInfo = $('#' + id).find('.img');
	if (!img) {
		$imgInfo.text('图片长边像素必须大于600，且短边像素必须大于400');
		return false;
	}
	var arr = [],
		_w = width >= height ? '260' : (width * 260 / height).toFixed(0),
		_h = width >= height ? (height * 260 / width).toFixed(0) : '260';
	$imgInfo.css('background', 'url(' + img + ') center center / ' + _w + 'px ' + _h + 'px no-repeat');
}

function getFileid() {
	var id = new Date().getTime();
	return id + '_' + (Math.random() * 1000).toFixed(0);
}

function uploadFiles(obj) {
	if (obj == null) {
		return false;
	}
	var blob = dataURLtoBlob(obj.img);
	//使用ajax发送
	var formData = new FormData();
	formData.append('image', blob, obj.imgid + '.jpg');
	formData.append('imgw_h', obj.imgw_h);
	formData.append('isFirst', obj.isFirst);
	formData.append('title', obj.title);
	formData.append('content', obj.content);
	formData.append('numno', obj.numno);
	formData.enctype = 'multipart/form-data';
	//使用ajax发送
	var myRequest = new XMLHttpRequest();
	myRequest.open('POST', 'http://192.168.1.104:8080/20001');
	// 进度条
	myRequest.upload.onprogress = updateProgress;
	myRequest.upload.onload = transferComplete;
	myRequest.upload.onerror = transferFailed;
	myRequest.upload.onabort = transferCanceled;
	myRequest.upload.onloadstart = function(){//上传开始执行方法
        console.log('---');
    };
    
    myRequest.onreadystatechange = function() {

    	if(myRequest.readyState == 4 && myRequest.status == 200){    
            var b = JSON.parse(myRequest.responseText);    
            if(b.error_no === "0"){
            	uploadend++;
            	uploadFiles(imgArr[uploadend]);
            }else{    
                alert(b.error_info);    
            }           
        }
        
     // 上传
    };
    myRequest.send(formData);
}

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
     if (evt.lengthComputable) {//
//         progressBar.max = evt.total;
//         progressBar.value = evt.loaded;
         console.log(Math.round(evt.loaded / evt.total * 100) + "%");
     }
}

//上传成功响应
function transferComplete(evt) {
 //服务断接收完文件返回的结果
 //    alert(evt.target.responseText);
//     alert("上传成功！");
}
//上传失败
function transferFailed(evt) {
//    alert("上传失败！");
}
  //取消上传
function transferCanceled(){
    xhr.abort();
}








