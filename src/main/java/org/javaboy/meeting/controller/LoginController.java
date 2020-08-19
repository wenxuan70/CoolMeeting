package org.javaboy.meeting.controller;

import org.javaboy.meeting.domain.Department;
import org.javaboy.meeting.domain.Employee;
import org.javaboy.meeting.service.DepartmentService;
import org.javaboy.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    // RequsetMapping和GetMapping的不同是前者可以接收get,post,delete,put等请求方法,后者只能接收get请求方法
    @RequestMapping("/")
    public String login() {
        return  "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, Model model, HttpSession session) {
        Employee employee = employeeService.doLogin(username, password);
        if (employee == null) {
            model.addAttribute("error", "用户名或密码错误,登录失败");
            return "forward:/";
        } else {
            Integer status = employee.getStatus();
            if (status.equals(0)) {
                model.addAttribute("error", "用户待审批");
                return "forward:/";
            } else if (status.equals(2)) {
                model.addAttribute("error", "用户审批失败");
                return "forward:/";
            } else if (status.equals(-1)) {
                model.addAttribute("error", "账号已被关闭");
                return "forward:/";
            } else {
                session.setAttribute("currentuser", employee);
                return "redirect:/notifications";
            }
        }
    }

    @RequestMapping("/register")
    public String register(Model model) {
        List<Department> deps = departmentService.getAllDeps();
        model.addAttribute("deps",deps);
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(@Validated Employee employee, BindingResult result, Model model) {
        System.out.println("result = " + result);
        System.out.println("employee = " + employee);
        if (result.hasErrors()) {
            model.addAttribute("error",
                    result.getAllErrors().get(0).getDefaultMessage());
            return "forward:/register";
        }
        int addCode = employeeService.addEmployee(employee);
        if (addCode == 1) {
            return "redirect:/";
        } else {
            model.addAttribute("error","注册失败");
            return "forward:/register";
        }
    }

    @RequestMapping("/changepassword")
    public String changePassword() {
        return "changepassword";
    }

    /**
     * 修改密码
     * @param session
     * @param oldPassword
     * @param newPassword
     * @param model
     * @return
     */
    @PostMapping("/doChangePassword")
    public String doChangePassword(HttpSession session, String oldPassword, String newPassword, Model model) {
        Employee currentUser = (Employee) session.getAttribute("currentuser");
        String username = currentUser.getUsername();
        if (employeeService.doLogin(username, oldPassword) != null) {
            if (employeeService.updatePassword(username, newPassword).equals(1)) {
                session.removeAttribute("currentuser");
                return "redirect:/";
            } else {
                model.addAttribute("error", "修改失败");
            }
        } else {
            model.addAttribute("error", "密码错误");
        }
        return "forward:/changepassword";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("currentuser");
        return "redirect:/";
    }
}
