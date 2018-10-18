
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
				$.get("SearchMajor?page=1&search="+$(".major-search-bar").val(),function(data,status){
					vmMajorContent.majorList = data.majorList;
					vmMajorContent.page = data.page;
					vmMajorContent.prePage = data.prePage;
					vmMajorContent.nextPage = data.nextPage;
					vmMajorContent.pageNum = data.pageNum;
					vmMajorContent.allMajorCount = data.allMajorCount;
					vmMajorContent.search = data.search;
					vmMajorContent.flag = false;
				})
			})
		},
		searchPage:function(page,search){
			this.$nextTick(function(){
				$.get("SearchMajor?page="+page+"&search="+search,function(data,status){
					vmMajorContent.majorList = data.majorList;
					vmMajorContent.page = data.page;
					vmMajorContent.prePage = data.prePage;
					vmMajorContent.nextPage = data.nextPage;
					vmMajorContent.pageNum = data.pageNum;
					vmMajorContent.allMajorCount = data.allMajorCount;
					vmMajorContent.search = data.search;
					vmMajorContent.flag = false;
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
				$("#major-add").modal('hide');
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
$("#major-update-form").validator({
	rules : {
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "专业名称必须是中文或者英文" ],
	},
	fields : {
		'id' : "required;integer",
		'name' : "required;nameValidate",
		'college_id' : "required"
	},

	/**
	 * 表单验证通过后管理员权限更新提交按钮
	 * 提交表单
	 */
	valid : $("#major-update-input").click(function() {
		$.post("updateMajor", $("#major-update-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("修改专业成功!");
				$("#major-update").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/MajorManagement");
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
		$("#major-add-form div #major-college").append(res);
	});
	
});

/**
 * 修改按钮点击事件
 * 回显表单
 */
$("#table-content").on("click", "#major-update-button", function() {
	var id = $(this).val();
	var path = $("#path").val();
	var url = "SearchMajor?page=1&search=" + id;
	$.get("getCollege", function(data, status) {
		var college = data;
		$("#major-update-form div #major-college").html("");
		var res = "<option value=''>---请选择---</option>";
		for (var i = 0; i < college.length; i++) {
			res += "<option value='" + college[i].id + "'>" + college[i].name + "</option>";
		}
		$("#major-update-form div #major-college").append(res);
	});
	$.get(url, function(data, status) {
		$("#major-update-form div #major-id").val(data.majorList[0].id);
		$("#major-update-form div #major-name").val(data.majorList[0].name);
		$("#major-update-form div #major-college option").each(function(){
			if($(this).text() == data.majorList[0].collegeName){
				$(this).attr("selected","selected");
			}
		});
	});
});


/**
 * 管理员删除按钮点击事件
 * 删除相对应的管理员
 */
$("#table-content").on("click", "#major-delete-button",function(){
	var id = $(this).val();
	var path = $("#path").val();
	var url = "deleteMajor?id=" + id;
	if(confirm("确定删除吗")){
		$.get(url,function(data,status){
			if(status == 'success'){
				alert("删除成功!");
				$('#contain').load(path + "/MajorManagement");
			}else{
				alert("删除失败，可能您的网络有问题!");
			}
		});
	}
})