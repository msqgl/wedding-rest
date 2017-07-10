package com.msqgl.app.dao;

import com.msqgl.app.WeddingRespApp;
import com.msqgl.app.config.DBConfig;
import com.msqgl.app.model.Gift;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spark.utils.CollectionUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WeddingRespApp.class, DBConfig.class})
public class GiftDaoTest {

  @Autowired
  private GiftDao giftDao;

  @Test
  public void getAllGift() throws SQLException, ClassNotFoundException {
    final List allGift = giftDao.getAllGift();
    Assert.assertFalse(CollectionUtils.isEmpty(allGift));
    final Gift gift = (Gift) allGift.get(0);
    Assert.assertTrue(StringUtils.isNotEmpty(gift.getIdGift()));
    Assert.assertTrue(StringUtils.isNotEmpty(gift.getTitle()));
    Assert.assertTrue(StringUtils.isNotEmpty(gift.getDescription()));
    Assert.assertTrue(StringUtils.isNotEmpty(gift.getImgPath()));
    Assert.assertTrue(gift.getConsumedPrice() != null);
  }

  @Test
  public void getGiftFromId() {
    final Gift gift = giftDao.getGiftFromId(String.valueOf(1));
    Assert.assertTrue(gift != null);
    Assert.assertTrue(StringUtils.isNoneEmpty(gift.getIdGift()));
    Assert.assertTrue(StringUtils.isNoneEmpty(gift.getTitle()));
    Assert.assertTrue(StringUtils.isNoneEmpty(gift.getDescription()));
    Assert.assertTrue(StringUtils.isNoneEmpty(gift.getImgPath()));
    Assert.assertTrue(gift.getTotalPrice() != null);
    Assert.assertTrue(gift.getConsumedPrice() != null);
  }

  @Test
  public void updateConsumedPrice() {
    final Gift gift = giftDao.getGiftFromId(String.valueOf(1));
    final BigDecimal oldValue = gift.getConsumedPrice();
    final BigDecimal newValue = oldValue != null ? oldValue.add(new BigDecimal(2)) : new BigDecimal(2);
    giftDao.updateConsumedPrice(gift.getIdGift(), newValue);
    final Gift newGift = giftDao.getGiftFromId(String.valueOf(1));
    Assert.assertTrue(Objects.equals(newValue, newGift.getConsumedPrice()));
  }

}
