package com.msqgl.app.dao;

import com.msqgl.app.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MsgDao {

  private static final String QUERY_INSERT_GIFT_MSG = "INSERT INTO GIFTMSG (ID_GIFT, MSG, SENDER, AMOUNT) VALUES (?, ?, ?, ?);";
  private static final String QUERY_SELECT_ALL_GIFT_MSG = "SELECT * FROM GIFTMSG ORDER BY INSERT_DATE;";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List getAllMsg() {
    return jdbcTemplate.query(QUERY_SELECT_ALL_GIFT_MSG, new BeanPropertyRowMapper(Msg.class));
  }

  public void saveMsg(final Msg msg) {
    jdbcTemplate.update(QUERY_INSERT_GIFT_MSG, msg.getIdGift(), msg.getMsg(), msg.getSender(), msg.getAmount());
  }

}
