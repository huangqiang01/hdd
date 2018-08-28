$(function(){
	"use strict";
	var $container = $("#Qcontainer"),
		currentPage = null,
		// ajax请求默认参数
		ajaxs = {
			timeout: 1000 * 60 * 10,
			dataType: "json",
			beforeSend: null,
			complete: null,
		};
	
	/**
	 * 初始页面，--刷新等动作
	 */
	function initWeb(){
		var state = history.state;
		if (state){
			currentPage = state;
		} else {
			currentPage = "first.html";
			history.replaceState(currentPage, "HDD", "index.html?tdd@" + currentPage);
		}
		goPage(currentPage, "noPush");
	}
	
	/**
	 * 加载页面
	 * @param {Object} page
	 */
	function _loadPage(page){
		var _page = page,
			pageId = _page.replace(".html", ""),
			pageJS = _page.replace(".html", ".js");
		$container.load(_page, function() {
			// 移除js 文件
			currentPage = page;
			// 动态加载js
			var id = "JS_" + pageId,
				src = "js/" + pageJS,
				html = "<script type='text/javascript' id='"+id+"' src='"+src+"'></script>";
			$("body").append(html);
		});
	}
	
	/**
	 * 去相应页面
	 * @param {Object} page
	 * @param {Object} mark
	 */
	function goPage(page, mark){
	
		// 移除 多余的js  文件
		_removeJs();
		
		// 加载相应页面
		_loadPage(page);
		//
		if (mark !== "noPush"){
			history.pushState(page, "HDD", "index.html?tdd@" + page);
		}
	}
	
	//
	window.onpopstate = function(data) {
		var page = data.state;
		if (page){
			goPage(page, "noPush");
		}
	};
	
	// 
	function goBack(){
		history.go(-1);
	}
	
	/**
	 * 移除多余的js 文件
	 */
	function _removeJs(){
		var id = currentPage.replace(".html", "");
		$("#JS_" + id).remove();
	}
	
	
	function Qajax(param, success, aJson){
		var ajaxP = {
//					url: "/" + param.funNo + "?num=" + Math.random(), // 生产修改
			url: "/" + param.funNo + "?num=" + Math.random(), // 生产修改
			async: true,
			type: "post",
			success: success,
			data: param
		};
		$.ajax($.extend(ajaxs, ajaxP, aJson));
	}
	
	/**
	 * 获取页面参数
	 */
	function getParameter(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) {
			return unescape(r[2]);
		} else {
			return null;
		}
	} 
	
	// 暴露全局方法
	window.initWeb = initWeb;
	window.goPage = goPage;
	window.goBack = goBack;
	window.Qajax = Qajax;
	window.getParameter = getParameter;
});














