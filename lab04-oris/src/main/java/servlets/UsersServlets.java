package servlets;

import models.User;
import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet("/users")
public class UsersServlets {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "171070";

    private UsersRepository usersRepository;
    private List<User> users;

    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            usersRepository = new UsersRepositoryJdbcImpl(connection, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List result;
        try {


        result = usersRepository.findAllByAge(25);
    }catch(Exception e){
        throw new RuntimeException(e);
    }
        for (int i = 0; i <result.size() ; i++) {
            System.out.println(result.get(i));
        }
        request.setAttribute("usersForJsp", result);
        request.getRequestDispatcher("src/main/webapp/WEB-INF/jsp/users.jsp").forward(request,response);
}
}

