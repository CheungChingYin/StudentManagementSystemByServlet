
var vmMajorContent = new Vue({
	el : '#contain',
	data : {
		majorList : [],
		allMajorCount : "",
		prePage : "",
		nextPage : "",
		pageNum : [],
		page : "",
		search:"",
		flag:true
	},
	mounted : function() {
		var self = this;
		$.get("MajorManagementContent?page=1", function(data, status) {
			self.majorList = data.majorList;
			self.page = data.page;
			self.prePage = data.prePage;
			self.nextPage = data.nextPage;
			self.pageNum = data.pageNum;
			self.allMajorCount = data.allMajorCount;
			self.search = "";
			self.flag = true;
		});
	},
	methods : {
		pageChange : function(page) {
			this.$nextTick(function() {
				$.get("MajorManagementContent?page=" + page, function(data, status) {
					console.log(data.adminList)
					vmMajorContent.majorList = data.majorList;
					vmMajorContent.page = data.page;
					vmMajorContent.prePage = data.prePage;
					vmMajorContent.nextPage = data.nextPage;
					vmMajorContent.pageNum = data.pageNum;
					vmMajorContent.allMajorCount = data.allMajorCount;
					vmMajorContent.search = "";
					vmMajorContent.flag = true;
				});
			});
		},
		searchFunction:function(){
			this.$nextTick(function(){
				$.get("searchAdministraotr?page=1&search="+$(".admin-search-bar").val(),function(data,status){
					console.log(data.adminList);
					vmAdminContent.adminList = data.adminList;
					vmAdminContent.page = data.page;
					vmAdminContent.prePage = data.prePage;
					vmAdminContent.nextPage = data.nextPage;
					vmAdminContent.pageNum = data.pageNum;
					vmAdminContent.allAdminCount = data.allAdminCount;
					vmAdminContent.search = data.search;
					vmAdminContent.flag = false;
				})
			})
		},
		searchPage:function(page,search){
			this.$nextTick(function(){
				$.get("searchAdministraotr?page="+page+"&search="+search,function(data,status){
					vmAdminContent.adminList = data.adminList;
					vmAdminContent.page = data.page;
					vmAdminContent.prePage = data.prePage;
					vmAdminContent.nextPage = data.nextPage;
					vmAdminContent.pageNum = data.pageNum;
					vmAdminContent.allAdminCount = data.allAdminCount;
					vmAdminContent.search = data.search;
					vmAdminContent.flag = false;
				})
			})
		}
	}
});

/*
 * 添加功能表单校验
 */
$("#major-add-form").validator({
	rules : {
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "专业名称必须是中文或者英文" ],
	},
	fields : {
		'id' : "required;integer;remote(" + $("#path").val() + "/majorIdIsExist)",
		'name' : "required;nameValidate",
		'college_id' : "required"
	},
	/**
	 * 新增管理员表单的提交按钮点击事件
	 * 提交表单
	 */
	valid : $("#major-add-input").click(function() {
		$.post("AddMajor", $("#major-add-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("添加成功!");
				$("#admin-add").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/MajorManagement");
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

	/**
	 * 表单验证通过后管理员权限更新提交按钮
	 * 提交表单
	 */
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


/*
 * 修改管理员密码表单校验
 */
$("#admin-update-password-form").validator({
	rules : {
		passwordValidate : [ /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$/, "密码需要为字母数字混合,限制为6-14位" ],
	},
	fields : {
		'password' : "required;passwordValidate",
		'password-confirm':"required;passwordValidate;match(password)"
	},
	/**
	 * 表单验证通过后管理员密码更新提交按钮
	 * 提交表单
	 */
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

/**
 * 点击添加管理员按钮事件
 * 清空表单内容
 */
$("#major-add-button").click(function(){
	$("#major-add-form div #major-id").val("");
	$("#major-add-form div #major-name").val("");
	$("#major-add-form div #major-college").val("");
	$("#major-add-form div #major-college").html("");
	$.get("getCollege", function(data, status) {
		var college = data;
		var res = "<option value=''>---请选择---</option>";
		for (var i = 0; i < college.length; i++) {
			res += "<option value='" + college[i].id + "'>" + college[i].name + "</option>";
		}
		$("#major-college").append(res);
	});
	
});


/**
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


/**
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

/**
 * 管理员删除按钮点击事件
 * 删除相对应的管理员
 */
$("#table-content").on("click", "#admin-delete-button",function(){
	var id = $(this).val();
	var path = $("#path").val();
	var url = "deleteAdministrator?id=" + id;
	if(confirm("确定删除吗")){
		$.get(url,function(data,status){
			if(status == 'success'){
				alert("删除成功!");
				$('#contain').load(path + "/AdministratorManagement");
			}else{
				alert("删除失败，可能您的网络有问题!");
			}
		});
	}
})