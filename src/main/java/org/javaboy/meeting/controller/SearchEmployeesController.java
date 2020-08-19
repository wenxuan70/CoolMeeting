package org.javaboy.meeting.controller;

import org.javaboy.meeting.domain.Employee;
import org.javaboy.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SearchEmployeesController {
    private static final Integer PAGE_SIZE = 10;

    private static final Integer CLOSE = -1;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/searchemployees")
    public String searchEmployees(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "1") Integer status,
                                  String username, String employeeName, Model model) {
        if (page < 1) page = 1;
        Integer total = employeeService.getTotal(status, username, employeeName);
        int pageNum = total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1;
        if (page > pageNum) page = pageNum > 0 ? pageNum : 1;
        List<Employee> employees = employeeService.searchEmployees(page, PAGE_SIZE, status, username, employeeName);
        model.addAttribute("employees", employees);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("status", status);
        model.addAttribute("prev", page.equals(1) ? 1 : page - 1);
        model.addAttribute("next", page.equals(pageNum) ? pageNum : (page.compareTo(pageNum) > 0 ? 1 : page + 1));
        return "searchemployees";
    }

    @RequestMapping("/closeAccount")
    public String closeAccount(Integer employeeId) {
        Integer result = employeeService.updateStatus(employeeId, CLOSE);
        return "redirect:/admin/searchemployees";
    }
}
