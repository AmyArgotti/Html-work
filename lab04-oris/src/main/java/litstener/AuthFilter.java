package litstener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Boolean isAuthenticated = false;
        Boolean sessionExists = session != null;
        Boolean isLoginPage = request.getRequestURI().equals("/signIn");

        if (sessionExists) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");
            if (isAuthenticated == null) {
                isAuthenticated = false;
            }
        }
        // если авторизован и запрашивает не логин или если не авторизован и запрашивает логин
        if(isAuthenticated && !isLoginPage || !isAuthenticated && isLoginPage) {
            // отдаем то что хочет
            filterChain.doFilter(request, response);
        } else if (isAuthenticated && isLoginPage) {
            // пользователь авторизован и просит страницу авторизации
            response.sendRedirect("/");
        } else {
            // не авторизован и просит другие страницы
            response.sendRedirect("/signIn");
        }

    }

    @Override
    public void destroy() {

    }
}