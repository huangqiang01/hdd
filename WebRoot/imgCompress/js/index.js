
// 选择图片
$('.file-input').on('change', function(e) {
	e.stopPropagation();
	
	
	
	var files = e.target.files,
		filesLength = files.length,
		isNotImg = 0;
		console.log(files);
	for (var i = 0; i < filesLength; i++) {
		var item = files[i];
		
		console.log(item.lastModifiedDate);
		if (item.type.split('/')[0] !== 'image') {
			alert('请上传正确的图片文件');
			continue;
		}
		var reader = new FileReader();
      	reader.readAsDataURL(item);
      	reader.onloadstart = function () {
      		//用以在上传前加入一些事件或效果，如载入中...的动画效果
      		
      	};
      	reader.onloadend = function (e) {
	        var imaged = new Image();
	        imaged.src = this.result;
	        // 利用canvas对图片进行压缩
	        imaged.onload = function () {
	        	var _this = this;
	          	// 设置图片高度
				var arr = setImgWH(_this.width, _this.height);
				if (!arr) {
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
		        $('.img-list-box').append(addImgStr(_src, i, w, h));
		        // 上传
				uploadFiles(_src);
	        };
      	};
	}
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

// 设置图片
function addImgStr(img, id, width, height) {
	var arr = [],
		_w = width >= height ? '260' : (width * 260 / height).toFixed(0),
		_h = width >= height ? (height * 260 / width).toFixed(0) : '260',
		bg = 'background: url(' + img + ') center center / ' + _w + 'px ' + _h + 'px no-repeat;';
	arr.push('<div class="img-info">');
	arr.push('<div class="img" style="' + bg + '">');
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

function uploadFiles(imageBase64) {
	var blob = dataURLtoBlob(imageBase64);
	//使用ajax发送
	var formData = new FormData();
	formData.append('image', blob, 'image.jpg');
	formData.append('name', '1111111');
	formData.enctype = 'multipart/form-data';
	//使用ajax发送
	var myRequest = new XMLHttpRequest();
	myRequest.open('POST', 'http://192.168.9.24:8081/hdd/20001');
	// 进度条
	myRequest.upload.onprogress = updateProgress;
	myRequest.upload.onload = transferComplete;
	myRequest.upload.onerror = transferFailed;
	myRequest.upload.onabort = transferCanceled;
	myRequest.upload.onloadstart = function(){//上传开始执行方法
        console.log('---');
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
     alert("上传成功！");
}
//上传失败
function transferFailed(evt) {
    alert("上传失败！");
}
  //取消上传
function transferCanceled(){
    xhr.abort();
}




