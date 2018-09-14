var editor = null,
	setTime = null;

$(document).ready(function() {
	var content = getParameter('content');
	// 初始化编辑器
	initMarkdown();
	
	// 初始化数据
	var param = {
		funNo: '20011',
		id: content,
	};
	Qajax(param, function(data){
		if (data.error_no === '0'){
			var result = data.results;
			if (result.length > 0) {
				showData(result[0].content);
			} else {
				alert('无数据');
			}
		} else {
			alert(data.error_info);
		}
	});
});

// 显示数据
function showData(data) {
	if (setTime) {
		clearTimeout(setTime);
		setTime = null;
	}
	if (editor.cm) {
		editor.setValue(data);
		showData1();
		console.log();
	} else {
		setTime = setTimeout(function() {
			showData(data);
		}, 40);
	}
}

//显示数据
function showData1() {
	if (setTime) {
		clearTimeout(setTime);
		setTime = null;
	}
	if ($('.editormd-preview-container').html()) {
		$('.details').html($('.editormd-preview').html()).show(); 
	} else {
		setTime = setTimeout(function() {
			showData1();
		}, 40);
	}
}

// 初始化编辑器
function initMarkdown() {
	editor = editormd('editormd', {
        path : '../web/markdown/lib/',
        toolbar: false,
        autoHeight: true
    });
}


