<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'Ajax_Main.jsp' starting page</title>

<!-- 新 Bootstrap4 核心 CSS 文件 -->
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

<!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>

<!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<!-- 导入前端数据校验插件 -->
<script	src="https://cdn.staticfile.org/nice-validator/1.1.4/jquery.validator.min.js?local=zh-CN"></script>

<!-- 引入css文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/Ajax_Main.css">
	

<script
	src="${pageContext.request.contextPath }/Resources/js/Ajax_Main.js"></script>

</head>

<body>
	<div class="top">
		<h2>您好！${sessionScope.admin}管理员，欢迎使用学生学籍管理系统</h2>
	</div>
	<div class="bottom">
		<div class="index">
			<ul class="nav nav-pills flex-column nav-justified">
				<li class="nav-item"><a class="nav-link active"
					href="${pageContext.request.contextPath }/StudentManagement?page=1">学生管理</a></li>
				<li class="nav-item"><a class="nav-link" href="#">管理员管理</a></li>
				<li class="nav-item"><a class="nav-link" href="#">专业管理</a></li>
				<li class="nav-item"><a class="nav-link" href="#">学院管理</a></li>
				<li class="nav-item"><a class="nav-link" href="#">退出登录</a></li>
			</ul>
		</div>
		<div class="contain" id="contain"></div>
	</div>

</body>
</html>
