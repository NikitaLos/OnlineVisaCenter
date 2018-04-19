package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminPage(){
        return "WEB-INF/admin_page";
    }

    @RequestMapping(value = "/manage_employees",method = RequestMethod.GET)
    public String showEmployeesPage(@ModelAttribute("duplicate_message") String duplicateMessage, Model model) throws UserServiceException {
        List<User> employees = userService.findAllEmployees();
        model.addAttribute("all_employees",employees);
        model.addAttribute("duplicate_message",duplicateMessage);
        return "WEB-INF/all_employees";
    }

    @RequestMapping(value = "/delete_user/{employee_id}",method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("employee_id") Integer employeeId) throws UserServiceException {
        userService.deleteUserById(employeeId);
        return "redirect:/admin/manage_employees";
    }

    @RequestMapping(value = "/add_employee",method = RequestMethod.POST)
    public String addEmployee(User employee, RedirectAttributes redirectAttributes) throws UserServiceException {
        try {
            userService.register(employee);
        } catch (DuplicateException e) {
            redirectAttributes.addAttribute("duplicate_message",e.getMessage());
        }
        return "redirect:/admin/manage_employees";

    }
}
