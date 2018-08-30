$(document).ready(function() {
	// 获取图合集类型
	var param = {
		funNo: "20003"
	};
	Qajax(param, function(data){
		if (data.error_no === "0"){
			var result = data.results,
				str = '<option selected disabled>请选择图合集类型</option>';
			result.map(function(item) {
				str += '<option value=' + item.id + '>' + item.typename + '</option>';
			});
			$('#select-imgtype').html(str);
		} else {
			alert(data.error_info);
		}
	});

	$("#fileuploader").uploadFile({
		// 上线修改
		url: "/20001",
		fileName: "myfile",
		uploadStr: "点击上传文件",
		abortStr: "取消",
		deleteStr: "删除",
		onSuccess: function(files, response, xhr, pd) {
			var img = response.results[0],
				image_width = Number(img.image_width),
				image_height = Number(img.image_height);
			var str = '';
			console.log(img);
			if(image_width > image_height) {
				var top = (200 - 200 / image_width * image_height) * .5
				str = 'width: 100%;margin-top:' + top + 'px';
			} else {
				str = 'height: 100%';
			}
			var _uri = decodeURIComponent(img.uri);
			// 上线修改
			pd.progressbar.html('<img style="'+str+'" src=' + _uri + '>');
		},
		onError: function (files, status, message, pd) {
			pd.imginfo.remove();
		}
	});

	//提交
	$('.img-arr-info span').on('click', function(e) {
		var $box = $('.ajax-file-upload-statusbar'),
			boxLength = $box.length;
		if (boxLength === 0) {
			return false;
		}
		var errorLength = $box.find('.ajax-file-upload-error').length,
			$img = $box.find('.ajax-file-upload-bar img'),
			imgLength = $img.length;
		var uploadSize = errorLength + imgLength;
		if (boxLength !== uploadSize) {
			alert('照片正在上传，请稍等');
			return false;
		}
		if (imgLength === 0) {
			return false;
		}
		// 图集标题
		var arrTitle = $('.img-arr-info input').val(),
			// 图合集类型
			imgType = $('.img-arr-info #select-imgtype').val();
		if (!imgType) {
			alert('请选择图合集类型');
			return false;
		}
		if (boxLength > imgLength) {
			if (confirm('有 ' + imgLength + ' 张照片上传成功，有 ' + errorLength + ' 张照片上传失败，是否继续提交？')) {
				// 上传
				submitImgArr($box, arrTitle, imgType);
			}
		} else {
			// 上传
			submitImgArr($box, arrTitle, imgType);
		}
	});

	// 提交图片集合
	function submitImgArr($box, arrTitle, imgType) {
		var imgAllArr = [],
			imgNameArr = [];
		$box.each(function(index, item) {
			var $item = $(item),
				$img = $item.find('.ajax-file-upload-bar img');
			if ($img.length > 0) {
				var _arr = [],
					src = $img.attr('src');
				_arr.push(src);
				_arr.push($item.find('.ajax-file-upload-tip input.input-img-title').val());
				_arr.push($item.find('.ajax-file-upload-tip textarea').val());
				if ($item.find('.ajax-file-upload-tip input[type=radio]').prop('checked')) {
					_arr.push('1');
				}
				// 保存图片信息
				imgAllArr.push(_arr.join('$@'));
				// 保存图片名称
				var srcArr = src.split('\\');
				imgNameArr.push(srcArr[srcArr.length - 1]);
			}
		});
		// 保存图片路径---防止重复提交图片
		sessionStorage.setItem('_hdd_submit_imgnamearr', imgNameArr);
		// 提交
		submitImgArrInfo(imgAllArr.join('||'), arrTitle, imgType);
	}


	// 提交图片集合信息
	function submitImgArrInfo(imgStr, arrTitle, imgType) {
		$('.cover').show();
		var param = {
			funNo: "20002",
			imgstr: encodeURIComponent(imgStr),
			arrtitle: encodeURIComponent(arrTitle),
			imgtype: imgType,
			updatemark: new Date().getTime()
		};
		Qajax(param, function(data){
			$('.cover').hide();
			if (data.error_no === "0"){
				alert(data.error_info);
				window.location.reload();
			} else {
				alert(data.error_info);
			}
		});
	}
});
