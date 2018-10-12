/*
 * 点击侧边的菜单事件
 * 让main根据地址刷新相对应的内容
 */
$(function() {
	$(".nav li a").click(function() {
		var type = $(this).attr("value");
		var address = $(this).attr("href");
		$("a").removeClass("active")
		$(this).addClass("active");
		$('#contain').nextAll().remove();
		$('#contain').load(address);
		switch (type) {
		case 'StudentManagement':
			requestStudentContent("StudentManagementContent?page=1");
			break;
		}
		return false;
	});

});

function requestStudentContent(url) {
	$.get(url, function(data, status) {
		var stu = data.studentList;
		var res = "";
		var pages = "";
		var path = $("#path").val();
		for (var i = 0; i < stu.length; i++) {
			var sex = "";
			if (stu[i].sex == 0) {
				sex = "女";
			} else {
				sex = "男";
			}
			res += "<tr>" + "<td>" + stu[i].id + "</td>" +
				"<td>" + stu[i].name + "</td>" +
				"<td>" + sex + "</td>" +
				"<td>" + stu[i].birth + "</td>" +
				"<td>" + stu[i].schoolDay + "</td>" +
				"<td>" + stu[i].majorName + "</td>" +
				"<td>" + stu[i].collegeName + "</td>" +
				"<td><button class='btn btn-primary stu-alert' value=''>修改</button><button class='btn btn-danger stu-delete' value=''>删除</button></td></tr>";
		}
		$("tbody").nextAll().remove();
		$("tbody").append(res);
		$("#stu-total").append("共" + data.allStudentCount + "条信息");
		pages += "<li class='page-item' value=''><a class='page-link' id='page' href='" + path + "/StudentManagementContent?page=" + data.prePage + "'>上一页</a></li>";
		for (var i = 0; i < data.pageNum.length; i++) {
			if (data.page == data.pageNum[i]) {
				pages += "<li class='page-item active' value=''><a class='page-link' id='page' href='" + path + "/StudentManagementContent?page=" + data.pageNum[i] + "'>" + data.pageNum[i] + "</a></li>"
			} else {
				pages += "<li class='page-item' value=''><a class='page-link' id='page' href='" + path + "/StudentManagementContent?page=" + data.pageNum[i] + "'>" + data.pageNum[i] + "</a></li>"
			}
		}
		pages += "<li class='page-item' value=''><a class='page-link' id='page' href='" + path + "/StudentManagementContent?page=" + data.nextPage + "'>下一页</a></li>"
		$('.pageNav ul').nextAll().remove();
		$('.pageNav ul').append(pages);
		//页码点击事件
		$(".page-item a").on("click",function(){
			var address = $(this).attr("href");
			var path = $("#path").val();
			request2html(address);
			return false;
		});
	});
}