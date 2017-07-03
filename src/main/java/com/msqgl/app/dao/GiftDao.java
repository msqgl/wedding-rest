package com.msqgl.app.dao;

import com.msqgl.app.data.Gift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GiftDao extends BaseDao {

  private String QUERY_SELECT_ALL_GIFT = "SELECT * FROM GIFT";

  public List<Gift> getAllGift() throws SQLException, ClassNotFoundException {
    final Statement statement = getStatement();
    final ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL_GIFT);
    final List<Gift> returnList = new ArrayList<>();

    while (resultSet.next()) {
      final Gift gift = new Gift();
      gift.setIdGift(resultSet.getString("ID_GIFT"));
      gift.setTitle(resultSet.getString("TITLE"));
      gift.setDescription(resultSet.getString("DESCRIPTION"));
      gift.setImgPath(resultSet.getString("IMG_PATH"));
      gift.setTotalPrice(resultSet.getBigDecimal("TOTAL_PRICE"));
      gift.setConsumedPrice(resultSet.getBigDecimal("CONSUMED_PRICE"));
      System.out.println(gift);
      returnList.add(gift);
    }
    return returnList;
  }

}
