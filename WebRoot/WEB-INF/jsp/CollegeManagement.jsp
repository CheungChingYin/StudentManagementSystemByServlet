<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 引入css样式表 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/CollegeManagement.css">

<!-- 引入JS文件 -->
<script
	src="${pageContext.request.contextPath }/Resources/js/CollegeManagement.js"></script>
</head>

<body>
	<div class="college-top">
		<div class="college-title">
			<h2>管理员信息管理</h2>
		</div>
		<div class="college-functionArea">
			<div class="college-search input-group-lg">
				<input type="text" id="college-search-bar"  class="college-search-bar" name="search"
					placeholder="请输入学院ID或者学院名">
				<button class="btn btn-secondary college-search-button"
					id="college-search-button" @click="searchFunction()">搜索</button>
			</div>
			<div>
				<button class="btn btn-primary college-addcollegedent"
					data-toggle="modal" data-target="#college-add" id="college-add-button">添加专业</button>
			</div>

		</div>
	</div>
	<!-- 专业列表 -->
	<div class="content" id='content'>
		<table class="table table-striped table-hover" id="table-content">
			<thead>
				<tr>
					<th>学院ID</th>
					<th>学院名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="college in collegeList">
					<td>{{college.id}}</td>
					<td>{{college.name}}</td>
					<td><button class='btn btn-primary college-alert' v-bind:value="college.id" data-toggle='modal' data-target='#college-update' id='college-update-button'>修改</button>
					<button id='college-delete-button' class='btn btn-danger college-delete' v-bind:value="college.id">删除</button></td>
				</tr>
			</tbody>
		</table>
		<div class="college-total" id="college-total">共有{{allCollegeCount}}条信息</div>
		<!-- 分页功能 -->
		<div class="pageNav">
			<ul class="pagination">
				<li class='page-item'><a class='page-link' id='page' href="#" @click="pageChange(prePage)" v-if="flag == true">上一页</a></li>
				<li class='page-item'><a class='page-link' id='page' href="#" @click="searchPage(prePage,search)" v-if="flag == false">上一页</a></li>
				<li class='page-item active' v-for="pages in pageNum" v-if="pages==page">
					<a class='page-link' id='page' @click="pageChange(pages)" href="#" v-if="flag">{{pages}}</a>
					<a class='page-link' id='page' @click="searchPage(pages,search)" href="#" v-else>{{pages}}</a>
				</li>
				<li class='page-item' v-else>
					<a class='page-link' id='page' @click="pageChange(pages)" href="#" v-if="flag">{{pages}}</a>
					<a class='page-link' id='page' @click="searchPage(pages,search)" href="#" v-else>{{pages}}</a>
				</li>
				
				<li class='page-item'><a class='page-link' id='page' href="#" @click="pageChange(nextPage)" v-if="flag == true">下一页</a></li>
				<li class='page-item'><a class='page-link' id='page' href="#" @click="searchPage(nextPage,search)" v-if="flag == false">下一页</a></li>
			</ul>
		</div>
	</div>

	<!-- 学院添加模态框 -->
	<div class="modal fade" id="college-add">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">添加学院</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="college-add-form" method="post">
						<div>
							<label for="college-name">学院ID:</label><input type="text"
								name="id" id="college-id">
						</div>
						<div>
							<label for="college-password">专业名称:</label><input type="text"
								name="name" id="college-name">
						</div>
					</form>
				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="college-add-input">提交</button>
				</div>

			</div>
		</div>
	</div>

	<!-- 学院修改权限模态框 -->
	<div class="modal fade" id="college-update">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">修改学院</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="college-update-form" method="post">
						<div>
							<label for="college-name">学院ID:</label><input type="text"
								name="id" id="college-id" readonly="readonly">
						</div>
						<div>
							<label for="college-password">学院名称:</label><input type="text"
								name="name" id="college-name">
						</div>
					</form>

				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="college-update-input">修改</button>
				</div>

			</div>
		</div>
	</div>
	
	
</body>
</html>
