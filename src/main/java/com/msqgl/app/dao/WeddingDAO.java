package com.msqgl.app.dao;

import com.msqgl.app.data.Gift;
import com.msqgl.app.data.GiftMsg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeddingDAO {

  private String QUERY_SELECT_ALL_GIFT = "SELECT * FROM GIFT";
  private String QUERY_INSERT_GIFT_MSG = "INSERT INTO `wedding`.`giftmsg` (`ID_GIFT`, `MSG`, `SENDER`, `AMOUNT`) VALUES ('%s', '%s', '%s', '%s');";

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

  public void saveGiftMsg(final GiftMsg giftMsg) throws SQLException, ClassNotFoundException {
    final Statement statement = getStatement();
    final String query = String.format(QUERY_INSERT_GIFT_MSG,
        giftMsg.getIdGift(),
        giftMsg.getMsg(),
        giftMsg.getSender(),
        giftMsg.getAmount()
    );
    statement.executeUpdate(query);
  }

  private Statement getStatement() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
    final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/wedding?user=w3dd1ng&password=9cEmpKmY");
    return connection.createStatement();
  }

}
