<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="/styles/common.css"/>
    </head>
    <body>
        <#include 'top.ftl'>
        <div class="page-body">
            <#include 'sidebar.ftl'>
            <div class="page-content">
                <div class="content-nav">
                    修改密码
                </div>
                <form action="/doChangePassword" method="post">
                    <fieldset>
                        <legend>修改密码信息</legend>
                        <#if error??><div style="color: red;">${error}</div></#if>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>原密码:</td>
                                <td>
                                    <input id="origin" name="oldPassword" type="password" />
                                </td>
                            </tr>
                            <tr>
                                <td>新密码:</td>
                                <td>
                                    <input id="new" name="newPassword" type="password" onchange="check()"/>
                                </td>
                            </tr>
                            <tr>
                                <td>确认新密码：</td>
                                <td>
                                    <input id="confirm" type="password" onchange="check()"/>
                                    <div id="confirmInfo" style="color: red;"></div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="submit" value="确认修改" class="clickbutton"/>
                                    <input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="/images/footer.png" alt="CoolMeeting"/>
        </div>
    <script>
        function check() {
            let password = document.getElementById("new"),
                confirm = document.getElementById("confirm"),
                confirmInfo = document.getElementById("confirmInfo");
            if (password.value != confirm.value) {
                confirmInfo.innerHTML = "两次输入的密码不一致";
            } else {
                confirmInfo.innerHTML = "";
            }
        }
    </script>
    </body>
</html>