/*
 * 点击侧边的菜单事件
 * 让main根据地址刷新相对应的内容
 */
$(function(){
	$(".nav li a").click(function(){
		var address = $(this).attr("href");
		$("a").removeClass("active")
		$(this).addClass("active"); 
		$('#contain').load(address);
		return false;
	});
	
});