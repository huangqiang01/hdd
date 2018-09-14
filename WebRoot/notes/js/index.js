$(document).ready(function() {
	// 初始化数据
	var param = {
		funNo: '20010'
	};
	Qajax(param, function(data){
		if (data.error_no === '0'){
			var result = data.results,
				str = '';
			result.map(function(item) {
				str += '<li><a href="content.html?content=' + item.id + '">' + item.title +'</a><p>' + item.articledesc +'</p><span>' + item.createtime.split(' ')[0] +'</span></li>';
			});
			$('.list>ul').html(str);
		} else {
			alert(data.error_info);
		}
	});
});
