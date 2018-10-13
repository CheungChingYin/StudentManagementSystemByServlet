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
			} else if(stu[i].sex == 1) {
				sex = "男";
			}else{
				sex=stu[i].sex;
			}
			res += "<tr>" + "<td>" + stu[i].id + "</td>" +
				"<td>" + stu[i].name + "</td>" +
				"<td>" + sex + "</td>" +
				"<td>" + stu[i].birth + "</td>" +
				"<td>" + stu[i].schoolDay + "</td>" +
				"<td>" + stu[i].majorName + "</td>" +
				"<td>" + stu[i].collegeName + "</td>" +
				"<td><button class='btn btn-primary stu-alert' value='" + stu[i].id + "' data-toggle='modal' data-target='#stu-update' id='stu-update-button'>修改</button>" +
				"<button id='stu-delete-button' class='btn btn-danger stu-delete' value='"+stu[i].id+"'>删除</button></td></tr>";
		}
		$("tbody").html("");
		$("tbody").append(res);
		$("#stu-total").html("");
		$("#stu-total").append("共" + data.allStudentCount + "条信息");
		if (data.search == null) {
			//非搜索结果情况下
			pages += "<li class='page-item' value=''><a class='page-link' id='page' href='" + path + "/StudentManagementContent?page=" + data.prePage + "'>上一页</a></li>";
			for (var i = 0; i < data.pageNum.length; i++) {
				if (data.page == data.pageNum[i]) {
					pages += "<li class='page-item active' value=''><a class='page-link' id='page' href='" + path + "/StudentManagementContent?page=" + data.pageNum[i] + "'>" + data.pageNum[i] + "</a></li>"
				} else {
					pages += "<li class='page-item' value=''><a class='page-link' id='page' href='" + path + "/StudentManagementContent?page=" + data.pageNum[i] + "'>" + data.pageNum[i] + "</a></li>"
				}
			}
			pages += "<li class='page-item' value=''><a class='page-link' id='page' href='" + path + "/StudentManagementContent?page=" + data.nextPage + "'>下一页</a></li>"
		} else {
			//如果是搜索结果则分页地址要为请求搜索的地址
			pages += "<li class='page-item' value=''><a class='page-link' id='page' href='" + path + "/SearchStudent?page=" + data.prePage + "&search=" + data.search + "'>上一页</a></li>";
			for (var i = 0; i < data.pageNum.length; i++) {
				if (data.page == data.pageNum[i]) {
					pages += "<li class='page-item active' value=''><a class='page-link' id='page' href='" + path + "/SearchStudent?page=" + data.pageNum[i] + "&search=" + data.search + "'>" + data.pageNum[i] + "</a></li>"
				} else {
					pages += "<li class='page-item' value=''><a class='page-link' id='page' href='" + path + "/SearchStudent?page=" + data.pageNum[i] + "&search=" + data.search + "'>" + data.pageNum[i] + "</a></li>"
				}
			}
			pages += "<li class='page-item' value=''><a class='page-link' id='page' href='" + path + "/SearchStudent?page=" + data.nextPage + "&search=" + data.search + "'>下一页</a></li>"
		}

		$('.pageNav ul').html("");
		$('.pageNav ul').append(pages);
		//分页点击事件
		$(".page-item a").on("click", function() {
			var address = $(this).attr("href");
			var path = $("#path").val();
			request2html(address);
			return false;
		});
	});
}

//修改按钮点击事件
$("#table-content").on("click", "#stu-update-button", function() {
	var id = $(this).val();
	var path = $("#path").val();
	var url = "SearchStudent?page=1&search=" + id;
	$.get(url, function(data, status) {
		var stu = data.studentList[0];
		$("#stu-update-form div #stu-id").val(stu.id);
		$("#stu-update-form div #stu-name").val(stu.name);
		if(stu.sex == 0){
			$("#stu-update-form div  #stu-sex").html("");
			$("#stu-update-form div #stu-sex").append("<option value='0' selected='selected'>女</option><option value='1'>男</option>");
		}else{
			$("#stu-update-form div #stu-sex").html("");
			$("#stu-update-form div #stu-sex").append("<option value='0' >女</option><option value='1' selected='selected'>男</option>");
		}
		$("#stu-update-form div #birth ").val(stu.birth);
		$("#stu-update-form div #schoolDay").val(stu.schoolDay);
		$("#stu-update-form div #major").html("");
		$("#stu-update-form div #major").append("<option selected='selected' value='"+stu.majorName+"'></option></select>");
		$("#stu-update-form div #college").html("");
		$("#stu-update-form div #college").append("<option selected='selected' value='"+stu.collegeName+"'></option></select>");
		//请求获取学院列表
		$.get("getCollege", function(data, status) {
			var college = data;
			var res = "";
			var collegeValue = $("#stu-update-form div #college").val();
			for (var i = 0; i < college.length; i++) {
				if(college[i].name == collegeValue){
					res += "<option value='" + college[i].id + "' selected='selected'>" + college[i].name + "</option>";
				}else{
					res += "<option value='" + college[i].id + "'>" + college[i].name + "</option>";
				}
			}
			$("#stu-update-form div #college").html("");
			$("#stu-update-form div #college").append(res);
			var collegeId = $("#stu-update-form div #college").val();
			
			$.get("getMajor?collegeId=" + collegeId, function(data, status) {
				var major = data;
				var res = "";
				var majorValue = $("#stu-update-form div #major").val();
				for (var i = 0; i < major.length; i++) {
					if(major[i].name == majorValue){
						res += "<option value='" + major[i].id + "' selected='selected'>" + major[i].name + "</option>";
					}else{
						res += "<option value='" + major[i].id + "'>" + major[i].name + "</option>";
					}
				}
				$("#stu-update-form div #major").html("");
				$("#stu-update-form div #major").append(res);
			});
		});
	});
});

$("#stu-update-form div #college").change(function(){
	var collegeId = $("#stu-update-form div #college").val();
	
	$.get("getMajor?collegeId=" + collegeId, function(data, status) {
		var major = data;
		var res = "";
		var majorValue = $("#stu-update-form div #major").val();
		for (var i = 0; i < major.length; i++) {
			if(major[i].name == majorValue){
				res += "<option value='" + major[i].id + "' selected='selected'>" + major[i].name + "</option>";
			}else{
				res += "<option value='" + major[i].id + "'>" + major[i].name + "</option>";
			}
		}
		$("#stu-update-form div #major").html("");
		$("#stu-update-form div #major").append(res);
	});
});

$("#table-content").on("click", "#stu-delete-button",function(){
	var id = $(this).val();
	alert(id);
	if(confirm("确定删除吗")){
		$.get("deleteStudent?id="+id,function(data,status){
			if(status == 'success'){
				alert("删除成功!");
				requestStudentContent("StudentManagementContent?page=1");
			}else{
				alert("删除失败，可能您的网络有问题!");
			}
		});
	}
});