
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>滴答办公系统-班级新增</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/media/layui/css/layui.css" media="all">
    <script type="text/javascript" src="/media/js/jquery.min.js"></script>
    <script type="text/javascript">
        // 新增班级
        function addClass(){
            var classNames = $("[name=className]").val();
            if(classNames==""){
                $("[name=className]").parent().prop("style","border:1px solid red");
                $("[name=className]").focus();
                return;
        }else{
                $("[name=className]").parent().prop("style","");
            }
            var majorId = $("[name=majorId]").val();
            if(majorId==-1){
                $("[name=majorId]").parent().prop("style","border:1px solid red");
                $("[name=majorId]").focus();
                return;
            }else{
                $("[name=majorId]").parent().prop("style","");
            }
            var classDate = $("[name=classDate]").val();
            if(classDate==""){
                $("[name=classDate]").parent().prop("style","border:1px solid red");
                $("[name=classDate]").focus();
                return;
            }else{
                $("[name=classDate]").parent().prop("style","");
            }
            var classTime = $("[name=classTime]").val();
            if(classTime==""){
                $("[name=classTime]").parent().prop("style","border:1px solid red");
                $("[name=classTime]").focus();
                return;
            }else{
                $("[name=classTime]").parent().prop("style","");
            }
            var classAddress = $("[name=classAddress]").val();
            if(classAddress==""){
                $("[name=classAddress]").parent().prop("style","border:1px solid red");
                $("[name=classAddress]").focus();
                return;
            }else{
                $("[name=classAddress]").parent().prop("style","");
            }
            $.post("/class_add",$("#ff").serialize(),function (result){
                if(result.status=100){
                    alert(result.message);
                }else{
                    alert(result.message);
                }
            },"json")
        }

    </script>
</head>
<body>
<div class="layui-container" style="margin-top: 5px">
    <form class="layui-form" action="/class_add" method="post" id="ff">
        <div class="layui-form-item">
            <label class="layui-form-label">班级名称</label>
            <div class="layui-input-block">
                <input type="text" name="className" lay-verify="name" autocomplete="off"
                       placeholder="请输入名称" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级学科</label>
            <div class="layui-input-block">
                <select name="majorId" id="cds">
                    <option value="-1">--请选择学科--</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开班日期</label>
            <div class="layui-input-block">
                <input type="text" name="classDate" id="date" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级周期</label>
            <div class="layui-input-block">
                <input type="text" name="classTime" lay-verify="name" autocomplete="off"
                       placeholder="请输入周期" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级地址</label>
            <div class="layui-input-block">
                <input type="text" name="classAddress" lay-verify="name" autocomplete="off"
                       placeholder="请输入地址" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <input class="layui-btn"  style="margin-left: 10%" onclick="addClass()" value="确认新增">
        </div>
    </form>
</div>


<script src="/media/layui/layui.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    var form;
    layui.use([ 'form', 'laydate' ],
        function() {
            form = layui.form, layer = layui.layer, laydate = layui.laydate;
            //日期
            laydate.render({
                elem : '#date'
            });
            initData(); //初始化数据  下拉框
        });

    //初始化数据
    function initData() {
        $.getJSON("/major_getNames",null,function(arr){
            for(i=0;i<arr.length;i++){
                $("#cds").append("<option value='"+arr[i].id+"'>"+arr[i].majorName+"</option>");
            }
            form.render("select"); //渲染下拉框
        })
    }

</script>
</body>
</html>