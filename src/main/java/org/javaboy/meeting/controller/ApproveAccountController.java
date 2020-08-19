package org.javaboy.meeting.controller;

import org.javaboy.meeting.domain.Employee;
import org.javaboy.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ApproveAccountController {

    private static final Integer PENDING_APPROVE = 0;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/approveaccount")
    public String approveAccount(Model model) {
        List<Employee> employeeList = employeeService.getEmployeesByStatus(PENDING_APPROVE);
        model.addAttribute("employeeList", employeeList);
        return "approveaccount";
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(Integer employeeId, Integer status) {
        Integer result = employeeService.updateStatus(employeeId,status);
        return "redirect:/admin/approveaccount";
    }
}
