package org.javaboy.meeting.interceptor;

import org.javaboy.meeting.domain.Employee;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RoleInterceptor implements HandlerInterceptor {
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if ("/".equals(requestURI) || "/doLogin".equals(requestURI) || "/register".equals(requestURI) || "/doRegister".equals(requestURI)) {
            return true;
        }
        HttpSession session = request.getSession();
        Employee currentuser = (Employee) session.getAttribute("currentuser");
        if (pathMatcher.match("/admin/**", requestURI)) {
            // 管理员页面
            if (currentuser != null) {
                if (currentuser.getRole().equals(2)) {
                    return true;
                } else {
                    response.getWriter().write("权限不足");
                    return false;
                }
            }
        } else {
            // 非管理员页面,且登录
            if (currentuser != null) {
                return true;
            }
        }
        response.sendRedirect("/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
