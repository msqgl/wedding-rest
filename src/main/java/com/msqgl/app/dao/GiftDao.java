package com.msqgl.app.dao;

import com.msqgl.app.data.Gift;
import spark.utils.CollectionUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GiftDao extends BaseDao {

  private static final String QUERY_SELECT_ALL_GIFT = "SELECT * FROM GIFT";
  private static final String QUERY_SELECT_FROM_ID = "SELECT * FROM GIFT WHERE ID_GIFT = '%s'";
  private static final String QUERY_UPDATE_GIFT = "UPDATE GIFT SET CONSUMED_PRICE = %s WHERE ID_GIFT = '%s'";

  public List getAllGift() throws SQLException, ClassNotFoundException {
    final Statement statement = getStatement();
    final ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL_GIFT);
    return mapRersultSetToObject(resultSet, Gift.class);
  }

  public Gift getGiftFromId(final String giftId) throws SQLException, ClassNotFoundException {
    final Statement statement = getStatement();
    final ResultSet resultSet = statement.executeQuery(String.format(QUERY_SELECT_FROM_ID, giftId));
    final List list = mapRersultSetToObject(resultSet, Gift.class);
    Gift returnGift = null;
    if (!CollectionUtils.isEmpty(list)) {
      returnGift = (Gift) list.get(0);
    }
    return returnGift;
  }

  public void updateConsumedPrice(final String giftId,
                                  final BigDecimal consumedPrice) throws SQLException, ClassNotFoundException {
    final Statement statement = getStatement();
    statement.executeUpdate(String.format(QUERY_UPDATE_GIFT, consumedPrice, giftId));
  }

}
