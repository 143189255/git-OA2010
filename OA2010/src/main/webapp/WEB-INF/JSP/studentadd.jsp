<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>滴答办公系统-学员新增</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
	<%--${pageContext.request.contextPath}作用是取出部署的应用程序名或者是当前的项目名称--%>
    <%--是绝对路径--%>
	<script>
	       var form;
		   layui.use(
					[ 'form','upload', 'layedit', 'laydate' ],
					function() {
						form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;
						var upload = layui.upload;  //初始化文件上传组件
						  //执行实例
						  var uploadInst = upload.render({
						     elem: '#upfile' //绑定元素
						    ,url: '${pageContext.request.contextPath}/emp/uploade' //上传接口地址
						    ,done: function(obj){
						      //上传完毕回调
						      // console.log(obj); 
						      if(obj.code==200){
						    	  $("#p1").val(obj.msg); //图片路径 新增有用
							      $("#img1")[0].src="${pageContext.request.contextPath}/"+obj.msg;
						      }else{
						    	  //上传失败默认图片
						    	  $("#img1")[0].src="${pageContext.request.contextPath}/media/images/cw1.jpg";
						      }
						      
						    }
						    ,error: function(){
						      //请求异常回调
						    }
						  });
						//日期
						laydate.render({
							elem : '#date1'
						});
						laydate.render({
							elem : '#date2'
						});
						initData();
		      });
           //初始化数据
           function initData() {
               $.getJSON("/tclass_getAllClasses", null, function (arr) {
                   for (i = 0; i < arr.length; i++) {
                       $("#cds").append("<option value='" + arr[i].id + "'>" + arr[i].className + "</option>");
                   }
                   //渲染下拉框
                   form.render("select");
               });
           }
          function stuAdd(){
              var no = $("[name=no]").val();
              if(no=="") {
                  $("[name=no]").parent().prop("style", "border:1px solid red");
                  $("[name=no]").focus();
                  return;
              }
               $.post("/student_insert",$("#ff").serialize(),function (result) {
				   if(result.status==100){
				       alert(result.message);
				       location.href="/student_list/1/5";
				   }else{
                       alert(result.message);
				   }
               },"json")
		  }
	</script>
</head>
<body>
	<div class="layui-container" style="margin-top: 5px">
		<%--enctype="multipart/form-data参数用于文件上传，意思是由有多种类型的数据上传--%>
		<form class="layui-form" id="ff" action="${pageContext.request.contextPath}/student_insert" method="post" enctype="multipart/form-data">
			<div class="layui-form-item">
				<label class="layui-form-label">学号</label>
				<div class="layui-input-block">
					<input type="text" name="no" lay-verify="required" autocomplete="off"
						placeholder="请输入学号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学员姓名</label>
				<div class="layui-input-block">
					<input type="text" name="name" lay-verify="name" autocomplete="off"
						placeholder="请输入姓名" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
 				 <label class="layui-form-label">头像</label>
 				 <button type="button" class="layui-btn" id="upfile" name="file">
				  <i class="layui-icon">&#xe67c;</i>上传图片
				</button>
				<input type="hidden" name="photo" id="p1">
 				 <div class="layui-input-block">
 				 	<img alt="个人一寸照片"  id="img1" width="100px" height="100px">
 				 </div>
 			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">所属班级</label>
				<div class="layui-input-block">
					<select name="classId" id="cds">
						<option value="-1" >--请选择班级--</option>

					</select>
				</div>

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别</label>
				<div class="layui-input-block">
					<input type="radio" name="sex" value="男" title="男" checked>
					<input type="radio" name="sex" value="女" title="女">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-inline">
					<input type="text" name="email" lay-verify="required"
						placeholder="请输入有效邮箱地址" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">手机号</label>
				<div class="layui-input-inline">
					<input type="text" name="phone" lay-verify="required"
						placeholder="请输入手机号" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">QQ</label>
				<div class="layui-input-inline">
					<input type="text" name="qq" lay-verify="required"
						placeholder="请输入QQ" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">身份证号</label>
				<div class="layui-input-inline">
					<input type="text" name="cardno" lay-verify="required"
						placeholder="请输入身份证号" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">毕业学校</label>
				<div class="layui-input-inline">
					<input type="text" name="school" lay-verify="required"
						placeholder="请输入毕业学校" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学历</label>
				<div class="layui-input-inline">
					<select name="education">
						<option>初中</option>
						<option>高中</option>
						<option>专科</option>
						<option>本科</option>
						<option>硕士</option>
						<option>博士</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">出生日期</label>
				<div class="layui-input-inline">
					<input type="text" name="birthday" id="date1" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">入学日期</label>
				<div class="layui-input-inline">
					<input type="text" name="createdate" id="date2" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<input class="layui-btn"  style="margin-left: 10%" id="btn1" type="button" onclick="stuAdd()"
					value="确认新增">
			</div>
		</form>
	</div>


	
</body>
</html>