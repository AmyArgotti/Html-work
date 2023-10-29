package servlets;

import models.User;
import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@WebServlet("/signUp")
public class TestServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "171070";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    private UsersRepository usersRepository;
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            usersRepository = new UsersRepositoryJdbcImpl(connection, statement);
        } catch (SQLException throwables) {
            throw  new IllegalStateException(throwables);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/profile.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("1234567890-");
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = User.builder()
                .name(firstName)
                .surname(lastName)
                .login(login)
                .password(password)
                .build();
        try {
            usersRepository.save(user);
            response.sendRedirect("/users");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}