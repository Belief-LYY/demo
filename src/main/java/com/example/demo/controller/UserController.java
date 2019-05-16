package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    // 查询
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonUtil.sendJson(response, userService.selectAllUser());
    }

    // 新增
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonUtil.sendJson(response, userService.selectAllUser());
    }

    // 修改
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = JsonUtil.fromRequest(request, User.class);
        JsonUtil.send(response, userService.update(user).toString());
    }

    // 删除
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = JsonUtil.fromRequest(request, User.class);
        JsonUtil.send(response, userService.delete(user.getId()).toString());
    }
}
