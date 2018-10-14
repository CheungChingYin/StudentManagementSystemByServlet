
var vmAdminContent = new Vue({
	el : '#content',
	data : {
		adminList : [],
		allStudentCount : "",
		prePage : "",
		nextPage : "",
		pageNum : [],
		page : ""
	},
	mounted : function() {
		var self = this;
		$.get("AdministratorManagementContent?page=1", function(data, status) {
			self.adminList = data.adminList;
			self.page = data.page;
			self.prePage = data.prePage;
			self.nextPage = data.nextPage;
			self.pageNum = data.pageNum;
			self.allStudentCount = data.allStudentCount;
		});
	},
	methods:{
		pageChange:function(page){
			this.$nextTick(function(){
				$.get("AdministratorManagementContent?page="+page,function(data,status){
					console.log(data.adminList)
					vmAdminContent.adminList = data.adminList;
					vmAdminContent.page = data.page;
					vmAdminContent.prePage = data.prePage;
					vmAdminContent.nextPage = data.nextPage;
					vmAdminContent.pageNum = data.pageNum;
					vmAdminContent.allStudentCount = data.allStudentCount;
				});
			});
			console.log(this.adminList)
		}
	}
});

//$(".pagination").on("click","#page",function(){
//	Vue.set(function(){
//		var page = $("#page").attr("value");
//		$.get("AdministratorManagementContent?page="+page, function(data, status) {
//			vmAdminContent.adminList = data.adminList;
//			vmAdminContent.page = data.page;
//			vmAdminContent.prePage = data.prePage;
//			vmAdminContent.nextPage = data.nextPage;
//			vmAdminContent.pageNum = data.pageNum;
//			vmAdminContent.allStudentCount = data.allStudentCount;
//		});
//	});
//	false;
//})