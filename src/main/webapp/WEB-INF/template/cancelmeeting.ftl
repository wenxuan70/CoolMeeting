<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="/styles/common.css"/>
        <style type="text/css">
            #divoperator input[type="button"]{
                margin:10px 0;
            }
        </style>
    </head>
    <body>
    <#include 'top.ftl'>
        <div class="page-body">
            <#include 'sidebar.ftl'>
            <div class="page-content">
                <div class="content-nav">
                    会议预定 > 撤销会议预定
                </div>
                <form action="/doCancelMeeting" method="post">
                    <fieldset>
                        <legend>撤销预定</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>${meeting.meetingName}</td>
                            </tr>
                            <tr>
                                <td>预计开始时间：</td>
                                <td>${meeting.startTime?datetime}</td>
                            </tr>
                            <tr>
                                <td>撤销理由：</td>
                                <td> <textarea name="cancelReason" rows="5" maxlength="200" required></textarea></td>
                            </tr>
                            <tr>
                                <td class="command" colspan="2">
                                    <input hidden name="meetingId" value="${meeting.meetingId}">
                                    <input type="submit" class="clickbutton" value="确认撤销"/>
                                    <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
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
    </body>
</html>