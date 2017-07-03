package com.msqgl.app.dao;

import com.msqgl.app.data.Msg;

import java.sql.SQLException;
import java.sql.Statement;

public class MsgDao extends BaseDao {

  private String QUERY_INSERT_GIFT_MSG = "INSERT INTO `wedding`.`giftmsg` (`ID_GIFT`, `MSG`, `SENDER`, `AMOUNT`) VALUES ('%s', '%s', '%s', '%s');";

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
