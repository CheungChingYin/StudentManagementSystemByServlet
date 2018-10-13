/*
 * 添加按钮点击事件
 * 使用Ajax获得相对应的学院，并且添加到学院的<select>控件中
 */
$("#stu-add-button").on("click", function() {
	$.get("getCollege", function(data, status) {
		var college = data;
		var res = "";
		for (var i = 0; i < college.length; i++) {
			res += "<option value='" + college[i].id + "'>" + college[i].name + "</option>";
		}
		$("#college").append(res);
	});
});

$("#stu-search-button").on("click", function() {
	var search = $(".stu-search-bar").val();
	request2html("SearchStudent?page=1&search=" + search);
});

/*
 * 学院按钮改变时
 * 当选中学院下拉菜单中的一项时，通过Ajax加载相对应的学院专业
 */
$("#college").change(function() {
	var collegeId = $(this).val();
	$("#major option:not(:first)").remove();
	$.get("getMajor?collegeId=" + collegeId, function(data, status) {
		var major = data;
		var res = "";
		for (var i = 0; i < major.length; i++) {
			res += "<option value='" + major[i].id + "'>" + major[i].name + "</option>";
		}
		$("#major").append(res);
	});
});

/*
 * 添加学生表单的表单验证
 * 使用了第三方插件 nice-validator 进行表单的前端验证，只有验证通过才能使用Ajax提交表单
 */
$("#stu-add-form,#stu-update-form").validator({
	rules : {
		//自定义规则
		idType : [ /^\d+/, "学生学号必须为纯数字" ],
		idLength : [ /^\d{15}$/, "学生学号长度必须为15位" ],
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "学生姓名必须是中文或者英文" ],
	},
	fields : {
		//表单应用规则
		'id' : "required;idType;idLength",
		'name' : "required;nameValidate",
		'sex' : "required",
		'birth' : "required",
		'schoolDay' : "required",
		'major_id' : "required",
		'college_id' : "required"
	},
	//验证通过后需要执行的方法
	valid : $("#stu-add-input").click(function() {
		$.post("addStudent", $("#stu-add-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("添加成功!");
				$("#stu-add").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/StudentManagement");
				request2html("StudentManagementContent?page=1");
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		});
	}),
	valid : $("#stu-update-input").click(function() {
		$.post("updateStudent", $("#stu-update-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("修改成功!");
				$("#stu-update").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/StudentManagement");
				request2html("StudentManagementContent?page=1");
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		});
	})
});


function request2html(url) {
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
		if (stu.sex == 0) {
			$("#stu-update-form div  #stu-sex").html("");
			$("#stu-update-form div #stu-sex").append("<option value='0' selected='selected'>女</option><option value='1'>男</option>");
		} else {
			$("#stu-update-form div #stu-sex").html("");
			$("#stu-update-form div #stu-sex").append("<option value='0' >女</option><option value='1' selected='selected'>男</option>");
		}
		$("#stu-update-form div #birth ").val(stu.birth);
		$("#stu-update-form div #schoolDay").val(stu.schoolDay);
		$("#stu-update-form div #major").html("");
		$("#stu-update-form div #major").append("<option selected='selected' value='" + stu.majorName + "'></option></select>");
		$("#stu-update-form div #college").html("");
		$("#stu-update-form div #college").append("<option selected='selected' value='" + stu.collegeName + "'></option></select>");
		//请求获取学院列表
		$.get("getCollege", function(data, status) {
			var college = data;
			var res = "";
			var collegeValue = $("#stu-update-form div #college").val();
			for (var i = 0; i < college.length; i++) {
				if (college[i].name == collegeValue) {
					res += "<option value='" + college[i].id + "' selected='selected'>" + college[i].name + "</option>";
				} else {
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
					if (major[i].name == majorValue) {
						res += "<option value='" + major[i].id + "' selected='selected'>" + major[i].name + "</option>";
					} else {
						res += "<option value='" + major[i].id + "'>" + major[i].name + "</option>";
					}
				}
				$("#stu-update-form div #major").html("");
				$("#stu-update-form div #major").append(res);
			});
		});
	});
});

$("#stu-update-form div #college").change(function() {
	var collegeId = $("#stu-update-form div #college").val();

	$.get("getMajor?collegeId=" + collegeId, function(data, status) {
		var major = data;
		var res = "";
		var majorValue = $("#stu-update-form div #major").val();
		for (var i = 0; i < major.length; i++) {
			if (major[i].name == majorValue) {
				res += "<option value='" + major[i].id + "' selected='selected'>" + major[i].name + "</option>";
			} else {
				res += "<option value='" + major[i].id + "'>" + major[i].name + "</option>";
			}
		}
		$("#stu-update-form div #major").html("");
		$("#stu-update-form div #major").append(res);
	});
});

$("#table-content").on("click", "#stu-delete-button",function(){
	var id = $(this).val();
	if(confirm("确定删除吗")){
		$.get("deleteStudent?id="+id,function(data,status){
			if(status == 'success'){
				alert("删除成功!");
				request2html("StudentManagementContent?page=1");
			}else{
				alert("删除失败，可能您的网络有问题!");
			}
		});
	}
});