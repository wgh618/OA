package com.my.oa.core.web.spring.interceptor;


import com.my.oa.system.domain.Privilege;
import com.my.oa.system.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public class PrivilegeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String uri = request.getRequestURI();
        String url = uri.substring(request.getContextPath().length());

        if (url.endsWith(".png") || url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".jpg") || url
                .endsWith(".gif") || url.endsWith(".PNG") || url.endsWith(".html")) {
            return true;
        }

        if (url.contains("index")) {
            return true;
        }

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("loginUser");
        if (url.equals("/user/login")) {
            if (user != null) {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            return true;
        }

        if (user == null) {
            request.setAttribute("errorMsg", "您未登录，请先登录！");
            request.getRequestDispatcher("/WEB-INF/jsps/user/loginUI.jsp").forward(request, response);
            return false;
        } else {
            List<Privilege> privilegeList = (List<Privilege>) session.getAttribute("privilegeList");
            for (Privilege privilege : privilegeList) {
                if (privilege.getUrl().endsWith("*")) {
                    int lastIndexOf = url.lastIndexOf("/");
                    if (privilege.getUrl().substring(0, privilege.getUrl().length() - 1).equals(url.substring(0,
                            lastIndexOf+1))) {
                        return true;
                    }
                } else {
                    if (privilege.getUrl().equals(url)) {
                        return true;
                    }
                }
            }
        }

        request.getRequestDispatcher("/WEB-INF/jsps/common/noPrivilege.jsp").forward(request, response);
        return false;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception
            ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }
}
