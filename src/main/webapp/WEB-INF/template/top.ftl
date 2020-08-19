<div class="page-header">
    <div class="header-banner">
        <img src="/images/header.png" alt="CoolMeeting"/>
    </div>
    <div class="header-title">
        欢迎访问Cool-Meeting会议管理系统
    </div>
    <div class="header-quicklink" style="padding-top: 0px;">
        <#if currentuser??>
            欢迎您，<strong>${currentuser.employeeName}</strong>
            <a href="/changepassword">[修改密码]</a>
            <a href="/logout">[退出登录]</a>
        </#if>
    </div>
</div>