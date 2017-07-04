package com.msqgl.app.dao;

import com.msqgl.app.data.Gift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GiftDao extends BaseDao {

  private String QUERY_SELECT_ALL_GIFT = "SELECT * FROM GIFT";

  public List getAllGift() throws SQLException, ClassNotFoundException {
    final Statement statement = getStatement();
    final ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL_GIFT);
    return mapRersultSetToObject(resultSet, Gift.class);
  }

}
