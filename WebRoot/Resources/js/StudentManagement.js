/*
 * 点击分页按钮
 * 通过点击分页按钮，让分页的内容刷新在Main(即Contain)中
 */
$(function(){
	$(".page-item a").click(function(){
		var address = $(this).attr("href");
		$('#contain').load(address);
		return false;
	});
});

/*
 * 添加按钮点击事件
 * 使用Ajax获得相对应的学院，并且添加到学院的<select>控件中
 */
$("#stu-add-button").click(function(){
	$.get("getCollege",function(data,status){
		var college = data;
		var res = "";
		for(var i = 0 ;i<college.length;i++){
			res += "<option value='"+college[i].id+"'>"+college[i].name+"</option>";
		}
		$("#college").append(res);
	});
});

/*
 * 学院按钮改变时
 * 当选中学院下拉菜单中的一项时，通过Ajax加载相对应的学院专业
 */
$("#college").change(function(){
	var collegeId = $(this).val();
	$("#major option:not(:first)").remove();
	$.get("getMajor?collegeId="+collegeId,function(data,status){
		var major = data;
		var res = "";
		for(var i = 0;i<major.length;i++){
			res += "<option value='"+major[i].id+"'>"+major[i].name+"</option>";
		}
		$("#major").append(res);
	});
});

/*
 * 添加学生表单的表单验证
 * 使用了第三方插件 nice-validator 进行表单的前端验证，只有验证通过才能使用Ajax提交表单
 */
$("#stu-add-form").validator({
	rules:{
		//自定义规则
		idType:[/^\d+/,"学生学号必须为纯数字"],
		idLength:[/^\d{15}$/,"学生学号长度必须为15位"],
		nameValidate:[/[\u4e00-\u9fa5_a-zA-Z_]+/,"学生姓名必须是中文或者英文"],
	},
	fields:{
		//表单应用规则
		'id':"required;idType;idLength",
		'name':"required;nameValidate",
		'sex':"required",
		'birth':"required",
		'schoolDay':"required",
		'major_id':"required",
		'college_id':"required"
	},
	//验证通过后需要执行的方法
	valid:$("#stu-add-input").click(function(){
		
		$.post("addStudent",$("#stu-add-form").serialize(),function(data,status){
			if(status == "success"){
				alert("添加成功!");
				$("#stu-add").modal('hide');
				$(".modal-backdrop").remove();
				$('#contain').load($(".page-item a").attr("href"));
			}else{
				alert("服务器出现未知错误，添加失败!");
			}
		});
	})
});



//检验姓名中文或者英文
jQuery.validator.addMethod("nameValidate",function(value,element,param){
	return this.optional(element) || /[\u4e00-\u9fa5_a-zA-Z_]+$/.test(value);
},$.validator.format("请输入中文或者英文"));