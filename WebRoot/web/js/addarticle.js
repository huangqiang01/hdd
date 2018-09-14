// 编辑器实例
var editor = null,
	// 选取的标签
	selectSign = '';


$(document).ready(function() {
	// 初始化编辑器
	initMarkdown();
	// 获取初始化数据
	queryInitData();
    
    // 提交文章
    $('.subimt-img').on('click', function() {
    	// 内容
    	var content = editor.getMarkdown(),
    		// 标题
    		title = $('.arr-title').val(),
    		// 描述
    		desc = $('.arr-describe').val(),
    		// 标签
    		sign = selectSign;
    	// 检测数据的合法性
    	if (checkInput(content, title, desc, sign)) {
    		$('.cover-box').show();
    		// 提交
    		submitContent(content, title, desc, sign);
    	}
    });
});

// 初始化编辑器
function initMarkdown() {
	editor = editormd('editormd', {
        path : './markdown/lib/',// Autoload modules mode, codemirror, marked... dependents libs path
        imageUpload: true,
        imageFormats: ['jpg'],
        // 生产修改
        imageUploadURL: '/hdd/20008',
        uploadCallbackURL: ''
    });
}

// 获取标签数据
function queryInitData() {
	var param = {
		funNo: '20009'
	};
	Qajax(param, function(data){
		if (data.error_no === '0'){
			var result = data.results,
				str = '';
			result.map(function(item) {
				str += '<em data-val=' + item.id + '>' + item.itemname + '</em>';
			});
			$('.img-sign').html(str);
			// 选取图集标签
			$('.img-sign>em').on('click', function(e) {
				var $this = $(this);
				// 全局
				selectSign = $this.attr('data-val');
				if ($this.hasClass('active')) {
					return false;
				}
				$this.addClass('active').siblings().removeClass('active');
			});
		} else {
			alert(data.error_info);
		}
	});
}

// 提交
function submitContent(content, title, desc, sign) {
	var param = {
		funNo: '20012',
		itemid: sign,
		title: title,
		desc: desc,
		writer: 'hdd',
		content: content
	};
	Qajax(param, function(data){
		if (data.error_no === '0'){
			alert('提交成功');
			window.location.reload();
		} else {
			$('.cover-box').hide();
			alert(data.error_info);
		}
	});
}

// 检测输入数据的合法性
function checkInput(content, title, desc, sign) {
	if (!content) {
		alert('提交的内容不能为空');
		return false;
	} else if (!title) {
		alert('提交的标题不能为空');
		return false;
	} else if (!desc) {
		alert('提交的描述不能为空');
		return false;
	} else if (!sign) {
		alert('提交的标签不能为空');
		return false;
	} else {
		return true;
	}
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