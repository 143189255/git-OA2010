<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%--
  Created by IntelliJ IDEA.
  User: 张罡逢
  Date: 2020/11/9
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>滴答办公系统-员工列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/media/layui/css/layui.css" media="all">
    <script src="/media/js/jquery.min.js"></script>
    <script type="text/javascript">
        /*
        动态修改页码大小
         */
        function goPage(select){
            var pageSize=$(select).val();
            location.href="/emp_list/1/"+pageSize;
        }
        //跳转页面
        function jumpPage() {
            var number = $("#number").val();
            if(!(/(^[1-9]\d*$)/.test(number))){
                alert("输入的字符非法");
                $("#number").focus();
                $("#number").val("");
            }else if(number>=1&&number<=${pageUtils.pageCount}){
                location.href="/emp_list/"+number+"/${pageUtils.pageSize}";
            }else{
                alert("输入的页码必须在1-${pageUtils.pageCount}");
                $("#number").focus();
                $("#number").val("");
            }

        }
        function updateDepart(id) {
            $.getJSON("/emp_update_id",{"id":id},function (result) {
                if(result.code=100){
                    alert(result.message);
                }else{
                    alert(result.message);
                }

            },"json")

        }
    </script>
</head>
<body>
<div class="layui-container">
    <table class="layui-table" id="tbdata" lay-filter="tbop">
        <thead>
        <tr>
            <td>工号</td>
            <td>姓名</td>
            <td>部门</td>
            <td>性别</td>
            <td>手机号</td>
            <td>QQ号</td>
            <td>邮箱</td>
            <td>入职日期</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageUtils.records}" var="emp">
        <tr>
            <td>${emp.no}</td>
            <td>${emp.name}</td>
            <td>${emp.depart.name}</td>
            <td>${emp.sex}</td>
            <td>${emp.phone}</td>
            <td>${emp.qq}</td>
            <td>${emp.email}</td>
            <td>${emp.cratedate}</td>
            <td>
                <shiro:hasPermission name="emp:update">
                <a class="layui-btn layui-btn-mini" href="/empupdate_${emp.id}">编辑</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="emp:delete">
                <a class="layui-btn layui-btn-danger layui-btn-mini"
                   lay-event="del" onclick="deleteCourse(${emp.id});">删除</a>
                </shiro:hasPermission>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
        <c:if test="${pageUtils.pageIndex==1}">
        <a href="#" class="layui-laypage-prev ${pageUtils.pageIndex==1?"layui-disabled":''}" data-page="0">
            <i class="layui-icon">&lt;</i>
     </c:if>
     <c:if test="${pageUtils.pageIndex!=1}">
         <a href="/emp_list/${pageUtils.pageIndex-1}/${pageUtils.pageSize}" class="layui-laypage-next " data-page="2">
             <i class="layui-icon">&lt;</i>
         </a>
     </c:if>

        <c:forEach begin="${pageUtils.numberStart}" end="${pageUtils.numberEnd}" step="1" var="num">
            <c:if test="${pageUtils.pageIndex==num}">
                <span style="color:#009688;font-weight:bold">${num}</span>
            </c:if>
            <c:if test="${pageUtils.pageIndex!=num}">
                <a href="/emp_list/${num}/${pageUtils.pageSize}">${num}</a>
            </c:if>

        </c:forEach>

        <c:if test="${pageUtils.pageIndex==pageUtils.pageCount}">
        <a href="#" class="layui-laypage-next ${pageUtils.pageIndex==pageUtils.pageCount?"layui-disabled":''} " data-page="2">
            <i class="layui-icon">&gt;</i>
        </c:if>
            <c:if test="${pageUtils.pageIndex!=pageUtils.pageCount}">
            <a href="/emp_list/${pageUtils.pageIndex+1}/${pageUtils.pageSize}" class="layui-laypage-next " data-page="2">
                <i class="layui-icon">&gt;</i>
            </c:if>

        </a>
        <span class="layui-laypage-skip">到第
							   <input type="text" min="1" value="${pageUtils.pageIndex}" class="layui-input" id="number">页
								<button type="button" class="layui-laypage-btn" onclick="jumpPage()">确定</button>
							</span>
        <span class="layui-laypage-count">共 ${pageUtils.totalCount} 条</span>
        <span class="layui-laypage-limits">当前${pageUtils.pageIndex}/${pageUtils.pageCount}页
							    <select lay-ignore=""onchange="goPage(this)">
							        <option value="5" ${pageUtils.pageSize==5?"selected":""}>5 条/页</option>
									<option value="10" ${pageUtils.pageSize==10?"selected":""}>10 条/页</option>
									<option value="20" ${pageUtils.pageSize==20?"selected":""}>20 条/页</option>
									<option value="30" ${pageUtils.pageSize==30?"selected":""}>30 条/页</option>
									<option value="40" ${pageUtils.pageSize==40?"selected":""}>40 条/页</option>
									<option value="50" ${pageUtils.pageSize==50?"selected":""}>50 条/页</option>
							</select>
							</span>
    </div>
</div>

<script src="/media/layui/layui.js"></script>

<script type="text/javascript">
    function deleteCourse(id){
        layui.use('table', function() {
            layer.confirm('是否确认删除员工?',function(index) {
                $.getJSON("/emp_delete",{"id":id}, function (result) {
                    //  alert(result.code+result.message);
                    if(result.status=100){
                        layer.msg("删除成功", {icon : 5},function () {
                            window.location.reload();
                        });
                    }else{
                        layer.msg("删除失败", {icon : 6});
                    }
                });

            });
        });
    }
</script>

</body>
</html>