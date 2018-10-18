<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 引入css样式表 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/MajorManagement.css">

<!-- 引入JS文件 -->
<script
	src="${pageContext.request.contextPath }/Resources/js/MajorManagement.js"></script>
</head>

<body>
	<div class="major-top">
		<div class="major-title">
			<h2>管理员信息管理</h2>
		</div>
		<div class="major-functionArea">
			<div class="major-search input-group-lg">
				<input type="text" class="major-search-bar" name="search"
					placeholder="请输入专业ID或者专业名">
				<button class="btn btn-secondary major-search-button"
					id="major-search-button" @click="searchFunction()">搜索</button>
			</div>
			<div>
				<button class="btn btn-primary major-addmajordent"
					data-toggle="modal" data-target="#major-add" id="major-add-button">添加专业</button>
			</div>

		</div>
	</div>
	<!-- 专业列表 -->
	<div class="content" id='content'>
		<table class="table table-striped table-hover" id="table-content">
			<thead>
				<tr>
					<th>专业ID</th>
					<th>专业名称</th>
					<th>所属学院</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="major in majorList">
					<td>{{major.id}}</td>
					<td>{{major.name}}</td>
					<td>{{major.collegeName}}</td>
					<td><button class='btn btn-primary major-alert' v-bind:value="major.id" data-toggle='modal' data-target='#major-update' id='major-update-button'>修改</button>
					<button id='major-delete-button' class='btn btn-danger major-delete' v-bind:value="major.id">删除</button></td>
				</tr>
			</tbody>
		</table>
		<div class="major-total" id="major-total">共有{{allMajorCount}}条信息</div>
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

	<!-- 专业添加模态框 -->
	<div class="modal fade" id="major-add">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">添加专业</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="major-add-form" method="post">
						<div>
							<label for="major-name">专业ID:</label><input type="text"
								name="id" id="major-id">
						</div>
						<div>
							<label for="major-password">专业名称:</label><input type="text"
								name="name" id="major-name">
						</div>
						<div>
							<label for="major-college">所属专业:</label> <select
								id="major-college" name="college_id">
								<option value="">---请选择---</option>
							</select>
						</div>
					</form>
				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="major-add-input">提交</button>
				</div>

			</div>
		</div>
	</div>

	<!-- 专业修改权限模态框 -->
	<div class="modal fade" id="major-update">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">专业修改</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="major-update-form" method="post">
						<div>
							<label for="major-name">专业ID:</label><input type="text"
								name="id" id="major-id" readonly="readonly">
						</div>
						<div>
							<label for="major-password">专业名称:</label><input type="text"
								name="name" id="major-name">
						</div>
						<div>
							<label for="major-college">所属专业:</label> <select
								id="major-college" name="college_id">
								<option value="">---请选择---</option>
							</select>
						</div>
					</form>

				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="major-update-input">修改</button>
				</div>

			</div>
		</div>
	</div>
	
	
</body>
</html>
