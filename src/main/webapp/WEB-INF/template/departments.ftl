<!DOCTYPE html>
<html>
<head>
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="/styles/common.css"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<#include 'top.ftl'>
<div class="page-body">
    <#include 'sidebar.ftl'>
    <div class="page-content">
        <div class="content-nav">
            人员管理 > 部门管理
        </div>
        <form action="/admin/addDepartment" method="post">
            <fieldset>
                <legend>添加部门</legend>
                部门名称:
                <input type="text" name="departmentName" id="departmentname" maxlength="20"/>
                <input type="submit" class="clickbutton" value="添加"/>
                <#if error??>
                    <div style="color: red">${error}</div>
                </#if>
            </fieldset>
        </form>
        <table class="listtable">
            <caption>所有部门:</caption>
            <tr class="listheader">
                <th>部门编号</th>
                <th>部门名称</th>
                <th>操作</th>
            </tr>
            <#if deps??>
                <#list deps as dep>
                    <tr>
                        <td>${dep.departmentId}</td>
                        <td id="depName${dep.departmentId}" data-name="${dep.departmentName}">${dep.departmentName}</td>
                        <td>
                            <a class="clickbutton" id="edit${dep.departmentId}" onclick="editDep(${dep.departmentId})"
                               href="#">编辑</a>
                            <a class="clickbutton" style="display: none;" id="cancel${dep.departmentId}"
                               onclick="cancel(${dep.departmentId}, '${dep.departmentName}')">取消</a>
                            <a class="clickbutton" href="/admin/deleteDep?departmentId=${dep.departmentId}">删除</a>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</div>
<div class="page-footer">
    <hr/>
    更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
    <img src="/images/footer.png" alt="CoolMeeting"/>
</div>
</body>
<script>
    function editDep(depId) {
        let cancelBtn = $("#cancel" + depId),
            editBtn = $("#edit" + depId),
            ele = $("#depName" + depId);
        if (cancelBtn.css("display") === "none") {
            cancelBtn.css("display", "inline");
            editBtn.html("确认");
            ele.html("<input type='text' value='" + ele.html() + "'/>");
        } else {
            let depName = $(ele.children("input")).val();
            if (depName.length === 0) {
                alert("部门名称不能为空");
            } else {
                $.post("/admin/updateDepartment", {
                    departmentId: depId,
                    departmentName: depName
                }, function (e) {
                    alert(e.message);
                    if (e.code === 1) {
                        editBtn.html("编辑");
                        ele.attr("data-name",depName);
                        ele.html(depName);
                        cancelBtn.css("display", "none");
                    }
                });
            }
        }
    };

    function cancel(depId) {
        let cancelBtn = $("#cancel" + depId),
            editBtn = $("#edit" + depId),
            ele = $("#depName" + depId);
        ele.html(ele.attr("data-name"));
        cancelBtn.css("display", "none");
        editBtn.html("编辑");
    }
</script>
</html>