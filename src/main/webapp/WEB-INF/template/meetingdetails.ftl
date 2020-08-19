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
    <body onload="body_load()">
    <#include 'top.ftl'>
        <div class="page-body">
            <#include 'sidebar.ftl'>
            <div class="page-content">
                <div class="content-nav">
                    会议预定 > 修改会议预定
                </div>
                <form>
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>${meeting.meetingName}</td>
                            </tr>
                            <tr>
                                <td>预计参加人数：</td>
                                <td>${meeting.numberOfParticipants}</td>
                            </tr>
                            <tr>
                                <td>预计开始时间：</td>
                                <td>${meeting.startTime?datetime}</td>
                            </tr>
                            <tr>
                                <td>预计结束时间：</td>
                                <td>${meeting.endTime?datetime}</td>
                            </tr>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                    <textarea id="description" rows="5" readonly>${meeting.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>参会人员：</td>
                                <td>
                                    <table class="listtable">
                                        <tr class="listheader">
                                            <th>姓名</th>
                                            <th>联系电话</th>
                                            <td>电子邮件</td>
                                        </tr>
                                       <#if employees??>
                                           <#list employees as e>
                                               <tr>
                                                   <td>${e.employeeName}</td>
                                                   <td>${e.phone}</td>
                                                   <td>${e.email}</td>
                                               </tr>
                                           </#list>
                                       </#if>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td class="command" colspan="2">
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