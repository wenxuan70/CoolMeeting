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
            会议预定 > 修改会议室信息
        </div>
        <form method="post" action="/updateMeetingRoom">
            <fieldset>
                <legend>会议室信息</legend>
                <#if error??><div style="color: red;">${error}</div></#if>
                <table class="formtable">
                    <tr>
                        <td>门牌号:</td>
                        <td>
                            <input name="roomNum" type="text" <#if mr??>value="${mr.roomNum!''}"</#if> maxlength="10"/>
                        </td>
                    </tr>
                    <tr>
                        <td>会议室名称:</td>
                        <td>
                            <input name="roomName" type="text" <#if mr??>value="${mr.roomName!''}"</#if>
                                   maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td>最多容纳人数：</td>
                        <td>
                            <input name="capacity" type="text" <#if mr??>value="${mr.capacity!''}"</#if>/>
                        </td>
                    </tr>
                    <tr>
                        <td>当前状态：</td>
                        <td>
                            <#list ['启用','停用'] as statusName>
                                <input type="radio" name="status"
                                       <#if (mr??)&&(mr.status==statusName_index)>checked</#if>
                                       value="${statusName_index}"/>
                                <label for="status">
                                    ${statusName}
                                </label>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>备注：</td>
                        <td>
                            <textarea name="description" maxlength="200" rows="5"
                                      cols="60"><#if mr??>${mr.description!''}</#if></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="command">
                            <input type="hidden" name="roomId" <#if mr??>value="${mr.roomId}"</#if>/>
                            <input type="submit" value="确认修改" class="clickbutton"/>
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