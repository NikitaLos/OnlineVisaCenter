package com.vironit.onlinevisacenter.controller.servlets;

import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.service.DuplicateUserException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.UserServiceImpl;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));

        UserService userService = new UserServiceImpl();

        try {
            userService.register(user);
            HttpSession session = request.getSession();
            session.setAttribute("user_id",user.getId());
            request.getRequestDispatcher("/after_register.jsp").forward(request,response);
        } catch (DuplicateUserException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        } catch (UserServiceException e) {
            //todo
        }

    }
}
