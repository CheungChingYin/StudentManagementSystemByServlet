
var vmCollegeContent = new Vue({
	el : '#contain',
	data : {
		collegeList : [],
		allCollegeCount : "",
		prePage : "",
		nextPage : "",
		pageNum : [],
		page : "",
		search:"",
		flag:true
	},
	mounted : function() {
		var self = this;
		$.get("CollegeManagementContent?page=1", function(data, status) {
			self.collegeList = data.collegeList;
			self.page = data.page;
			self.prePage = data.prePage;
			self.nextPage = data.nextPage;
			self.pageNum = data.pageNum;
			self.allCollegeCount = data.allCollegeCount;
			self.search = "";
			self.flag = true;
		});
	},
	methods : {
		pageChange : function(page) {
			this.$nextTick(function() {
				$.get("CollegeManagementContent?page=" + page, function(data, status) {
					vmCollegeContent.majorList = data.collegeList;
					vmCollegeContent.page = data.page;
					vmCollegeContent.prePage = data.prePage;
					vmCollegeContent.nextPage = data.nextPage;
					vmCollegeContent.pageNum = data.pageNum;
					vmCollegeContent.allCollegeCount = data.allCollegeCount;
					vmCollegeContent.search = "";
					vmCollegeContent.flag = true;
				});
			});
		},
		searchFunction:function(){
			this.$nextTick(function(){
				$.get("searchCollege?page=1&search="+$(".college-search-bar").val(),function(data,status){
					vmCollegeContent.collegeList = data.collegeList;
					vmCollegeContent.page = data.page;
					vmCollegeContent.prePage = data.prePage;
					vmCollegeContent.nextPage = data.nextPage;
					vmCollegeContent.pageNum = data.pageNum;
					vmCollegeContent.allCollegeCount = data.allCollegeCount;
					vmCollegeContent.search = data.search;
					vmCollegeContent.flag = false;
				})
			})
		},
		searchPage:function(page,search){
			this.$nextTick(function(){
				$.get("searchCollege?page="+page+"&search="+search,function(data,status){
					vmCollegeContent.collegeList = data.collegeList;
					vmCollegeContent.page = data.page;
					vmCollegeContent.prePage = data.prePage;
					vmCollegeContent.nextPage = data.nextPage;
					vmCollegeContent.pageNum = data.pageNum;
					vmCollegeContent.allCollegeCount = data.allCollegeCount;
					vmCollegeContent.search = data.search;
					vmCollegeContent.flag = false;
				})
			})
		}
	}
});

/*
 * 添加功能表单校验
 */
$("#college-add-form").validator({
	rules : {
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "专业名称必须是中文或者英文" ],
	},
	fields : {
		'id' : "required;integer;remote(" + $("#path").val() + "/collegeIsExist)",
		'name' : "required;nameValidate"
	},
	/**
	 * 新增管理员表单的提交按钮点击事件
	 * 提交表单
	 */
	valid : $("#college-add-input").click(function() {
		$.post("addCollege", $("#college-add-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("添加成功!");
				$("#college-add").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/CollegeManagement");
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});

/*
 * 修改权限表单校验
 */
$("#college-update-form").validator({
	rules : {
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "专业名称必须是中文或者英文" ],
	},
	fields : {
		'id' : "required;integer",
		'name' : "required;nameValidate"
	},

	/**
	 * 表单验证通过后学院修改更新提交按钮
	 * 提交表单
	 */
	valid : $("#college-update-input").click(function() {
		$.post("updateCollege", $("#college-update-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("修改学院成功!");
				$("#college-update").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/CollegeManagement");
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});



/**
 * 点击添加学院按钮事件
 * 清空表单内容
 */
$("#college-add-button").click(function(){
	$("#college-add-form div #college-id").val("");
	$("#college-add-form div #college-name").val("");
});

/**
 * 修改按钮点击事件
 * 回显表单
 */
$("#table-content").on("click", "#college-update-button", function() {
	var id = $(this).val();
	var path = $("#path").val();
	var url = "searchCollege?page=1&search=" + id;
	$.get(url, function(data, status) {
		$("#college-update-form div #college-id").val(data.collegeList[0].id);
		$("#college-update-form div #college-name").val(data.collegeList[0].name);
	});
});


/**
 * 管理员删除按钮点击事件
 * 删除相对应的管理员
 */
$("#table-content").on("click", "#college-delete-button",function(){
	var id = $(this).val();
	var path = $("#path").val();
	var url = "deleteCollege?id=" + id;
	if(confirm("确定删除吗")){
		$.get(url,function(data,status){
			if(status == 'success'){
				alert("删除成功!");
				$('#contain').load(path + "/CollegeManagement");
			}else{
				alert("删除失败，可能您的网络有问题!");
			}
		});
	}
})