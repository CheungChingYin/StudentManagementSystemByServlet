
var vmAdminContent = new Vue({
	el : '#contain',
	data : {
		adminList : [],
		allAdminCount : "",
		prePage : "",
		nextPage : "",
		pageNum : [],
		page : "",
	},
	mounted : function() {
		var self = this;
		$.get("AdministratorManagementContent?page=1", function(data, status) {
			self.adminList = data.adminList;
			self.page = data.page;
			self.prePage = data.prePage;
			self.nextPage = data.nextPage;
			self.pageNum = data.pageNum;
			self.allAdminCount = data.allAdminCount;
		});
	},
	methods : {
		pageChange : function(page) {
			this.$nextTick(function() {
				$.get("AdministratorManagementContent?page=" + page, function(data, status) {
					console.log(data.adminList)
					vmAdminContent.adminList = data.adminList;
					vmAdminContent.page = data.page;
					vmAdminContent.prePage = data.prePage;
					vmAdminContent.nextPage = data.nextPage;
					vmAdminContent.pageNum = data.pageNum;
					vmAdminContent.allAdminCount = data.allAdminCount;
				});
			});
		}
	}
});

/*
 * 添加功能表单校验
 */
$("#admin-add-form").validator({
	rules : {
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "管理员姓名必须是中文或者英文" ],
		passwordValidate : [ /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$/, "密码需要为字母数字混合,限制为6-14位" ],
	},
	fields : {
		'user' : "required;nameValidate;remote(" + $("#path").val() + "/AdministratorNameIsExist)",
		'password' : "required;passwordValidate",
		'permission' : "required"
	},
	valid : $("#admin-add-input").click(function() {
		$.post("addAdministrator", $("#admin-add-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("添加成功!");
				$("#admin-add").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/AdministratorManagement");
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});

/*
 * 修改权限表单校验
 */
$("#admin-update-form").validator({
	rules : {
		passwordValidate : [ /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$/, "密码需要为字母数字混合,限制为6-14位" ],
	},
	fields : {
		'user' : "required",
		'password' : "required;passwordValidate",
		'permission' : "required"
	},

	valid : $("#admin-update-input").click(function() {
		$.post("updateAdministrator", $("#admin-update-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("修改权限成功!");
				$("#admin-add").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/AdministratorManagement");
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});


$("#admin-update-password-form").validator({
	rules : {
		passwordValidate : [ /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$/, "密码需要为字母数字混合,限制为6-14位" ],
	},
	fields : {
		'password' : "required;passwordValidate",
		'password-confirm':"required;passwordValidate;match(password)"
	},

	valid : $("#admin-update-password-input").click(function() {
		$.post("updateAdministratorPassword", $("#admin-update-password-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("修改密码成功!");
				$("#admin-add").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/AdministratorManagement");
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});




/*
 * 修改按钮点击事件
 * 回显表单
 */
$("#table-content").on("click", "#admin-update-button", function() {
	var id = $(this).val();
	var path = $("#path").val();
	var url = "searchAdministraotr?page=1&search=" + id;
	$.get(url, function(data, status) {
		$("#admin-update-form div #admin-id").val(data.adminList[0].id);
		$("#admin-update-form div #admin-name").val(data.adminList[0].user);
		$("#admin-update-form div #admin-permission").val(data.adminList[0].permission);
	})
});


/*
 * 修改密码按钮点击事件
 * 回显表单
 */
$("#table-content").on("click", "#admin-alertPassword-button",function(){
	var id = $(this).val();
	var path = $("#path").val();
	var url = "searchAdministraotr?page=1&search=" + id;
	$.get(url, function(data, status){
		$("#admin-update-password-form div #admin-id").val(data.adminList[0].id);
		$("#admin-update-password-form #admin-name").val(data.adminList[0].user);
		$("#admin-update-password-form #admin-password").val("");
		$("#admin-update-password-form #admin-password-confirm").val("");
	})
})