package com.example.demo.controller;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.example.demo.model.User;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.SecurityManager;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static Logger logger = LogManager.getLogger();

	// 登录接口
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Subject currentUser = SecurityManager.currentUser();
		Session session = currentUser.getSession();
		Map<String, Object> message = new Hashtable<>();
		if (!currentUser.isAuthenticated()) {
			User user = JsonUtil.fromRequest(request, User.class);
			UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
			token.setRememberMe(true);
			try {
				currentUser.login(token);
				session.setAttribute("currentUser", user);
				message.put("success", true);
				message.put("message", "登陆成功！");
			} catch (UnknownAccountException uae) {
				logger.info("There is no user with username of " + token.getPrincipal());
				message.put("success", false);
				message.put("message", "登陆失败，用户名不存在！");
			} catch (IncorrectCredentialsException ice) {
				logger.info("Password for account " + token.getPrincipal() + " was incorrect!");
				message.put("success", false);
				message.put("message", "登陆失败，请检查用户名和密码！");
			} catch (LockedAccountException lae) {
				logger.info("The account for username " + token.getPrincipal() + " is locked.  ");
				message.put("success", false);
				message.put("message", "登陆失败，账户被锁定，请联系管理员！");
			} catch (AuthenticationException ae) {
				logger.debug("unexpected condition? error?" + token.getPrincipal());
				message.put("success", false);
				message.put("message", "登陆失败，未预料的错误！");
			}
		} else {
			message.put("success", true);
			message.put("message", "当前用户已经登陆！");
		}
		JsonUtil.sendJson(response, message);
	}

	// 登出接口
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}

}
