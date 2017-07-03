package com.msqgl.app.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {

  private static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String HOSTNAME = "localhost";
  private static final String DB_NAME = "wedding";
  private static final String USER = "w3dd1ng";
  private static final String PASSWORD = "9cEmpKmY";
  private static final String URL = "jdbc:mysql://%s/%s?user=%s&password=%s";

  protected Statement getStatement() throws SQLException, ClassNotFoundException {
    Class.forName(COM_MYSQL_JDBC_DRIVER);
    final String url = String.format(URL, HOSTNAME, DB_NAME, USER, PASSWORD);
    final Connection connection = DriverManager.getConnection(url);
    return connection.createStatement();
  }

  protected List<Object> getResultList(final ResultSet resultSet,
                                       final Class<?> aClass) {
    final List<Object> retList = new ArrayList<>();
    // TODO: add logic
    return retList;
  }

}
