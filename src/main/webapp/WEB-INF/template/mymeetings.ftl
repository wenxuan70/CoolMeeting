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
                    个人中心 > 我的会议
                </div>
                <table class="listtable">
                    <caption>我将参加的会议：</caption>
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议预定时间</th>
                        <th>预定者</th>
                        <th>操作</th>
                    </tr>
                    <#if meetings??>
                        <#list meetings as m>
                            <tr>
                                <td>${m.meetingName}</td>
                                <td>${m.roomName}</td>
                                <td>${m.startTime?datetime}</td>
                                <td>${m.endTime?datetime}</td>
                                <td>${m.reservationistTime?datetime}</td>
                                <td>${m.reservationistName}</td>
                                <td>
                                    <a class="clickbutton" href="/meetingdetails?meetingId=${m.meetingId}">查看详情</a>
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