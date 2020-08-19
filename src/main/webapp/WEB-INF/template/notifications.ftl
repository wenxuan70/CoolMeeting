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
                    个人中心 > <a href="notifications">最新通知</a>
                </div>
                <table class="listtable">
                    <caption>
                        未来我要参加的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th style="width:100px">操作</th>
                    </tr>
                    <#if todoMeeting??>
                        <#list todoMeeting as m>
                            <tr>
                                <td>${m.meetingName}</td>
                                <td>${m.roomName}</td>
                                <td>${m.startTime?datetime}</td>
                                <td>${m.endTime?datetime}</td>
                                <td>
                                    <a class="clickbutton" href="/meetingdetails?meetingId=${m.meetingId}">查看详情</a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                </table>
                <table class="listtable">
                    <caption>
                        已取消的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th>取消原因</th>
                        <th style="width:100px">操作</th>
                    </tr>
                    <#if canceledMeeting??>
                        <#list canceledMeeting as cm>
                            <tr>
                                <td>${cm.meetingName}</td>
                                <td>${cm.roomName}</td>
                                <td>${cm.startTime?datetime}</td>
                                <td>${cm.endTime?datetime}</td>
                                <td>${cm.canceledReason}</td>
                                <td>
                                    <a class="clickbutton" href="/meetingdetails?meetingId=${cm.meetingId}">查看详情</a>
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