package repository;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    private Statement statement;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from users_co";
    private static final String SQL_INSERT_INTO_USERS = "insert into users_co(login,password,first_name,last_name) values ";


    public UsersRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.statement = statement;
        this.connection = connection;
    }


    public List findAllByAge(Integer age) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("first_name"))
                        .surname(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .build();
                result.add(user);
//               if (user.getAge().equals(age)) {
//                    result.add(user);
//                }
            }
            if (result.isEmpty()) {
                System.out.println("По введенному возврасту ничего не найдено...");
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User entity) {
        String sql = SQL_INSERT_INTO_USERS + "('" + entity.getLogin() + "', '" + entity.getPassword() + "', '" + entity.getName() + "', '" +entity.getSurname() + "');";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        System.out.println(entity.getLogin() + " " + entity.getPassword() + " " + entity.getName() + " " + entity.getSurname());

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByLogin(User login) {
        return Optional.empty();
    }


}