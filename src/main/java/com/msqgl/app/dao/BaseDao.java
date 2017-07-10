package com.msqgl.app.dao;

import com.msqgl.app.config.DBConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BaseDao<T> {

  private static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://%s/%s?user=%s&password=%s";

  @Autowired
  private DBConfig dbConfig;

  public DBConfig getDbConfig() {
    return dbConfig;
  }

  public void setDbConfig(DBConfig dbConfig) {
    this.dbConfig = dbConfig;
  }

  protected Statement getStatement() throws SQLException, ClassNotFoundException {
    Class.forName(COM_MYSQL_JDBC_DRIVER);
    final String url = String.format(
        URL, dbConfig.getDbHostname(), dbConfig.getDbName(), dbConfig.getDbUser(), dbConfig.getDbPassword());
    final Connection connection = DriverManager.getConnection(url);
    return connection.createStatement();
  }

  protected List<T> mapRersultSetToObject(ResultSet rs, Class outputClass) {
    List<T> outputList = new ArrayList<T>();
    try {
      if (rs != null) {
        if (outputClass.isAnnotationPresent(Entity.class)) {
          ResultSetMetaData rsmd = rs.getMetaData();
          Field[] fields = outputClass.getDeclaredFields();
          while (rs.next()) {
            T bean = (T) outputClass.newInstance();
            for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
              String columnName = rsmd.getColumnName(_iterator + 1);
              Object columnValue = rs.getObject(_iterator + 1);
              for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                  Column column = field.getAnnotation(Column.class);
                  if (column.name().equalsIgnoreCase(columnName) && columnValue != null) {
                    BeanUtils.setProperty(bean, field.getName(), columnValue);
                    break;
                  }
                }
              }
            }
            outputList.add(bean);
          }

        }
      }
    } catch (IllegalAccessException | SQLException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return outputList;
  }

}
