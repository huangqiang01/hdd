var editor = null,
	setTime = null;

$(document).ready(function() {
	var content = getParameter('content');
	
	// 初始化数据
	var param = {
		funNo: '20011',
		id: content,
	};
	Qajax(param, function(data){
		if (data.error_no === '0'){
			var result = data.results;
			if (result.length > 0) {
				var item = result[0];
				$('.header').html('<p class="title">' + item.title + '</p><div class="time">' + item.createtime.split(' ')[0] + '</div>');
				$('#editormd textarea').val(item.content);
				editormd.markdownToHTML('editormd');
			} else {
				alert('无数据');
			}
		} else {
			alert(data.error_info);
		}
	});
});
