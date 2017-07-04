package com.msqgl.app.dao;

import com.msqgl.app.data.Msg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MsgDao extends BaseDao {

  private String QUERY_INSERT_GIFT_MSG = "INSERT INTO `wedding`.`giftmsg` (`ID_GIFT`, `MSG`, `SENDER`, `AMOUNT`) VALUES ('%s', '%s', '%s', '%s');";
  private String QUERY_SELECT_ALL_GIFT_MSG = "SELECT * FROM GIFTMSG";

  public List getAllMsg() throws SQLException, ClassNotFoundException {
    final Statement statement = getStatement();
    final ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL_GIFT_MSG);
    return mapRersultSetToObject(resultSet, Msg.class);
  }

  public void saveGiftMsg(final Msg msg) throws SQLException, ClassNotFoundException {
    final Statement statement = getStatement();
    final String query = String.format(QUERY_INSERT_GIFT_MSG,
        msg.getIdGift(),
        msg.getMsg(),
        msg.getSender(),
        msg.getAmount()
    );
    statement.executeUpdate(query);
  }

}
