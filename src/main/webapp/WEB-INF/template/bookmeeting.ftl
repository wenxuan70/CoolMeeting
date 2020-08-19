<!DOCTYPE html>
<html>
<head>
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="/styles/common.css"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style type="text/css">
        #divfrom {
            float: left;
            width: 150px;
        }

        #divto {
            float: left;
            width: 150px;
        }

        #divoperator {
            float: left;
            width: 50px;
            padding: 60px 5px;
        }

        #divoperator input[type="button"] {
            margin: 10px 0;
        }

        #selDepartments {
            display: block;
            width: 100%;
        }

        #selEmployees {
            display: block;
            width: 100%;
            height: 200px;
        }

        #selSelectedEmployees {
            display: block;
            width: 100%;
            height: 225px;
        }
    </style>
    <script type="application/javascript">
        function employee(employeeid, employeename) {
            this.employeeid = employeeid;
            this.employeename = employeename;
        }

        function department(departmentid, departmentname, employees) {
            this.departmentid = departmentid;
            this.departmentname = departmentname;
            this.employees = employees;
        }

        let selDepartments;
        let selEmployees;
        let selSelectedEmployees;
        let selMeetingRooms;

        function body_load() {
            selDepartments = document.getElementById("selDepartments");
            selEmployees = document.getElementById("selEmployees");
            selSelectedEmployees = document.getElementById("selSelectedEmployees");
            selMeetingRooms = document.getElementById("roomId");
            // 获取部门
            $.get("/getAllDeps", function (deps) {
                for (let i = 0; i < deps.length; i++) {
                    let dep = document.createElement("option");
                    dep.value = deps[i].departmentId;
                    dep.text = deps[i].departmentName;
                    selDepartments.appendChild(dep);
                }
                fillEmployees();
            });
            // 获取会议室
            $.get("/getMeetingRooms", function (mrs) {
                for (let i = 0; i < mrs.length; i++) {
                    let mr = document.createElement("option");
                    mr.value = mrs[i].roomId;
                    mr.text = mrs[i].roomName;
                    selMeetingRooms.appendChild(mr);
                }
            });
        }
        // 获取部门员工
        function fillEmployees() {
            clearList(selEmployees);
            let departmentid = selDepartments.options[selDepartments.selectedIndex].value;

            $.get("/getEmployeesByDepId?depId=" + departmentid, function (employees) {
                for (let i = 0; i < employees.length; i++) {
                    let emp = document.createElement("option");
                    emp.value = employees[i].employeeId;
                    emp.text = employees[i].employeeName;
                    selEmployees.appendChild(emp);
                }
            });
        }

        function clearList(list) {
            while (list.childElementCount > 0) {
                list.removeChild(list.lastChild);
            }
        }

        function selectEmployees() {
            for (let i = 0; i < selEmployees.options.length; i++) {
                if (selEmployees.options[i].selected) {
                    addEmployee(selEmployees.options[i]);
                    selEmployees.options[i].selected = false;
                }
            }
        }

        function deSelectEmployees() {
            let elementsToRemoved = new Array();
            let options = selSelectedEmployees.options;
            for (let i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    elementsToRemoved.push(options[i]);
                }
            }
            for (let i = 0; i < elementsToRemoved.length; i++) {
                selSelectedEmployees.removeChild(elementsToRemoved[i]);
            }
        }

        function addEmployee(optEmployee) {
            let options = selSelectedEmployees.options;
            let i = 0;
            let insertIndex = -1;
            while (i < options.length) {
                if (optEmployee.value == options[i].value) {
                    return;
                } else if (optEmployee.value < options[i].value) {
                    insertIndex = i;
                    break;
                }
                i++;
            }
            let opt = document.createElement("option");
            opt.value = optEmployee.value;
            opt.text = optEmployee.text;
            opt.selected = true;
            if (insertIndex == -1) {
                selSelectedEmployees.appendChild(opt);
            } else {
                selSelectedEmployees.insertBefore(opt, options[insertIndex]);
            }
        }
    </script>
</head>
<body onload="body_load()">
<#include 'top.ftl'>
<div class="page-body">
    <#include 'sidebar.ftl'>
    <div class="page-content">
        <div class="content-nav">
            会议预定 > 预定会议
        </div>
        <form action="/toBookMeeting" method="post">
            <fieldset>
                <legend>会议信息</legend>
                <table class="formtable">
                    <tr>
                        <td>会议名称：</td>
                        <td>
                            <input type="text" name="meetingName" maxlength="20" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>预计参加人数：</td>
                        <td>
                            <input type="text" name="numberOfParticipants" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>预计开始时间：</td>
                        <td>
                            <input type="datetime-local" name="startTime" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>预计结束时间：</td>
                        <td>
                            <input type="datetime-local" name="endTime" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>会议室名称：</td>
                        <td>
                            <select id="roomId" name="roomId">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>会议说明：</td>
                        <td>
                            <textarea name="description" rows="5" required></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>选择参会人员：</td>
                        <td>
                            <div id="divfrom">
                                <select id="selDepartments" onchange="fillEmployees()">
                                </select>
                                <select id="selEmployees" multiple="true">
                                </select>
                            </div>
                            <div id="divoperator">
                                <input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                            </div>
                            <div id="divto">
                                <select id="selSelectedEmployees" name="employeeIds" multiple="true" required>
                                </select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="command" colspan="2">
                            <input type="submit" class="clickbutton" value="预定会议"/>
                            <input type="reset" class="clickbutton" value="重置"/>
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