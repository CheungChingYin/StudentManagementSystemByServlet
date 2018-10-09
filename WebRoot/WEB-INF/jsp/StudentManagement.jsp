<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'StudentManagement.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 引入css样式表 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/StudentManagement.css">

<!-- 引入JS文件 -->
<script
	src="${pageContext.request.contextPath }/Resources/js/StudentManagement.js"></script>

</head>

<body>
	<div class="stu-top">
		<div class="stu-title">
			<h2>学生学籍管理</h2>
		</div>
		<div class="stu-functionArea">
			<div class="stu-search input-group-lg">
				<input type="text" class="stu-search-bar" name="search"
					placeholder="请输入学号或者姓名">
				<button class="btn btn-secondary stu-search-button">搜索</button>
			</div>
			<div>
				<button class="btn btn-primary stu-addStudent" data-toggle="modal"
					data-target="#stu-add" id="stu-add-button">添加学生学籍</button>
			</div>

		</div>
	</div>
	<div class="content">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>出生日期</th>
					<th>入学日期</th>
					<th>所属专业</th>
					<th>所属学院</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${requestScope.studentList }">
					<tr>
						<td>${student.id }</td>
						<td>${student.name }</td>
						<td><c:if test="${student.sex == 0 }" var="女">女</c:if> <c:if
								test="${student.sex == 1 }" var="男">男</c:if></td>
						<td>${student.birth }</td>
						<td>${student.schoolDay }</td>
						<td>${student.majorName }</td>
						<td>${student.collegeName }</td>
						<td><button class="btn btn-primary stu-alert" value="">修改</button>
							<button class="btn btn-danger stu-delete" value="">删除</button></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<div class="stu-total">共${requestScope.allStudentCount }条信息</div>
		<div class="pageNav">
			<ul class="pagination">
				<li class="page-item" value=""><a class="page-link"
					href="${pageContext.request.contextPath }/StudentManagement?page=${requestScope.prePage}">上一页</a></li>
				<c:forEach var="i" items="${requestScope.pageNum }">
					<c:choose>
						<c:when test="${requestScope.page ==i }">
							<li class='page-item active' value=""><a class="page-link"
								href="${pageContext.request.contextPath }/StudentManagement?page=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class='page-item' value=""><a class="page-link"
								href="${pageContext.request.contextPath }/StudentManagement?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li class="page-item" value=""><a class="page-link"
					href="${pageContext.request.contextPath }/StudentManagement?page=${requestScope.nextPage}">下一页</a></li>
			</ul>
		</div>
	</div>

	<!-- 学生添加模态框 -->
	<div class="modal fade" id="stu-add">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">添加学生资料</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="stu-add-form" method="post">
						<div>
							<label for="stu-id">学生学号:</label><input type="text" name="id"
								id="stu-id">
						</div>
						<div>
							<label for="stu-name">学生姓名:</label><input type="text" name="name"
								id="stu-name">
						</div>
						<div>
							<label for="stu-sex">学生性别:</label> <select id="stu-sex"
								name="sex">
								<option value="">---请选择---</option>
								<option value="0">女</option>
								<option value="1">男</option>
							</select>
						</div>
						<div>
							<label for="birth">出生日期:</label><input type="date" name="birth"
								id="birth">
						</div>
						<div>
							<label for="schoolDay">入学日期:</label><input type="date"
								name="schoolDay" id="schoolDay">
						</div>
						<div>
							<label for="major">所属专业:</label><select id="major"
								name="major_id"><option selected="selected" value="">---请选择---</option></select>
						</div>
						<div>
							<label for="college">所属专业:</label><select id="college"
								name="college_id"><option selected="selected" value="">---请选择---</option></select>
						</div>
					</form>

				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="stu-add-input">提交</button>
					<button class="btn btn-secondary" id="stu-add-reset">清空</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
