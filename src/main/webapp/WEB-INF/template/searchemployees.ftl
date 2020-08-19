<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="/styles/common.css"/>
        <style type="text/css">
            
        </style>
    </head>
    <body>
        <#include 'top.ftl'>
        <div class="page-body">
            <#include 'sidebar.ftl'>
            <div class="page-content">
                <div class="content-nav">
                    会议预定 > 搜索员工
                </div>
                <form action="/admin/searchemployees">
                    <fieldset>
                        <legend>搜索会议</legend>
                        <table class="formtable">
                            <tr>
                                <td>姓名：</td>
                                <td>
                                    <input type="text" name="employeeName" id="employeename" maxlength="20"/>
                                </td>
                                <td>账号名：</td>
                                <td>
                                    <input type="text" name="username" id="accountname" maxlength="20"/>
                                </td>
                                <td>状态：</td>
                                <td>
                                    <input type="radio" id="status" name="status" value="1" checked="checked"/><label>已批准</label>
                                    <input type="radio" id="status" name="status" value="0"/><label>待审批</label>
                                    <input type="radio" id="status" name="status" value="-1"/><label>已关闭</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="submit" class="clickbutton" value="查询"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
                <div>
                    <h3 style="text-align:center;color:black">查询结果</h3>
                    <div class="pager-header">
                        <div class="header-info">
                            共<span class="info-number">${total!0}</span>条结果，
                            分成<span class="info-number">${pageNum!0}</span>页显示，
                            当前第<span class="info-number">${page!0}</span>页
                        </div>
                        <div class="header-nav">
                            <a class="clickbutton" href="/admin/searchemployees?status=${status!''}&page=1"/>首页</a>
                            <a class="clickbutton" href="/admin/searchemployees?status=${status!''}&page=${prev!1}"/>上页</a>
                            <a class="clickbutton" href="/admin/searchemployees?status=${status!''}&page=${next!1}"/>下页</a>
                            <a class="clickbutton" href="/admin/searchemployees?status=${status!''}&page=${pageNum!1}"/>末页</a>
                            <form action="/admin/searchemployees">
                                <#if status??><input hidden value="${status}"name="status"></#if>
                                跳到第<input type="text" name="page" id="pagenum" class="nav-number"/>页
                                <input type="submit" class="clickbutton" value="跳转"/>
                            </form>
                        </div>
                    </div>
                </div>
                <table class="listtable">
                    <tr class="listheader">
                        <th>姓名</th>
                        <th>账号名</th>
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>操作</th>
                    </tr>
                    <#if employees??>
                        <#list employees as employee>
                            <tr>
                                <td>${employee.employeeName}</td>
                                <td>${employee.username}</td>
                                <td>${employee.phone}</td>
                                <td>${employee.password}</td>
                                <td>
                                    <a class="clickbutton" href="/admin/closeAccount?employeeId=${employee.employeeId}">关闭账号</a>
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
</html>