//import com.sun.net.httpserver.HttpServer;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/sitting")
//public class SittingPage extends HttpServer {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie[]color= request.getCookies();
//       // System.out.println(color[0].getValue()+" "+color[0].getName());
//        for (int i = 0; i < color.length; i++) {
//            System.out.println(color[1].getValue()+" "+color[1].getName());
//        }
//        request.getRequestDispatcher("src/main/webapp/WEB-INF/jsp/setting.jsp").forward(request,response);
//    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
//        String color = request.getParameter("color");
//        Cookie colorCooki= new Cookie("color",color);
//        colorCooki.setMaxAge(60);
//        response.addCookie(colorCooki);
//        request.getRequestDispatcher("src/main/webapp/WEB-INF/jsp/setting.jsp");
//    }
//}
