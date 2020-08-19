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
                    会议预定 > 搜索会议
                </div>
                <form action="/searchmeetings" method="get">
                    <fieldset>
                        <legend>搜索会议</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" name="meetingName" maxlength="20"/>
                                </td>
                                <td>会议室名称：</td>
                                <td>
                                    <input type="text" name="roomName" maxlength="20"/>
                                </td>
                                <td>预定者姓名：</td>
                                <td>
                                    <input type="text" name="resName" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>预定日期：</td>
                                <td colspan="5">
                                    从&nbsp;<input type="date" name="startTimeFirst" placeholder="例如：2013-10-20" />
                                    到&nbsp;<input type="date" name="startTimeSecond" placeholder="例如：2013-10-22" />
                                </td>
                            </tr>
                            <tr>
                                <td>会议日期：</td>
                                <td colspan="5">
                                    从&nbsp;<input type="date" name="endTimeFirst" placeholder="例如：2013-10-20"/>
                                    到&nbsp;<input type="date" name="endTimeSecond" placeholder="例如：2013-10-22"/>
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
                            共<span class="info-number">${total}</span>条结果，
                            分成<span class="info-number">${pageNum}</span>页显示，
                            当前第<span class="info-number">${page}</span>页
                        </div>
                        <div class="header-nav">
                            <a class="clickbutton" href="/searchmeetings?page=1<#if urlQuery??>&${urlQuery}</#if>">首页</a>
                            <a class="clickbutton" href="/searchmeetings?page=${prev}<#if urlQuery??>&${urlQuery}</#if>">上页</a>
                            <a class="clickbutton" href="/searchmeetings?page=${next}<#if urlQuery??>&${urlQuery}</#if>">下页</a>
                            <a class="clickbutton" href="/searchmeetings?page=${pageNum}<#if urlQuery??>&${urlQuery}</#if>">末页</a>
                            <form action="/searchmeetings" method="get">
                                跳到第<input type="number" name="page" min="1" <#if pageNum??>max="${pageNum}"</#if> class="nav-number" required/>页
                                <input type="submit" class="clickbutton" value="跳转"/>
                            </form>
                        </div>
                    </div>
                </div>
                <table class="listtable">
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