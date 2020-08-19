package org.javaboy.meeting.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Employee {
    private Integer employeeId;
    @NotNull(message = "{employeeName.notnull}")
    @Size(min = 2, max = 16, message = "{employeeName.size}")
    private String employeeName;
    @NotNull(message = "{username.notnull}")
    @Size(min = 2, max = 16, message = "{username.size}")
    private String username;
    @NotNull(message = "{password.notnull}")
    @Size(max = 20, message = "{password.size}")
    private String password;
    @Pattern(regexp = "^1[3-9][0-9]{9}$", message = "{phone.pattern}")
    @NotNull(message = "${phone.notnull}")
    private String phone;
    @Pattern(regexp = "^\\w+@\\w+\\.\\w+$", message = "{email.email}")
    @NotNull(message = "{email.notnull}")
    private String email;
    private Integer status;
    private Integer role;
    @NotNull(message = "{departmentId.notnull}")
    private Integer departmentId;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", departmentId=" + departmentId +
                '}';
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
