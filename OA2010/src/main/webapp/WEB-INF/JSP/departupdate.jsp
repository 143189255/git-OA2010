
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>滴答办公系统-部门更新</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/media/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-container" style="margin-top: 5px">
    <form class="layui-form" action="#" id="ff" method="post">

        <div class="layui-form-item">
            <label class="layui-form-label">部门名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="name" autocomplete="off"
                       placeholder="请输入新部门名称" class="layui-input">
                <input type="hidden" name="id" value="${sessionScope.departUpdate}">
            </div>
        </div>

            <div class="layui-form-item">
                <label class="layui-form-label">部门主管</label>
                <div class="layui-input-block">
                    <input type="text" name="manager" lay-verify="name" autocomplete="off"
                           placeholder="请输入新的部门主管" class="layui-input">
                </div>
            </div>
        <div class="layui-form-item">
            <label class="layui-form-label">创立日期</label>
            <div class="layui-input-block">
                <input type="text" name="createtime" placeholder="请输入新的创立日期" id="date" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <input class="layui-btn" style="margin-left: 10%" type="button" value="确认更新" onclick="departUpdate()">
        </div>
    </form>
</div>

<script type="text/javascript" src="/media/js/jquery.min.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script src="/media/layui/layui.js"></script>
<script>
    layui.use(
        [ 'form', 'laydate' ],
        function() {
            var form = layui.form, layer = layui.layer, laydate = layui.laydate;

            //日期
            laydate.render({
                elem : '#date'
            });


            //自定义验证规则
            form.verify({
                title : function(value) {
                    if (value.length < 5) {
                        return '标题至少得5个字符啊';
                    }
                },
                pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
                content : function(value) {
                    layedit.sync(editIndex);
                }
            });
        });
    function departUpdate(){
            $.post("/depart_update", $("#ff").serialize(), function (result) {

                if (result.status == 100) {
                    alert(result.message);
                    window.location.href="/depart_list/1/5"
                } else
                    alert(result.message);
            }, "json")
        }
</script>
</body>
</html>
