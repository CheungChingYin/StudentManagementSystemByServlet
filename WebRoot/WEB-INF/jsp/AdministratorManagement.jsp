<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 引入css样式表 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/AdministratorManagement.css">

<!-- 引入JS文件 -->
<script
	src="${pageContext.request.contextPath }/Resources/js/AdministratorManagement.js"></script>
</head>

<body>
	<div class="admin-top">
		<div class="admin-title">
			<h2>管理员信息管理</h2>
		</div>
		<div class="admin-functionArea">
			<div class="admin-search input-group-lg">
				<input type="text" class="admin-search-bar" name="search"
					placeholder="请输入ID或者姓名">
				<button class="btn btn-secondary admin-search-button"
					id="admin-search-button">搜索</button>
			</div>
			<div>
				<button class="btn btn-primary admin-addadmindent"
					data-toggle="modal" data-target="#admin-add" id="admin-add-button">添加管理员</button>
			</div>

		</div>
	</div>
	<!-- 管理员列表 -->
	<div class="content" id='content'>
		<table class="table table-striped table-hover" id="table-content">
			<thead>
				<tr>
					<th>编号ID</th>
					<th>姓名</th>
					<th>权限</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="admin in adminList">
					<td>{{admin.id}}</td>
					<td>{{admin.user}}</td>
					<td>{{admin.permission==0?'普通管理员':'超级管理员'}}</td>
					<td><button class='btn btn-primary admin-alert' v-bind:value="admin.id" data-toggle='modal' data-target='#admin-update' id='admin-update-button'>修改权限</button>
					<button type="button" id='admin-alertPassword-button' class="btn btn-secondary admin-alertPassword-button" v-bind:value="admin.id" data-toggle='modal' data-target='#admin-update-password'>修改密码</button>
					<button id='admin-delete-button' class='btn btn-danger admin-delete' v-bind:value="admin.id">删除</button></td>
				</tr>
			</tbody>
		</table>
		<div class="admin-total" id="admin-total">共有{{allAdminCount}}条信息</div>
		<!-- 分页功能 -->
		<div class="pageNav">
			<ul class="pagination">
				<li class='page-item'><a class='page-link' id='page' href="#" @click="pageChange(prePage)">上一页</a></li>
				<li class='page-item active' v-for="pages in pageNum" v-if="pages==page">
					<a class='page-link' id='page' @click="pageChange(pages)" href="#">{{pages}}</a>
				</li>
				<li class='page-item' v-else>
					<a class='page-link' id='page' @click="pageChange(pages)" href="#">{{pages}}</a>
				</li>
				
				<li class='page-item'><a class='page-link' id='page' href="#" @click="pageChange(nextPage)">下一页</a></li>
			</ul>
		</div>
	</div>

	<!-- 管理员添加模态框 -->
	<div class="modal fade" id="admin-add">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">添加管理员</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="admin-add-form" method="post">
						<div>
							<label for="admin-name">管理员姓名:</label><input type="text"
								name="user" id="admin-name">
						</div>
						<div>
							<label for="admin-password">管理员密码:</label><input type="password"
								name="password" id="admin-password">
						</div>
						<div>
							<label for="admin-sex">管理员权限:</label> <select
								id="admin-permission" name="permission">
								<option value="">---请选择---</option>
								<option value="0">普通管理员</option>
								<option value="1">超级管理员</option>
							</select>
						</div>
					</form>
				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="admin-add-input">提交</button>
				</div>

			</div>
		</div>
	</div>

	<!-- 管理员修改权限模态框 -->
	<div class="modal fade" id="admin-update">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">更新管理员权限</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="admin-update-form" method="post">
						<div>
							<label for="admin-id">管理员ID:</label><input type="text" name="id"
								id="admin-id" readonly="readonly">
						</div>
						<div>
							<label for="admin-name">管理员姓名:</label><input type="text"
								name="user" id="admin-name" readonly="readonly">
						</div>
						<div>
							<label for="admin-sex">管理员权限:</label> <select
								id="admin-permission" name="permission">
								<option value="">---请选择---</option>
								<option value="0">普通管理员</option>
								<option value="1">超级管理员</option>
							</select>
						</div>
					</form>

				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="admin-update-input">修改</button>
				</div>

			</div>
		</div>
	</div>
	
	<!-- 管理员修改密码模态框 -->
	<div class="modal fade" id="admin-update-password">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">更新管理员密码</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="admin-update-password-form" method="post">
						<div>
							<label for="admin-id">管理员ID:</label><input type="text" name="id"
								id="admin-id" readonly="readonly">
						</div>
						<div>
							<label for="admin-name">管理员姓名:</label><input type="text"
								name="user" id="admin-name" readonly="readonly">
						</div>
						<div>
							<label for="admin-password">管理员密码:</label><input type="password" name="password" id="admin-password"> 
						</div>
						<div><label for="admin-password-confirm">再次确认密码:</label><input type="password" name="password-confirm" id="admin-password-confirm"></div>
					</form>

				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="admin-update-password-input">修改</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
