package com.vironit.onlinevisacenter.controller.servlets;

import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
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

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));

        UserService userService = new UserServiceImpl();
        try {
            user = userService.logIn(user);
            HttpSession session = request.getSession();
            session.setAttribute("user_id",user.getId());
            request.getRequestDispatcher("/after_register.jsp").forward(request,response);
        } catch (LoginationException e) {
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }


    }
}
