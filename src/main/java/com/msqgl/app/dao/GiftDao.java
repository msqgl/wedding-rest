package com.msqgl.app.dao;

import com.msqgl.app.model.Gift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class GiftDao {

  private static final String QUERY_SELECT_ALL_GIFT = "SELECT * FROM GIFT ORDER BY INDEX_ORDER";
  private static final String QUERY_SELECT_FROM_ID = "SELECT * FROM GIFT WHERE ID_GIFT = ?";
  private static final String QUERY_UPDATE_GIFT = "UPDATE GIFT SET CONSUMED_PRICE = ? WHERE ID_GIFT = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List getAllGift() {
    return jdbcTemplate.query(QUERY_SELECT_ALL_GIFT, new BeanPropertyRowMapper(Gift.class));
  }

  public Gift getGiftFromId(final String giftId) {
    return (Gift) jdbcTemplate.queryForObject(QUERY_SELECT_FROM_ID, new Object[]{giftId}, new BeanPropertyRowMapper(Gift.class));
  }

  public void updateConsumedPrice(final String giftId,
                                  final BigDecimal consumedPrice) {
    jdbcTemplate.update(QUERY_UPDATE_GIFT, consumedPrice, giftId);
  }

}
